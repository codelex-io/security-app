package io.codelex.securityapp.repository.models;

import io.codelex.securityapp.api.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
