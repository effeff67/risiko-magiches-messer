package edu.htwk.mm.risiko.service;

import edu.htwk.mm.risiko.model.ContinentList;
import edu.htwk.mm.risiko.repository.ContinentRepository;
import org.springframework.stereotype.Service;


@Service
public class ContinentService {
	
	private ContinentRepository continentRepository;
	
	public ContinentService(ContinentRepository continentRepository) {
		this.continentRepository = continentRepository;
	}
	
	public ContinentList getContinents() {
		return new ContinentList(continentRepository.getAllContinents());
	}

}
