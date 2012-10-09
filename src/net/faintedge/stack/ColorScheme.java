package net.faintedge.stack;

import java.util.HashMap;
import org.newdawn.slick.Color;

public abstract class ColorScheme {
  private final HashMap<TetroType, Color> colors = new HashMap<TetroType, Color>();

  public ColorScheme() {
    init();
  }

  public abstract void init();

  public void add(TetroType t, Color c) {
    this.colors.put(t, c);
  }

  public Color getColor(TetroType t) {
    return (Color) this.colors.get(t);
  }

  public abstract Color getGhostColor();
}