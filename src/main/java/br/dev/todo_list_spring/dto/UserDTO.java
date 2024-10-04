package br.dev.todo_list_spring.dto;

import br.dev.todo_list_spring.model.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents an user in the system.")
public record UserDTO(
    @Schema(description = "Unique identifier of a user", example = "1") Long id,
    @Schema(description = "Username", example = "abelferreira12") String username) {

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



