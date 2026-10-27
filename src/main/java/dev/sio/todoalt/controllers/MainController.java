package dev.sio.todoalt.controllers;

import dev.sio.todoalt.entities.User;
import dev.sio.todoalt.services.UserService;
import dev.sio.todoalt.ui.UiMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/register/{login}/{password}")
    public RedirectView fastRegister(@PathVariable String login, @PathVariable String password, RedirectAttributes attrs){
        User u=userService.createUser(login,password);
        attrs.addFlashAttribute("message",
                new UiMessage("Register","Utilisateur "+u.getLogin()+" créé.")
                );
        return new RedirectView("/todo");
    }
}
