package it.ongame.gestionale.rest.auth.service.impl;

import it.ongame.gestionale.rest.auth.SupabaseAuthClient;

import it.ongame.gestionale.rest.auth.model.dto.LoginRequestDto;
import it.ongame.gestionale.rest.auth.model.dto.LoginResponseDto;
import it.ongame.gestionale.rest.auth.model.dto.TokenInfoDto;
import it.ongame.gestionale.rest.auth.service.AuthService;
import it.ongame.gestionale.utils.supabase.dto.response.SupabaseLoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final SupabaseAuthClient supabase;

    public SupabaseLoginResponseDto login(LoginRequestDto req) throws Exception {
        return supabase.login(req.getEmail(), req.getPassword());
        //return null;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto, HttpServletRequest httpReq, HttpServletResponse httResp) throws Exception {
        SupabaseLoginResponseDto supabaseLogin = supabase.login(dto.getEmail(), dto.getPassword());

        return LoginResponseDto
                .builder()
                /*.ok(true)
                .message("Login Success!")
                .tokenInfo(new TokenInfoDto(supabaseLogin.getToken_type(),
                        supabaseLogin.getExpires_in(),
                        supabaseLogin.getExpires_at()- supabaseLogin.getExpires_in()))*/
                .build();
    }
}
