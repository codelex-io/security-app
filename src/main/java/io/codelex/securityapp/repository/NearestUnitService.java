package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Unit;

import java.math.BigDecimal;

public interface NearestUnitService {
    
    public Unit searchNearestUnit(BigDecimal latitude, BigDecimal longitude);
}
