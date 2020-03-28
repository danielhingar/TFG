package com.project.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Comment;
import com.project.repositories.CommentRepository;

@Service
@Transactional
public class CommentService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private CommentRepository commentRepository;

	// Services------------------------------------------------------------------------------------------------
	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------List comments by
	// client------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Comment> findCommentByClient(String clientName) {
		return commentRepository.findCommentByClient(clientName);
	}

	// ----------------------------------------List comments by
	// product------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Comment> findCommentByProduct(int productId, Pageable pageable) {
		return commentRepository.findCommentByProduct(productId, pageable);
	}

	// -----------------------------------------Show comment
	// -----------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Comment findById(int id) {
		return commentRepository.findById(id).orElse(null);

	}

	// ----------------------------------------Save
	// Create------------------------------------------------
	@Transactional
	public Comment save(Comment comment, int productId, String clientName) {
		comment.setCreateDate(new Date());
		comment.setProduct(this.productService.findById(productId));
		comment.setClient(this.clientService.findByUsername(clientName));
		return commentRepository.save(comment);
	}

	// -----------------------------------------Delete----------------
	@Transactional
	public void delete(int id) {
		commentRepository.deleteById(id);
	}

	// -----------------------------------------Delete condition----------------
	@Transactional
	public boolean deleteCondition(int commentId, String username) {
		boolean a= false;
		Comment c= this.commentRepository.findById(commentId).orElse(null);
		if(c.getClient().getUsername().equals(username)) {
			a= true;
		}
		return a;
	}

}
