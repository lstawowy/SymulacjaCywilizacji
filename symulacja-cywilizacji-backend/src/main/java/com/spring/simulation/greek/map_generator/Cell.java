package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.Simulation.Country;
import com.spring.simulation.greek.enums.*;

public class Cell {

  private int x;
  private int y;
  private int color;

  private Country country;
  private AreaType areaType;
  private ClimateType climateType;
  private int fertility;  //zyznosc gleby, produktywnosc w procentach
  private int distanceToSea;
  private int distanceToRiver;

  //Surowce naturalne
  private boolean iron;
  private boolean copper;
  private boolean coal;
  private boolean lead;

  private double population;

  private double areaFriendlinessFactor;
  private double provinceValue;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    color = 0;
    distanceToRiver = Integer.MAX_VALUE;
    distanceToSea = Integer.MAX_VALUE;
    iron = false;
    copper = false;
    coal = false;
    lead = false;
  }

  //zyznosc bedzie ustalana proporcjonalnie do wysokosci n.p.m.
  private void countFertility(int c) {
    if (c > 220) {
      fertility = 100;
    } else if (c <= 220 && c >= 165) {
      int range = 500;
      fertility = (c / range) * 100;
    } else {
      areaType = AreaType.MOUNTAIN;
      fertility = 0;
    }
  }

  public void readColorByMountainMap() {
    int c = color & 0xFF;
    if (c > 240) {
      areaType = AreaType.SEA;
      fertility = 0;
      distanceToSea = 0;
    } else {
      areaType = AreaType.LAND;
      countFertility(c);
    }
  }

  public void readColorByRiverMap() {
    int c = color & 0xFF;
    if (c < 200) {
      areaType = AreaType.RIVER;
      fertility = 80;
      distanceToRiver = 1;
    }
  }

  public void readColorByClimatMap(){
    int c = color & 0xFF;
    if(areaType == AreaType.SEA){
      climateType = ClimateType.NONE;
    } else if(c == 59){
      climateType = ClimateType.MARINE;
    } else if(c == 255){
      climateType = ClimateType.CONTINENTAL;
    } else if(c == 126){
      climateType = ClimateType.STEPPE;
    } else if(c == 0){
      climateType = ClimateType.SUBTROPICAL;
    } else if(c == 171){
      climateType = ClimateType.MEDITERRANEAN;
    } else{
      climateType = ClimateType.MOUNTAIN;
    }
  }

  public void setWaterDistace(int distance, AreaType areaType) {
    if (areaType == AreaType.SEA) {
      setDistanceToSea(distance);
    } else {
      setDistanceToRiver(distance);
    }
  }

  public int getWaterDistance(AreaType areaType) {
    if (areaType == AreaType.SEA) {
      return distanceToSea;
    } else {
      return distanceToRiver;
    }
  }

  public void setResource(ResourceType resource){
    if(resource == ResourceType.IRON)
      setIron(true);
    else if(resource == ResourceType.COPPER)
      setCopper(true);
    else if(resource == ResourceType.COAL)
      setCopper(true);
    else if(resource == ResourceType.LEAD)
      setLead(true);
  }

  public void countAreaFriendlinessFactor(){
    areaFriendlinessFactor = getClimateFactor()*getAreaFactor();
  }

  public void countPopulation(){
    population = Score.basicPopulation * areaFriendlinessFactor;
  }

  public void evaluateProvince(){
    provinceValue = Score.basicProvinceValue * areaFriendlinessFactor;
  }
  ////////////////////////////////////// czynniki atrakcyjnosci terenu/////////////////////////

  public double getClimateFactor(){
    switch(climateType){
      case MARINE:
        return Score.marineFactor;
      case STEPPE:
        return Score.steppeFactor;
      case MOUNTAIN:
        return Score.mountainClimateFactor;
      case CONTINENTAL:
        return Score.continentalFactor;
      case SUBTROPICAL:
        return Score.subtropicalFactor;
      case MEDITERRANEAN:
        return Score.mediterraneanFactor;
      default:
        return Score.defaultFactor;
    }
  }

  public double getAreaFactor(){
    switch(areaType){
      case LAND:
        return Score.landFactor;
      case COAST:
        return Score.coastFactor;
      case RIVER:
        return Score.riverFactor;
      case MOUNTAIN:
        return Score.mountainAreaFactor;
      default:
        return Score.defaultFactor;
    }
  }
  ////////////////////////////////////// Settery, gettery /////////////////////////////////////
  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public AreaType getAreaType() {
    return areaType;
  }

  protected void setAreaType(AreaType areaType) {
    this.areaType = areaType;
  }

  public int getDistanceToSea() {
    return distanceToSea;
  }

  protected void setDistanceToSea(int distanceToSea) {
    this.distanceToSea = distanceToSea;
  }

  public int getDistanceToRiver() {
    return distanceToRiver;
  }

  protected void setDistanceToRiver(int distanceToRiver) {
    this.distanceToRiver = distanceToRiver;
  }

  public ClimateType getClimateType() {
    return climateType;
  }

  public boolean isIron() {
    return iron;
  }

  protected void setIron(boolean iron) {
    this.iron = iron;
  }

  public boolean isCopper() {
    return copper;
  }

  protected void setCopper(boolean copper) {
    this.copper = copper;
  }

  public boolean isCoal() {
    return coal;
  }

  protected void setCoal(boolean coal) {
    this.coal = coal;
  }

  public boolean isLead() {
    return lead;
  }

  protected void setLead(boolean lead) {
    this.lead = lead;
  }

  public int getFertility() {
    return fertility;
  }

  protected void setFertility(int fertility) {
    this.fertility = fertility;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public double getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public Country getCountry() {
    return country;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public double getProvinceValue() {
    return provinceValue;
  }
}
