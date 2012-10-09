package net.faintedge.stack.colorschemes;

import net.faintedge.stack.ColorScheme;
import net.faintedge.stack.TetroType;

import org.newdawn.slick.Color;

public class TGMColorScheme extends ColorScheme {
  public void init() {
    add(TetroType.I, new Color(0, 255, 255));
    add(TetroType.J, new Color(0, 0, 255));
    add(TetroType.L, new Color(255, 170, 0));
    add(TetroType.O, new Color(255, 255, 0));
    add(TetroType.S, new Color(0, 255, 0));
    add(TetroType.T, new Color(153, 0, 255));
    add(TetroType.Z, new Color(255, 0, 0));
  }

  public Color getGhostColor() {
    return Color.gray;
  }
}