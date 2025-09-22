package com.validator.cpfcnpj.annotation.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentCpf implements Validation {

    public static final String CPF_INVALIDO = "CPF inválido";

    @Override
    public boolean isValid(String value) {
        if (value == null || !value.matches("\\d{11}")) {
            return false;
        }
        var cpf = value.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            var soma = 0;

            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            var resto = 11 - (soma % 11);
            var digito1 = (resto == 10 || resto == 11) ? 0 : resto;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = 11 - (soma % 11);

            var digito2 = (resto == 10 || resto == 11) ? 0 : resto;

            return cpf.charAt(9) == Character.forDigit(digito1, 10) &&
                   cpf.charAt(10) == Character.forDigit(digito2, 10);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getDefaultMessage() {
        return CPF_INVALIDO;
    }

}
