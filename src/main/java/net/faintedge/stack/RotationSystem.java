package net.faintedge.stack;

import java.util.HashMap;

import net.faintedge.stack.util.SimplePoint;

public abstract class RotationSystem {
  private HashMap<TetroType, TetroState[]> pieces = new HashMap<TetroType, TetroState[]>();

  public RotationSystem() {
    init();
  }

  public TetroState[] getTetroStates(TetroType t) {
    return (TetroState[]) this.pieces.get(t);
  }
  
  public abstract SimplePoint[] getWallkickOffsets(TetroType t, int oldRotation, int newRotation);
  protected abstract void init();

  protected void add(TetroType t, TetroState[] s) {
    this.pieces.put(t, s);
  }
}