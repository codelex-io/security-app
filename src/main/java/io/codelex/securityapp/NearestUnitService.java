package io.codelex.securityapp;

import io.codelex.securityapp.api.Unit;

import java.math.BigDecimal;

public interface NearestUnitService {
    
    public Unit findNearestUnit(BigDecimal latitude, BigDecimal longitude);
}
