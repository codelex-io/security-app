package io.codelex.securityapp;

import io.codelex.securityapp.api.*;
import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client-api")
public class ClientController {
    private RepositoryClientService service;

    public ClientController(RepositoryClientService service) {
        this.service = service;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody AddClientRequest request) {
        return new ResponseEntity<>(service.addClient(request), HttpStatus.OK);
    }

    @PostMapping("/incidents")
    public ResponseEntity<Incident> requestForHelp(@RequestBody Incident request) {
        return new ResponseEntity<>(service.requestForHelp(request), HttpStatus.OK);
    }
    
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
    
    @DeleteMapping("/delete/{request}")
    public void cancelRequest(@PathVariable Incident request) {
        service.cancelRequestForHelp(request);
    }
    
    @DeleteMapping("delete")
    public void deleteAll() {
        service.deleteAll();
    }
}
