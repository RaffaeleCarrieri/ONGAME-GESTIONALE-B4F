package it.ongame.gestionale.rest.sede;

import java.util.UUID;

import it.ongame.gestionale.common.repository.OnGameCommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends OnGameCommonRepository<Sede> {
    //boolean existsByCodice(String codice);
}
