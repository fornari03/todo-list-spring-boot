package br.dev.todo_list_spring.handler;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(Long id) {
        super(id, "Task");
    }

}
