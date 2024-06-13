package de.ait_tr.DiaHelper.exception_handling.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName) {
        super(String.format("User with name %s not found", userName));
    }

    public UserNotFoundException(Long userId) {
        super(String.format("User with ID %d not found", userId));
    }
}
