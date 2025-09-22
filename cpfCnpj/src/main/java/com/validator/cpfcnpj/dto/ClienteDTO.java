package com.validator.cpfcnpj.dto;

import com.validator.cpfcnpj.annotation.CNPJ;
import com.validator.cpfcnpj.annotation.CPF;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @CNPJ(message = "CNPJ informado não é válido!")
    private String cnpj;

    @CPF // aqui usará a mensagem padrão
    private String cpf;

}
