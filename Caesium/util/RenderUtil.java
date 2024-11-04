package Caesium.util;

import net.minecraft.client.gui.Gui;

public final class RenderUtil {
   public static void drawRect(int left, int top, int right, int bottom, int color) {
      Gui.drawRect(left, top, right, bottom, color);
   }

   public static void drawHorizontalLine(int startX, int endX, int y, int color) {
      if (endX < startX) {
         int i = startX;
         startX = endX;
         endX = i;
      }

      drawRect(startX, y, endX + 1, y + 1, color);
   }

   public static void drawVerticalLine(int x, int startY, int endY, int color) {
      if (endY < startY) {
         int i = startY;
         startY = endY;
         endY = i;
      }

      drawRect(x, startY + 1, x + 1, endY, color);
   }

   public static boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
      return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY < y + height;
   }
}
