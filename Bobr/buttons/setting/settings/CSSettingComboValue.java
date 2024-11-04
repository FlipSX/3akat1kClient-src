package Bobr.buttons.setting.settings;

import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class CSSettingComboValue {
   public int x;
   public int y;
   public int width;
   public int height;
   public String displayString;
   public Minecraft mc = Minecraft.getMinecraft();
   public FontRenderer fr;
   public ArrayList<String> values;
   public Setting set;

   public CSSettingComboValue(int x, int y, int width, int height, Setting s, String displayString) {
      this.fr = this.mc.fontRenderer;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.displayString = displayString;
      this.set = s;
      this.values = s.getOptions();
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.isHovered(mouseX, mouseY)) {
         this.set.setValString(this.displayString);
      }

   }

   public void drawScreen(int mouseX, int mouseY) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Integer.MIN_VALUE);
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Integer.MIN_VALUE);
      int color = this.set.getValString().equalsIgnoreCase(this.displayString) ? ClickGUI.getColor() : Integer.MAX_VALUE;
      this.fr.drawString(this.displayString, this.x + this.width / 2 - this.fr.getStringWidth(this.displayString) / 2, this.y + 1, color);
   }

   public boolean isHovered(int mouseX, int mouseY) {
      boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
      boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
      return hoveredx && hoveredy;
   }
}
