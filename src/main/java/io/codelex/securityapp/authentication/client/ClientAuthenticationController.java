package io.codelex.securityapp.authentication.client;

import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.authentication.AuthService;
import io.codelex.securityapp.repository.RepositoryClientService;
import io.codelex.securityapp.repository.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import static io.codelex.securityapp.authentication.user.UserRoles.USER;

@RestController
@RequestMapping("/clients-api")
class ClientAuthenticationController {
    private final AuthService authService;
    private RepositoryClientService service;

    ClientAuthenticationController(AuthService authService, RepositoryClientService service) {
        this.authService = authService;
        this.service = service;
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestParam("email") String email,
                       @RequestParam("password") String password) {
        authService.authorise(email, password, USER);
    }

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName) {
        if (service.isEmailPresent(email)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        authService.register(email, password, USER);
        AddClientRequest request = new AddClientRequest(firstName, lastName, email, password);
        return new ResponseEntity<>(service.addClient(request), HttpStatus.CREATED);
    }

    @PostMapping("/sign-out")
    public void signOut() {
        authService.clearAuthentication();
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }
}
