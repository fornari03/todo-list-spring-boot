package br.dev.todo_list_spring.service.impl;

import java.util.List;
import java.util.function.Supplier;

import br.dev.todo_list_spring.model.User;
import br.dev.todo_list_spring.repository.UserRepository;
import br.dev.todo_list_spring.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User create(User newUser) {
        return userRepository.save(newUser);
    }

    @Transactional
    public User update(Long id, User newUser) {
        userRepository.findById(id).orElseThrow((Supplier<RuntimeException>) () -> new RuntimeException("User not found"));
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
