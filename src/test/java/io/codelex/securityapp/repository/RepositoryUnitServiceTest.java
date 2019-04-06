package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryUnitServiceTest {

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    IncidentRepository incidentRepository;

    RepositoryUnitService repositoryUnitService;

    @BeforeEach
    void setUp() {
        repositoryUnitService = new RepositoryUnitService(unitRepository);
    }


    @Test
    void should_add_unit() {
        AddUnitRequest request = new AddUnitRequest(
                new BigDecimal(100), new BigDecimal(2), true);

        Unit unit1 = repositoryUnitService.addUnit(request);

        Assertions.assertEquals(request.getLatitude(), unit1.getLatitude());


    }

    @Test
    void should_find_available_units() {

        repositoryUnitService.addUnit(new AddUnitRequest(
                new BigDecimal(1), new BigDecimal(2), true)
        );

        repositoryUnitService.addUnit(new AddUnitRequest(
                new BigDecimal(1), new BigDecimal(2), false)
        );

        repositoryUnitService.addUnit(new AddUnitRequest(
                new BigDecimal(1), new BigDecimal(2), false)
        );

        List<Unit> availableUnits = repositoryUnitService.findAvailable();

        Assertions.assertEquals(availableUnits.size(), 1);

    }
}