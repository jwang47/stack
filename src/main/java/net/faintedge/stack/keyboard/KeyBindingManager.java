package net.faintedge.stack.keyboard;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyBindingManager {
  private final HashMap<Action, ArrayList<KeyBinding>> bindings = new HashMap<Action, ArrayList<KeyBinding>>();

  public void ensureBindable(Action a) {
    if (this.bindings.get(a) == null) {
      this.bindings.put(a, new ArrayList<KeyBinding>());
    }
  }

  public void bindKey(Action a, Integer key) {
    ensureBindable(a);
    this.bindings.get(a).add(new SingleKeyBinding(key.intValue()));
  }

  public void update(int d) {
    for (Action a : this.bindings.keySet()) {
      boolean activated = false;
      for (KeyBinding k : bindings.get(a)) {
        if (k.isPressed()) {
          activated = true;
          break;
        }
      }
      if ((!activated) && (a.isActive()))
        a.notifyReleased();
      if ((activated) && (!a.isActive()))
        a.notifyPressed();
      a.setActive(activated);
      if (!activated)
        continue;
      a.update(d);
    }
  }

  public HashMap<Action, ArrayList<KeyBinding>> getBindings() {
    return this.bindings;
  }
}