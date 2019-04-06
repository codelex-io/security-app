package io.codelex.securityapp.repository.models;

import io.codelex.securityapp.api.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UnitRepository extends JpaRepository<Unit, Long> {
    
    @Query("SELECT unit from Unit unit where " +
            "available = true")
    List<Unit> searchAvailable();

}