package com.spring.simulation.greek.enums;

import java.util.Random;

public class Score {

    public static double defaultFactor = 1.0;

    public static double numberOfResourcesBonus = 5;
    public static double copperProd = 20;
    public static double coalProd = 20;
    public static double leadProd = 20;
    public static double ironProd = 20;

    public static double coastFactor = 1.0;
    public static double riverFactor = 1.0;
    public static double mountainAreaFactor = 0.5;
    public static double landFactor = 1.0;

    public static double marineFactor = 0.8;
    public static double continentalFactor = 0.7;
    public static double mountainClimateFactor = 0.4;
    public static double steppeFactor = 0.5;
    public static double subtropicalFactor = 0.9;
    public static double mediterraneanFactor = 1;

    public static double marketPotentialFactor = 1.3;
    public static double industrialPotentialFactor = 0.5;
    public static double defenseFactor = 1.0;
    public static double basicAreaFactor = 1.0;

    public static double mountainDefense = 30.0;
    public static double riverDefense = 10.0;

    public static double basicPopulation = 1000.0;
    public static double basicProvinceValue = 100.0;

    public static double maxSeaDistance = 50;
    public static double maxRiverDistance = 50;

    public static double securityBonus = 60;

    public static int basicOccupationAbility = 100;

    public static double basicPopulationIncrease = 5.0;
    public static double basicIndustryIncrease = 5.0;

    public static double lowProgressLevel = 1200.0;
    public static int lowOccupAbility = 0;

    public static double randFactor(int d, int h){
        Random gen = new Random();
        double rand = (double)(gen.nextInt(h-d)+d);
        return rand;
    }
}
