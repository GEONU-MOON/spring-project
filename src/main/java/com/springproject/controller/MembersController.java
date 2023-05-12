package com.springproject.controller;


import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MembersController {

    private final MemberService memberService;
}
