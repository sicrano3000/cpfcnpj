package com.validator.cpfcnpj.annotation.component;

public interface Validation {

    boolean isValid(String value);
    String getDefaultMessage();

}
