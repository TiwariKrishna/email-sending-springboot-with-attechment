package com.ktech.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktech.dto.EmailRequest;
import com.ktech.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.emailSending(emailRequest);
			return ResponseEntity.ok("Email sent successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send email: " + e.getMessage());
		}
	}

	@PostMapping("/send/attech")
	public ResponseEntity<String> sendEmailWithAttachment(@RequestParam String to, @RequestParam String subject,
			@RequestParam String text, @RequestParam MultipartFile attachment) throws MessagingException {
		try {
			emailService.sendEmailWithAttachment(to, subject, text, attachment);
			return ResponseEntity.ok("Email sent successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send email: " + e.getMessage());
		}
	}
}
