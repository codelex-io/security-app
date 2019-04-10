package io.codelex.securityapp.route;

import io.codelex.securityapp.repository.RepositoryUnitService;
import io.codelex.securityapp.repository.SimpleNearestUnitService;
import io.codelex.securityapp.repository.UnitRepository;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SimpleNearestUnitServiceTest {


    private RouteGateway routeGateway;
    @Autowired
    private UnitRepository unitRepository;
    private RepositoryUnitService repositoryUnitService;

    private SimpleNearestUnitService nearestUnit;

    @BeforeEach
    void setUp() {
        repositoryUnitService = new RepositoryUnitService(unitRepository);
        routeGateway = new RouteGateway();
        nearestUnit = new SimpleNearestUnitService(repositoryUnitService, routeGateway);

    }

    @Test
    void should_return_unit() {
        //given
        Incident incident = new Incident(
                new Client("name", "surname"),
                new BigDecimal(56.951855),
                new BigDecimal(24.113781)
        );

        Unit closest = new Unit(
                new BigDecimal(56.952092),
                new BigDecimal(24.099975),
                true
        );

        Unit farthest = new Unit(
                new BigDecimal(56.940931),
                new BigDecimal(24.137081),
                true
        );
        unitRepository.save(closest);
        unitRepository.save(farthest);

        //then

        Assertions.assertTrue(unitRepository.existsById(1L));
        Assertions.assertTrue(unitRepository.existsById(2L));
        Assertions.assertNotNull(nearestUnit.searchNearestUnit(incident));
        Assertions.assertEquals(closest.getLatitude(), nearestUnit.searchNearestUnit(incident).getLatitude());
    }


}