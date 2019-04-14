package io.codelex.securityapp.authentication.admin;

import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/admin-api")
class AdminController {

    private RepositoryClientService service;

    public AdminController(RepositoryClientService service) {
        this.service = service;
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/clients") //todo is needed?
    public ResponseEntity<Client> addClient(@RequestBody AddClientRequest request) {
        if (service.isEmailPresent(request.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.addClient(request), HttpStatus.OK);
    }
}
