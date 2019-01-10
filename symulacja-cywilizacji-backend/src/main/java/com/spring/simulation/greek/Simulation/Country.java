package com.spring.simulation.greek.Simulation;

import com.spring.simulation.greek.map_generator.Cell;
import com.spring.simulation.greek.map_generator.Map;

import java.util.LinkedList;
import java.util.List;

import static com.spring.simulation.greek.Simulation.Simulation.countries;

public class Country {
    public String name;
    private List<Cell> provinces = new LinkedList<Cell>();
    private int color;
    private long population;
    private int countrySize;

    Country(String name, int x, int y, int color){
        this.name = name;
        this.color = color;
        population = 0;
        countrySize = 0;
        Simulation.countries.put(name,this);
        Simulation.countryColor.put(this,color);

        this.occupyTerritory(x,y, this);
    }

    private void occupyTerritory(int x, int y, Country c){
        Cell occupiedArea = Map.grid[y][x];
        occupiedArea.setCountry(c);
        occupiedArea.setColor(this.color);
        population += occupiedArea.getPopulation();
        countrySize++;

        provinces.add(occupiedArea);
    }

    public int getColor(){
        return color;
    }
}
