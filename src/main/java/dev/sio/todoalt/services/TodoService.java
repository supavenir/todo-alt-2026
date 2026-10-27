package dev.sio.todoalt.services;

import dev.sio.todoalt.dtos.TodolistUpdateRequest;
import dev.sio.todoalt.entities.Todolist;
import dev.sio.todoalt.exceptions.TodolistNotFoundException;
import dev.sio.todoalt.repositories.TodolistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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

    public Optional<Todolist> findById(UUID id) {
        return todolistRepository.findById(id);
    }

    public Optional<Todolist> update (TodolistUpdateRequest request){
        Optional<Todolist> optList=findById(request.getId());
        if(optList.isPresent()) {
            Todolist list = optList.get();
            request.update(list);
            return Optional.of(addOrUpdate(list));
        }
        return Optional.empty();
    }

    public List<Todolist> searchByText(String text) {
        return todolistRepository.findByText("%"+text+"%");
    }
}
