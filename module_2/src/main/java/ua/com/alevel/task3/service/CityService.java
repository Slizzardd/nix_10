package ua.com.alevel.task3.service;

import ua.com.alevel.task3.entity.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.task3.service.TheShortestWay.find;

public class CityService {

    private static int countOfRoads;
    private static final List<String> citiesNamesForFind = new ArrayList<>();
    private static final List<Integer> cheapestRoads = new ArrayList<>();

    public static String findTheCheapestRoad(String[] arrayRoadsCities) {
        int countOfCities = Integer.parseInt(arrayRoadsCities[0].trim());
        List<City> citiesList = getCitiesFromArray(arrayRoadsCities, countOfCities);
        for (int i = 0; i < countOfRoads; i++) {
            cheapestRoads.add(find(citiesList, citiesNamesForFind.get(i)));
            citiesList = getCitiesFromArray(arrayRoadsCities, countOfCities);
        }
        return getStringFromArray();
    }

    private static String getStringFromArray() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < CityService.cheapestRoads.size(); i++) {
            if (i != 0) {
                result.append("\n");
            }
            result.append(CityService.cheapestRoads.get(i));
        }
        return result.toString();
    }

    private static List<City> getCitiesFromArray(String[] arrayRoadsCities, int countCities) {
        List<City> cityList = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < countCities; i++) {
            City city = new City();
            String nameCity = arrayRoadsCities[index].trim();
            city.setName(nameCity);
            city.setId(i + 1);
            index++;
            int countNeighbour = Integer.parseInt(arrayRoadsCities[index].trim());
            index++;
            Map<Integer, Integer> mapNeighbours = new HashMap<>();
            for (int j = 0; j < countNeighbour; j++) {
                String[] temp = arrayRoadsCities[index].trim().split(" ");
                int cityIndex = Integer.parseInt(temp[0]);
                int distance = Integer.parseInt(temp[1]);
                mapNeighbours.put(cityIndex, distance);
                index++;
            }
            city.setDistanceBetweenNeighbors(mapNeighbours);
            cityList.add(city);
        }
        countOfRoads = Integer.parseInt(arrayRoadsCities[index].trim());
        index++;
        for (int i = 0; i < countOfRoads; i++) {
            citiesNamesForFind.add(arrayRoadsCities[index].trim());
            index++;
        }
        return cityList;
    }
}
