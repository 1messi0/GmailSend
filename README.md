# GmailSend
지메일 보내는 코드 (인증번호 수정중)

- SpringBoot 이용 Gmail 보내기 -

application.properties - 내용 추가 필수

spring.mail.host=smtp.gmail.com
spring.mail.port=587 / 465 중 택1
spring.mail.username=[gmail 주소]
spring.mail.password=[발급받은 앱 비밀번호]
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.starttls.enable=true

- 생성순서 -

1. src - main - webapp - WEB-INF - views - mail.jsp 생성 (html 만들기)

<@ page language="java" ~(본인 조건에 맞게 설정)~ >
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>메일 보내기</title>
</head>
<body>
    <form method="post" action="/mail">
        <input type="text" name="receiver" placeholder="받는 사람 입력"><br>
        <input type="text" name="title" placeholder="제목"><br>
        <textarea name= "content" cols="10" rows="10"></textarea><br>
        <input type="submit" value="전송">
    </form>
</body>
</html>

2. src - main - com - board - domain - MailVo.java (값 오브젝트로만 사용 / !ReadOnly!)

@Data
public class MailVo {

    private String receiver;
    private String title;
    private String content;
}

2. src - main - com - board - controller - MailController.java (페이지 이동 역할)
   
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

3. src - main - com - board - controller => MailService.java 생성 ( 임포트 후 자동생성 가능 )

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private static final String senderEmail= "gohwangbong@gmail.com";

    public void CreateMail(MailVo mailVo){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailVo.getReceiver());
        message.setFrom(senderEmail);
        message.setSubject(mailVo.getTitle());
        message.setText(mailVo.getContent());

        javaMailSender.send(message);
    }
}

-마감-

@중요사항
- src - main - resources => index.html 없으면 실행 잘 안됨 : 스프링은 실행하면 index.html을 1번으로 찾는데 없어서 그렇다고 함(확인중)
   
