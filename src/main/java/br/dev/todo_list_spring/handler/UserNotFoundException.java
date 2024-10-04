package br.dev.todo_list_spring.handler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found.");
    }

}
