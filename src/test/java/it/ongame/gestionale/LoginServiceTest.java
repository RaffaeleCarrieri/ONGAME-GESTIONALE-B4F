package it.ongame.gestionale;

import it.ongame.gestionale.rest.auth.SupabaseAuthClient;
import it.ongame.gestionale.rest.auth.model.dto.LoginRequestDto;
import it.ongame.gestionale.rest.auth.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    /**
     *
     * casistiche:
     *      - email non trovata
     *      - password errata
     *
     *  */
    @Mock
    private SupabaseAuthClient supabaseAuthClient;

    @InjectMocks
    private AuthServiceImpl authService;


    @Test
    void login_withWrongCredentials() throws Exception{
        LoginRequestDto req = new LoginRequestDto("example@ongame.com","123456789");
        when(supabaseAuthClient.login(req.getEmail(),req.getPassword())).thenThrow(
                new Exception("Error 400 bad request")
        );

        assertThrows(Exception.class,()->authService.login(req));
    }

    void login_withCorrectCredentials(){

    }


}
