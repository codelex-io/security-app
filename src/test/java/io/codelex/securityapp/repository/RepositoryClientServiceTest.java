package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddClientRequest;
import io.codelex.securityapp.repository.models.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import static org.mockito.ArgumentMatchers.any;

class RepositoryClientServiceTest {

    private ClientRepository repository = Mockito.mock(ClientRepository.class);

    private RepositoryClientService service = new RepositoryClientService(repository);

    @Test
    void should_save_client() {
        //given
        AddClientRequest request = new AddClientRequest(
                "John",
                "Doe",
                "email@email.com",
                "123"
        );
        Mockito.when(repository.save(any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        //then
        Client client = service.addClient(request);
        Assertions.assertEquals(request.getFirstName(), client.getFirstName());
        Assertions.assertEquals(request.getLastName(), client.getLastName());
    }


}