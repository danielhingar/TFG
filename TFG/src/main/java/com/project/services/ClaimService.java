package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Admin;
import com.project.domain.Claim;

import com.project.repositories.ClaimRepository;

@Service
@Transactional
public class ClaimService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ClaimRepository claimRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------List claims by
	// facture------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Claim> findClaimByFacture(Long clientId) {
		return (List<Claim>) claimRepository.findClaimByFacture(clientId);
	}

	// -----------------------------------------Show claim
	// -----------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Claim findById(Long id) {
		return claimRepository.findById(id).orElse(null);

	}
}
