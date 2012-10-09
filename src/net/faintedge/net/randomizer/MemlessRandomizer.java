package net.faintedge.net.randomizer;

import net.faintedge.stack.Randomizer;
import net.faintedge.stack.TetroType;

public class MemlessRandomizer extends Randomizer {
  public TetroType nextPiece() {
    return TetroType.random();
  }
}