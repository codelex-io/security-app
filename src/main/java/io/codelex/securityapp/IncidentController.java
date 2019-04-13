package io.codelex.securityapp;

import io.codelex.securityapp.repository.RepositoryIncidentService;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.api.AddIncidentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incident-api")
public class IncidentController {

    private final RepositoryIncidentService service;

    public IncidentController(RepositoryIncidentService service) {
        this.service = service;
    }

    @PostMapping("/incidents")
    public ResponseEntity<Incident> addIncident(@RequestBody AddIncidentRequest request) {
        return new ResponseEntity<>(service.addIncident(request), HttpStatus.OK);
    }

    @GetMapping("/incidents/{id}")
    public ResponseEntity<Incident> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }



}