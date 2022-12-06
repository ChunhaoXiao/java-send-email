package com.xiao.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class SendmailApplication {
	@Autowired
	private EmailSendService emailSendService;

	public static void main(String[] args) {
		SpringApplication.run(SendmailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException, IOException {
		//emailSendService.sendMail("xiaochunhao@gmail.com","测试","测试邮件");
		emailSendService.sendMailWithAttach();
	}

}
