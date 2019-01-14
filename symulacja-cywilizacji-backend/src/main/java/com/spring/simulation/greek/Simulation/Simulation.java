package com.spring.simulation.greek.Simulation;

import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.map_generator.Cell;
import com.spring.simulation.greek.map_generator.Map;
import com.spring.simulation.greek.map_generator.MapGenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Simulation {

    public static boolean firstRound;

    public static List<Country> countriesList = new LinkedList<Country>();
    public static java.util.Map<String,Country> countries = new HashMap<>();
    public static java.util.Map<Country,Integer> countryColor = new HashMap<>();

    private static void createInitialCountries(){
        countriesList.add(new Country("Greece",360,420, 0x24FF39));
        countriesList.add(new Country("Rome", 316,324, 0xF90A0A));
        countriesList.add(new Country("Macedonia",306,386, 0xF8F60D));
        countriesList.add(new Country("Germania",195,310, 0x7E3606));
        countriesList.add(new Country("Cartagena", 386,292, 0x6E00B0));

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
//                if(cell.getDistanceToRiver() == 40)
//                    cell.setColor(0xED09C2);
            }
        }
    }

    public static int maxSeaDistance(){
        int maxDistance = -1;
        for(int i=0 ; i<Map.height ; ++i){
            for(int j=0 ; j<Map.width ; ++j){
                if(Map.grid[i][j].getDistanceToSea() > maxDistance)
                    maxDistance = Map.grid[i][j].getDistanceToSea();
            }
        }
        return maxDistance;
    }

    public static int maxRiverDistance(){
        int maxDistance = -1;
        for(int i=0 ; i<Map.height ; ++i){
            for(int j=0 ; j<Map.width ; ++j){
                if(Map.grid[i][j].getDistanceToRiver() > maxDistance && Map.grid[i][j].getDistanceToRiver() != Integer.MAX_VALUE)
                    maxDistance = Map.grid[i][j].getDistanceToRiver();
            }
        }
        return maxDistance;
    }

    private static int getLongestRound(){
        int longest = 0;
        for(Country c : countriesList){
            if(c.occupationAbility > longest)
                longest = c.occupationAbility;
        }
        return longest;
    }

    public static void main(String[] args){
        firstRound = true;
        int decades = 100;
        Map map = MapGenerator.readDataFromMapImages();
        setColorsByCountries();
        map.evaluateProvinces();
        createInitialCountries();
        for(int d=0 ; d<decades ; ++d) {
            int i = 0;
            int longest = getLongestRound();
            for (Country c : countriesList)
                c.startRound(firstRound);
            firstRound = false;

            while (i < longest) {
                for (Country c : countriesList) {
                    if (i < c.occupationAbility)
                        c.occupateTerritories();
                }
                i++;
            }

            for (Country c : countriesList) {
                c.endRound();
            }
            map.drawMap(d);
        }
    }
}
