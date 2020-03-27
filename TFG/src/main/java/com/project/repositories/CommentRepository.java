package com.project.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query("select c from Comment c where c.product.id = ?1")
	Page<Comment> findCommentByProduct(int productId,Pageable pageable);
	
	@Query("select c from Comment c where c.client.username= ?1")
	List<Comment> findCommentByClient(String clientName);

}
