package br.dev.todo_list_spring.service.impl;

import java.util.List;

import br.dev.todo_list_spring.handler.IllegalDateLimitFormat;
import br.dev.todo_list_spring.handler.TaskNotFoundException;
import br.dev.todo_list_spring.model.Task;
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
    public List<Task> findByUserIdOrderByDateLimitAsc(Long userId) {
        return taskRepository.findByUserIdOrderByDateLimitAsc(userId);
    }

    @Transactional
    public List<Task> findByUserIdAndDoneOrderByDateLimitAsc(Long userId, Boolean done) {
        return taskRepository.findByUserIdAndDoneOrderByDateLimitAsc(userId, done);
    }
    
    @Transactional
    public Task create(Task entity) throws IllegalDateLimitFormat {
        return taskRepository.save(entity);
    }
    
    @Transactional
    public Task update(Long id, Task entity) {
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskRepository.save(entity);
    }
    
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task deleteByIdAndUserId(Long id, Long userId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access. Verify if the taskId is correct.");
        }
        taskRepository.deleteById(id);
        return task;
    }
}
