package br.dev.todo_list_spring.handler;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String entity) {
        super(entity + " with id " + id + " not found.");
    }

    public NotFoundException(String name, String entity) {
        super(entity + " with name " + name + " not found.");
    }

}
