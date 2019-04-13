package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class RepositoryUnitServiceTest {

    private UnitRepository repository = Mockito.mock(UnitRepository.class);
    private RepositoryUnitService service = new RepositoryUnitService(repository);

    @Test
    void should_save_unit() {
        //given
        AddUnitRequest request = new AddUnitRequest(
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
        Mockito.when(repository.save(any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        //when
        Unit unit = service.addUnit(request);
        Assertions.assertEquals(request.getLongitude(), unit.getLongitude());
        Assertions.assertEquals(request.getLatitude(), unit.getLatitude());
        Assertions.assertEquals(request.getAvailable(), unit.getAvailable());
    }

    @Test
    void should_find_available_units() {
        //given
        Unit unit = new Unit(
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
        List<Unit> availableUnits = new ArrayList<>();
        availableUnits.add(unit);
        //when
        Answer<List<Unit>> answer = invocation -> availableUnits;

        Mockito.when(repository.searchAvailable())
                .thenAnswer(answer);

        List<Unit> units = service.findAvailable();
        //then
        Assertions.assertEquals(unit.getAvailable(), units.get(0).getAvailable());
    }
}