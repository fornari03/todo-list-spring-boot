package br.dev.todo_list_spring.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dev.todo_list_spring.dto.TaskDTO;
import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.model.User;
import br.dev.todo_list_spring.service.TaskService;
import br.dev.todo_list_spring.service.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskDTO>> findTasksByUserId(Principal principal) {
        Long loggedUserId = userService.findByUsername(principal.getName()).getId();
        List<Task> tasks = taskService.findByUserId(loggedUserId);
        List<TaskDTO> taskDTOs = tasks.stream()
                                      .map(TaskDTO::new)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TaskDTO> createTask(Principal principal, @RequestBody TaskDTO taskDTO) {
        Long loggedUserId = userService.findByUsername(principal.getName()).getId();
        Task task = taskDTO.toEntity(userService.findById(loggedUserId));
        return ResponseEntity.ok(new TaskDTO(taskService.create(task)));
    }
}
