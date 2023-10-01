package com.ktech.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktech.dto.EmailRequest;

public interface EmailService {

	public void emailSending(EmailRequest request);

	public void sendEmailWithAttachment(String to, String subject, String text, MultipartFile attachment);
}
