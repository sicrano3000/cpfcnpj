package com.validator.cpfcnpj.annotation.validator;

import com.validator.cpfcnpj.annotation.CNPJ;
import com.validator.cpfcnpj.annotation.component.ComponentCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CnpjValidator implements ConstraintValidator<CNPJ, String> {

    private final ComponentCnpj component;

    private String message;

    @Override
    public void initialize(CNPJ annotation) {
        this.message = annotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var valid = component.isValid(value);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message != null ? message : component.getDefaultMessage())
                    .addConstraintViolation();
        }

        return valid;
    }

}
