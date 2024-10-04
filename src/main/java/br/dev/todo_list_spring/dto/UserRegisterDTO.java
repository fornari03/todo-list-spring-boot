package br.dev.todo_list_spring.dto;

import br.dev.todo_list_spring.model.User;

public record UserRegisterDTO(String username, String password, String role) {
    public User toEntity() {
        User user = new User();
        user.setUsername(this.username());
        user.setPassword(this.password());
        user.setRole(role);
        return user;
    }
}
