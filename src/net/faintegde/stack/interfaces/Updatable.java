package net.faintegde.stack.interfaces;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public abstract interface Updatable {
  public abstract void update(GameContainer paramGameContainer, int paramInt) throws SlickException;
}