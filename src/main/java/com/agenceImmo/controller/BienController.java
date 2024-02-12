package com.agenceImmo.controller;

import com.agenceImmo.model.Bien;
import com.agenceImmo.service.BienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BienController {

    @Autowired
    private BienService bienService;


    /*Recuperer la page index*/
    @GetMapping("/")
    public String index(ModelMap map){
        List<Bien> bienList = bienService.getActiveBien();
        map.put("bienList", bienList);
        return "home";
    }

    /*Recuperer la page register*/
/*    @GetMapping("/bien_register")
    public String getAddBien(ModelMap map){
        map.put("bien", new Bien());
        return "bienRegister";
    }
    *//*Enregister les biens*//*
    @PostMapping("/bien_register")
    public String addBien(@ModelAttribute Bien bien, ModelMap map){

        try {
            bienService.saveAndUpdate(bien);
            return "redirect:/list_bien";
        }catch (Exception ex){
            map.put("error", ex.getMessage());
            map.put("bien", bien);
            return "bienRegister";
        }

    }*/

    /*Recuperer la page des bien*/
    @GetMapping("/list_bien")
    public String getAllBien(ModelMap map, @RequestParam(required = false) Integer id){
        List<Bien> bienList = this.bienService.getAll();
        map.put("bienList", bienList);
        return "bienList";
    }

    /*Modifier le bien*/
    @RequestMapping("/bien_update/{id}")
    public String updateBien(@PathVariable("id") int id, Model model){
        Bien bien = bienService.getById(id);
        model.addAttribute("bien", bien);
        return "bienUpdate";
    }

    /*Effacer le bien*/
    @RequestMapping("/delete_bien/{id}")
    public  String deleteBienById(@PathVariable("id") int id){
        bienService.deleteById(id);
        return "redirect:/list_bien";
    }


    @GetMapping("/delete_bien/active/{id}") // /dashboard/produit/activate/3   @PathVariable
    public String deleteBien(@PathVariable Integer id) throws Exception{
        Bien bien = this.bienService.getById(id);
        bien.setActive(!bien.isActive());
        this.bienService.saveAndUpdate(bien);
        return "redirect:/list_bien";
    }
    /* Detail du bien by id */
    @GetMapping("/bien_detail/{id}")
    public String getBienById(@PathVariable("id") int id, ModelMap map){

        Bien bien = bienService.getById(id);
        map.put("bien", bien);
        return "bienDetail";
    }


    @PostMapping
    public String uploadPhoto(){
        return null;
    }
}
