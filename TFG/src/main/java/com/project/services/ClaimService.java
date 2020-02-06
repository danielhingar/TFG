package com.project.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Claim;
import com.project.repositories.ClaimRepository;

@Service
@Transactional
public class ClaimService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ClaimRepository claimRepository;

	// Services------------------------------------------------------------------------------------------------
	@Autowired
	private FactureService factureService;
	
	@Autowired
	private ReporterService reporterService;

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------List claims by
	// facture------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Claim> findClaimByFacture(int factureId) {
		return (List<Claim>) claimRepository.findClaimByFacture(factureId);
	}

	// ----------------------------------------List claims by
	// facture------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Claim> findClaimByReporter(int reporterId) {
		return (List<Claim>) claimRepository.findClaimByReporter(reporterId);
	}

	// ----------------------------------------List claims by
	// facture------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Claim> findAll() {
		return (List<Claim>) claimRepository.findAll();
	}

	// -----------------------------------------Show claim
	// -----------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Claim findById(int id) {
		return claimRepository.findById(id).orElse(null);
 
	}

	// ----------------------------------------Save
	// Create------------------------------------------------
	@Transactional
	public Claim save(Claim claim, int factureId) {
		claim.setFacture(this.factureService.findById(factureId));
		claim.setCreateDate(new Date());
		claim.setStatus("PENDING");
		return claimRepository.save(claim);
	}

	// ----------------------------------------Save
	// update-----------------------------------------------------
	@Transactional
	public Claim saveClaim(Claim claim) {
		return claimRepository.save(claim);
	}

	// -----------------------------------------Delete----------------
	@Transactional
	public void delete(int id) {
		claimRepository.deleteById(id);
	}
	
	//-------------------------------Assign claim-------------------
	@Transactional
	public Claim assign(Claim claim, int reporterId) {
		claim.setReporter(reporterService.findById(reporterId));
		return claimRepository.save(claim);
	}
	
}
