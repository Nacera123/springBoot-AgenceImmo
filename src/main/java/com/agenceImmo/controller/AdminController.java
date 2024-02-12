package com.agenceImmo.controller;

import com.agenceImmo.model.Bien;
import com.agenceImmo.model.User;
import com.agenceImmo.service.BienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private BienService bienService;
    @GetMapping("/admin")
    public String index(@AuthenticationPrincipal User userConnecter, ModelMap modelMap){
        modelMap.put("userConnecter",userConnecter);
        return "admin/index";
    }

    @GetMapping("/admin/register_bien")
    public String getRegister(ModelMap map){

        map.put("bien", new Bien());
        return "admin/bienRegister";
    }

    @PostMapping("/admin/register_bien")
    public String addBien(@ModelAttribute Bien bien, ModelMap map)throws Exception{
        try {
            this.bienService.saveAndUpdate(bien);
            return "redirect:/bienList";

        }catch (Exception ex){
            map.put("error", ex.getMessage());
            map.put("bien", bien);
            return "admin/bienRegister";
        }
    }
}
