package net.faintedge.stack.colorschemes;

import net.faintedge.stack.ColorScheme;
import net.faintedge.stack.TetroType;
import org.newdawn.slick.Color;

public class LightColorScheme extends ColorScheme {
  public void init() {
    add(TetroType.I, new Color(179, 255, 255));
    add(TetroType.J, new Color(153, 204, 255));
    add(TetroType.L, new Color(255, 217, 179));
    add(TetroType.O, new Color(255, 255, 153));
    add(TetroType.S, new Color(179, 255, 179));
    add(TetroType.T, new Color(217, 179, 255));
    add(TetroType.Z, new Color(255, 179, 209));
  }

  public Color getGhostColor() {
    return Color.gray;
  }
}