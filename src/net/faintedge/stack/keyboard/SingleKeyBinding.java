package net.faintedge.stack.keyboard;

import org.lwjgl.input.Keyboard;

public class SingleKeyBinding extends KeyBinding {
  private int key;

  public SingleKeyBinding(int key) {
    this.key = key;
  }

  public int getKey() {
    return this.key;
  }

  public boolean isPressed() {
    return Keyboard.isKeyDown(this.key);
  }
}