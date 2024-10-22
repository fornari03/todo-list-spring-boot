package br.dev.todo_list_spring.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.dev.todo_list_spring.model.User;
import br.dev.todo_list_spring.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("123");
        adminUser.setRole("ADMIN");

        userService.create(adminUser);
    }
}