package dev.sio.todoalt.dtos;

import dev.sio.todoalt.entities.Todolist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodolistCreateRequest {
    private String name;
    private String description;

    public Todolist toTodolist(){
        Todolist list=new Todolist();
        list.setName(name);
        list.setDescription(description);
        return list;
    }
}
