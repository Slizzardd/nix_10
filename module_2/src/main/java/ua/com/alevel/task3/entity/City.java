package ua.com.alevel.task3.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class City {

    private int id;
    private String name;
    private Map<Integer, Integer> distanceBetweenNeighbors;
}
