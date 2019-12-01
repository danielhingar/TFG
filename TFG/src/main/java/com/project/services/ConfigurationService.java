package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Configuration;
import com.project.repositories.ConfigurationRepository;

@Service
@Transactional
public class ConfigurationService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ConfigurationRepository configurationRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Configuration findOne() {
		List<Configuration> configurations= this.configurationRepository.findAll();
		return configurations.get(0);
	}

}