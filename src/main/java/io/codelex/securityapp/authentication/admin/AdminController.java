package io.codelex.securityapp.authentication.admin;

import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.RepositoryIncidentService;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/admin-api")
class AdminController {

    private RepositoryClientService clientService;
    private RepositoryIncidentService incidentService;

    public AdminController(RepositoryClientService clientService,
                           RepositoryIncidentService incidentService) {
        this.clientService = clientService;
        this.incidentService = incidentService;
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }


    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
        } catch (
                NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/incidents/{id}")
    public ResponseEntity<Incident> findIncidentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(incidentService.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
