package edu.htwk.mm.risiko.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.htwk.mm.risiko.model.Continent;
import edu.htwk.mm.risiko.model.Country;
import edu.htwk.mm.risiko.model.GameMap;
import edu.htwk.mm.risiko.model.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class GameMapRepository {

	private ObjectMapper objectMapper = new ObjectMapper();

	public List<GameMap> getAllMaps(){
		return Arrays.stream(Map.values())
				.map(map -> getMapByName(map.getMapName()))
				.collect(Collectors.toList());

	}

	public GameMap getMapByName(String mapName) {
		try {
			File file = new File(String.format("templates/%s.json", mapName));
			if(file.exists()) {
				return objectMapper.readValue(file, GameMap.class);
			} else {
				log.error("failed to find map file templates/{}.json", mapName);
			}
			return null;
		}catch (Exception e) {
			log.error("failed to load map {}", mapName, e);
			return null;
		}
	}
} 
