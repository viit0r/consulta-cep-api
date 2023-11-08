package com.viit0r.consultacepapi.dto;

public record CepResponseDTO(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ddd){}
