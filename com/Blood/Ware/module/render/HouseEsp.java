package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HouseEsp extends Module {
   ArrayList armorStands = new ArrayList();

   public HouseEsp() {
      super("HouseEsp", Category.RENDER);
      BloodWare.instance.settingsManager.rSetting(new Setting("Wallhack", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Glowing", this, false));
   }

   public void onDisable() {
      super.onDisable();
      Iterator var1 = mc.world.loadedEntityList.iterator();

      while(var1.hasNext()) {
         Entity entity = (Entity)var1.next();
         if (entity instanceof EntityArmorStand && entity.isGlowing()) {
            entity.setGlowing(false);
         }
      }

   }

   void render(Entity entity, float n) {
      try {
         if (entity == null || entity == mc.player) {
            return;
         }

         if (entity == mc.getRenderViewEntity() && mc.gameSettings.thirdPersonView == 0) {
            return;
         }

         mc.entityRenderer.disableLightmap();
         mc.getRenderManager().renderEntityStatic(entity, n, false);
         mc.entityRenderer.enableLightmap();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   @SubscribeEvent
   public void ShkafRender(RenderWorldLastEvent renderWorldLastEvent) {
      GlStateManager.clear(256);
      RenderHelper.enableStandardItemLighting();
      boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Wallhack").getValBoolean();
      boolean valBoolean2 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Glowing").getValBoolean();
      Iterator var4 = Minecraft.getMinecraft().world.loadedEntityList.iterator();

      while(var4.hasNext()) {
         Entity entity = (Entity)var4.next();
         if (entity instanceof EntityArmorStand && entity != Minecraft.getMinecraft().getRenderViewEntity()) {
            if (valBoolean) {
               this.render(entity, renderWorldLastEvent.getPartialTicks());
            }

            if (valBoolean2 && !entity.isGlowing()) {
               entity.setGlowing(true);
            }
         }
      }

   }
}
