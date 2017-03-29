package net.faintedge.stack.interfaces;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract interface Renderable {
  public abstract void render(GameContainer paramGameContainer, Graphics paramGraphics) throws SlickException;
}