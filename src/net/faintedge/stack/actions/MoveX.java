package net.faintedge.stack.actions;

import net.faintedge.stack.Playfield;
import net.faintedge.stack.Timer;
import net.faintedge.stack.keyboard.Action;

public class MoveX extends Action {
  private Timer timer;
  private Playfield playfield;
  private boolean doneDelay = false;
  private int offset;

  public MoveX(Playfield playfield, int offset) {
    this.playfield = playfield;
    this.offset = offset;
    this.timer = new Timer();
  }

  public void update(int d) {
    if (!this.doneDelay) {
      if (this.timer.getDifference() > this.playfield.getDASDelay()) {
        this.playfield.movePieceX(this.offset);
        this.timer.click();
        this.doneDelay = true;
      }
    } else if (this.timer.getDifference() > this.playfield.getDAS()) {
      this.playfield.movePieceX(this.offset);
      this.timer.click();
    }
  }

  public void notifyPressed() {
    this.playfield.movePieceX(this.offset);
    this.timer.click();
    this.doneDelay = false;
  }

  public void notifyReleased() {
  }
}