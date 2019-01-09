package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.enums.ClimateType;
import com.spring.simulation.greek.enums.CountryType;
import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.enums.ResourceType;

public class Cell {

  // Tu beda wszystkie parametry dla danej komorki, z ktorych bedziemy kiedys wyliczac czy dany teren jest korzystny dla
  //rozwoju czy tez nie
  private int x;
  private int y;
  private int color;

  private CountryType country;
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

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    color = 0;
    distanceToRiver = Integer.MAX_VALUE;
    distanceToSea = Integer.MAX_VALUE;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public AreaType getAreaType() {
    return areaType;
  }

  public void setAreaType(AreaType areaType) {
    this.areaType = areaType;
  }

  public int getDistanceToSea() {
    return distanceToSea;
  }

  public void setDistanceToSea(int distanceToSea) {
    this.distanceToSea = distanceToSea;
  }

  public int getDistanceToRiver() {
    return distanceToRiver;
  }

  public void setDistanceToRiver(int distanceToRiver) {
    this.distanceToRiver = distanceToRiver;
  }

  public ClimateType getClimateType() {
    return climateType;
  }

  public boolean isIron() {
    return iron;
  }

  public void setIron(boolean iron) {
    this.iron = iron;
  }

  public boolean isCopper() {
    return copper;
  }

  public void setCopper(boolean copper) {
    this.copper = copper;
  }

  public boolean isCoal() {
    return coal;
  }

  public void setCoal(boolean coal) {
    this.coal = coal;
  }

  public boolean isLead() {
    return lead;
  }

  public void setLead(boolean lead) {
    this.lead = lead;
  }

  public int getFertility() {
    return fertility;
  }

  public void setFertility(int fertility) {
    this.fertility = fertility;
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

  //zyznosc bedzie ustalana proporcjonalnie do wysokosci n.p.m.
  private void countFertility(int c) {
    if (c > 220) {
      fertility = 100;
    } else if (c <= 220 && c >= 165) {  //mozna jeszcze podniesc dolny zakres
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
      fertility = 0;  //rozwazyc zmiane!!!!!
      distanceToRiver = 0;
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
}
