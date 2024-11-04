package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ViewModel extends Module {
   public ViewModel() {
      super("ViewModel", Category.RENDER);
      BloodWare.instance.settingsManager.rSetting(new Setting("Position X", this, 0.0D, -10.0D, 10.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("Position Y", this, -0.23D, -10.0D, 10.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("Position Z", this, -0.45D, -10.0D, 10.0D, false));
   }

   @SubscribeEvent
   public void onRender(RenderSpecificHandEvent e) {
      double x = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Position X").getValDouble();
      double y = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Position Y").getValDouble();
      double z = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Position Z").getValDouble();
      GL11.glTranslated(x, y, z);
   }
}
