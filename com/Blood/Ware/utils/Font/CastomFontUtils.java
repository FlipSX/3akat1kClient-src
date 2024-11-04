package com.Blood.Ware.utils.Font;

import java.awt.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public final class CastomFontUtils {
   public static CustomFontRenderer fr = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("font.otf"), 20.0F, 0), true, true);
   public static CustomFontRenderer fr2 = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("main.ttf"), 20.0F, 0), true, true);
   public static CustomFontRenderer fr3 = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("rerty.ttf"), 20.0F, 0), true, true);
   public static CustomFontRenderer fr4 = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("latoregular.ttf"), 20.0F, 0), true, true);

   public static Font getFontFromTTF(ResourceLocation loc, float fontSize, int fontType) {
      try {
         Font output = Font.createFont(fontType, Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream());
         output = output.deriveFont(fontSize);
         return output;
      } catch (Exception var4) {
         return null;
      }
   }
}
