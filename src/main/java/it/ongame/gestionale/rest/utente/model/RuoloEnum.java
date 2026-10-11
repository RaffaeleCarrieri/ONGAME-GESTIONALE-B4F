package it.ongame.gestionale.rest.utente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public enum RuoloEnum {
    PROPRIETARIO(1,"PROPRIETARIO"),
    DIPENDENTE(2,"DIPENDENTE"),
    CLIENTE(3,"CLIENTE");

    private final Integer id;
    private final String label;



}
