package com.Blood.Ware.module.hud;

import Caesium.Panel;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColourUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Module {
   public static int red;
   public static int green;
   public static int blue;
   public static int color;
   public static List<String> Modes = new ArrayList();

   public ClickGUI() {
      super("ClickGUI", Category.HUD);
      this.setKey(54);
      Modes.add("Castom");
      Modes.add("New");
      Modes.add("Old");
      Modes.add("SimpleGUI");
      BloodWare.instance.settingsManager.rSetting(new Setting("Gui", this, "Old", (ArrayList)Modes));
      BloodWare.instance.settingsManager.rSetting(new Setting("Rainbow", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Red", this, 225.0D, 0.0D, 225.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Green", this, 0.0D, 0.0D, 225.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Blue", this, 0.0D, 0.0D, 225.0D, true));
   }

   public static int getColor() {
      boolean setcolor = BloodWare.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();
      float[] hue = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int rgb = Color.HSBtoRGB(hue[0], 1.0F, 1.0F);
      int red = (int)BloodWare.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
      int green = (int)BloodWare.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
      int blue = (int)BloodWare.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
      int color = ColourUtils.toRGBA(red, green, blue, 255);
      int TheColor = setcolor ? ColourUtils.genRainbow() : color;
      return TheColor;
   }

   public static int getColor2() {
      boolean setcolor = BloodWare.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();
      float[] hue = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int rgb = Color.HSBtoRGB(hue[0], 1.0F, 1.0F);
      int red = (int)BloodWare.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
      int green = (int)BloodWare.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
      int blue = (int)BloodWare.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
      int color = ColourUtils.toRGBA(red, green, blue, 195);
      int TheColor = setcolor ? ColourUtils.genRainbowShadow() : color;
      return TheColor;
   }

   public static float getColor3() {
      boolean setcolor = BloodWare.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();
      float[] hue = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      float rgb = (float)Color.HSBtoRGB(hue[0], 1.0F, 1.0F);
      float red = (float)BloodWare.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
      float green = (float)BloodWare.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
      float blue = (float)BloodWare.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
      int color = ColourUtils.toRGBA(red, green, blue, 255.0F);
      float TheColor = setcolor ? (float)ColourUtils.genRainbowShadow() : (float)color;
      return TheColor;
   }

   public void onEnable() {
      String Mode = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Gui").getValString();
      byte var3 = -1;
      switch(Mode.hashCode()) {
      case -120434167:
         if (Mode.equals("SimpleGui")) {
            var3 = 3;
         }
         break;
      case 78208:
         if (Mode.equals("New")) {
            var3 = 0;
         }
         break;
      case 79367:
         if (Mode.equals("Old")) {
            var3 = 1;
         }
         break;
      case 2011275645:
         if (Mode.equals("Castom")) {
            var3 = 2;
         }
      }

      BloodWare var10001;
      switch(var3) {
      case 0:
         mc.displayGuiScreen(new Panel("Caesium", 22));
         break;
      case 1:
         var10001 = BloodWare.instance;
         mc.displayGuiScreen(BloodWare.bobrGui);
         break;
      case 2:
         var10001 = BloodWare.instance;
         mc.displayGuiScreen(BloodWare.CastomGui);
         break;
      case 3:
         mc.displayGuiScreen(BloodWare.instance.SimpleGui);
      }

      super.onEnable();
      this.setToggled(false);
   }
}
