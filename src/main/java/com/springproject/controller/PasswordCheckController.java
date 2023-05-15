package com.springproject.controller;

import com.springproject.domain.Members;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PasswordCheckController {

    @PostMapping("/check-password")
    public String checkPassword(@RequestParam("password") String inputPassword, HttpServletRequest request) {

        Members member = (Members) request.getSession().getAttribute("Member");

        if (member != null && member.getPassword().equals(inputPassword)) {
            return "true";
        }

        return "false";
    }
}