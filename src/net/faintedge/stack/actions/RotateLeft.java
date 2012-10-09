package net.faintedge.stack.actions;

import net.faintedge.stack.Playfield;
import net.faintedge.stack.keyboard.Action;

public class RotateLeft extends Action {
  private Playfield playfield;

  public RotateLeft(Playfield playfield) {
    this.playfield = playfield;
  }

  public void update(int d) {
  }

  public void notifyPressed() {
    this.playfield.rotateCurrentPiece(-1);
  }

  public void notifyReleased() {
  }
}