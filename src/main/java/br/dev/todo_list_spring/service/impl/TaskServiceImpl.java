package br.dev.todo_list_spring.service.impl;

import java.lang.StackWalker.Option;
import java.security.Principal;
import java.util.List;
import java.util.function.Supplier;

import br.dev.todo_list_spring.dto.TaskDTO;
import br.dev.todo_list_spring.handler.IllegalDateLimitFormat;
import br.dev.todo_list_spring.handler.TaskNotFoundException;
import br.dev.todo_list_spring.model.Task;
import br.dev.todo_list_spring.model.User;
import br.dev.todo_list_spring.repository.TaskRepository;
import br.dev.todo_list_spring.repository.UserRepository;
import br.dev.todo_list_spring.service.TaskService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }
    
    @Transactional
    public Task create(Task entity) throws IllegalDateLimitFormat {
        return taskRepository.save(entity);
    }
    
    @Transactional
    public Task update(Long id, Task entity) {
        return taskRepository.save(entity);
    }
    
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
