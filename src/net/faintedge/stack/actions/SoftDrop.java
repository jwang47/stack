package net.faintedge.stack.actions;

import net.faintedge.stack.Playfield;
import net.faintedge.stack.keyboard.Action;

public class SoftDrop extends Action {
  private Playfield playfield;

  public SoftDrop(Playfield playfield) {
    this.playfield = playfield;
  }

  public void update(int d) {
    this.playfield.softDrop();
  }

  public void notifyPressed() {
  }

  public void notifyReleased() {
  }
}