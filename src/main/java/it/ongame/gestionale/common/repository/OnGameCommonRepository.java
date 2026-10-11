package it.ongame.gestionale.common.repository;

import it.ongame.gestionale.common.model.OnGameCommonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface OnGameCommonRepository <T extends OnGameCommonModel> extends JpaRepository<T, UUID> {

}
