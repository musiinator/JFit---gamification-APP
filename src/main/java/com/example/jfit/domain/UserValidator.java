package com.example.jfit.domain;

import com.example.jfit.domain.User;
import com.example.jfit.exceptions.ValidationException;

public class UserValidator {

    public void validate(User user) {
        String errors = "";
        if (user.getUsername().equals("")) {
            errors += "Username must not be empty!";
        }
        if (user.getPassword().equals("")) {
            errors += "Password must not be empty!";
        }
        if (!errors.equals("")) {
            throw new ValidationException(errors);
        }
    }
}
