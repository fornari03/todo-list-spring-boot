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
import io.swagger.v3.oas.annotations.Operation;
import br.dev.todo_list_spring.dto.UserDTO;
import br.dev.todo_list_spring.dto.UserRegisterDTO;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Find all users",
               description = "Find all users.", 
               responses = {
                   @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Users found", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDTO.class)))
               })
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOs = userService.findAll().stream()
                                         .map(UserDTO::new)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a user by ID",
               description = "Find a user by ID.", 
               responses = {
                   @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User found", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDTO.class)))
               })
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDTO(userService.findById(id)));
    }
    
    @PostMapping
    @Operation(summary = "Create a new user",
               description = "Create a new user.", 
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserRegisterDTO.class))),
               responses = {
                   @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User created", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDTO.class)))
               })
    public ResponseEntity<UserDTO> create(@RequestBody UserRegisterDTO newUserDTO) {
        return ResponseEntity.ok(new UserDTO(userService.create(newUserDTO.toEntity())));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user by ID",
               description = "Update a user by ID.", 
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserRegisterDTO.class))),
               responses = {
                   @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User updated", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDTO.class)))
               })
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserRegisterDTO newUserDTO) {
        return ResponseEntity.ok(new UserDTO(userService.update(id, newUserDTO.toEntity())));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID",
               description = "Delete a user by ID.", 
               responses = {
                   @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "User deleted")
               })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

