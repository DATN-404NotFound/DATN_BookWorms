package com.poly.DATN_BookWorms.services;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.responses.MailInformationResponse;

import jakarta.mail.MessagingException;

@Service
public interface MailService {
	void send(MailInformationResponse mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	void queue(MailInformationResponse mail);
	void queue(String to, String subject, String body);
}
