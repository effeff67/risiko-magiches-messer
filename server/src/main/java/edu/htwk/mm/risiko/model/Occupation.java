package edu.htwk.mm.risiko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Occupation {
    private Color color;
    private Region source;
    private Region target;
}
