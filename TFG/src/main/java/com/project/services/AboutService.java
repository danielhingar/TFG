package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.About;
import com.project.repositories.AboutRepository;

@Service
@Transactional
public class AboutService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private AboutRepository aboutRepository;

	// -----------------------------------Show --------------------------------
	@Transactional(readOnly = true)
	public About findById(int id) {
		return aboutRepository.findById(id).orElse(null);
	}

	// -----------------------------------Update----------------------------------
	@Transactional
	public About save(About about) {
		return aboutRepository.save(about);
	}

	// ----------------------------------------Delete----------------------------------------------------
	@Transactional
	public void delete(int id) {
		this.aboutRepository.deleteById(id);
	}

}
