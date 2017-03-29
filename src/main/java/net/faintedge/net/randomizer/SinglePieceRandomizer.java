package net.faintedge.net.randomizer;

import net.faintedge.stack.Randomizer;
import net.faintedge.stack.TetroType;

public class SinglePieceRandomizer extends Randomizer {
  private TetroType type;

  public SinglePieceRandomizer(TetroType t) {
    this.type = t;
  }

  public TetroType nextPiece() {
    return this.type;
  }
}