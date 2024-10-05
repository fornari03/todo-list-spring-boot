package br.dev.todo_list_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.todo_list_spring.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdOrderByDateLimitAsc(Long userId);

    List<Task> findByUserIdAndDoneOrderByDateLimitAsc(Long userId, Boolean done);
}
