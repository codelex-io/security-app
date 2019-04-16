package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("select count (unit) > 0 from Unit unit where unit.email = :email")
    boolean isEmailPresent(@Param("email") String email);

    @Query("SELECT unit from Unit unit where "
            + "available = true")
    List<Unit> searchAvailable();

    @Transactional
    @Modifying
    @Query("UPDATE Unit unit SET unit.available = CASE unit.available " +
            "WHEN true THEN false ELSE true END where unit.id in :id")
    void changeStatus(@Param("id") Long id);
}