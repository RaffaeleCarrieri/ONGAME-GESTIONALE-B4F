package it.ongame.gestionale.rest.clienti.service.impl;

import it.ongame.gestionale.common.service.impl.OnGameCommonServiceImpl;
import it.ongame.gestionale.rest.clienti.model.Cliente;
import it.ongame.gestionale.rest.clienti.model.ClienteRepository;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteRequestDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteResponseDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClientiResponseListDto;
import it.ongame.gestionale.rest.clienti.service.ClienteService;
import it.ongame.gestionale.rest.sede.SedeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ClienteServiceImpl extends OnGameCommonServiceImpl<Cliente, ClienteRepository, ClienteRequestDto, ClienteResponseDto, ClientiResponseListDto> implements ClienteService {

    private final SedeRepository sedeRepository;
    public ClienteServiceImpl(ClienteRepository repository, SedeRepository sedeRepository) {
        super(repository);
        this.sedeRepository = sedeRepository;
    }

    @Override
    protected void updateRecordWithRequestData(Cliente recordToUpdate, ClienteRequestDto clienteRequestDto) {
        recordToUpdate.setNome(clienteRequestDto.getNome());
        recordToUpdate.setCognome(clienteRequestDto.getCognome());
        recordToUpdate.setDataNascita(LocalDate.parse(clienteRequestDto.getDataNascita()));
        recordToUpdate.setUpdatedAt(LocalDateTime.now());
        recordToUpdate.setEmail(clienteRequestDto.getEmail());
        recordToUpdate.setTelefonoCifrato(clienteRequestDto.getTelefono());
    }

    @Override
    public Cliente toModel(ClienteRequestDto res) {
        return Cliente
                .builder()
                .sedeCliente(sedeRepository.findById(UUID.fromString(res.getSedeId())).orElseThrow())
                .email(res.getEmail())
                .nome(res.getNome())
                .cognome(res.getCognome())
                .telefonoCifrato(res.getTelefono())
                .dataNascita(LocalDate.parse(res.getDataNascita()))
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public ClienteResponseDto toDto(Cliente entity) {
        return ClienteResponseDto
                .builder()
                .id(entity.getId().toString())
                .sedeId(entity.getSedeCliente().getId().toString())
                .nome(entity.getNome())
                .cognome(entity.getCognome())
                .email(entity.getEmail())
                .dataNascita(entity.getDataNascita())
                .telefono(entity.getTelefonoCifrato())
                .build();
    }
}
