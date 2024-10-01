package br.dev.todo_list_spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.todo_list_spring.dto.TaskDTO;
import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getUserTasks() {
        List<Task> tasks = taskService.getTasksByUser("test");
        List<TaskDTO> taskDTOs = tasks.stream()
                                      .map(TaskDTO::toDTO)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }
}
