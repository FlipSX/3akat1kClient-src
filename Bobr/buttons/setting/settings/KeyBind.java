package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import com.Blood.Ware.module.Module;
import java.io.IOException;
import org.lwjgl.input.Keyboard;

public class KeyBind extends CSSetting {
   private boolean binding;

   public KeyBind(int x, int y, int width, int height, Module s) {
      super(x, y, width, height, s);
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
   }

   public void setBinding(boolean binding) {
      this.binding = binding;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      String displayString = "KeyBind: " + Keyboard.getKeyName(this.mod.getKey());
      if (this.binding) {
         displayString = "Listen... ";
         this.fr.drawString(displayString, this.x, this.y, Integer.MAX_VALUE);
      }

      this.fr.drawString(displayString, this.x, this.y, Integer.MAX_VALUE);
      this.fr.getStringWidth(displayString);
      if (this.binding && Keyboard.getEventKey() != 0 && Keyboard.getEventKeyState()) {
         this.mod.setKey(Keyboard.getEventKey());
         this.binding = false;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.isHovered(mouseX, mouseY) && mouseButton == 0) {
         this.setBinding(true);
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   private boolean isHovered(int mouseX, int mouseY) {
      int stringwidth = this.fr.getStringWidth(this.displayString);
      boolean hoveredx = mouseX > this.x + 15 && mouseX < this.x + stringwidth + 35;
      boolean hoveredy = mouseY > this.y && mouseY < this.y + 10;
      return hoveredx && hoveredy;
   }
}
