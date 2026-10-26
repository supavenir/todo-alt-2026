package dev.sio.todoalt.exceptions;

import dev.sio.todoalt.ui.UiMessage;
import dev.sio.todoalt.ui.UiMessageType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(TodolistNotFoundException.class)
    public ModelAndView handleTodoNotFoundException(){
        return new ModelAndView("/todo/index","message",new UiMessage("TodoListe","Cette todoliste n'a pas été trouvée.", UiMessageType.WARNING));
    }
}
