package com.stage.myldapappp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bienvenue sur l'annuaire d'entreprise MyLdapApp");
        return "index";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/modify")
    public String modify() {
        return "modif";
    }

    @GetMapping("/add")
    public String addEntryForm() {
        return "entry";
    }

    @PostMapping("/add")
    public String addEntry(@RequestParam String name, @RequestParam String email, @RequestParam String phone, Model model) {
        // Ajouter la logique pour enregistrer l'entrée dans l'annuaire LDAP
        model.addAttribute("message", "Entrée ajoutée avec succès");
        return "entry";
    }
}

