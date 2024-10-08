package br.dev.todo_list_spring.handler;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Long id) {
        super("User with id '" + id + "' already exists");
    }

    public UserAlreadyExistsException(String username) {
        super("User with username '" + username + "' already exists");
    }
}
