package com.validator.cpfcnpj.annotation.validator;

import com.validator.cpfcnpj.annotation.CPF;
import com.validator.cpfcnpj.annotation.component.ComponentCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CpfValidator implements ConstraintValidator<CPF, String> {

    private final ComponentCpf component;

    private String message;

    @Override
    public void initialize(CPF annotation) {
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
