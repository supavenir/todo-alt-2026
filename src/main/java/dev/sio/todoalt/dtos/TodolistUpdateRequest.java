package dev.sio.todoalt.dtos;

import dev.sio.todoalt.entities.Todolist;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TodolistUpdateRequest {
    private UUID id;
    private String name;
    private String description;

   public void update(Todolist todolist){
       todolist.setName(name);
       todolist.setDescription(description);
   }
}
