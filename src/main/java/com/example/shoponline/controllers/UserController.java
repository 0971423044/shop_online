package com.example.shoponline.controllers;

import com.example.shoponline.exception.UserNotFoundException;
import com.example.shoponline.models.Role;
import com.example.shoponline.models.User;
import com.example.shoponline.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/users")
    public String showListUsers(Model model)
    {
        List<User> listUsers = userService.getListUsers();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }
    @GetMapping("/users/new")
    public String showNewUserForm(Model model)
    {
        User user = new User();
        List<Role> listRoles = userService.getListRoles();
        model.addAttribute("user",user);
        model.addAttribute("listRoles",listRoles);
        model.addAttribute("pageTitle","Create New User");
        return "user_form";
    }
    @PostMapping("/users/save")
    public String createNewUser(User user, RedirectAttributes redirectAttributes)
    {
         userService.saveUser(user);
         redirectAttributes.addFlashAttribute("message","The user has been saved successfully!");
         return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String editExistedUser(@PathVariable(name = "id") Integer id,
                                  RedirectAttributes redirectAttributes,Model model) throws UserNotFoundException {
        try{
            User userExisted= userService.getUserById(id);
            List<Role> listRoles = userService.getListRoles();
            model.addAttribute("user",userExisted);
            model.addAttribute("listRoles",listRoles);
            model.addAttribute("pageTitle","Edit User( ID=" +id+ " )");
            return "user_form";
        }catch(UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
            return "redirect:/users";
        }
    }
}
