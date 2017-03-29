package net.faintedge.stack.actions;

import net.faintedge.stack.Playfield;
import net.faintedge.stack.keyboard.Action;

public class Hold extends Action {
  private Playfield playfield;

  public Hold(Playfield playfield) {
    this.playfield = playfield;
  }

  public void update(int d) {
  }

  public void notifyPressed() {
    this.playfield.hold();
  }

  public void notifyReleased() {
  }
}