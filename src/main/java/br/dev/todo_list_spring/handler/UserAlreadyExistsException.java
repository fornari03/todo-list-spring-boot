package br.dev.todo_list_spring.handler;

public class UserAlreadyExistsException extends NotFoundException {
    public UserAlreadyExistsException(Long id) {
        super(id, "User");
    }

    public UserAlreadyExistsException(String username) {
        super(username, "User");
    }
}
