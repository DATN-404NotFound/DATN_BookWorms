package com.poly.DATN_BookWorms.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.poly.beans.MailInformation;

@Service
public interface MailService {
	void send(MailInformation mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	void queue(MailInformation mail);
	void queue(String to, String subject, String body);
}
