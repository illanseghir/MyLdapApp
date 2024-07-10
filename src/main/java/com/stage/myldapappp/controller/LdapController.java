package com.stage.myldapappp.controller;

import com.stage.myldapappp.service.LdapClient;
import com.stage.myldapappp.service.LdapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LdapController {

    Logger logger = LoggerFactory.getLogger(LdapController.class);

    @Autowired
    private LdapClient ldapClient;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "username", required = false) String username, Model model) {

        //logger.error(username.toString());

        if (username != null && !username.isEmpty()) {
            List<LdapEntry> results = ldapClient.searchByCommonName(username);
            model.addAttribute("results", results);

            logger.error(results.toString());
            logger.error(results.get(0).toString());
            logger.error("=====================================");

            System.out.println(results);
            System.out.println(results.size());
        }

        return "search";
    }

    @PostMapping("/add")
    public String add(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        try {
            ldapClient.create(username, password);
            model.addAttribute("message", "User added successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error adding user: " + e.getMessage());
        }
        return "add";
    }

    @PostMapping("/modif")
    public String modify(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        try {
            ldapClient.modify(username, password);
            model.addAttribute("message", "User modified successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error modifying user: " + e.getMessage());
        }
        return "modif";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            ldapClient.authenticate(username, password);
            //model.addAttribute("message", "User authenticated successfully.");
            return "success";
        } catch (Exception e) {
            //model.addAttribute("error", "Authentication failed: " + e.getMessage());
            return "fail";
        }
    }
}
