package com.Blood.Ware.notifications;

import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class notifications {
   public static List<String> Names = new ArrayList();
   public static List<String> Tests = new ArrayList();
   public static List<Type> Types = new ArrayList();
   public static List<Integer> Times = new ArrayList();
   private static float height = 30.0F;
   private static float width = 100.0F;

   public static void add(String main_input, String text_input, Type type_input) {
      Names.add(main_input);
      Tests.add(text_input);
      Types.add(type_input);
      Times.add(0);
   }

   public static void drawnotif(int i, String name, String text, Type type, int time) {
      GlStateManager.enableTexture2D();
      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      GL11.glPushMatrix();
      if (time >= 110) {
         GL11.glTranslated((double)(sr.getScaledWidth() - 110), (double)sr.getScaledHeight() - (double)time * 1.5D + 10.0D, 0.0D);
      } else {
         GL11.glTranslated((double)(sr.getScaledWidth() - time), (double)sr.getScaledHeight() - (double)time * 1.5D + 10.0D, 0.0D);
      }

      int alpha = (int)(230.0D - ((double)time - 7.6D));
      if (alpha >= 0) {
         if (type == Type.Green) {
            RenderUtil.drawSmoothRect(0.0F, 0.0F, width, height, (new Color(35, 35, 40, alpha)).getRGB());
            RenderUtil.drawSmoothRect(0.0F, 0.0F, 7.0F, height, (new Color(51, 255, 0, alpha)).getRGB());
            CastomFontUtils.fr3.drawStringWithShadow(name, 10.0F, 2.0F, ClickGUI.getColor());
            CastomFontUtils.fr3.drawString(text, 10.0F, (float)(4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), (new Color(255, 255, 255, alpha)).getRGB());
         } else if (type == Type.Red) {
            RenderUtil.drawSmoothRect(0.0F, 0.0F, width, height, (new Color(35, 35, 40, alpha)).getRGB());
            RenderUtil.drawSmoothRect(0.0F, 0.0F, 7.0F, height, (new Color(255, 0, 0, alpha)).getRGB());
            CastomFontUtils.fr3.drawStringWithShadow(name, 10.0F, 2.0F, ClickGUI.getColor());
            CastomFontUtils.fr3.drawString(text, 10.0F, (float)(4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), (new Color(255, 255, 255, alpha)).getRGB());
         } else if (type == Type.OK) {
            RenderUtil.drawSmoothRect(0.0F, 0.0F, width, height, (new Color(35, 35, 40, alpha)).getRGB());
            RenderUtil.drawSmoothRect(0.0F, 0.0F, 7.0F, height, (new Color(51, 255, 0, alpha)).getRGB());
            CastomFontUtils.fr3.drawStringWithShadow(name, 10.0F, 2.0F, ClickGUI.getColor());
            CastomFontUtils.fr3.drawString(text, 10.0F, (float)(4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), (new Color(255, 255, 255, alpha)).getRGB());
         }
      }

      GL11.glPopMatrix();
      GlStateManager.disableTexture2D();
   }

   public static void show() {
      int i;
      for(i = 0; i < Names.size(); ++i) {
         if ((Integer)Times.get(i) != 230) {
            Times.set(i, (Integer)Times.get(i) + 1);
         } else {
            Names.remove(i);
            Tests.remove(i);
            Types.remove(i);
            Times.remove(i);
         }
      }

      height = 22.0F;

      for(i = 0; i < Names.size(); ++i) {
         String name = (String)Names.get(i);
         String text = (String)Tests.get(i);
         Type type = (Type)Types.get(i);
         int time = (Integer)Times.get(i);
         drawnotif(i, name, text, type, time);
      }

   }
}
