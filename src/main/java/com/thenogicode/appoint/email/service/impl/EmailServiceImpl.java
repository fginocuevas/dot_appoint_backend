package com.thenogicode.appoint.email.service.impl;

import java.time.format.DateTimeFormatter;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.email.config.EmailConfig;
import com.thenogicode.appoint.email.service.EmailService;
import com.thenogicode.appoint.event.domain.Event;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    
	private final JavaMailSender emailSender;
	private final EmailConfig emailConfig;
	
	private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm:ss a");
	
	public void sendNotificationEmailForAppointmentCreated(Event event, DoctorAppUser doctor) {
		
		String email= doctor.getEmail();
		String subject= emailConfig.getNewAppointmentSubject();
		
		StringBuilder builder= new StringBuilder();
		builder.append("Hi Dr. " + doctor.getLastname() + ", \n\n");
		builder.append("An appointment was set by " + event.getCreatedBy().getUserDisplayName());
		builder.append(" with the following details: \n\n");
		builder.append("Patient Name: " + event.getPatientName());
		builder.append("\nDate: " + event.getEventDate());
		builder.append("\nStart Time: " + event.getStartTime().format(pattern));
		builder.append("\nEnd Time: " + event.getEndTime().format(pattern));
		builder.append("\n\nPlease confirm and accept if you are available. ");
		builder.append("\n\nThank you and have a great day. ");
		
		String messageBody= builder.toString();
		
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@dotappoint.com");
        log.info("Setting up email for {} with subject {}", email, subject);
        log.info("Message body: \n" + messageBody);
        message.setTo(email); 
        message.setSubject(subject); 
        message.setText(messageBody);
//        emailSender.send(message);
        
        log.info("Email successfully sent");
    }
	
}
