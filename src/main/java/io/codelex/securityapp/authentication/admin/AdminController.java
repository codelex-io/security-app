package io.codelex.securityapp.authentication.admin;

import io.codelex.securityapp.repository.RepositoryClientService;
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

}
