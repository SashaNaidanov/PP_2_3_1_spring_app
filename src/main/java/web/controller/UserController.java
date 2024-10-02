package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        return "user-info";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-info";
        }
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam(name = "userId") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-info";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "userId") Long id) {
        userService.remove(id);
        return "redirect:/users";
    }
}
