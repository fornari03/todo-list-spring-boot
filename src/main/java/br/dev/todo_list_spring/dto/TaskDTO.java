package br.dev.todo_list_spring.dto;

import java.time.LocalDateTime;

import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.model.User;

public record TaskDTO(Long id, String description, LocalDateTime dateLimit, boolean done) {
    public TaskDTO(Task task) {
        this(task.getId(), 
             task.getDescription(), 
             task.getDateLimit(), 
             task.isDone());
    }

    public Task toEntity(User user) {
        Task task = new Task();
        task.setId(this.id());
        task.setDescription(this.description());
        task.setDateLimit(this.dateLimit());
        task.setDone(this.done());
        task.setUser(user);
        return task;
    }
}


