package io.codelex.securityapp.authentication;

import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SecurityTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final String email = "john@doe.com";

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
    public void client_should_be_authorised_on_valid_sign_in() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        result = signInClient();
        assertEquals(ACCEPTED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(OK, accessClientAccount(sessionId).getStatusCode());
    }

    @Test
    public void client_should_not_be_authorised_when_entering_wrong_email() {
        var result = registerClient();
        assertEquals(CREATED, result.getStatusCode());

        result = invalidEmailSignInClient();
        assertEquals(UNAUTHORIZED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(FORBIDDEN, accessClientAccount(sessionId).getStatusCode());
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
        assertEquals(ACCEPTED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(OK, accessUnitAccount(sessionId).getStatusCode());
    }

    @Test
    public void unit_should_not_be_authorised_when_entering_wrong_email() {
        var result = registerUnit();
        assertEquals(CREATED, result.getStatusCode());

        result = invalidEmailSignInUnit();
        assertEquals(UNAUTHORIZED, result.getStatusCode());

        var sessionId = sessionId(result);
        assertEquals(FORBIDDEN, accessClientAccount(sessionId).getStatusCode());
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
        final String uri = "/clients-api/register";
        HttpEntity<Client> request = new HttpEntity<>(new Client(
                "John",
                "Doe",
                "John@Doe.com",
                "123456"
        ));

        return restTemplate.postForEntity(uri, request, Void.class);
    }

    private ResponseEntity<Void> signInClient() {
        final String uri = "/clients-api/sign-in";
        HttpEntity<Client> request = new HttpEntity<>(new Client(
                "John",
                "Doe",
                "John@Doe.com",
                "123456"
        ));

        return restTemplate.postForEntity(uri, request, Void.class);
    }

    private ResponseEntity<Void> invalidEmailSignInClient() {
        final String uri = "/clients-api/sign-in";
        HttpEntity<Client> request = new HttpEntity<>(new Client(
                "John",
                "Doe",
                "John1@Doe.com",
                "123456"
        ));

        return restTemplate.postForEntity(uri, request, Void.class);
    }

    private ResponseEntity<Void> signOutClient(String sessionId) {
        return restTemplate.exchange("/clients-api/sign-out", POST, request(sessionId), Void.class);
    }

    private ResponseEntity<String> accessClientAccount(String sessionId) {
        return restTemplate.exchange("/clients-api/account", GET, request(sessionId), String.class);
    }

    private ResponseEntity<Void> registerUnit() {
        final String uri = "/units-api/register";
        HttpEntity<Unit> request = new HttpEntity<>(new Unit(
                "John@Doe.com",
                "123456",
                new BigDecimal(56.952092),
                new BigDecimal(24.099975),
                true
        ));

        return restTemplate.postForEntity(uri, request, Void.class);
    }

    private ResponseEntity<Void> signInUnit() {
        final String uri = "/units-api/sign-in";
        HttpEntity<Unit> request = new HttpEntity<>(new Unit(
                "John@Doe.com",
                "123456",
                new BigDecimal(56.952092),
                new BigDecimal(24.099975),
                true
        ));

        return restTemplate.postForEntity(uri, request, Void.class);
    }

    private ResponseEntity<Void> invalidEmailSignInUnit() {
        final String uri = "/units-api/sign-in";
        HttpEntity<Unit> request = new HttpEntity<>(new Unit(
                "John1@Doe.com",
                "123456",
                new BigDecimal(56.952092),
                new BigDecimal(24.099975),
                true
        ));

        return restTemplate.postForEntity(uri, request, Void.class);
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
