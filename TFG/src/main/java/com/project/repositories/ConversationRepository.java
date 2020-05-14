package com.project.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.project.domain.Conversation;

@Repository
public interface ConversationRepository  extends JpaRepository<Conversation, Integer>{
	
	@Query("select c from Conversation c where c.client.username= ?1")
	Page<Conversation> findConversationByClient(String username, Pageable pageable);
	
	@Query("select c from Conversation c where c.company.username = ?1")
	Page<Conversation> findConversationByCompany(String username,Pageable pageable);

}
