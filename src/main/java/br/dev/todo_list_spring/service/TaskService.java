package br.dev.todo_list_spring.service;

import java.util.List;

import br.dev.todo_list_spring.model.Task;

public interface TaskService extends CrudService<Long, Task> {
    List<Task> findByUserId(Long userId);

    Task deleteByIdAndUserId(Long id, Long userId);
}
