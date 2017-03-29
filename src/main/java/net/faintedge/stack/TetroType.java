package net.faintedge.stack;

public enum TetroType {
  I, O, T, S, Z, J, L;

  public static TetroType random() {
    return values()[(int) (java.lang.Math.random() * values().length)];
  }
}