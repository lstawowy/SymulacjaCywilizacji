package com.spring.simulation.greek.Simulation;

import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.map_generator.Cell;
import com.spring.simulation.greek.map_generator.Map;
import org.omg.CORBA.MARSHAL;

import java.util.*;

import static com.spring.simulation.greek.Simulation.Simulation.countries;

public class Country {
    public String name;
    private List<Cell> provinces = new LinkedList<Cell>();
    private List<Cell> border = new LinkedList<Cell>();
    private int color;
    private long population;
    private int countrySize;

    private int occupationAbility;

    Country(String name, int x, int y, int color){
        this.name = name;
        this.color = color;
        population = 0;
        countrySize = 0;
        Simulation.countries.put(name,this);
        Simulation.countryColor.put(this,color);
        occupationAbility = 100;             ///to bedzie do zmiany, sparametryzowac

        this.occupyTerritory(x,y, this);
    }

    private void occupyTerritory(int x, int y, Country c){
        Cell occupiedArea = Map.grid[x][y];
        occupiedArea.setCountry(c);
        occupiedArea.setColor(this.color);
        population += occupiedArea.getPopulation();
        countrySize++;

        provinces.add(occupiedArea);
        updateBorder(x,y);
    }

    private boolean canBeBorder(int x, int y){
        Cell c = Map.grid[x][y];
        return (x>=0 && y>=0 && x<Map.height && y<Map.width && c.getAreaType() != AreaType.SEA
                                                            && c.getCountry() != this
                                                            && !(border.contains(c)));
    }
    private void updateBorder(int x, int y){
        if(canBeBorder(x-1,y))
            border.add(Map.grid[x-1][y]);
        if(canBeBorder(x+1,y))
            border.add(Map.grid[x+1][y]);
        if(canBeBorder(x,y+1))
            border.add(Map.grid[x][y+1]);
        if(canBeBorder(x,y-1))
            border.add(Map.grid[x][y-1]);

        border.remove(Map.grid[x][y]);
    }

    public void conquerRandomTerritory(){
        Random gen = new Random();
        for(int i=0 ; i<occupationAbility ; ++i){
            int rand = Math.abs(gen.nextInt()%border.size());
            Cell c = border.get(rand);
            occupyTerritory(c.getX(), c.getY(), this);
        }
    }

    public void occupateTerritories(){
        for(Cell c : border)
            System.out.println(c.getX() + " " + c.getY() + " ma -> " + c.getProvinceValue());
        for(int i=0 ; i<occupationAbility ; ++i){
            for(Cell c : border)
                System.out.println(c.getX() + " " + c.getY() + " ma -> " + c.getProvinceValue());
            border.sort((a,b)->Double.compare(a.getProvinceValue(),b.getProvinceValue()));
            Cell bestProvince = border.remove(border.size()-1);
            System.out.println("Okupuje -> " + bestProvince.getX() + " " + bestProvince.getY());
            occupyTerritory(bestProvince.getX(), bestProvince.getY(), this);
        }
    }

    public int getColor(){
        return color;
    }
}
