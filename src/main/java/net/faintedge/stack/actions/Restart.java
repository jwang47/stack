package net.faintedge.stack.actions;

import net.faintedge.stack.StackGame;
import net.faintedge.stack.keyboard.Action;

public class Restart extends Action {
  private StackGame game;

  public Restart(StackGame game) {
    this.game = game;
  }

  public void update(int d) {
  }

  public void notifyPressed() {
    this.game.restart();
  }

  public void notifyReleased() {
  }

}