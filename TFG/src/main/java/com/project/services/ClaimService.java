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
	// reporter------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Claim> findClaimByReporter(String username) {
		return (List<Claim>) claimRepository.findClaimByReporter(username);
	}
	
	// ----------------------------------------List claims by client------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Claim> findClaimByClient(String username) {
		return (List<Claim>) claimRepository.findClaimByClient(username);
	}

	// ----------------------------------------List claims------------------------------
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
		claim.setStatus("Pendiente de respuesta");
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
	public Claim assign(Claim claim, String username) {
		claim.setReporter(reporterService.findByUsername(username));
		return claimRepository.save(claim);
	}
	
}
