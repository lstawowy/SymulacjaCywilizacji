package com.spring.simulation.greek.Simulation;

import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.enums.CountryAttitude;
import com.spring.simulation.greek.enums.Score;
import com.spring.simulation.greek.map_generator.Cell;
import com.spring.simulation.greek.map_generator.Map;
import org.omg.CORBA.MARSHAL;

import java.util.*;

import static com.spring.simulation.greek.Simulation.Simulation.countries;

public class Country {
    public String name;
    private List<Cell> provinces = new LinkedList<Cell>();
    private List<Cell> border = new LinkedList<Cell>();
    public List<Country> wars = new ArrayList<>();
    private int color;
    private long population;
    private int countrySize;
    private long industrialPotential;

    private double populationIncrease;
    private double industryIncrease;
    private CountryAttitude attitude;

    public int occupationAbility;

    private Cell capitol;

    Country(String name, int x, int y, int color){
        this.name = name;
        this.color = color;
        population = 0;
        countrySize = 0;
        industrialPotential = 0;
        attitude = CountryAttitude.NEUTRAL;
        populationIncrease = Score.basicPopulationIncrease;
        industryIncrease = Score.basicIndustryIncrease;

        Simulation.countries.put(name,this);
        Simulation.countryColor.put(this,color);
        occupationAbility = Score.basicOccupationAbility;
        this.occupyTerritory(x,y, this);
        capitol = Map.grid[x][y];
    }

    private void occupyTerritory(int x, int y, Country c){
        Cell occupiedArea = Map.grid[x][y];
        occupiedArea.setCountry(c);
        occupiedArea.setColor(this.color);
        population += occupiedArea.getPopulation();
        countrySize++;
        population += occupiedArea.getPopulation();
        industrialPotential += occupiedArea.getIndustrialPotential();

        provinces.add(occupiedArea);
        updateBorder(x,y);
    }

    private boolean canBeBorder(int x, int y){
        return (x>=0 && y>=0 && x<Map.height && y<Map.width && Map.grid[x][y].getAreaType() != AreaType.SEA
                                                            && Map.grid[x][y].getCountry() != this
                                                            && !(border.contains(Map.grid[x][y]))
                                                            /*&& Map.grid[x][y].getCountry() == null*/);
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

    public void occupateTerritories(){
        if(border.isEmpty())
            return;
        border.sort((a,b)->Double.compare(a.getProvinceValue()+individualFactors(a.getX(),a.getY()),
                                              b.getProvinceValue()+individualFactors(b.getX(),b.getY())));
        int i = 1;
        while (i <= border.size()) {
            Cell bestProvince = border.get(border.size() - i);
            if(attitude == CountryAttitude.DEFENDER && !wars.contains(bestProvince.getCountry())){
                i++;
                continue;
            }
            if(bestProvince.getCountry() != null){
                if(attitude != CountryAttitude.NEUTRAL
                        && getForce() - bestProvince.getCountry().getForce() > 2000
                        && chanceToWin(bestProvince.getX(),bestProvince.getY()) > 0){
                    attitude = CountryAttitude.AGGRESSOR;
                    bestProvince.getCountry().setAttitude(CountryAttitude.DEFENDER);
                    if(!wars.contains(bestProvince.getCountry())){
                        wars.add(bestProvince.getCountry());
                        bestProvince.getCountry().wars.add(this);
                    }
                    bestProvince.getCountry().lostTerritory(bestProvince.getCountry(),bestProvince);
                    occupyTerritory(bestProvince.getX(), bestProvince.getY(), this);
                    break;
                } else{
                    i++;
                    continue;
                }
            } else {
                occupyTerritory(bestProvince.getX(), bestProvince.getY(), this);
                break;
            }
        }
    }

    public void lostTerritory(Country country, Cell cell){
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                int cx = cell.getX() + i;
                int cy = cell.getY() + j;
                if (cx < 0 || cx >= Map.height || cy < 0 || cy >= Map.width || (cx == cell.getX() && cy == cell.getY())) {
                    continue;
                }
                country.border.remove(Map.grid[cx][cy]);
            }
        }
        country.provinces.remove(cell);
    }

    private double individualFactors(int x, int y){
        double bonus = 0;
        bonus += Score.securityBonus  * Score.defenseFactor * areaProtection(x,y);
        bonus += 100/distanceToCapitol(x,y);

        return bonus;
    }

    private double areaProtection(int x, int y){
        double borderProtectingProvinces = 0;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                int cx = x + i;
                int cy = y + j;
                if (cx < 0 || cx >= Map.height || cy < 0 || cy >= Map.width) {
                    borderProtectingProvinces++;
                    continue;
                }
                if(Map.grid[cx][cy].getCountry() == this){
                    borderProtectingProvinces += 1.0;
                }
                if(Map.grid[cx][cy].getAreaType() == AreaType.SEA)
                    borderProtectingProvinces += 0.3;
            }
        }
        return borderProtectingProvinces;
    }

    public void startRound(boolean firstRound){
        if(!firstRound){
            for(Cell p : provinces){
                p.setPopulation(p.getPopulation() + p.getPopulation()*populationIncrease);
                p.setIndustrialPotential(p.getIndustrialPotential() + p.getIndustrialPotential()*industryIncrease/100);
                p.evaluateProvince();
            }
            industryIncrease = population/(industrialPotential+1);
            populationIncrease = (Score.randFactor(5,30)/100);
        } else{
            return;
        }
    }

    private void countOccupationAbility(){
        if(getForce() < Score.lowProgressLevel){
            occupationAbility = Score.lowOccupAbility;
        } else if(getForce() > 1000){
            occupationAbility = Score.basicOccupationAbility + (int)(getForce()/10)%100;
        } else{
            occupationAbility = Score.basicOccupationAbility;
        }
    }

    public void endRound(){
        long industry = 0;
        long population = 0;
        for(Cell c : provinces){
            industry += c.getIndustrialPotential();
            population += c.getPopulation();
        }

        this.industrialPotential = industry;
        this.population = population;
        countOccupationAbility();
        attitudeUpdate();
    }


    private double distanceToCapitol(int x, int y){
        return Math.sqrt(Math.pow(x-capitol.getX(),2) + Math.pow(y-capitol.getY(),2));
    }

    public double getForce(){
        return population/countrySize;
    }

    private double chanceToWin(int x, int y){
        double chance = areaProtection(x,y) * 10;
        chance -= Map.grid[x][y].getDefenseValue();
        chance += (this.getForce() - Map.grid[x][y].getCountry().getForce());
        chance += Score.randFactor(-50,50);
        return chance;
    }

    public void setAttitude(CountryAttitude a){
        attitude = a;
    }

    public void attitudeUpdate(){
        for(Cell c : border){
            if(c.getCountry() == null){
                return;
            }
        }
        attitude = CountryAttitude.AGGRESSOR;
    }

    public int getColor(){
        return color;
    }
}
