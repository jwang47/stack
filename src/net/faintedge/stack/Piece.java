package net.faintedge.stack;

public class Piece {
  private TetroType type;

  public Piece(TetroType type) {
    this.type = type;
  }

  public TetroType getType() {
    return this.type;
  }
}