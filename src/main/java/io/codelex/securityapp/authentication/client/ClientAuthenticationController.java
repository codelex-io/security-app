package io.codelex.securityapp.authentication.client;

import io.codelex.securityapp.authentication.AuthService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static io.codelex.securityapp.authentication.user.UserRoles.ROLE_USER;

@RestController
@RequestMapping("/client-api")
class ClientAuthenticationController {
    private final AuthService authService;

    ClientAuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestParam("email") String email ,
                       @RequestParam("password") String password) {
        authService.authorise(email, ROLE_USER);
    }

    @PostMapping("/register")
    public void register(@RequestParam("email") String email ,
                         @RequestParam("password") String password) {
        authService.authorise(email, ROLE_USER);
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
