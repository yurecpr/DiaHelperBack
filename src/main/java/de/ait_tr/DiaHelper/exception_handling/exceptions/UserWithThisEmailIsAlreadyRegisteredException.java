package de.ait_tr.DiaHelper.exception_handling.exceptions;

public class UserWithThisEmailIsAlreadyRegisteredException extends RuntimeException {
    public UserWithThisEmailIsAlreadyRegisteredException(String email) {
        super(String.format("User with this email %s is already registered", email));
    }


}
