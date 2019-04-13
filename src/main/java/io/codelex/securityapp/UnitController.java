package io.codelex.securityapp;

import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.repository.models.Unit;
import io.codelex.securityapp.repository.RepositoryUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/unit-api")
public class UnitController {

    private final RepositoryUnitService service;

    public UnitController(RepositoryUnitService service) {
        this.service = service;
    }

    @PostMapping("/units")
    public ResponseEntity<Unit> addUnit(@RequestBody AddUnitRequest request) {
        return new ResponseEntity<>(service.addUnit(request), HttpStatus.OK);
    }

    @GetMapping("/units/{id}")
    public ResponseEntity<Unit> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
