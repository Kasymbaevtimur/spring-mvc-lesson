package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.model.User;
import peaksoft.service.impl.UserService;

/**
 * @Controller: Эта аннотация говорит Spring, что класс является контроллером.
 * Когда приходит HTTP-запрос, Spring будет использовать методы этого класса для
 * обработки запроса и возвращения соответствующего ответа.
 */
@Controller
/**
 * Аннотация @RequestMapping("/users") в Spring MVC используется для маппинга URL-путей к методам контроллера, которые должны обрабатывать запросы,
 * связанные с пользователями. Когда клиент отправляет HTTP-запрос с URL,
 * соответствующим "/users", будет вызван метод контроллера, помеченный этой аннотацией.
 */
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *Model в Spring MVC представляет собой интерфейс, предназначенный для передачи данных между контроллером и представлением (view).
     *  Когда контроллер обрабатывает запрос и готовит данные для отображения, он обычно взаимодействует с объектом Model,
     *  чтобы передать эти данные представлению.
     */

    /**
     *Аннотация @GetMapping("/add") в Spring MVC используется для маппинга HTTP GET-запросов по указанному пути "/add" к методу контроллера.
     * Это означает, что когда пользователь делает
     * GET-запрос по пути "/add", будет вызван соответствующий метод контроллера.
     */
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "users/save";
    }

    /**
     *Аннотация @PostMapping("/save") в Spring MVC используется для маппинга HTTP POST-запросов по указанному пути "/save"
     *  к методу контроллера. Это означает, что когда пользователь отправляет форму с методом POST на путь "/save",
     * будет вызван соответствующий метод контроллера для обработки этих данных.
     */
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:find-all";
    }

    @GetMapping("find-all")
    public String getAllUser(Model model) {
        model.addAttribute("userList",userService.findAll());
        return "users/get-all";
    }


}
