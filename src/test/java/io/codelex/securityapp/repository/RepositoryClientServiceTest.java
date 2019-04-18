package io.codelex.securityapp.repository;

import io.codelex.securityapp.Password;
import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.repository.models.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;

class RepositoryClientServiceTest {
    private Password encoder = new Password();
    private ClientRepository repository = Mockito.mock(ClientRepository.class);

    private RepositoryClientService service = new RepositoryClientService(encoder, repository);

    @Test
    void should_save_client() {
        //given
        AddClientRequest request = createAddClientRequest();
        Mockito.when(repository.save(any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        //then
        Client client = service.addClient(request);
        Assertions.assertEquals(request.getFirstName(), client.getFirstName());
        Assertions.assertEquals(request.getLastName(), client.getLastName());
    }

    @Test
    void should_match_password() {
        //given
        Client client = new Client(
                "John",
                "Doe",
                "example@example.com",
                encoder.passwordEncoder().encode("password"));

        AddClientRequest request = createAddClientRequest();
        //when
        Mockito.when(repository.findClientByEmail(any()))
                .thenReturn(client);
        //then
        Assertions.assertTrue(service.isPasswordMatching(client.getEmail(), request.getPassword()));
    }

    @Test
    void should_not_match_passwords_if_they_are_not_equal() {
        //given
        Client client = new Client(
                "John",
                "Doe",
                "example@example.com",
                encoder.passwordEncoder().encode("1234567"));

        AddClientRequest request = createAddClientRequest();
        //when
        Mockito.when(repository.findClientByEmail(any()))
                .thenReturn(client);
        //then
        Assertions.assertFalse(service.isPasswordMatching(client.getEmail(), request.getPassword()));
    }

    @Test
    void should_find_email_if_present() {
        //given
        AddClientRequest request = createAddClientRequest();
        Mockito.when(repository.save(any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        //when
        Mockito.when(service.isEmailPresent(any()))
                .thenReturn(true);
        //then
        Assertions.assertTrue(service.isEmailPresent(request.getEmail()));
    }

    private AddClientRequest createAddClientRequest() {
        return new AddClientRequest(
                "John",
                "Doe",
                "example@example.com",
                "password"
        );
    }
}