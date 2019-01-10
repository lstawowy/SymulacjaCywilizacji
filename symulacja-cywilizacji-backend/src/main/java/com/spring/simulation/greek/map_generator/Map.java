package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.enums.AreaType;
import com.spring.simulation.greek.enums.ClimateType;
import com.spring.simulation.greek.enums.MapType;
import com.spring.simulation.greek.enums.ResourceType;

import java.awt.image.BufferedImage;
import java.util.Date;

public class Map {

  public static int height;
  public static int width;
  public static Cell[][] grid;
//  private Date timestamp;

  Map() {
    width = 728;
    height = 420;
    Map.grid = new Cell[height][];
    for (int i = 0; i < height; ++i) {
      Map.grid[i] = new Cell[width];
    }
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        grid[i][j] = new Cell(i, j);
      }
    }
  }

  public void findLandBorder() {
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        if (grid[i][j].getAreaType() == AreaType.SEA) {
          continue;
        }
        if (findNeighbourByAreaType(i, j, AreaType.SEA)) {
          grid[i][j].setAreaType(AreaType.COAST);
          grid[i][j].setDistanceToSea(1);
        }
      }
    }
  }

  private boolean findNeighbourByAreaType(int x, int y, AreaType areaType) {
    for (int i = -1; i < 2; ++i) {
      for (int j = -1; j < 2; ++j) {
        int cx = x + i;
        int cy = y + j;
        if (cx < 0 || cx >= height || cy < 0 || cy >= width) {
          continue;
        }
        if (grid[cx][cy].getAreaType() == areaType) {
          return true;
        }
      }
    }
    return false;
  }

  public void setDistancesToWater() {
    int distance = 0;
    while (distance <100) {
      for (int i = 0; i < height; ++i) {
        for (int j = 0; j < width; ++j) {
          if (grid[i][j].getDistanceToSea() == distance && distance != 0) {
            fillNeighbourhoodDistances(i, j, distance, AreaType.SEA);
          }
          if (grid[i][j].getDistanceToRiver() == distance) {
            fillNeighbourhoodDistances(i, j, distance, AreaType.RIVER);
          }
        }
      }
      ++distance;
    }
  }

  private void fillNeighbourhoodDistances(int x, int y, int distance, AreaType areaType) {
    for (int i = -1; i < 2; ++i) {
      for (int j = -1; j < 2; ++j) {
        int cx = x + i;
        int cy = y + j;
        if (cx < 0 || cx >= height || cy < 0 || cy >= width || (cx == x && cy == y)) {
          continue;
        }
        if(grid[cx][cy].getAreaType() == AreaType.SEA)   //nie potrzeba odleglosci punktu na morzu od rzeki
          continue;
        if (grid[cx][cy].getWaterDistance(areaType) > (distance + 1)) {
          grid[cx][cy].setWaterDistace(distance + 1, areaType);
        }
      }
    }
  }

  ///////////////////////////////////////// Rysowanie map //////////////////////////////////////////////////

  public void readMap(BufferedImage map, MapType maptype) {
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        int pixelColor = map.getRGB(j, i);
        grid[i][j].setColor(pixelToGrayscale(pixelColor));
        readColorByMapType(grid[i][j], maptype);
      }
    }
  }

  public void readNaturalResourcesMap(BufferedImage map, ResourceType resource){
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        int pixelColor = map.getRGB(j, i);
        if(grid[i][j].getAreaType() !=AreaType.SEA && (pixelColor & 0xFFFF) == 0xE00) {
          grid[i][j].setResource(resource);
          grid[i][j].setColor(pixelColor);
        }
      }
    }
  }

  private int pixelToGrayscale(int color) {
    int p = color;
    int a = (p >> 24) & 0xff;
    int r = (p >> 16) & 0xff;
    int g = (p >> 8) & 0xff;
    int b = p & 0xff;
    int avg = (r + g + b) / 3;
    p = (a << 24) | (avg << 16) | (avg << 8) | avg;
    return p;
  }

  public void readColorByMapType(Cell cell, MapType maptype) {
    if (maptype == MapType.MOUNTAIN_MAP) {
      cell.readColorByMountainMap();
    } else if (maptype == MapType.RIVER_MAP) {
      cell.readColorByRiverMap();
    } else if(maptype == MapType.CLIMATE_MAP){
      cell.readColorByClimatMap();
    }
  }

  private void setColorsByArea() {
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        Cell cell = grid[i][j];
        AreaType area = cell.getAreaType();
        if (area == AreaType.SEA) {
          cell.setColor(0x42CDFF);
        }
        else if (area == AreaType.LAND) {
          cell.setColor(0x00F274);
        }
        else if(area == AreaType.MOUNTAIN)
          cell.setColor(0xF20012);
        else if (area == AreaType.RIVER) {
          cell.setColor(0x0002F7);
        }
        else if (area == AreaType.COAST) {
          cell.setColor(0x0);
        }
        if (cell.getDistanceToRiver() == 3) {
          cell.setColor(0xFF7A06);
        }
                else
                    cell.setColor(0xFF);
      }
    }
  }

  private void setColorsByClimate(){
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        Cell cell = grid[i][j];
        ClimateType clim = cell.getClimateType();
        if(clim == ClimateType.NONE)
          cell.setColor(0xFFFFFF);
        else if (clim == ClimateType.MARINE)
          cell.setColor(0x4099F4);
        else if (clim == ClimateType.CONTINENTAL)
          cell.setColor(0x003974);
        else if (clim == ClimateType.MOUNTAIN)
          cell.setColor(0x6E0500);
        else if (clim == ClimateType.STEPPE)
          cell.setColor(0xB77A07);
        else if (clim == ClimateType.SUBTROPICAL)
          cell.setColor(0x0FB707);
        else if (clim == ClimateType.MEDITERRANEAN)
          cell.setColor(0xFFF629);
        else
          cell.setColor(0xD20B96);
      }
    }
  }

  //na razie tylko do kontroli
  public void drawMap() {
//    setColorsByArea();
//    setColorsByClimate();
    BufferedImage map = new BufferedImage(width, height, 5);
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        map.setRGB(j, i, grid[i][j].getColor());
      }
    }
    MapReader.saveImageInResources(map);

  }
}
