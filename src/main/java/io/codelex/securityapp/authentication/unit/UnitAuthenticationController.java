package io.codelex.securityapp.authentication.unit;

import io.codelex.securityapp.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static io.codelex.securityapp.authentication.user.UserRoles.ROLE_UNIT;

@RestController
@RequestMapping("/unit-api")
class UnitAuthenticationController {
    @Autowired
    private final AuthService authService;

    UnitAuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestParam("email") String email ,
                       @RequestParam("password") String password) {
        authService.authorise(email, ROLE_UNIT);
    }

    @PostMapping("/register")
    public void register(@RequestParam("email") String email ,
                         @RequestParam("password") String password) {
        authService.authorise(email, ROLE_UNIT);
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
