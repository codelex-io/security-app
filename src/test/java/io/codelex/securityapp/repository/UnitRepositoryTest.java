package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UnitRepositoryTest {

    @Autowired
    UnitRepository unitRepository;

    @Test
    void should_find_available_unit() {
        //given
        Unit unit = (new Unit(
                "John@Doe.com", "123", new BigDecimal(1),
                new BigDecimal(2),
                true));
        unitRepository.save(unit);
        //then
        Assertions.assertEquals(1, unitRepository.searchAvailable().size());
        Assertions.assertNotNull(unitRepository.findAll());
    }

    @Test
    void should_not_find_unit_if_not_available() {
        //given
        Unit unit = (new Unit(
                "John@Doe.com", "123", new BigDecimal(1),
                new BigDecimal(2),
                false));
        unitRepository.save(unit);
        //then
        Assertions.assertEquals(0, unitRepository.searchAvailable().size());
    }

    @Test
    void should_change_availability_status_to_false() {
        //given
        Unit unit = new Unit(
                "John@Doe.com", "123", new BigDecimal(1),
                new BigDecimal(2),
                true);
        unitRepository.save(unit);
        //when
        unitRepository.changeStatus(unit.getId());
        //then
        Assertions.assertEquals(0, unitRepository.searchAvailable().size());
    }

    @Test
    void should_change_availability_status_to_true() {
        //given
        Unit unit = new Unit(
                "John@Doe.com", "123", new BigDecimal(1),
                new BigDecimal(2),
                false);
        unitRepository.save(unit);
        //when
        unitRepository.changeStatus(unit.getId());
        //then
        Assertions.assertEquals(1, unitRepository.searchAvailable().size());
    }
}