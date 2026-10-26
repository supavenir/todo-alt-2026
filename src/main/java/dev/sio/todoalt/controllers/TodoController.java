package dev.sio.todoalt.controllers;

import dev.sio.todoalt.dtos.TodolistCreateRequest;
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
    public ModelAndView addForm(){
        return new ModelAndView("/todo/form","todo",new Todolist());
    }

    @PostMapping("/add")
    public RedirectView submitAdd(@ModelAttribute TodolistCreateRequest request, RedirectAttributes attrs){
        attrs.addFlashAttribute("message",new UiMessage("Ajout","La TodoListe "+request.getName()+" a été créée."));
        todoService.addOrUpdate(request.toTodolist());
        return new RedirectView("/todo");
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable UUID id) throws TodolistNotFoundException {
        todoService.deleteById(id);
        return new RedirectView("/todo");
    }
}
