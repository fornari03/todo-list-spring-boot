package br.dev.todo_list_spring.service.impl;

import java.util.List;

import br.dev.todo_list_spring.model.User;
import br.dev.todo_list_spring.repository.UserRepository;
import br.dev.todo_list_spring.service.UserService;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> findAllByOrderByDateLimitAsc() {
        return userRepository.findAllByOrderByDateLimitAsc();
    }

    @Transactional
    public List<User> findByDone(boolean done) {
        return userRepository.findByDone(done);
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
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
