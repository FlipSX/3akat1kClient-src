package com.Blood.Ware.Menu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class drawLogo {
   public static void drawString(double scale, String text, float x, float y, int color) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(scale, scale, scale);
      Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, x, y, color);
      GlStateManager.popMatrix();
   }
}
