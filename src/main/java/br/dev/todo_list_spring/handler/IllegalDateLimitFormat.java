package br.dev.todo_list_spring.handler;

public class IllegalDateLimitFormat extends IllegalArgumentException {
    public IllegalDateLimitFormat() {
        super("The date limit must be in the format HH:mm dd/MM/yyyy and must be a valid date.");
    }

}
