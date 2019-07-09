package com.example.demo.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class EmailController {
     
    @Autowired
    private JavaMailSender sender;
 
    @RequestMapping("/sendemail")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
 
    private void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo("mail-goes-here");
        helper.setText("How are you?");
        helper.setSubject("Hi");
         
        sender.send(message);
    }
    

    @RequestMapping("/sendwithattach")
    @ResponseBody
    String home2() {
        try {
            sendEmail2();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
 
    private void sendEmail2() throws Exception{
        MimeMessage message = sender.createMimeMessage();
         
        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
         
        helper.setTo("mail-goes-here");
        helper.setText("This is a test");
        helper.setSubject("^ What he said ^");
         
        ClassPathResource file = new ClassPathResource("3f5.jpg");
        helper.addAttachment("3f5.jpg", file);
        
        /*FileSystemResource file 
        = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file); */
        //TODO make this work
         
        sender.send(message);
}
}    