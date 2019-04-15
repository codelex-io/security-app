package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    void should_find_user_by_email() {
        //given
        Client client = createClient();
        clientRepository.save(client);
        //when
        Assertions.assertTrue(clientRepository.isEmailPresent("email@email.com"));
    }
    
    @Test
    void should_not_return_true_if_email_not_the_same() {
        //given
        Client client = createClient();
        clientRepository.save(client);
        //when
        Assertions.assertFalse(clientRepository.isEmailPresent("email@example.com"));
    }
    
    @Test
    void should_find_client_by_email() {
        //given
        Client client = createClient();
        clientRepository.save(client);
        //when
        Client clientByEmail = clientRepository.findClientByEmail("email@email.com");
        Assertions.assertEquals(client.getEmail(), clientByEmail.getEmail());
    }
    
    @Test
    void should_not_find_client_if_email_do_not_match() {
        //given
        Client client = createClient();
        clientRepository.save(client);
        //when
        Assertions.assertNull(clientRepository.findClientByEmail("email@example.com"));
    }
    
    private Client createClient() {
        return new Client(
                "John",
                "Doe",
                "email@email.com",
                "123"
        );
    }
}