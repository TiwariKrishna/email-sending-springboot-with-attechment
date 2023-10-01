package com.ktech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktech.dto.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void emailSending(EmailRequest emailRequest) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailRequest.getTo());
			message.setSubject(emailRequest.getSubject());
			message.setText(emailRequest.getBody());
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailWithAttachment(String to, String subject, String text, MultipartFile attachment) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			helper.addAttachment(attachment.getOriginalFilename(), attachment);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
}
