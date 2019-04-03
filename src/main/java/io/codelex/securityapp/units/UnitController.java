package io.codelex.securityapp.units;

import io.codelex.securityapp.UnitDispatcherService;
import io.codelex.securityapp.units.api.DispatchRequest;
import io.codelex.securityapp.units.api.Unit;
import io.codelex.securityapp.units.api.UnitResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Sends DispatchRequest for confirmation to the nearest calculated Unit
@RestController
@RequestMapping("/unit-api")
public class UnitController {

    private UnitDispatcherService service;

    @PostMapping("/unit")
    public ResponseEntity<Unit> findClosestUnit(@RequestBody DispatchRequest request) {
        return new ResponseEntity<>(service.findUnit(request), HttpStatus.OK);
    }
    
    @PostMapping("/accept?={response}")
    public ResponseEntity<Unit> unitsResponse(@PathVariable UnitResponse response) {
        return new ResponseEntity<>(service.getUnitsAcceptance(response), HttpStatus.OK);
    }

}
