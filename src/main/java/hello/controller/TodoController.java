package hello.controller;

import hello.persistance.User;
import hello.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("/")
    public String todo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("todos", todoService.list(user));
        return "todo";
    }

    @RequestMapping("/{id}/item/add")
    public String addNewItem(@PathVariable("id") Long todoId) {
        todoService.addNewItem(todoId);
        return "redirect:/todo/";
    }

    @RequestMapping("/item/{elementId}")
    public String updateItem(
            @PathVariable("elementId") Long itemId,
            @RequestParam(name = "checked", required = false) Boolean checked,
            @RequestParam(name = "content", required = false) String content) {

        if (checked != null) {
            todoService.updateItem(itemId, checked);
        }
        if (content != null) {
            todoService.updateItem(itemId, content);
        }
        return "redirect:/todo/";
    }

    @RequestMapping("/{todoId}")
    public String updateTodoName(
            @PathVariable("todoId") Long todoId,
            @RequestParam(name = "name", required = false) String name) {

        if (name != null) {
            todoService.updateTodoName(todoId, name);
        }
        return "redirect:/todo/";
    }

    @RequestMapping("/new")
    public String newTodo() {
        todoService.create();
        return "redirect:/todo/";
    }

    @RequestMapping("/item/{elementId}/delete")
    public String deleteItem(@PathVariable("elementId") Long itemId) {
        todoService.deleteItem(itemId);
        return "redirect:/todo/";
    }

    @RequestMapping("/{todoId}/delete")
    public String deleteTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return "redirect:/todo/";
    }


}