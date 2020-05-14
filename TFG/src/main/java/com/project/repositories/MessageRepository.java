package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
