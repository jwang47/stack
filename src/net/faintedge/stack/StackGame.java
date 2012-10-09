package net.faintedge.stack;

import net.faintedge.stack.actions.HardDrop;
import net.faintedge.stack.actions.Hold;
import net.faintedge.stack.actions.MoveX;
import net.faintedge.stack.actions.MoveY;
import net.faintedge.stack.actions.Pause;
import net.faintedge.stack.actions.Quit;
import net.faintedge.stack.actions.Restart;
import net.faintedge.stack.actions.RotateLeft;
import net.faintedge.stack.actions.RotateRight;
import net.faintedge.stack.actions.SoftDrop;
import net.faintedge.stack.colorschemes.TGMColorScheme;
import net.faintedge.stack.keyboard.Action;
import net.faintedge.stack.keyboard.KeyBindingManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class StackGame extends BasicGame {
  private Action moveLeft;
  private Action moveRight;
  private Action moveDown;
  private Action rotateLeft;
  private Action rotateRight;
  private Action hardDrop;
  private Action softDrop;
  private Action hold;
  private Action pause;
  private Action restart;
  private Action quit;
  private KeyBindingManager keyBindingManager;
  private Playfield playfield;
  private boolean paused;
  private GameContainer container;
  private ColorScheme colorScheme = new TGMColorScheme();

  public StackGame(String title) {
    super(title);
  }

  public void init(GameContainer c) throws SlickException {
    this.container = c;
    this.playfield = new Playfield(this);
    this.playfield.centerPlayfield(c);
    initKeyBindingManager();
  }

  private void initKeyBindingManager() {
    this.keyBindingManager = new KeyBindingManager();
    this.moveLeft = new MoveX(this.playfield, -1);
    this.moveRight = new MoveX(this.playfield, 1);
    this.moveDown = new MoveY(this.playfield, 1);
    this.rotateLeft = new RotateLeft(this.playfield);
    this.rotateRight = new RotateRight(this.playfield);
    this.hardDrop = new HardDrop(this.playfield);
    this.softDrop = new SoftDrop(this.playfield);
    this.hold = new Hold(this.playfield);
    this.pause = new Pause(this);
    this.restart = new Restart(this);
    this.quit = new Quit(this);

    this.keyBindingManager.bindKey(this.moveLeft, Integer.valueOf(203));
    this.keyBindingManager.bindKey(this.moveRight, Integer.valueOf(205));

    this.keyBindingManager.bindKey(this.rotateLeft, Integer.valueOf(29));
    this.keyBindingManager.bindKey(this.rotateRight, Integer.valueOf(200));
    this.keyBindingManager.bindKey(this.hardDrop, Integer.valueOf(57));
    this.keyBindingManager.bindKey(this.softDrop, Integer.valueOf(208));
    this.keyBindingManager.bindKey(this.hold, Integer.valueOf(44));
    this.keyBindingManager.bindKey(this.pause, Integer.valueOf(25));
    this.keyBindingManager.bindKey(this.restart, Integer.valueOf(19));
    this.keyBindingManager.bindKey(this.quit, Integer.valueOf(16));
  }

  public void update(GameContainer c, int d) throws SlickException {
    this.keyBindingManager.update(d);
    this.playfield.update(c, d);
  }

  public void render(GameContainer c, Graphics g) throws SlickException {
    this.playfield.render(c, g);
    g.setColor(Color.orange);
    g.drawString("DAS delay: " + this.playfield.getDASDelay() + " ms", 10.0F, c.getHeight() - 20);
    g.drawString("DAS: " + this.playfield.getDAS() + " ms", 10.0F, c.getHeight() - 40);
  }

  public void keyPressed(int key, char c) {
  }

  public static void main(String[] argv) {
    try {
      AppGameContainer container = new AppGameContainer(new StackGame("StackGame"), 800, 600, false);
      container.setVSync(true);
      container.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void pause() {
    this.paused = (!this.paused);
  }

  public void restart() {
    this.playfield.restart();
  }
}