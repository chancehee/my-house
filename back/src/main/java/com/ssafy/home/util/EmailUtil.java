package com.ssafy.home.util;

import java.util.Map;

import javax.mail.MessagingException;

public interface EmailUtil {
	void sendEmail(String email, String title, String contents) throws MessagingException;
}
