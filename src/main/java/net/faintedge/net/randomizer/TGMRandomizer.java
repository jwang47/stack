package net.faintedge.net.randomizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import net.faintedge.stack.Randomizer;
import net.faintedge.stack.TetroType;

public class TGMRandomizer extends Randomizer {

  private int tries = 6;
  private Queue<TetroType> history = new LinkedList<TetroType>();
  private boolean firstTime = true;

  private ArrayList<TetroType> bag = new ArrayList<TetroType>();

  public TGMRandomizer() {
    this.history.add(TetroType.Z);
    this.history.add(TetroType.S);
    this.history.add(TetroType.Z);
    this.history.add(TetroType.S);
    for (TetroType t : TetroType.values()) {
      this.bag.add(t);
    }
    this.bag.remove(TetroType.S);
    this.bag.remove(TetroType.Z);
    this.bag.remove(TetroType.O);
  }

  public TetroType nextPiece() {
    TetroType candidate = null;
    if (this.firstTime) {
      candidate = (TetroType) this.bag.get((int) (Math.random() * this.bag.size()));
      this.firstTime = false;
    } else {
      for (int i = 0; i < this.tries; i++) {
        candidate = TetroType.random();
        if (!this.history.contains(candidate)) {
          break;
        }
      }
    }
    this.history.poll();
    this.history.add(candidate);
    return candidate;
  }
}