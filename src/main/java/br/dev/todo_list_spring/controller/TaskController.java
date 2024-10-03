package br.dev.todo_list_spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<TaskDTO>> findTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        List<TaskDTO> taskDTOs = tasks.stream()
                                      .map(TaskDTO::new)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
        Task task = taskDTO.toEntity(userService.findById(userId));
        return ResponseEntity.ok(new TaskDTO(taskService.create(task)));
    }
}
