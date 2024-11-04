package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import java.io.IOException;
import org.lwjgl.input.Keyboard;

public class CSSettingDouble extends CSSetting {
   public CSSettingDouble(int x, int y, int width, int height, Setting s) {
      super(x, y, width, height, s);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      double reach = this.set.getValDouble();
      double reach1 = reach * 100.0D;
      double reach2 = (double)Math.round(reach1);
      double round = reach2 / 100.0D;
      this.mc.fontRenderer.drawString("<", this.x + 1, this.y + 1, this.isHoveredLeft(mouseX, mouseY) ? ClickGUI.getColor() : Integer.MAX_VALUE);
      this.mc.fontRenderer.drawString(">", this.x + 1 + this.fr.getStringWidth(this.set.getName() + " " + round) + 15, this.y + 1, this.isHoveredRight(mouseX, mouseY) ? ClickGUI.getColor() : Integer.MAX_VALUE);
      this.mc.fontRenderer.drawString(this.set.getName() + " " + round, this.x + 10, this.y + 1, Integer.MAX_VALUE);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public boolean isHoveredLeft(int mouseX, int mouseY) {
      boolean hoveredx = mouseX > this.x + 1 && mouseX < this.x + 1 + 5;
      boolean hoveredy = mouseY > this.y + 1 && mouseY < this.y + this.mc.fontRenderer.FONT_HEIGHT;
      return hoveredx && hoveredy;
   }

   public boolean isHoveredRight(int mouseX, int mouseY) {
      double round = (double)(Math.round(this.set.getValDouble() * 10.0D) / 10L);
      boolean hoveredx = mouseX > this.x + 1 + this.fr.getStringWidth(this.set.getName() + " " + round) + 15 && mouseX < this.x + 1 + this.fr.getStringWidth(this.set.getName() + " " + round) + 20;
      boolean hoveredy = mouseY > this.y + 1 && mouseY < this.y + this.mc.fontRenderer.FONT_HEIGHT;
      return hoveredx && hoveredy;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      Setting s = this.set;
      if (mouseButton == 0) {
         if (this.isHoveredLeft(mouseX, mouseY)) {
            boolean more = Keyboard.isKeyDown(42);
            double plus = 0.0D;
            if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(29)) {
               if (s.onlyInt()) {
                  plus = 1.0D;
               } else {
                  plus = 0.1D;
               }
            } else if (Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(29)) {
               plus = s.onlyInt() ? 10.0D : 1.0D;
            } else if (Keyboard.isKeyDown(42) && Keyboard.isKeyDown(29)) {
               plus = s.onlyInt() ? 1.0D : 0.01D;
            } else if (!Keyboard.isKeyDown(42) && Keyboard.isKeyDown(29)) {
               plus = 0.0D;
               s.setValDouble(s.getMin());
            }

            if (!(s.getValDouble() - plus < s.getMin()) && s.getValDouble() - plus != s.getMin()) {
               if (s.getValDouble() - plus > s.getMin()) {
                  s.setValDouble(s.getValDouble() - plus);
               }
            } else {
               s.setValDouble(s.getMin());
            }

            if (Keyboard.isKeyDown(29)) {
               s.setValDouble(s.getMin());
            }
         } else if (this.isHoveredRight(mouseX, mouseY)) {
            double plus = 0.0D;
            if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(29)) {
               if (s.onlyInt()) {
                  plus = 1.0D;
               } else {
                  plus = 0.1D;
               }
            } else if (Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(29)) {
               plus = s.onlyInt() ? 10.0D : 1.0D;
            } else if (Keyboard.isKeyDown(42) && Keyboard.isKeyDown(29)) {
               plus = s.onlyInt() ? 1.0D : 0.01D;
            } else if (!Keyboard.isKeyDown(42) && Keyboard.isKeyDown(29)) {
               plus = 0.0D;
               s.setValDouble(s.getMax());
            }

            if (!(s.getValDouble() + plus > s.getMax()) && s.getValDouble() + plus != s.getMax()) {
               if (s.getValDouble() + plus < s.getMax()) {
                  s.setValDouble(s.getValDouble() + plus);
               }
            } else {
               s.setValDouble(s.getMax());
            }
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }
}
