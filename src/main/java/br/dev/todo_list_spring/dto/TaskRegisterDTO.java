package br.dev.todo_list_spring.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.dev.todo_list_spring.handler.IllegalDateLimitFormat;
import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a new task in the system.")
public record TaskRegisterDTO(
    @Schema(description = "Description of the task", example = "Study Spring Boot") String description,
    @Schema(description = "Date limit of the task", example = "07:00 10/10/2024") String dateLimit) {
    public TaskRegisterDTO(
        String description,
        String dateLimit) {
        this.description = description;
        this.dateLimit = dateLimit;
    }

    public Task toEntity(User user) {
        Task task = new Task();
        task.setDescription(this.description());
        try {
            LocalDateTime dateLimit = LocalDateTime.parse(this.dateLimit(), DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
            task.setDateLimit(dateLimit);
        } catch (IllegalDateLimitFormat e) {
            throw new IllegalDateLimitFormat();
        }
        task.setUser(user);
        return task;
    }
}
