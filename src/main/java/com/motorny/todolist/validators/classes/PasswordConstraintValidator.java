package com.motorny.todolist.validators.classes;

import com.motorny.todolist.validators.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    private static final Pattern UPPER_LOWER_DIGIT_CASE = Pattern.compile("[A-Za-z0-9]{3,}");

    private static final Pattern SPECIAL_CHAR_PATTERN =
            Pattern.compile("[" + Pattern.quote(SPECIAL_CHARACTERS) + "]");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        return UPPER_LOWER_DIGIT_CASE.matcher(password).find()
                && SPECIAL_CHAR_PATTERN.matcher(password).find();
    }
}
