package br.dev.todo_list_spring.service.impl;

import java.util.List;
import java.util.function.Supplier;

import br.dev.todo_list_spring.model.User;
import br.dev.todo_list_spring.repository.UserRepository;
import br.dev.todo_list_spring.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
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
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        String role = newUser.getRole();
        newUser.setRole(role == null ? "USER" : role);
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

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }

}
