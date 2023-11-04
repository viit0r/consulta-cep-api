package com.viit0r.consultacepapi.service;

import com.viit0r.consultacepapi.dto.CepResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CepService {
    ResponseEntity<CepResponseDTO> getAddressInfo(String cep);
}
