package dev.sio.todoalt.controllers;

import dev.sio.todoalt.dtos.SearchRequest;
import dev.sio.todoalt.dtos.TodolistCreateRequest;
import dev.sio.todoalt.dtos.TodolistUpdateRequest;
import dev.sio.todoalt.entities.Todolist;
import dev.sio.todoalt.exceptions.TodolistNotFoundException;
import dev.sio.todoalt.services.TodoService;
import dev.sio.todoalt.ui.UiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    @RequestMapping
    public String index(ModelMap model){
        model.put("todos",todoService.findAll());
        return "/todo/index";
    }

    @GetMapping("/add")
    public String addForm(ModelMap model){
        model.put("todo",new Todolist());
        model.put("submitUrl","add");
        model.put("title","Ajout de todoliste");
        return "/todo/form";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable UUID id, ModelMap model) throws TodolistNotFoundException {
        Optional<Todolist> optList=todoService.findById(id);
        if(optList.isPresent()) {
            model.put("todo",optList.get());
            model.put("submitUrl","update");
            model.put("title","Modification de todoliste");
            return "/todo/form";
        }
        throw new TodolistNotFoundException("Cette todoListe est introuvable.");
    }

    @PostMapping("/add")
    public RedirectView submitAdd(@ModelAttribute TodolistCreateRequest request, RedirectAttributes attrs){
        attrs.addFlashAttribute("message",new UiMessage("Ajout","La TodoListe "+request.getName()+" a été créée."));
        todoService.addOrUpdate(request.toTodolist());
        return new RedirectView("/todo");
    }

    @PostMapping("/update")
    public RedirectView submitUpdate(@ModelAttribute TodolistUpdateRequest request, RedirectAttributes attrs) throws TodolistNotFoundException {
        Optional<Todolist> optList=todoService.update(request);
        if(optList.isPresent()) {
            attrs.addFlashAttribute("message", new UiMessage("Modification", "La TodoListe " + request.getName() + " a été modifiée."));
            return new RedirectView("/todo");
        }
        throw new TodolistNotFoundException("La todoliste n'a pas été trouvée.");
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable UUID id) throws TodolistNotFoundException {
        todoService.deleteById(id);
        return new RedirectView("/todo");
    }

    @PostMapping("/search")
    public String search(ModelMap model, @ModelAttribute SearchRequest req){
        model.put("todos",todoService.searchByText(req.getText()));
        return "/todo/index";
    }
}
