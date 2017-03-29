package net.faintedge.stack.actions;

import net.faintedge.stack.StackGame;
import net.faintedge.stack.keyboard.Action;

public class Quit extends Action {
  private StackGame game;

  public Quit(StackGame game) {
    this.game = game;
  }

  public void update(int d) {
    this.game.pause();
  }

  public void notifyPressed() {
  }

  public void notifyReleased() {
  }
}