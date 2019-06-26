package com.blog.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import com.blog.vo.TokenVO;

public class Mail {
	private JavaMailSender mailSender;
	
	public Mail(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendPasswordResetPage(TokenVO vo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("subject", "[blog] 비밀번호 재설정 메일입니다.");
		map.put("text", new StringBuffer().append("<p>아래 링크를 눌러 비밀번호를 재설정하세요.</p>")
				.append("<a href='https://hjahndev.com/member/resetPassword?email=")
				.append(vo.getEmail())
				.append("&token=")
				.append(vo.getToken())
				.append("'>비밀번호 재설정</a>")
				.toString());
		try {
			sendMail(map, vo.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMail(Map<String, String> map, String email) throws MessagingException {
		MimeMessage	message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); 
		helper.setFrom("hjahn.dev@gmail.com"); 
		helper.setTo(email); 
		helper.setSubject(map.get("subject")); 
		helper.setText(map.get("text"), true);
		mailSender.send(message);
	}
}
