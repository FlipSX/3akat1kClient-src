package Simple.components.listeners;

import Caesium.components.GuiComponent;
import java.util.ArrayList;

public abstract class ComponentListener {
   private final ArrayList<GuiComponent> components = new ArrayList();

   protected void add(GuiComponent component) {
      this.components.add(component);
   }

   public void clearComponents() {
      this.components.clear();
   }

   public ArrayList<GuiComponent> getComponents() {
      return this.components;
   }

   public abstract void addComponents();
}
