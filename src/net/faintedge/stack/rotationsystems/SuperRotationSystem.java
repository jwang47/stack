package net.faintedge.stack.rotationsystems;

import java.util.HashMap;
import java.util.Map;

import net.faintedge.stack.RotationSystem;
import net.faintedge.stack.TetroState;
import net.faintedge.stack.TetroType;
import net.faintedge.stack.util.SimplePoint;

public class SuperRotationSystem extends RotationSystem {

  private Map<TetroType, Map<String, SimplePoint[]>> wallkickData;

  @Override
  public SimplePoint[] getWallkickOffsets(TetroType t, int oldRotation, int newRotation) {
    return wallkickData.get(t).get(oldRotation + "->" + newRotation);
  }

  @Override
  protected void init() {
    initWallkickData();
    initI();
    initJ();
    initL();
    initO();
    initS();
    initT();
    initZ();
  }

  private void initWallkickData() {
    wallkickData = new HashMap<TetroType, Map<String, SimplePoint[]>>();
    
    Map<String, SimplePoint[]> data = new HashMap<String, SimplePoint[]>();
    data.put("0->1", new SimplePoint[] {new SimplePoint(-1, 0), new SimplePoint(-1, +1), new SimplePoint(0, -2), new SimplePoint(-1, -2)});
    data.put("1->0", new SimplePoint[] {new SimplePoint(+1, 0), new SimplePoint(+1, -1), new SimplePoint(0, +2), new SimplePoint(+1, +2)});
    data.put("1->2", new SimplePoint[] {new SimplePoint(+1, 0), new SimplePoint(+1, -1), new SimplePoint(0, +2), new SimplePoint(+1, +2)});
    data.put("2->1", new SimplePoint[] {new SimplePoint(-1, 0), new SimplePoint(-1, +1), new SimplePoint(0, -2), new SimplePoint(-1, -2)});
    data.put("2->3", new SimplePoint[] {new SimplePoint(+1, 0), new SimplePoint(+1, +1), new SimplePoint(0, -2), new SimplePoint(+1, -2)});
    data.put("3->2", new SimplePoint[] {new SimplePoint(-1, 0), new SimplePoint(-1, -1), new SimplePoint(0, +2), new SimplePoint(-1, +2)});
    data.put("3->0", new SimplePoint[] {new SimplePoint(-1, 0), new SimplePoint(-1, -1), new SimplePoint(0, +2), new SimplePoint(-1, +2)});
    data.put("0->3", new SimplePoint[] {new SimplePoint(+1, 0), new SimplePoint(+1, +1), new SimplePoint(0, -2), new SimplePoint(+1, -2)});
    wallkickData.put(TetroType.J, data);
    wallkickData.put(TetroType.L, data);
    wallkickData.put(TetroType.S, data);
    wallkickData.put(TetroType.T, data);
    wallkickData.put(TetroType.Z, data);
    
    data = new HashMap<String, SimplePoint[]>();
    data.put("0->1", new SimplePoint[] {new SimplePoint(-2, 0), new SimplePoint(+1, 0), new SimplePoint(-2, -1), new SimplePoint(+1, +2)});
    data.put("1->0", new SimplePoint[] {new SimplePoint(-2, 0), new SimplePoint(-1, 0), new SimplePoint(+2, +1), new SimplePoint(-1, -2)});
    data.put("1->2", new SimplePoint[] {new SimplePoint(-1, 0), new SimplePoint(+2, 0), new SimplePoint(-1, +2), new SimplePoint(+2, -1)});
    data.put("2->1", new SimplePoint[] {new SimplePoint(+1, 0), new SimplePoint(-2, 0), new SimplePoint(+1, -2), new SimplePoint(-2, +1)});
    data.put("2->3", new SimplePoint[] {new SimplePoint(+2, 0), new SimplePoint(-1, 0), new SimplePoint(+2, +1), new SimplePoint(-1, -2)});
    data.put("3->2", new SimplePoint[] {new SimplePoint(-2, 0), new SimplePoint(+1, 0), new SimplePoint(-2, -1), new SimplePoint(+1, +2)});
    data.put("3->0", new SimplePoint[] {new SimplePoint(+1, 0), new SimplePoint(-2, 0), new SimplePoint(+1, -2), new SimplePoint(-2, +1)});
    data.put("0->3", new SimplePoint[] {new SimplePoint(-1, 0), new SimplePoint(+2, 0), new SimplePoint(-1, +2), new SimplePoint(+2, -1)});
    wallkickData.put(TetroType.I, data);
    /*
(from http://tetrisconcept.net/wiki/SRS)
J, L, S, T, Z Tetromino Wall Kick Data
Test 1  Test 2  Test 3  Test 4  Test 5
0->R    ( 0, 0) (-1, 0) (-1,+1) ( 0,-2) (-1,-2)
R->0    ( 0, 0) (+1, 0) (+1,-1) ( 0,+2) (+1,+2)
R->2    ( 0, 0) (+1, 0) (+1,-1) ( 0,+2) (+1,+2)
2->R    ( 0, 0) (-1, 0) (-1,+1) ( 0,-2) (-1,-2)
2->L    ( 0, 0) (+1, 0) (+1,+1) ( 0,-2) (+1,-2)
L->2    ( 0, 0) (-1, 0) (-1,-1) ( 0,+2) (-1,+2)
L->0    ( 0, 0) (-1, 0) (-1,-1) ( 0,+2) (-1,+2)
0->L    ( 0, 0) (+1, 0) (+1,+1) ( 0,-2) (+1,-2)

I Tetromino Wall Kick Data
Test 1  Test 2  Test 3  Test 4  Test 5
0->R    ( 0, 0) (-2, 0) (+1, 0) (-2,-1) (+1,+2)
R->0    ( 0, 0) (+2, 0) (-1, 0) (+2,+1) (-1,-2)
R->2    ( 0, 0) (-1, 0) (+2, 0) (-1,+2) (+2,-1)
2->R    ( 0, 0) (+1, 0) (-2, 0) (+1,-2) (-2,+1)
2->L    ( 0, 0) (+2, 0) (-1, 0) (+2,+1) (-1,-2)
L->2    ( 0, 0) (-2, 0) (+1, 0) (-2,-1) (+1,+2)
L->0    ( 0, 0) (+1, 0) (-2, 0) (+1,-2) (-2,+1)
0->L    ( 0, 0) (-1, 0) (+2, 0) (-1,+2) (+2,-1)
     */
  }

