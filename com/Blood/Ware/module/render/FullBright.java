package com.Blood.Ware.module.render;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FullBright extends Module {
   public float oldgamma;

   public FullBright() {
      super("FullBright", Category.RENDER);
   }

   public void onEnable() {
      super.onEnable();
      this.oldgamma = mc.gameSettings.gammaSetting;
      mc.gameSettings.gammaSetting = 10000.0F;
   }

   public void onDisable() {
      super.onDisable();
      mc.gameSettings.gammaSetting = this.oldgamma;
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingUpdateEvent e) {
   }
}
