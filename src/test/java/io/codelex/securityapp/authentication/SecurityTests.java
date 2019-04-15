package io.codelex.securityapp.authentication;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SecurityTests {
    @Autowired
    TestRestTemplate restTemplate;

    static final String email = "dev@codelex.io";
    static final String password = "Password123";
    static final String firstName = "John";
    static final String lastName = "Doe";

    @Test
    public void client_account_should_be_secured_by_default() {
        var result = restTemplate.getForEntity("/clients-api/account", String.class);
        assertEquals(FORBIDDEN, result.getStatusCode());
    }

    @Test
    public void client_should_be_authorised_on_registration() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(OK, accessClientAccount(sessionId).getStatusCode());
    }

    @Test
    public void client_should_be_authorised_on_sign_in() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        result = signInClient();
        assertEquals(ACCEPTED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(OK, accessClientAccount(sessionId).getStatusCode());
    }

    @Test
    public void client_should_be_able_to_sign_out() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);

        result = signOutClient(sessionId);
        assertEquals(OK, result.getStatusCode());

        assertEquals(FORBIDDEN, accessClientAccount(sessionId).getStatusCode());
    }

    @Test
    public void client_should_be_able_to_get_account_details() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(email, accessClientAccount(sessionId).getBody());
    }

    @Test
    public void client_should_not_be_able_to_access_admin_endpoints() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(FORBIDDEN, accessAdminAccount(sessionId).getStatusCode());
    }

    @Test
    public void unit_account_should_be_secured_by_default() {
        var result = restTemplate.getForEntity("/units-api/account", String.class);
        assertEquals(FORBIDDEN, result.getStatusCode());
    }

    @Test
    public void unit_should_be_authorised_on_registration() {
        var result = registerUnit();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(OK, accessUnitAccount(sessionId).getStatusCode());
    }

    @Test
    public void unit_should_be_authorised_on_sign_in() {
        var result = registerUnit();
        assertEquals(CREATED, result.getStatusCode());

        result = signInUnit();
        assertEquals(OK, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(OK, accessUnitAccount(sessionId).getStatusCode());
    }

    @Test
    public void unit_should_be_able_to_sign_out() {
        var result = registerUnit();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);

        result = signOutUnit(sessionId);
        assertEquals(OK, result.getStatusCode());

        assertEquals(FORBIDDEN, accessUnitAccount(sessionId).getStatusCode());
    }

    @Test
    public void unit_should_be_able_to_get_account_details() {
        var result = registerUnit();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(email, accessUnitAccount(sessionId).getBody());
    }

    @Test
    public void unit_should_not_be_able_to_access_admin_endpoints() {
        var result = registerUnit();
        assertEquals(CREATED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(FORBIDDEN, accessAdminAccount(sessionId).getStatusCode());
    }

    private ResponseEntity<Void> registerClient() {
        var uri = fromPath("/clients-api/register")
                .queryParam("email", email)
                .queryParam("password", password)
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .build()
                .toUri();

        return restTemplate.postForEntity(uri, EMPTY, Void.class);
    }

    private ResponseEntity<Void> signInClient() {
        var uri = fromPath("/clients-api/sign-in")
                .queryParam("email", email)
                .queryParam("password", password)
                .build()
                .toUri();

        return restTemplate.postForEntity(uri, EMPTY, Void.class);
    }

    private ResponseEntity<Void> signOutClient(String sessionId) {
        return restTemplate.exchange("/clients-api/sign-out", POST, request(sessionId), Void.class);
    }

    private ResponseEntity<String> accessClientAccount(String sessionId) {
        return restTemplate.exchange("/clients-api/account", GET, request(sessionId), String.class);
    }


    private ResponseEntity<Void> registerUnit() {
        var uri = fromPath("/units-api/register")
                .queryParam("email", email)
                .queryParam("password", password)
                .build()
                .toUri();

        return restTemplate.postForEntity(uri, EMPTY, Void.class);
    }

    private ResponseEntity<Void> signInUnit() {
        var uri = fromPath("/units-api/sign-in")
                .queryParam("email", email)
                .queryParam("password", password)
                .build()
                .toUri();

        return restTemplate.postForEntity(uri, EMPTY, Void.class);
    }

    private ResponseEntity<Void> signOutUnit(String sessionId) {
        return restTemplate.exchange("/units-api/sign-out", POST, request(sessionId), Void.class);
    }

    private ResponseEntity<String> accessUnitAccount(String sessionId) {
        return restTemplate.exchange("/units-api/account", GET, request(sessionId), String.class);
    }

    private ResponseEntity<String> accessAdminAccount(String sessionId) {
        return restTemplate.exchange("/admin-api/account", GET, request(sessionId), String.class);
    }

    private String sessionId(ResponseEntity<?> result) {
        return result.getHeaders().getFirst("Set-Cookie");
    }

    private HttpEntity<?> request(String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        return new HttpEntity<>(headers);
    }
}
