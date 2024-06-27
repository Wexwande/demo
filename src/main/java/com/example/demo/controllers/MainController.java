package com.example.demo.controllers;

import com.example.demo.models.MyModel;

import com.example.demo.security.UsersDetails;
import com.example.demo.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {
    private final MyService myService;
    @Autowired
    public MainController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/creation_of_requests")
    public String creationOfRequests(){

        return "main/index";
    }
    @PostMapping("/creation_of_requests")
    public String creationOfRequestsPost(String name, String responsible, String customerInformation){
        myService.savingApplications(name, responsible, customerInformation);
        return "main/index";
    }

    @GetMapping("/{id}")
    public String editing(Model model, @PathVariable("id") int id){
        model.addAttribute("app", myService.findOne(id));
        return "main/editing";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("app") MyModel app, BindingResult bindingResult, @PathVariable("id") int id){
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
            if (usersDetails.getUsername().equals(myService.findOne(id).getResponsible()) || usersDetails.getUsername().equals("admin")) {
                myService.update(id, app);
                return "redirect:/";
            } else {
                return "main/error";
            }
        }
    }
   /* @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("app", myService.findOne(id));
    }*/
    @GetMapping()
    public String showsRequests(Model model){
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        // if (myService.isAdmin(usersDetails.getUser())){
        //     model.addAttribute("list", myService.receiveApplications());
        //     return "main/applications";
        // }else {
        //     return showsUserRequests(model);
        // }
        return "main/error";
    }

    @GetMapping("/user_request")
    public String showsUserRequests(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        System.out.println(usersDetails.getUsername());
        String responsible = usersDetails.getUsername();
       List<MyModel> list = myService.receiveMyApplications(responsible);
       for (MyModel myModel : list) {
           System.out.println(myModel.getId());
       }

        model.addAttribute("list", list);
        return "main/personal_area";
    }

    @GetMapping("/{id}/complete")
    public String applicationCompleted(@PathVariable("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        if (usersDetails.getUsername().equals(myService.findOne(id).getResponsible())) {
            myService.closeApp(id);
            return showsRequests(model);
        } else {
            return "main/error";
        }

    }

    @GetMapping("/{id}/delete")
    public String applicationDelete(@PathVariable("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        if (usersDetails.getUsername().equals("admin")) {
            myService.deleteApp(id);
            return showsRequests(model);
        } else {
            return "main/error";
        }
    }
}


