package net.faintedge.stack;

public abstract interface GameSettings
{
  public abstract float getGravity();

  public abstract float getDAS();

  public abstract float getLineClearDelay();

  public abstract float getLockDelay();
}