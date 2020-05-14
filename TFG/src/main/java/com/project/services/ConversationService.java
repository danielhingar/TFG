package com.project.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Conversation;
import com.project.repositories.ConversationRepository;

@Service
@Transactional
public class ConversationService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ConversationRepository conversationRepository;
	
	//Services----------------------------------------------------------------------------------
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CompanyService companyService;
	
	//Pagination----------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Conversation> findConversationByClient(String username, Pageable pageable) {
		return conversationRepository.findConversationByClient(username, pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<Conversation> findConversationByCompany(String username, Pageable pageable) {
		return conversationRepository.findConversationByCompany(username, pageable);
	}
	
	// -----------------------------------------Show conversation--------------------------------
	@Transactional(readOnly = true)
	public Conversation findById(int id) {
		return conversationRepository.findById(id).orElse(null);
	}
	
	// ----------Save Create------------------------------------
	@Transactional
	public Conversation save(Conversation conversation, String userClient, String userCompany) {
		conversation.setCreateDate(new Date());
		conversation.setClient(clientService.findByUsername(userClient));
		conversation.setCompany(companyService.findByUsername(userCompany));
		return conversationRepository.save(conversation);
	}

	// ---------------------------Save Update----------------------------------
	@Transactional
	public Conversation saveConversation(Conversation conversation) {
		return conversationRepository.save(conversation);
	}

	// -----------------------------------------Delete----------------
	@Transactional
	public void delete(int id) {
		conversationRepository.deleteById(id);
	}

}
