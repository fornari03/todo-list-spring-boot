package br.dev.todo_list_spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.todo_list_spring.service.UserService;
import br.dev.todo_list_spring.dto.UserDTO;
import br.dev.todo_list_spring.dto.UserRegisterDTO;
import br.dev.todo_list_spring.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOs = userService.findAll().stream()
                                         .map(UserDTO::new)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDTO(userService.findById(id)));
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserRegisterDTO newUserDTO) {
        return ResponseEntity.ok(new UserDTO(userService.create(newUserDTO.toEntity())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserRegisterDTO newUserDTO) {
        return ResponseEntity.ok(new UserDTO(userService.update(id, newUserDTO.toEntity())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

