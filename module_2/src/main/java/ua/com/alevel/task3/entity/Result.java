package ua.com.alevel.task3.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Result {

    private List<Integer> fromCity;
    private int distance;
    private boolean finish;
}
