package io.codelex.securityapp.authentication.client;

import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.api.ClientLogin;
import io.codelex.securityapp.authentication.AuthService;
import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.RepositoryIncidentService;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/clients-api")
class ClientAuthenticationController {

    private final AuthService authService;
    private RepositoryClientService clientService;
    private RepositoryIncidentService incidentService;

    ClientAuthenticationController(AuthService authService,
                                   RepositoryClientService service,
                                   RepositoryIncidentService incidentService) {
        this.authService = authService;
        this.clientService = service;
        this.incidentService = incidentService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Client> signIn(@Valid @RequestBody ClientLogin request) {
        if (clientService.isEmailPresent(request.getEmail()) && clientService.isPasswordMatching(request.getEmail(), request.getPassword())) {
            authService.authorizeClient(request.getEmail(), request.getPassword());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<Client> register(@Valid @RequestBody AddClientRequest request) {

        if (clientService.isEmailPresent(request.getEmail())) {
            System.out.println("Email is already registered!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        authService.authorizeClient(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(clientService.addClient(request), HttpStatus.CREATED);
    }

    @PostMapping("/sign-out")
    public void signOut() {
        authService.clearAuthentication();
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/incident")
    public ResponseEntity<Incident> newIncident(@Valid @RequestBody AddIncidentRequest request,
                                                Principal principal) {
        return new ResponseEntity<>(incidentService.addIncident(
                new AddIncidentRequest(
                        principal.getName(),
                        request.getLatitude(),
                        request.getLongitude())),
                HttpStatus.ACCEPTED);
    }
}
