package com.overlydesigned.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelperController exposes a bunch of helpful api's to users who either
 * forgot their username, password or even got locked out of their account.
 *
 * @author Ravi Eragapati
 * @Since 0.0.1
 */
@RestController
public class HelpController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/api/dashboard")
    public String dashboard(){

        return "Welcome to Dashboard";
    }




}
