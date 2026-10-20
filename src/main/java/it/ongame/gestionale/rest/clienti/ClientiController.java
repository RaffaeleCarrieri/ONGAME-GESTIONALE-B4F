package it.ongame.gestionale.rest.clienti;

import it.ongame.gestionale.common.controller.impl.OnGameCrudApiCommonControllerImpl;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteRequestDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteResponseDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClientiResponseListDto;
import it.ongame.gestionale.rest.clienti.service.ClienteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clienti")
public class ClientiController extends OnGameCrudApiCommonControllerImpl<ClienteRequestDto, ClienteResponseDto,
        ClientiResponseListDto, ClienteService>{
    public ClientiController(ClienteService service) {
        super(service);
    }
}
