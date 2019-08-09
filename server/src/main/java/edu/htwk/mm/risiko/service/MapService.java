package edu.htwk.mm.risiko.service;

import edu.htwk.mm.risiko.model.GameMap;
import edu.htwk.mm.risiko.repository.GameMapRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class MapService {
	
	private GameMapRepository mapRepository;
	
	public MapService(GameMapRepository mapRepository) {
		this.mapRepository = mapRepository;
	}
	
	public GameMap getMap(String name) {
		if(!StringUtils.isEmpty(name)) {
			return mapRepository.getMapByName(name);
		}
		return null;
	}

}
