package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import Bobr.util.RenderHelper;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.Gui;

public class CSSettingCheck extends CSSetting {
   private int animation = 20;

   public CSSettingCheck(int x, int y, int width, int height, Setting s) {
      super(x, y, width, height, s);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.fr.drawString(this.displayString, this.x, this.y, Integer.MAX_VALUE);
      int stringwidth = this.fr.getStringWidth(this.displayString);
      Gui.drawRect(this.x + stringwidth + 20, this.y, this.x + stringwidth + 30, this.y + 10, Color.black.getRGB());
      RenderHelper.drawFullCircle((double)(this.x + stringwidth + 20), (double)(this.y + 5), 5.0D, Color.black.getRGB());
      RenderHelper.drawFullCircle((double)(this.x + stringwidth + 30), (double)(this.y + 5), 5.0D, Color.black.getRGB());
      if (this.set.getValBoolean()) {
         RenderHelper.drawFullCircle((double)(this.x + stringwidth + this.animation), (double)(this.y + 5), 5.0D, ClickGUI.getColor());
      } else {
         RenderHelper.drawFullCircle((double)(this.x + stringwidth + this.animation), (double)(this.y + 5), 5.0D, -2);
      }

      if (this.set.getValBoolean()) {
         if (this.animation < 30) {
            ++this.animation;
         }
      } else if (this.animation > 20) {
         --this.animation;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.isHovered(mouseX, mouseY) && mouseButton == 0) {
         this.set.setValBoolean(!this.set.getValBoolean());
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   private boolean isHovered(int mouseX, int mouseY) {
      int stringwidth = this.fr.getStringWidth(this.displayString);
      boolean hoveredx = mouseX > this.x + stringwidth + 15 && mouseX < this.x + stringwidth + 35;
      boolean hoveredy = mouseY > this.y && mouseY < this.y + 10;
      return hoveredx && hoveredy;
   }
}
