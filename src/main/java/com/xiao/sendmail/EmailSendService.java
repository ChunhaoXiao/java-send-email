package com.xiao.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class EmailSendService {
    @Autowired
    private JavaMailSender mailSender;

    public void  sendMail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("xiaochunhao@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }

    public void sendMailWithAttach() throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("xiaochunhao@gmail.com");
        mimeMessageHelper.setTo("xiaochunhao@gmail.com");
        mimeMessageHelper.setText("测试带附件的邮件");
        mimeMessage.setSubject("带附件");
        FileWriter fileWriter = new FileWriter("abc.txt");
        fileWriter.write("测试附件");
        fileWriter.close();
        FileSystemResource fileSystemResource = new FileSystemResource(new File("abc.txt"));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        mailSender.send(mimeMessage);
    }
}
