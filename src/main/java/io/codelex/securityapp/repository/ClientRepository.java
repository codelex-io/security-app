package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select count (client) > 0 from Client client where client.email = :email")
    boolean isEmailPresent(@Param("email") String email);
    
    @Query("select client from Client client where client.email = :email")
    Client findClientByEmail(@Param("email") String email);
    
}
