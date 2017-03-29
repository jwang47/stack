package net.faintedge.stack.interfaces.stack.test;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;

public class KeyboardTest extends BasicGame {
  public KeyboardTest(String title) {
    super(title);
  }

  public void init(GameContainer container) throws SlickException {
  }

  public void update(GameContainer container, int delta) throws SlickException {
  }

  public void render(GameContainer container, Graphics g) throws SlickException {
    if (Keyboard.isKeyDown(13)) {
      g.setColor(Color.green);
      g.drawRect(100.0F, 100.0F, 10.0F, 10.0F);
    } else {
      g.setColor(Color.darkGray);
      g.drawRect(100.0F, 100.0F, 10.0F, 10.0F);
    }
  }

  public void keyPressed(int key, char c) {
    System.out.println(key + ", " + c);
  }

  public static void main(String[] argv) {
    try {
      AppGameContainer container = new AppGameContainer(new KeyboardTest("KeyboardTest"), 800, 600, false);
      container.setVSync(true);
      container.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}