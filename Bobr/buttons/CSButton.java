package Bobr.buttons;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public abstract class CSButton extends Gui {
   public int x;
   public int y;
   public int width;
   public int height;
   public int color;
   public String displayString;
   public Minecraft mc = Minecraft.getMinecraft();
   public FontRenderer fr;

   public CSButton(int x, int y, int width, int height, int color, String displayString) {
      this.fr = this.mc.fontRenderer;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.color = color;
      this.displayString = displayString;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void onKeyPress(int typedChar, int keyCode) {
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
   }

   public void initButton() {
   }
}
