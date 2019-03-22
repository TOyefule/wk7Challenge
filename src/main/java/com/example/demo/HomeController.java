package com.example.demo;


import com.cloudinary.utils.ObjectUtils;
import com.example.demo.beans.User;
import com.example.demo.configurations.CloudinaryConfig;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;


@Controller
public class HomeController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryConfig cloudc;


        @RequestMapping("/")
        public String list(Model model){
        model.addAttribute("message", messageRepository.findAll());
        return "list";
    }

        @RequestMapping("/login")
        public String login(){

            return "login";
        }

        @GetMapping("/add")
        public String message(Model model){
            model.addAttribute("message", new Message());

            return "message";
        }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute Message message, BindingResult result,
                              @RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "redirect:/add";
        }
        if (result.hasErrors()){
            return "message";
        }try {
            Map uploadResult = cloudinary.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            message.setPicture(uploadResult.get("url").toString());
            messageRepository.save(message);
        }catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        messageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user")
                                                  User user, BindingResult
                                                  result,
                                          Model model) {
        model.addAttribute("user", new User());
        if (result.hasErrors()) {
            return "registration";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "list";
    }
}
