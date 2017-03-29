package net.faintedge.stack;

public class Timer {
  private long startTime;

  public Timer() {
    this.startTime = 0L;
  }

  public long getDifference() {
    long difference = System.currentTimeMillis() - this.startTime;
    return difference;
  }

  public long click() {
    long difference = System.currentTimeMillis() - this.startTime;
    this.startTime = System.currentTimeMillis();
    return difference;
  }
}