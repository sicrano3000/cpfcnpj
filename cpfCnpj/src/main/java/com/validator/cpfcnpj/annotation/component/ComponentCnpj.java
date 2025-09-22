package com.validator.cpfcnpj.annotation.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentCnpj implements Validation {

    public static final String CNPJ_INVALIDO = "CNPJ inválido";

    @Override
    public boolean isValid(String value) {
        if (value == null || !value.matches("\\d{14}")) {
            return false;
        }

        var cnpj = value.replaceAll("\\D", "");

        if (cnpj.length() != 14) return false;

        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            var pesos1 = new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            var pesos2 = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            var soma = 0;

            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            }

            var resto = soma % 11;
            var digito1 = (resto < 2) ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
            }
            resto = soma % 11;

            var digito2 = (resto < 2) ? 0 : 11 - resto;

            return cnpj.charAt(12) == Character.forDigit(digito1, 10) &&
                   cnpj.charAt(13) == Character.forDigit(digito2, 10);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getDefaultMessage() {
        return CNPJ_INVALIDO;
    }

}
