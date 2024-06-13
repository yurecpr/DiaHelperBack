package de.ait_tr.DiaHelper.exception_handling.exceptions;

public class UserWithThisEmailNotFoundException extends RuntimeException{
    public UserWithThisEmailNotFoundException(String email) {
        super(String.format("User with this email %s not found", email));
    }


}
