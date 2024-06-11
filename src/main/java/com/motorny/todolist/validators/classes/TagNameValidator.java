package com.motorny.todolist.validators.classes;

import com.motorny.todolist.validators.ValidTagName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class TagNameValidator implements ConstraintValidator<ValidTagName, String> {

    private static final String SPECIAL_CHARACTERS = "!@$%^&*()-+";

    private static final Pattern SPECIAL_CHAR_PATTERN =
            Pattern.compile("[" + Pattern.quote(SPECIAL_CHARACTERS) + "]");

    @Override
    public boolean isValid(String tag, ConstraintValidatorContext context) {

        return !SPECIAL_CHAR_PATTERN.matcher(tag).find();
    }
}
