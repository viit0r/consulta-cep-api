package com.viit0r.consultacepapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viit0r.consultacepapi.dto.CepResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CepService {

    public static final String BASE_URL_VIACEP = "https://viacep.com.br/ws";
    public static final String ERRO_CEP_NAO_ENCONTRADO = "erro";
    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL_VIACEP).build();
    private final ObjectMapper objectMapper;

    public CepService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<CepResponseDTO> getAddressInfo(String cep) throws Exception {

        if(!isValid(cep)) {
            throw new Exception("CEP inválido. Verifique e tente novamente!");
        }

        String response = webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/{cep}/json/")
                    .build(cep))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if (response != null && response.contains(ERRO_CEP_NAO_ENCONTRADO)) {
            throw new Exception("CEP não existente na nossa base de dados. Por favor, verifique e tente novamente!");
        }

        return ResponseEntity.ok(objectMapper.readValue(response, CepResponseDTO.class));
    }

    private boolean isValid(String cep) {
        return cep.length() == 8 && cep.matches("^[0-9]*$");
    }
}
