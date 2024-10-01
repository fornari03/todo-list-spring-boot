package br.dev.todo_list_spring.dto;

import java.time.LocalDateTime;

import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskDTO {
    private Long id;
    private String description;
    private LocalDateTime dateLimit;
    private boolean done;
    private Long userId;

    public static TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setDateLimit(task.getDateLimit());
        dto.setDone(task.isDone());
        dto.setUserId(task.getUser().getId());
        return dto;
    }

    public Task toEntity(User user) {
        Task task = new Task();
        task.setId(getId());
        task.setDescription(getDescription());
        task.setDateLimit(getDateLimit());
        task.setDone(isDone());
        task.setUser(user);
        return task;
    }
}

