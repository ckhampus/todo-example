package todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import todo.domain.TaskRepository;
import todo.service.react.component.Component;
import todo.service.react.component.TaskListComponent;

@Controller
public class HomeController {
    @Autowired
    private TaskRepository repository;

    public HomeController() {
    }

    @RequestMapping(value="/tasks", produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Component taskListComponent() {
        return new TaskListComponent(repository.findAll());
    }

    @RequestMapping("/")
    public String taskList(Model model) {
        model.addAttribute("taskList", taskListComponent());
        return "index";
    }
}
