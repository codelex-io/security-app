package io.codelex.securityapp;

import io.codelex.securityapp.api.IncidentRequest;
import io.codelex.securityapp.api.Unit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("unit-api")
public class UnitController {
    
    @PostMapping("/accepted")
    public ResponseEntity<Unit> acceptedRequest (IncidentRequest request){
        return new ResponseEntity<>(HttpStatus.OK);
    } 
    
    @PostMapping("/rejected")
    public ResponseEntity<Unit> rejectedRequest(IncidentRequest request){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
