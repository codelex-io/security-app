package io.codelex.securityapp.route;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class RouteGatewayTest {
    @Rule
    static WireMockRule wireMock = new WireMockRule();
    private RouteGateway routeGateway;

    @BeforeAll
    static void setUpOnce() {
        wireMock.start();
    }

    @BeforeEach
    void setUp() {
        GoogleMapsProps props = new GoogleMapsProps();
        props.setApiUrl("http://localhost:" + wireMock.port());
        routeGateway = new RouteGateway(props);
    }

    @Test
    void should_fetch_distance() throws Exception {
        //given
        Unit unit = new Unit(
                "John@Doe.com", "123", new BigDecimal(24.941887),
                new BigDecimal(56.095740),
                true
        );
        Incident incident = new Incident(
                new Client("name", "surname", "email@example.com", "password"),
                unit, new BigDecimal(24.113705),
                new BigDecimal(56.254896),
                LocalDateTime.now());

        File file = ResourceUtils.getFile(this.getClass().getResource("/stubs/successful-response.json"));
        Assertions.assertTrue(file.exists());

        byte[] json = Files.readAllBytes(file.toPath());

        wireMock.stubFor(get(urlPathEqualTo("/maps/api/distancematrix/json"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(200)
                        .withBody(json)));
        //when
        Long distance = routeGateway.calculateRoute(unit, incident);
        //then
        Assertions.assertEquals(128773, distance);
    }

    @Test
    void should_handle_external_service_failure() {
        Unit unit = new Unit(
                "John@Doe.com", "123", new BigDecimal(24.941887),
                new BigDecimal(56.095740),
                true
        );
        Incident incident = new Incident(
                new Client("name", "surname", "email@example.com", "password"),
                unit, new BigDecimal(24.113705),
                new BigDecimal(56.254896),
                LocalDateTime.now());
        //given
        wireMock.stubFor(get(urlPathEqualTo("/maps/api/distancematrix/json"))
                .willReturn(aResponse()
                        .withStatus(500)));
        //then
        Assertions.assertThrows(IllegalStateException.class, () -> routeGateway.calculateRoute(unit, incident));
    }
}