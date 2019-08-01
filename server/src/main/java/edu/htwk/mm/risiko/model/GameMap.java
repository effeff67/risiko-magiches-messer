package edu.htwk.mm.risiko.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GameMap {

    private List<Continent> continentList;
    private List<String> playerColors;
}
