package br.dev.todo_list_spring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import br.dev.todo_list_spring.handler.UserAlreadyExistsException;
import br.dev.todo_list_spring.handler.UserNotFoundException;
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
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional
    public User create(User newUser) {
        Optional<User> existingUser = userRepository.findByUsername(newUser.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(existingUser.get().getUsername());
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        String role = newUser.getRole();
        newUser.setRole(role.toUpperCase().strip().equals("ADMIN") ? "ADMIN" : "USER");
        
        return userRepository.save(newUser);
    }    

    @Transactional
    public User update(Long id, User newUser) {
        User userRepo = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        userRepo.setUsername(newUser.getUsername());
        userRepo.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepo.setRole(newUser.getRole());
        return userRepository.save(userRepo);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException());

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }

}