  private void initZ() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, -1);
    state.addPoint(-1, -1);
    state.addPoint(1, 0);
    states[0] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, -1);
    state.addPoint(1, 0);
    state.addPoint(0, 1);
    states[1] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 1);
    state.addPoint(0, 1);
    state.addPoint(-1, 0);
    states[2] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, 1);
    state.addPoint(1, 0);
    state.addPoint(1, -1);
    states[3] = state;

    add(TetroType.Z, states);
  }

  private void initT() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(-1, 0);
    state.addPoint(0, -1);
    state.addPoint(1, 0);
    states[0] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, -1);
    state.addPoint(1, 0);
    state.addPoint(0, 1);
    states[1] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(0, 1);
    state.addPoint(-1, 0);
    states[2] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, 1);
    state.addPoint(-1, 0);
    state.addPoint(0, -1);
    states[3] = state;

    add(TetroType.T, states);
  }

  private void initS() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(-1, 0);
    state.addPoint(0, -1);
    state.addPoint(1, -1);
    states[0] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, -1);
    state.addPoint(1, 1);
    state.addPoint(1, 0);
    states[1] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(0, 1);
    state.addPoint(-1, 1);
    states[2] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, 1);
    state.addPoint(-1, 0);
    state.addPoint(-1, -1);
    states[3] = state;

    add(TetroType.S, states);
  }

  private void initO() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(1, 1);
    state.addPoint(0, 1);
    states[0] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(1, 1);
    state.addPoint(0, 1);
    states[1] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(1, 1);
    state.addPoint(0, 1);
    states[2] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(1, 1);
    state.addPoint(0, 1);
    states[3] = state;

    add(TetroType.O, states);
  }

  private void initL() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(-1, 0);
    state.addPoint(1, 0);
    state.addPoint(1, -1);

    states[0] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, -1);
    state.addPoint(0, 1);
    state.addPoint(1, 1);

    states[1] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(-1, 0);
    state.addPoint(-1, 1);
    state.addPoint(1, 0);

    states[2] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, 1);
    state.addPoint(0, -1);
    state.addPoint(-1, -1);

    states[3] = state;

    add(TetroType.L, states);
  }

  private void initJ() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(-1, -1);
    state.addPoint(-1, 0);
    state.addPoint(1, 0);
    state.addPoint(0, 0);
    states[0] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, -1);
    state.addPoint(1, -1);
    state.addPoint(0, 1);
    states[1] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(1, 1);
    state.addPoint(-1, 0);
    states[2] = state;

    state = new TetroState();
    state.addPoint(0, 0);
    state.addPoint(0, 1);
    state.addPoint(-1, 1);
    state.addPoint(0, -1);
    states[3] = state;

    add(TetroType.J, states);
  }

  private void initI() {
    TetroState[] states = new TetroState[4];

    TetroState state = new TetroState();
    state.addPoint(-1, 0);
    state.addPoint(0, 0);
    state.addPoint(1, 0);
    state.addPoint(2, 0);
    states[0] = state;

    state = new TetroState();
    state.addPoint(1, -1);
    state.addPoint(1, 0);
    state.addPoint(1, 1);
    state.addPoint(1, 2);
    states[1] = state;

    state = new TetroState();
    state.addPoint(-1, 1);
    state.addPoint(0, 1);
    state.addPoint(1, 1);
    state.addPoint(2, 1);
    states[2] = state;

    state = new TetroState();
    state.addPoint(0, -1);
    state.addPoint(0, 0);
    state.addPoint(0, 1);
    state.addPoint(0, 2);
    states[3] = state;

    add(TetroType.I, states);
  }
}