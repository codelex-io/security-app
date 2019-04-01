package io.codelex.securityapp;

import io.codelex.securityapp.units.api.DispatchRequest;
import io.codelex.securityapp.units.api.Unit;

import java.util.List;

//1. Checks for available Units
//2. Chooses for the nearest available Unit
//3. Waits for UnitResponse; if no response, continues iterating to the next nearest Unit
//4. If DispatchRequest is accepted, sends
public class UnitDispatcherService {

    List<Unit> getAvailable(){
        return null;
    }

    Unit getNearest(List<Unit> availableUnits){
        return null;
    }

    DispatchRequest createDispatchRequest(Unit nearest){
        return null;
    }

}
