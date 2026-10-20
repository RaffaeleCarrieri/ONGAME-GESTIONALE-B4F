package it.ongame.gestionale.rest.auth.model.dto;

public record TokenInfoDto(
        String tokenType,
        Long expiresAt,
        Long durata) {

}