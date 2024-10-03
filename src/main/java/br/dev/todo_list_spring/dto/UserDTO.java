package br.dev.todo_list_spring.dto;

import br.dev.todo_list_spring.model.User;

public record UserDTO(Long id, String username) {
    public UserDTO(User user) {
        this(user.getId(), user.getUsername());
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id());
        user.setUsername(this.username());
        return user;
    }
}


