package io.codelex.securityapp.authentication.client;

import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.authentication.AuthService;
import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.RepositoryIncidentService;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

import static io.codelex.securityapp.authentication.user.UserRoles.USER;

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

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName) {
        if (clientService.isEmailPresent(email)) {
            System.out.println("Email is already registered!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        authService.register(email, password, USER);
        AddClientRequest request = new AddClientRequest(firstName, lastName, email, password);
        return new ResponseEntity<>(clientService.addClient(request), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Client> signIn(@RequestParam("email") String email,
                                         @RequestParam("password") String password) {
        if (clientService.isEmailPresent(email)) {
            authService.authorise(email, password, USER);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<Incident> newIncident(Principal principal) {
        return new ResponseEntity<>(incidentService.addIncident(
                new AddIncidentRequest(
                        new BigDecimal(56.941887),
                        new BigDecimal(24.09574),
                        principal.getName())),
                HttpStatus.ACCEPTED);
    }
}
