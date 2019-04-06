package io.codelex.securityapp;

import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.api.IncidentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incident-api")
public class IncidentController {
    
    @PostMapping("/incidents")
    public ResponseEntity<Incident> addIncident (IncidentRequest request){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/incident/{id}")
    public ResponseEntity findIncidentById(@PathVariable Long id){
        return new ResponseEntity(HttpStatus.OK);
    }
}
