package com.spring.simulation.greek.simulation;

import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.map_generator.Cell;
import com.spring.simulation.greek.map_generator.Map;
import com.spring.simulation.greek.map_generator.MapGenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class Simulation {

    public static boolean firstRound;

    public static List<Country> countriesList = new LinkedList<Country>();
    public static java.util.Map<String,Country> countries = new HashMap<>();
    public static java.util.Map<Country,Integer> countryColor = new HashMap<>();

    private static void createInitialCountries(){
        countriesList.add(new Country("Greece",360,420, 0x24FF39));
        countriesList.add(new Country("Rome", 316,324, 0xF90A0A));
        countriesList.add(new Country("Macedonia",306,386, 0xF8F60D));
        countriesList.add(new Country("Germania",195,310, 0xB2CFC3));
        countriesList.add(new Country("Cartagena", 386,292, 0x6E00B0));
        countriesList.add(new Country("Slavic",159,397,0xFFAD11));
        countriesList.add(new Country("Turkey",349,509,0xFF11BA));
        countriesList.add(new Country("Hungary",229,382,0x11FFA2));
        countriesList.add(new Country("Vikings",20,270,0xA1FF11));
        countriesList.add(new Country("Espana",341,151,0x076705));
        countriesList.add(new Country("Russia",112,513,0x85152A));
        countriesList.add(new Country("Gallia",231,208,0x855A15));
        countriesList.add(new Country("Denmark",88,288,0x969696));
        countriesList.add(new Country("Britain",173,166,0xFFA905));
        countriesList.add(new Country("Scotland",161,186,0xFF0555));
        countriesList.add(new Country("Walia",108,165,0x3405FF));
        countriesList.add(new Country("Irleand",121,118,0x04CE25));
        countriesList.add(new Country("Bulgaria",293,481,0x600092));
        countriesList.add(new Country("Netherlands",158,244,0x00CD89));



    }

    private static void setColorsByCountries(){
        for (int i = 0; i < Map.height; ++i) {
            for (int j = 0; j < Map.width; ++j) {
                Cell cell = Map.grid[i][j];
                if(cell.getAreaType() == AreaType.SEA)
                    cell.setColor(0x0AE4F9);
                else if(cell.getAreaType() == AreaType.COAST)
                    cell.setColor(0x0);
                else
                    cell.setColor(0xFFFFFF);
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
        int iterations = 201;
        Map map = MapGenerator.readDataFromMapImages();
        setColorsByCountries();
        map.evaluateProvinces();
        createInitialCountries();
        for(int d=0 ; d<iterations ; ++d) {
            int i = 0;
            int longest = getLongestRound();
            for (Country c : countriesList) {
                c.startRound(firstRound);
            }
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
