package com.project.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	//Services----------------------------------------------------------------------------
	@Autowired
	private ConversationService conversationService;
	
	//Pagination----------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Message> findMessageByConversation(Integer conversationId, Pageable pageable) {
		return messageRepository.findMessageByConversation(conversationId, pageable);
	}
	
	// ----------Save Create------------------------------------
	@Transactional
	public Message save(Message message,int conversationId) {
		message.setCreateDate(new Date());
		message.setAnswer(false);
		message.setConversation(conversationService.findById(conversationId));
		return messageRepository.save(message);
	}
	
	// ----------Save ------------------------------------
	@Transactional
	public Message saveCompany(Message message,int conversationId) {
		message.setCreateDate(new Date());
		message.setAnswer(true);
		message.setConversation(conversationService.findById(conversationId));
		return messageRepository.save(message);
	}

}
