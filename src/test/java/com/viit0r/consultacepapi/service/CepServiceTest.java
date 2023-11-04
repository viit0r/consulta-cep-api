package com.viit0r.consultacepapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viit0r.consultacepapi.dto.CepResponseDTO;
import com.viit0r.consultacepapi.exception.CEPNotFoundException;
import com.viit0r.consultacepapi.exception.InvalidCEPException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CepServiceTest {

    @Autowired
    private CepService cepService;

    @Test
    void whenGetAddressInfo_ThenReturnSuccess() throws JsonProcessingException {

        ResponseEntity<CepResponseDTO> response = cepService.getAddressInfo("01001000");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("01001-000", response.getBody().getCep());
    }

    @Test
    void whenGetAddressInfo_ThenReturnCEPNotFoundException() {

        assertThrows(CEPNotFoundException.class, () -> {
            ResponseEntity<CepResponseDTO> response = cepService.getAddressInfo("11111111");

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        });
    }

    @Test
    void whenGetAddressInfo_ThenReturnInvalidCEPException() {

        assertThrows(InvalidCEPException.class, () -> {
            ResponseEntity<CepResponseDTO> response = cepService.getAddressInfo("1234567");

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });

        assertThrows(InvalidCEPException.class, () -> {
            ResponseEntity<CepResponseDTO> response = cepService.getAddressInfo("abcdefgh");

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }
}