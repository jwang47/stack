package net.faintedge.stack;

import java.util.ArrayList;

import net.faintedge.stack.util.SimplePoint;

public class TetroState {
  private ArrayList<SimplePoint> points = new ArrayList<SimplePoint>();

  public TetroState() {
  }

  public TetroState(SimplePoint[] points) {
    for (SimplePoint p : points)
      addPoint(p);
  }

  public void addPoint(SimplePoint p) {
    this.points.add(p);
  }

  public void addPoint(int x, int y) {
    addPoint(new SimplePoint(x, y));
  }

  public ArrayList<SimplePoint> getPoints() {
    return this.points;
  }
}