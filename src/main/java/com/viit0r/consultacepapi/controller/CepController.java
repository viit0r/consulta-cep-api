package com.viit0r.consultacepapi.controller;

import com.viit0r.consultacepapi.dto.CepResponseDTO;
import com.viit0r.consultacepapi.service.CepService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {


    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping(value = "/{cep}")
    public ResponseEntity<CepResponseDTO> getAddressInfo(@PathVariable(value = "cep") @NotNull String cep) throws Exception {
        return cepService.getAddressInfo(cep);
    }
}
