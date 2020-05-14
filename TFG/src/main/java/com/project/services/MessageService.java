package com.project.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Conversation;
import com.project.domain.Message;
import com.project.repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private MessageRepository messageRepository;

	// ----------Save Create------------------------------------
	@Transactional
	public Message save(Message message) {
		message.setCreateDate(new Date());
		message.setAnswer(false);
		return messageRepository.save(message);
	}
	
	// ----------Save ------------------------------------
	@Transactional
	public Message saveCompany(Message message) {
		message.setCreateDate(new Date());
		message.setAnswer(true);
		return messageRepository.save(message);
	}

}
