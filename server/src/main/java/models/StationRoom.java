package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class StationRoom {
    private int width;
    private int length;

    private List<Position> entries;
    private List<Position> exits;

    private List<List<Integer>> map;
}

