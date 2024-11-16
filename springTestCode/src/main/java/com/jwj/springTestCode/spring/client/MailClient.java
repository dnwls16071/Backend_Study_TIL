package com.jwj.springTestCode.spring.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailClient {

	public boolean sendEmail(String fromEmail, String toEmail, String subject, String content) {
		log.info("메일 전송");
		throw new IllegalArgumentException("메일 전송");
	}
}
