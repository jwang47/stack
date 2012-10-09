package net.faintedge.stack.actions;

import net.faintedge.stack.Playfield;
import net.faintedge.stack.keyboard.Action;

public class RotateRight extends Action {
  private Playfield playfield;

  public RotateRight(Playfield playfield) {
    this.playfield = playfield;
  }

  public void update(int d) {
  }

  public void notifyPressed() {
    this.playfield.rotateCurrentPiece(1);
  }

  public void notifyReleased() {
  }
}