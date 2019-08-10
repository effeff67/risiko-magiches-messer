package edu.htwk.mm.risiko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor

    public class Missions{

        private String name;
        private Mission mission;
        private Color targetPlayer;
        private String targetContinentA;
        private String targetContinentB;
        private int countryCount;

    }

