package com.spring.simulation.greek.Simulation;

import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.map_generator.Cell;
import com.spring.simulation.greek.map_generator.Map;
import com.spring.simulation.greek.map_generator.MapGenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Simulation {

    public static List<Country> countriesList = new LinkedList<Country>();
    public static java.util.Map<String,Country> countries = new HashMap<>();
    public static java.util.Map<Country,Integer> countryColor = new HashMap<>();

    private static void createInitialCountries(){
        countriesList.add(new Country("Greece",360,420, 0x24FF39));
        countriesList.add(new Country("Rome", 316,324, 0xF90A0A));
    }

    private static void setColorsByCountries(){
        for (int i = 0; i < Map.height; ++i) {
            for (int j = 0; j < Map.width; ++j) {
                Cell cell = Map.grid[i][j];
                if(cell.getAreaType() == AreaType.SEA)
                    cell.setColor(0x0AE4F9);
//                else if(cell.getAreaType() == AreaType.RIVER)
//                    cell.setColor(0x010EEB);
                else if(cell.getAreaType() == AreaType.COAST)
                    cell.setColor(0x0);
                else
                    cell.setColor(0xFFFFFF);
            }
        }
    }

    public static void main(String[] args){
        Map map = MapGenerator.readDataFromMapImages();
        setColorsByCountries();
        map.evaluateProvinces();

        createInitialCountries();
        for(Country c : countriesList){
            System.out.println(c.name);                 //debug
            c.occupateTerritories();
        }
        map.drawMap();
    }
}
