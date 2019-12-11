package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Reporter;

import com.project.repositories.ReporterRepository;


@Service
@Transactional
public class ReporterService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ReporterRepository reporterRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	@Transactional(readOnly = true)
	public List<Reporter> findAll() {
		return (List<Reporter>) reporterRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Reporter findById(int id) {
		return reporterRepository.findById(id).orElse(null);

	}

}
