package io.codelex.securityapp.authentication.unit;

import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.api.UnitLogin;
import io.codelex.securityapp.authentication.AuthService;
import io.codelex.securityapp.repository.RepositoryUnitService;
import io.codelex.securityapp.repository.models.Unit;
import org.hibernate.annotations.Synchronize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/units-api")
class UnitAuthenticationController {
    private final AuthService authService;
    private final RepositoryUnitService unitService;

    UnitAuthenticationController(AuthService authService,
                                 RepositoryUnitService unitService) {
        this.authService = authService;
        this.unitService = unitService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Unit> signIn(@Valid @RequestBody UnitLogin request) {
        if (unitService.isEmailPresent(request.getEmail())) {
            authService.authorizeUnit(request.getEmail(), request.getPassword());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<Unit> register(@Valid @RequestBody AddUnitRequest request) {
        if (unitService.isEmailPresent(request.getEmail())) {
            System.out.println("Email is already registered!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        authService.authorizeUnit(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(unitService.addUnit(request), HttpStatus.CREATED);
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
