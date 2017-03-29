package net.faintedge.stack.keyboard;

public abstract class Action {
  private boolean active = false;

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isActive() {
    return this.active;
  }

  public abstract void update(int paramInt);

  public abstract void notifyReleased();

  public abstract void notifyPressed();
}