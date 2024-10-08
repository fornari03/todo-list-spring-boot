package br.dev.todo_list_spring.handler;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id) {
        super(id, "User");
    }

    public UserNotFoundException(String username) {
        super(username, "User");
    }

}
