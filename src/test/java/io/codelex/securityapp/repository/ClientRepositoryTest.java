package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    void should_not_add_user_if_email_exists() {
        Client client1 = new Client(
                "John",
                "Doe",
                "email@email.com",
                "123"
        );

        Client client2 = new Client(
                "John",
                "Doe",
                "email@email.com",
                "123"
        );
        clientRepository.save(client1);
        clientRepository.save(client2);

        Assertions.assertTrue(clientRepository.isEmailPresent("email.@email.com"));
    }




}