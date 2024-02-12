package com.agenceImmo.controller;


import com.agenceImmo.model.Role;
import com.agenceImmo.model.User;
import com.agenceImmo.service.RoleService;
import com.agenceImmo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class UserController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String getRegisterPage(ModelMap map){
        List<Role> roleList = roleService.getAll();
        map.put("roles", roleList);

        return "user/regiter";
    }

//    @PostMapping("/register")
//    public String addUser(User user, ModelMap map){
//
//        //TODO: vérifier si l'adresse email existe deja + mdp supérieur à 5 caractères
//        Optional<Role> roleId = roleService.getById(id);
//
//        user.setRoles(List.of(this.roleService.getRoleFromName("USER")));
//
//         user.setActive(true);
//        user.setMdp(this.passwordEncoder.encode(user.getMdp()));
//        userService.add(user);
//
//        return "redirect:/";
//    }


    @PostMapping("/register")
    public String addUser(User user, ModelMap map, @RequestParam Integer roles_id) {
        //TODO: vérifier si l'adresse email existe déjà + mdp supérieur à 5 caractères
        Optional<Role> role = roleService.getById(roles_id);


        if (role.isPresent()) {
            List<Role> list = new ArrayList<>();
            list.add(role.get());

            user.setRoles(list);
            user.setActive(true);
            user.setMdp(this.passwordEncoder.encode(user.getMdp()));
            userService.add(user);

            return "redirect:/";
        }else{
            map.put("msgErreur","bla bla bla");
            List<Role> roleList = roleService.getAll();
            map.put("roles", roleList);

            return "user/regiter";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }


}
