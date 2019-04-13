package io.codelex.securityapp;

import io.codelex.securityapp.api.*;
import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client-api")
public class ClientController {
    private RepositoryClientService service;

    public ClientController(RepositoryClientService service) {
        this.service = service;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody AddClientRequest request) {
        return new ResponseEntity<>(service.addClient(request), HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (
                NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
