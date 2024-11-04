package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FogColor extends Module {
   public static List Modes = new ArrayList();
   private long spin = 0L;

   public FogColor() {
      super("FogColor", Category.OUTHER);
      Modes.add("Spin");
      Modes.add("Day");
      Modes.add("Night");
      Modes.add("Morning");
      Modes.add("Sunset");
      BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "Day", (ArrayList)Modes));
      BloodWare.instance.settingsManager.rSetting(new Setting("TimeSpin Speed", this, 2.0D, 1.0D, 10.0D, false));
   }

   @SubscribeEvent
   public void onUpdate(FogColors fogColors) {
      String valString = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Mode").getValString();
      float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "TimeSpin Speed").getValDouble();
      if (valString.equalsIgnoreCase("Spin")) {
         mc.world.setWorldTime(this.spin);
         this.spin += (long)(n * 100.0F);
      } else if (valString.equalsIgnoreCase("Day")) {
         mc.world.setWorldTime(5000L);
      } else if (valString.equalsIgnoreCase("Night")) {
         mc.world.setWorldTime(17000L);
      } else if (valString.equalsIgnoreCase("Morning")) {
         mc.world.setWorldTime(0L);
      } else if (valString.equalsIgnoreCase("Sunset")) {
         mc.world.setWorldTime(13000L);
      }

   }

   @SubscribeEvent
   public void onFogColorRender(FogColors fogColors) {
      Color hsbColor = Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + 300L + 300L)) / 15.0D % 360.0D / 360.0D), 0.4F, 1.0F);
      fogColors.setRed((float)hsbColor.getRed() / 0.0F);
      fogColors.setGreen((float)hsbColor.getGreen() / 255.0F);
      fogColors.setBlue((float)hsbColor.getBlue() / 0.0F);
   }
}
