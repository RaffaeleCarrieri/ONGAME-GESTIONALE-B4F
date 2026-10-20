package it.ongame.gestionale.rest.auth.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class AuthDtos {
    private AuthDtos() {}

    public record LoginRequest(@NotBlank @Email String email, @NotBlank String password) {}

    public record RegistrazioneRequest(
            @NotBlank String nome,
            @NotBlank String cognome,
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8) String password) {}
}
