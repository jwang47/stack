package net.faintedge.stack.actions;

import net.faintedge.stack.Playfield;
import net.faintedge.stack.keyboard.Action;

public class HardDrop extends Action {
  private Playfield playfield;

  public HardDrop(Playfield playfield) {
    this.playfield = playfield;
  }

  public void update(int d) {
  }

  public void notifyPressed() {
    this.playfield.hardDrop();
  }

  public void notifyReleased() {
  }
}