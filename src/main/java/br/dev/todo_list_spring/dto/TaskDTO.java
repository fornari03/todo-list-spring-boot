package br.dev.todo_list_spring.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a task of a User in the system.")
public record TaskDTO(
    @Schema(description = "Unique identifier of a task", example = "1") Long id,
    @Schema(description = "Description of the task", example = "Study Spring Boot") String description,
    @Schema(description = "Date limit of the task", example = "07:00 10/10/2024") String dateLimit,
    @Schema(description = "Tells whether the task is done or not", example = "false") boolean done) {
    public TaskDTO(Task task) {
        this(task.getId(), 
             task.getDescription(), 
             task.getDateLimit().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")),
             task.isDone());
    }

    public Task toEntity(User user) {
        Task task = new Task();
        task.setId(this.id());
        task.setDescription(this.description());
        try {
            LocalDateTime dateLimit = LocalDateTime.parse(this.dateLimit(), DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
            task.setDateLimit(dateLimit);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + this.dateLimit(), e);
        }
        task.setDone(this.done());
        task.setUser(user);
        return task;
    }
}


