package dev.sio.todoalt.services;

import dev.sio.todoalt.entities.Todolist;
import dev.sio.todoalt.exceptions.TodolistNotFoundException;
import dev.sio.todoalt.repositories.TodolistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodolistRepository todolistRepository;

    TodoService(TodolistRepository todolistRepository){
        this.todolistRepository=todolistRepository;
    }

    public List<Todolist> findAll(){
        return todolistRepository.findAll();
    }

    public Todolist addOrUpdate(Todolist todolist){
        return todolistRepository.save(todolist);
    }

    public void deleteById(UUID id) throws TodolistNotFoundException {
        Optional<Todolist> optTodo= todolistRepository.findById(id);
        if(optTodo.isPresent()) {
            todolistRepository.deleteById(id);
            return;
        }
        throw new TodolistNotFoundException("La todolist d'id "+id+" n'a pas été trouvée.");
    }
}
