package net.faintedge.stack.interfaces.stack.tool;

import net.faintedge.stack.util.SimplePoint;
import net.faintedge.stack.util.Stuff;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class TetroStateTool extends BasicGame {
  private int blockWidth = 20;
  private int blockHeight = 20;
  private SimplePoint center = new SimplePoint(0, 0);
  private ArrayList<SimplePoint> points = new ArrayList<SimplePoint>();

  public TetroStateTool(String title) {
    super(title);
  }

  public static void main(String[] argv) {
    try {
      AppGameContainer container = new AppGameContainer(new TetroStateTool("TetroStateTool"), 800, 600, false);
      container.setVSync(true);
      container.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void init(GameContainer container) throws SlickException {
    this.center.setX(container.getWidth() / this.blockWidth / 2);
    this.center.setY(container.getHeight() / this.blockHeight / 2);
  }

  public void update(GameContainer container, int delta) throws SlickException {
  }

  public void render(GameContainer container, Graphics g) throws SlickException {
    g.setColor(Color.darkGray);
    int j;
    for (int i = 0; i < container.getWidth() / this.blockWidth; i++) {
      for (j = 0; j < container.getHeight() / this.blockHeight; j++) {
        g.drawRect(i * this.blockWidth, j * this.blockHeight, this.blockWidth, this.blockHeight);
      }
    }
    g.setColor(Color.white);
    for (SimplePoint p : this.points) {
      g.drawRect(p.getX() * this.blockWidth, p.getY() * this.blockHeight, this.blockWidth, this.blockHeight);
    }
    g.setColor(Color.orange);
    if (this.center != null) {
      float multiplier = 0.5F;
      g.fillRect(this.center.getX() * this.blockWidth + this.blockWidth * (1.0F - multiplier) / 2.0F, this.center.getY() * this.blockHeight + this.blockHeight
          * (1.0F - multiplier) / 2.0F, this.blockWidth * multiplier, this.blockHeight * multiplier);
    }
  }

  public void mousePressed(int button, int x, int y) {
    int i = x / this.blockWidth;
    int j = y / this.blockHeight;
    if (Keyboard.isKeyDown(16)) {
      this.center.setPosition(i, j);
    } else {
      SimplePoint remove = null;
      for (SimplePoint p : this.points) {
        if ((p.getX() != i) || (p.getY() != j))
          continue;
        remove = p;
      }
      if (remove == null)
        this.points.add(new SimplePoint(i, j));
      else
        this.points.remove(remove);
    }
  }

  public void keyPressed(int key, char c) {
    if (key == 17) {
      this.points.clear();
    } else if (key == 18) {
      String toCopy = "";
      for (SimplePoint p : this.points) {
        toCopy = toCopy + "state.addPoint(" + (p.getX() - this.center.getX()) + ", " + (p.getY() - this.center.getY()) + ");\n";
      }
      Stuff.setClipboardContents(toCopy);
    }
  }
}
