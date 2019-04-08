package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.repository.models.Client;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryClientServiceTest {
    
    @Autowired
    private IncidentRepository incidentRepo;
    
    @Autowired
    ClientRepository clientRepo;
    
    RepositoryClientService service;

    @BeforeEach
    void setUp() {
        service = new RepositoryClientService(clientRepo, incidentRepo);
    }
    
    @Test
    void should_add_client() {
        //when
        AddClientRequest request = createRequest();
        
        //when
        Client client = service.addClient(request);
        
        //then
        Assertions.assertEquals(request, client);
        
    }

    @Test
    void requestForHelp() {
    }

    @Test
    void cancelRequestForHelp() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void findById() {
    }

    @NotNull
    private AddClientRequest createRequest() {
        return new AddClientRequest("John", "Doe");
    }
}
