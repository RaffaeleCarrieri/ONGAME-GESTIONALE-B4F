package it.ongame.gestionale.rest.auth.service;

import it.ongame.gestionale.rest.auth.model.dto.LoginRequestDto;
import it.ongame.gestionale.rest.auth.model.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto dto, HttpServletRequest httpReq, HttpServletResponse httResp) throws Exception;
}
