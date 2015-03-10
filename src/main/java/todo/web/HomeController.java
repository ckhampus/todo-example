package todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import todo.service.react.ComponentRenderService;

@Controller
public class HomeController {
    private final ComponentRenderService renderService;

    @Autowired
    public HomeController(ComponentRenderService renderService) {
        this.renderService = renderService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Cristian");

        return "index";
    }
}
