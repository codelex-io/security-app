package io.codelex.securityapp.repository;

import io.codelex.securityapp.Password;
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

    private Password encoder = new Password();
    private UnitRepository repository = Mockito.mock(UnitRepository.class);
    private RepositoryUnitService service = new RepositoryUnitService(encoder, repository);

    @Test
    void should_save_unit() {
        //given
        AddUnitRequest request = createAddUnitRequest();
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
                "John@Doe.com", "123",
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

    @Test
    void should_match_password() {
        //given
        Unit unit = new Unit(
                "John@Doe.com",
                encoder.passwordEncoder().encode("123456"),
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
        AddUnitRequest request = createAddUnitRequest();
        //when
        Mockito.when(repository.findUnitByEmail(any()))
                .thenReturn(unit);
        //then
        Assertions.assertTrue(service.isPasswordMatching(unit.getEmail(), request.getPassword()));
    }

    @Test
    void should_not_match_passwords_if_they_are_not_equal() {
        //given
        Unit unit = new Unit(
                "John@Doe.com",
                encoder.passwordEncoder().encode("password"),
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
        AddUnitRequest request = createAddUnitRequest();
        //when
        Mockito.when(repository.findUnitByEmail(any()))
                .thenReturn(unit);
        //then
        Assertions.assertFalse(service.isPasswordMatching(unit.getEmail(), request.getPassword()));
    }

    @Test
    void should_find_email_if_present() {
        //given
        AddUnitRequest request = createAddUnitRequest();
        Mockito.when(repository.save(any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        //when
        Mockito.when(service.isEmailPresent(any()))
                .thenReturn(true);
        //then
        Assertions.assertTrue(service.isEmailPresent(request.getEmail()));
    }

    private AddUnitRequest createAddUnitRequest() {
        return new AddUnitRequest(
                "John@Doe.com",
                "123456",
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
    }
}