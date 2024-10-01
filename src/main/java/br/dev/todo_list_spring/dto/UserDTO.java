package br.dev.todo_list_spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String username;
}

