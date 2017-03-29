package net.faintedge.stack;

import java.util.LinkedList;
import java.util.Queue;

import net.faintedge.net.randomizer.TGMRandomizer;
import net.faintedge.stack.colorschemes.TGMColorScheme;
import net.faintedge.stack.rotationsystems.SuperRotationSystem;
import net.faintedge.stack.util.SimplePoint;
import net.faintedge.stack.interfaces.Renderable;
import net.faintedge.stack.interfaces.Updatable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Playfield implements Renderable, Updatable {
  public static final int DEFAULT_WIDTH = 10;
  public static final int DEFAULT_HEIGHT = 24;
  public static final int DEFAULT_BLOCK_WIDTH = 20;
  public static final int DEFAULT_BLOCK_HEIGHT = 20;
  private int ceiling = 3;
  private float blockWidth = 20.0F;
  private float blockHeight = 20.0F;
  private float x;
  private float y;
  private Piece[][] field;
  private int currentRotation;
  private int pieceX;
  private int pieceY;
  private Piece currentPiece;
  private Piece heldPiece;
  private StackGame game;
  private RotationSystem rotationSystem;
  private ColorScheme colorScheme;
  private Randomizer randomizer;
  private Queue<Piece> pieceQueue = new LinkedList<Piece>();
  private int ghostY;

  public Playfield(StackGame game) {
    this(game, 10, 24);
  }

  public Playfield(StackGame game, int width, int height) {
    this.game = game;
    System.out.println("shiftGravity: " + getDASDelay());
    System.out.println("DAS: " + getDAS());
    this.field = new Piece[width][height];
    this.rotationSystem = new SuperRotationSystem();
    this.colorScheme = new TGMColorScheme();
    this.randomizer = new TGMRandomizer();
    for (int i = 0; i < 3; i++) {
      this.pieceQueue.add(new Piece(this.randomizer.nextPiece()));
    }
    nextPiece();
  }

  public void nextPiece() {
    if (this.currentPiece != null) {
      int linesCleared = checkLineClear();
      if ((linesCleared > 0) && (this.currentPiece.getType() == TetroType.T)) {
        if (linesCleared == 2) {
          System.out.println("TSPIN DOUBLE");
        } else if (linesCleared == 3) {
          System.out.println("TSPIN TRIPLE");
        }
      }
    }

    this.currentPiece = ((Piece) this.pieceQueue.poll());
    this.pieceX = getStartX();
    this.pieceY = getStartY();
    this.currentRotation = 0;
    this.pieceQueue.add(new Piece(this.randomizer.nextPiece()));
    setPiece(this.currentPiece, this.pieceX, this.pieceY, this.currentRotation, false);
    determineGhost();
  }

  public boolean setPiece(Piece piece, int x, int y, int rotation, boolean falling) {
    TetroState[] states = this.rotationSystem.getTetroStates(piece.getType());
    TetroState state = states[rotation];
    for (SimplePoint p : state.getPoints()) {
      // out of bounds?
      if (outOfBounds(x + p.getX(), y + p.getY())) {
        if (falling && hitBottom(y + p.getY())) {
          nextPiece();
        }
        return false;
      }

      // conflicting with another piece?
      Piece set = this.field[(x + p.getX())][(y + p.getY())];
      if ((set != null) && (set != this.currentPiece)) {
        if (falling) {
          nextPiece();
        }
        return false;
      }
    }

    // unset the old piece
    deletePiece(piece);

    // we're good; actually set the piece now
    for (SimplePoint p : state.getPoints()) {
      this.field[(x + p.getX())][(y + p.getY())] = piece;
    }
    return true;
  }

  private void determineGhost() {
    boolean hitBottom = false;
    SimplePoint bottomPoint = null;
    for (int j = this.pieceY; j < this.field[0].length; j++) {
      TetroState state = this.rotationSystem.getTetroStates(this.currentPiece.getType())[this.currentRotation];
      for (SimplePoint p : state.getPoints()) {
        if (j + p.getY() >= this.field[0].length - 1) {
          bottomPoint = p;
          hitBottom = true;
        }
        Piece piece = this.field[(this.pieceX + p.getX())][(j + p.getY())];
        if ((piece != null) && (piece != this.currentPiece)) {
          this.ghostY = (j - 1);
          return;
        }
      }
      if (hitBottom) {
        this.ghostY = (this.field[0].length - 1 - bottomPoint.getY());
        return;
      }
    }
  }

  private int getStartX() {
    return this.field.length / 2 - 1;
  }

  private int getStartY() {
    return this.ceiling;
  }

  private int checkLineClear() {
    int lineClearCount = 0;
    int index = 0;
    int[] lineCleared = new int[this.field[0].length];

    int minY = this.field[0].length;
    int maxY = 0;
    TetroState[] states = this.rotationSystem.getTetroStates(this.currentPiece.getType());
    TetroState state = states[this.currentRotation];
    for (SimplePoint p : state.getPoints()) {
      if (p.getY() < minY)
        minY = p.getY();
      if (p.getY() <= maxY)
        continue;
      maxY = p.getY();
    }

    for (int j = this.field[0].length - 1; j > 0; j--) {
      boolean cleared = true;
      for (int i = 0; i < this.field.length; i++) {
        if (this.field[i][j] == null) {
          cleared = false;
          break;
        }
      }
      if (cleared) {
        lineCleared[(index++)] = j;
        lineClearCount++;
      }

    }

    for (int i = 0; i < lineCleared.length; i++) {
      if (lineCleared[i] == 0)
        continue;
      for (int j = lineCleared[i]; j > 0; j--) {
        for (int x = 0; x < this.field.length; x++) {
          if (this.field[x][(j - 1)] == null)
            this.field[x][j] = null;
          else {
            this.field[x][j] = this.field[x][(j - 1)];
          }
        }
      }
      for (int p = 0; p < lineCleared.length; p++) {
        if (lineCleared[p] == 0)
          continue;
        lineCleared[p] += 1;
      }

    }

    return lineClearCount;
  }

  public void printArray(int[] array) {
    System.out.print("[ ");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("]");
  }

  public Piece[][] copyField() {
    Piece[][] newField = new Piece[this.field.length][this.field[0].length];
    for (int i = 0; i < newField.length; i++) {
      for (int j = 0; j < newField[i].length; j++) {
        newField[i][j] = this.field[i][j];
      }
    }
    return newField;
  }

  public void printField() {
    System.out.println("...");
    for (int j = 0; j < this.field[0].length; j++) {
      for (int i = 0; i < this.field.length; i++) {
        if (this.field[i][j] != null) {
          System.out.print(this.field[i][j].getType().name() + " ");
        } else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }
    System.out.println("...");
  }

  public void deletePiece(Piece piece) {
    for (int i = 0; i < this.field.length; i++)
      for (int j = 0; j < this.field[i].length; j++) {
        if (this.field[i][j] != piece)
          continue;
        this.field[i][j] = null;
      }
  }

  public boolean rotateCurrentPiece(int rotationDifference) {
    Piece piece = currentPiece;
    int rotation = currentRotation;
    int newRotation = (rotation + rotationDifference + 4) % 4;
    System.out.println(rotation + " -> " + newRotation);

    // first try rotating without wallkick
    if (setPiece(piece, pieceX, pieceY, newRotation, false)) {
      currentRotation = newRotation;
      determineGhost();
      return true;
    }

    SimplePoint[] wallkickOffsets = rotationSystem.getWallkickOffsets(piece.getType(), rotation, newRotation);
    if (wallkickOffsets == null) {
      return false;
    }

    for (SimplePoint wallkickOffset : wallkickOffsets) {
      if (setPiece(piece, pieceX + wallkickOffset.getX(), pieceY + wallkickOffset.getY(), newRotation, false)) {
        pieceX = pieceX + wallkickOffset.getX();
        pieceY = pieceY + wallkickOffset.getY();
        currentRotation = newRotation;
        determineGhost();
        return true;
      }
    }

    return false;
  }

  public void restart() {
    this.randomizer = new TGMRandomizer();
    this.pieceQueue.clear();
    for (int i = 0; i < 3; i++) {
      this.pieceQueue.add(new Piece(this.randomizer.nextPiece()));
    }
    this.heldPiece = null;
    this.field = new Piece[this.field.length][this.field[0].length];
    nextPiece();
  }

  public boolean outOfBounds(int x, int y) {
    return (x < 0) || (x >= this.field.length) || (y < 0) || (y >= this.field[x].length);
  }

  public boolean hitBottom(int y) {
    return y >= this.field[0].length;
  }

  public void render(GameContainer c, Graphics g) throws SlickException {
    renderGhost(c, g);
    renderField(c, g);
    renderHoldBox(c, g);
    renderPieceQueue(c, g);
  }

  private void renderGhost(GameContainer c, Graphics g) {
    if (this.currentPiece != null) {
      TetroState[] states = this.rotationSystem.getTetroStates(this.currentPiece.getType());
      TetroState state = states[this.currentRotation];
      for (SimplePoint p : state.getPoints()) {
        g.setColor(this.colorScheme.getGhostColor());
        g.fillRect(this.x + this.blockWidth * (p.getX() + this.pieceX), this.y + this.blockHeight * (p.getY() + this.ghostY), this.blockWidth, this.blockHeight);
      }
    }
  }

  private void renderPieceQueue(GameContainer c, Graphics g) {
    float queueX = this.x + this.field.length * this.blockWidth;
    float queueY = this.y + this.blockHeight * this.ceiling;
    float halfWidth = this.blockWidth * 0.5F;
    float halfHeight = this.blockHeight * 0.5F;
    float boxWidth = 60.0F;
    int i = 0;

    for (Piece piece : pieceQueue) {
      g.setColor(Color.darkGray);
      g.drawRect(queueX, queueY + i * boxWidth, boxWidth, boxWidth);
      TetroState state = this.rotationSystem.getTetroStates(piece.getType())[0];
      for (SimplePoint p : state.getPoints()) {
        float x = queueX + p.getX() * halfWidth + boxWidth / 2.0F;
        float y = queueY + p.getY() * halfHeight + boxWidth * i + boxWidth / 2.0F;
        g.setColor(this.colorScheme.getColor(piece.getType()));
        g.fillRect(x, y, halfWidth, halfHeight);
        g.setColor(Color.darkGray);
        g.drawRect(x, y, halfWidth, halfHeight);
      }
      i++;
    }
  }

  private void renderHoldBox(GameContainer c, Graphics g) {
    float halfWidth = this.blockWidth * 0.5F;
    float halfHeight = this.blockHeight * 0.5F;
    float holdBoxWidth = 60.0F;
    g.drawRect(this.x - holdBoxWidth, this.y + this.blockHeight * this.ceiling, holdBoxWidth, holdBoxWidth);
    if (this.heldPiece != null) {
      float heldPieceX = this.x - holdBoxWidth / 2.0F;
      float heldPieceY = this.y + halfHeight * 2.0F * this.ceiling + holdBoxWidth / 2.0F;
      TetroState state = this.rotationSystem.getTetroStates(this.heldPiece.getType())[0];
      for (SimplePoint p : state.getPoints()) {
        g.setColor(this.colorScheme.getColor(this.heldPiece.getType()));
        g.fillRect(heldPieceX + p.getX() * halfWidth, heldPieceY + p.getY() * halfHeight, halfWidth, halfHeight);
        g.setColor(Color.darkGray);
        g.drawRect(heldPieceX + p.getX() * halfWidth, heldPieceY + p.getY() * halfHeight, halfWidth, halfHeight);
      }
    }
  }

  private void renderField(GameContainer c, Graphics g) {
    for (int i = 0; i < this.field.length; i++)
      for (int j = this.ceiling; j < this.field[i].length; j++) {
        if (this.field[i][j] != null) {
          g.setColor(this.colorScheme.getColor(this.field[i][j].getType()));
          g.fillRect(this.x + this.blockWidth * i, this.y + this.blockHeight * j, this.blockWidth, this.blockHeight);
          g.setColor(Color.darkGray);
          g.drawRect(this.x + this.blockWidth * i, this.y + this.blockHeight * j, this.blockWidth, this.blockHeight);
        } else {
          g.setColor(Color.darkGray);
          g.drawRect(this.x + this.blockWidth * i, this.y + this.blockHeight * j, this.blockWidth, this.blockHeight);
        }
      }
  }

  public void update(GameContainer c, int d) throws SlickException {
  }

  public void centerPlayfield(GameContainer c) {
    setX(c.getWidth() / 2 - this.field.length * this.blockWidth / 2.0F);
    setY(c.getHeight() / 2 - this.field[0].length * this.blockHeight / 2.0F);
  }

  public void setPosition(float x, float y) {
    setX(x);
    setY(y);
  }

  public float getX() {
    return this.x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return this.y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public boolean movePieceY(int offset) {
    Piece temp = this.currentPiece;
    this.pieceY += offset;
    if (!setPiece(this.currentPiece, this.pieceX, this.pieceY, this.currentRotation, true)) {
      if (temp == this.currentPiece) {
        this.pieceY -= offset;
      }
      return false;
    }
    determineGhost();
    return true;
  }

  public boolean movePieceX(int offset) {
    this.pieceX += offset;
    if (!setPiece(this.currentPiece, this.pieceX, this.pieceY, this.currentRotation, false)) {
      this.pieceX -= offset;
      return false;
    }
    determineGhost();
    return true;
  }

  public void hardDrop() {
    while (movePieceY(1))
      ;
  }

  public void softDrop() {
    this.pieceY = this.ghostY;
    setPiece(this.currentPiece, this.pieceX, this.ghostY, this.currentRotation, true);
  }

  public void hold() {
    for (int i = 0; i < this.field.length; i++) {
      for (int j = 0; j < this.field[i].length; j++) {
        if (this.field[i][j] != this.currentPiece)
          continue;
        this.field[i][j] = null;
      }
    }
    if (this.heldPiece == null) {
      this.heldPiece = new Piece(this.currentPiece.getType());
      nextPiece();
    } else {
      TetroType temp = this.currentPiece.getType();
      this.currentPiece = new Piece(this.heldPiece.getType());
      this.heldPiece = new Piece(temp);

      this.currentRotation = 0;
      this.pieceX = getStartX();
      this.pieceY = getStartY();
      setPiece(this.currentPiece, this.pieceX, this.pieceY, this.currentRotation, false);
    }
  }

  public long getDASDelay() {
    float DASDelay = 10.0F;
    return (long) (1000 / 60.0f * DASDelay);
  }

  public long getDAS() {
    float DAS = 60.0F;
    return (long) (1.0F / DAS * 1000.0F);
  }

}