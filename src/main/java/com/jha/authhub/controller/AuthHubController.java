package com.jha.authhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthHubController {

    @GetMapping("/test")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("test");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }
}
