package it.ongame.gestionale.rest.clienti.model;

import it.ongame.gestionale.common.repository.OnGameCommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends OnGameCommonRepository<Cliente> {
}
