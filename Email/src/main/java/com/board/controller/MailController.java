package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.domain.MailVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/")
    public String MailPage(){
        return "mail";
    }

    @PostMapping("/mail")
    public void MailSend(MailVo mailVo){
        mailService.CreateMail(mailVo);
    }

}