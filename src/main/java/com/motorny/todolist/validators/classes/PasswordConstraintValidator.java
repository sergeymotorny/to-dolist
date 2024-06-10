package com.motorny.todolist.validators.classes;

import com.motorny.todolist.validators.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 30;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern ONE_DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN =
            Pattern.compile("[" + Pattern.quote(SPECIAL_CHARACTERS) + "]");

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null || password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
            return false;
        }

        if (!UPPER_CASE.matcher(password).find()) {
            return false;
        }

        if (!LOWER_CASE.matcher(password).find()) {
            return false;
        }

        if (!ONE_DIGIT.matcher(password).find()) {
            return false;
        }


        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        return true;
    }
}
