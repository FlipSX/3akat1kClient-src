package com.Blood.Ware.font;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FontUtils {
   public static volatile int completed;
   public static MinecraftFontRenderer normal;
   private static Font normal_;

   private static Font getFont(Map<String, Font> locationMap, String location, int size) {
      Font font = null;

      try {
         if (locationMap.containsKey(location)) {
            font = ((Font)locationMap.get(location)).deriveFont(0, (float)size);
         } else {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(location)).getInputStream();
            font = Font.createFont(0, is);
            locationMap.put(location, font);
            font = font.deriveFont(0, (float)size);
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         font = new Font("default", 0, 10);
      }

      return font;
   }

   public static boolean hasLoaded() {
      return completed >= 3;
   }

   public static void bootstrap() {
      (new Thread(() -> {
         Map<String, Font> locationMap = new HashMap();
         normal_ = getFont(locationMap, "font.otf", 10);
         ++completed;
      })).start();
      (new Thread(() -> {
         new HashMap();
         ++completed;
      })).start();
      (new Thread(() -> {
         new HashMap();
         ++completed;
      })).start();

      while(!hasLoaded()) {
         try {
            Thread.sleep(5L);
         } catch (InterruptedException var1) {
            var1.printStackTrace();
         }
      }

      normal = new MinecraftFontRenderer(normal_, true, true);
   }
}
