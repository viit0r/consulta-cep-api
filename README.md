# Consulta CEP API
 Microsserviço responsável por trazer informações do endereço utilizando como base o CEP informado.

## Requests
### Buscar informações do endereço
Retorna todas as informações de endereço baseado no CEP informado.

GET - [/cep/{cep}]

**Response body**
```
{
    "cep": "string",
    "logradouro": "string",
    "complemento": "string",
    "bairro": "string",
    "localidade": "string",
    "uf": "string",
    "ddd": "string",
}
```