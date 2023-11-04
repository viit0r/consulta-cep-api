package com.viit0r.consultacepapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viit0r.consultacepapi.dto.CepResponseDTO;
import com.viit0r.consultacepapi.exception.CEPNotFoundException;
import com.viit0r.consultacepapi.exception.InvalidCEPException;
import com.viit0r.consultacepapi.service.CepService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class CepServiceImpl implements CepService {

    public static final String BASE_URL_VIACEP = "https://viacep.com.br/ws";
    public static final String ERRO_CEP_NAO_ENCONTRADO = "erro";
    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL_VIACEP).build();
    private final ObjectMapper objectMapper;

    public CepServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public ResponseEntity<CepResponseDTO> getAddressInfo(String cep) {

        if(!isValid(cep)) {
            throw new InvalidCEPException("CEP inválido. Verifique e tente novamente!");
        }

        String response = webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/{cep}/json/")
                    .build(cep))
            .retrieve()
            .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(15))
                .block();

        if (response != null && response.contains(ERRO_CEP_NAO_ENCONTRADO)) {
            throw new CEPNotFoundException("CEP não existente na base de dados. Por favor, verifique e tente novamente!");
        }

        return ResponseEntity.ok(objectMapper.readValue(response, CepResponseDTO.class));
    }

    private boolean isValid(String cep) {
        return cep.length() == 8 && cep.matches("^[0-9]*$");
    }
}
