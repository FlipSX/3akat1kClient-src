package Caesium.components;

import Caesium.Panel;

public class GuiLabel implements GuiComponent {
   private String text;

   public GuiLabel(String text) {
      this.text = text;
   }

   public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
      Panel.fR.drawStringWithShadow(this.text, (float)(posX + width / 2 - Panel.fR.getStringWidth(this.text) / 2), (float)(posY + 2), Panel.fontColor);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
   }

   public void keyTyped(int keyCode, char typedChar) {
   }

   public int getWidth() {
      return Panel.fR.getStringWidth(this.text) + 4;
   }

   public int getHeight() {
      return Panel.fR.FONT_HEIGHT + 2;
   }

   public boolean allowScroll() {
      return true;
   }
}
