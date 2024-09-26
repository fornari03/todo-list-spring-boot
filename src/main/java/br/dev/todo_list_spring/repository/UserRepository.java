package br.dev.todo_list_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.todo_list_spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
