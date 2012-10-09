package net.faintedge.stack.util;

public class SimplePoint {
  private int x;
  private int y;

  public SimplePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return this.y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setPosition(int i, int j) {
    setX(i);
    setY(j);
  }
}