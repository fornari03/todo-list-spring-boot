package br.dev.todo_list_spring.dto;

import br.dev.todo_list_spring.model.User;

public record UserRegisterDTO(Long id, String username, String password) {
    public User toEntity() {
        User user = new User();
        user.setId(this.id());
        user.setUsername(this.username());
        user.setPassword(this.password());
        return user;
    }
}
