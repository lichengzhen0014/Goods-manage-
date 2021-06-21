package com.kcsj.admin.utils;

import com.kcsj.admin.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lcz
 * @create 2021-06-15-10:48
 */
@Component
public class SendMail {

    @Autowired
    private MailService mailService;

    @Async
    public  void sendMail(String to,String subject ,String content){
        System.out.println(Thread.currentThread().getName());
        mailService.sendSimpleMail(to,subject,content);
    }
}
