package br.dev.todo_list_spring.dto;

import br.dev.todo_list_spring.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a new user in the system.")
public record UserRegisterDTO(
    @Schema(description = "Username", example = "abelferreira12") String username,
    @Schema(description = "Password", example = "password123") String password,
    @Schema(description = "Role (USER or ADMIN)", example = "USER") String role) {
    public User toEntity() {
        User user = new User();
        user.setUsername(this.username());
        user.setPassword(this.password());
        user.setRole(role);
        return user;
    }
}
