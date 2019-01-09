package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.map_generator.Cell;
import java.util.Objects;
import javax.imageio.ImageIO;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;

public class Map {

  public enum MapType {
    MOUNTAIN_MAP,
    RIVER_MAP
  }

  private int height;
  private int width;
  private Cell[][] grid;

  Map() {
    width = 728;
    height = 420;
    this.grid = new Cell[height][];
    for (int i = 0; i < height; ++i) {
      this.grid[i] = new Cell[width];
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
        if (grid[i][j].getAreaType() == Cell.AreaType.SEA) {
          continue;
        }
        if (findNeighbourByAreaType(i, j, Cell.AreaType.SEA)) {
          grid[i][j].setAreaType(Cell.AreaType.COAST);
          grid[i][j].setDistanceToSea(1);
        }
      }
    }
  }

  private boolean findNeighbourByAreaType(int x, int y, Cell.AreaType areaType) {
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
            fillNeighbourhoodDistances(i, j, distance, Cell.AreaType.SEA);
          }
          if (grid[i][j].getDistanceToRiver() == distance) {
            fillNeighbourhoodDistances(i, j, distance, Cell.AreaType.RIVER);
          }
        }
      }
      ++distance;
    }
  }

  private void fillNeighbourhoodDistances(int x, int y, int distance, Cell.AreaType areaType) {
    for (int i = -1; i < 2; ++i) {
      for (int j = -1; j < 2; ++j) {
        int cx = x + i;
        int cy = y + j;
        if (cx < 0 || cx >= height || cy < 0 || cy >= width || (cx == x && cy == y)) {
          continue;
        }
        if(grid[cx][cy].getAreaType() == Cell.AreaType.SEA)   //nie potrzeba odleglosci punktu na morzu od rzeki
          continue;
        if (grid[cx][cy].getWaterDistance(areaType) > (distance + 1)) {
          grid[cx][cy].setWaterDistace(distance + 1, areaType);
        }
      }
    }
  }

  //Rysowanie map

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
    }
  }

  private void setColorsByArea() {
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        Cell cell = grid[i][j];
        Cell.AreaType area = cell.getAreaType();
        if (area == Cell.AreaType.SEA) {
          cell.setColor(0x42CDFF);
        }
        else if (area == Cell.AreaType.LAND) {
          cell.setColor(0x00F274);
        }
//                else if(area == Cell.AreaType.MOUNTAIN)
//                    cell.setColor(0xF20012);
        else if (area == Cell.AreaType.RIVER) {
          cell.setColor(0x0002F7);
        }
        else if (area == Cell.AreaType.COAST) {
          cell.setColor(0x0);
        }
        if (cell.getDistanceToRiver() == 3) {
          cell.setColor(0xFF7A06);
        }
//                else
//                    cell.setColor(0xFF);
      }
    }
  }

  public void readMap(BufferedImage map, MapType maptype) {
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        int pixelColor = map.getRGB(j, i);
        grid[i][j].setColor(pixelToGrayscale(pixelColor));
        readColorByMapType(grid[i][j], maptype);
      }
    }
  }

  //na razie tylko do kontroli
  public void drawMap() {
    setColorsByArea();
    BufferedImage map = new BufferedImage(width, height,5); //ustalic jaki typ najlepszy TYPE_BYTE_GRAY
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        map.setRGB(j, i, grid[i][j].getColor());
      }
    }
    try {
      File outputfile = new File("newmap.gif");
      ImageIO.write(map, "gif", outputfile);
    } catch (IOException e) {
      System.out.print(e);
    }

  }
}
