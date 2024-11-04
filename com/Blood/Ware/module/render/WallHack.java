package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WallHack extends Module {
   void render(Entity entity, float n) {
      try {
         if (entity == null || entity == Minecraft.getMinecraft().player) {
            return;
         }

         if (entity == Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            return;
         }

         Minecraft.getMinecraft().entityRenderer.disableLightmap();
         Minecraft.getMinecraft().getRenderManager().renderEntityStatic(entity, n, false);
         Minecraft.getMinecraft().entityRenderer.enableLightmap();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldLastEvent renderWorldLastEvent) {
      boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Mob").getValBoolean();
      boolean valBoolean2 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Animal").getValBoolean();
      GlStateManager.clear(256);
      RenderHelper.enableStandardItemLighting();
      Iterator var4;
      Entity entity2;
      if (valBoolean) {
         var4 = Minecraft.getMinecraft().world.loadedEntityList.iterator();

         while(var4.hasNext()) {
            entity2 = (Entity)var4.next();
            if (entity2 instanceof EntityMob && entity2 != Minecraft.getMinecraft().getRenderViewEntity()) {
               this.render(entity2, renderWorldLastEvent.getPartialTicks());
            }
         }
      }

      if (valBoolean2) {
         var4 = Minecraft.getMinecraft().world.loadedEntityList.iterator();

         while(var4.hasNext()) {
            entity2 = (Entity)var4.next();
            if (entity2 instanceof EntityAnimal && entity2 != Minecraft.getMinecraft().getRenderViewEntity()) {
               this.render(entity2, renderWorldLastEvent.getPartialTicks());
            }
         }
      }

   }

   public WallHack() {
      super("WallHackMob", Category.RENDER);
      BloodWare.instance.settingsManager.rSetting(new Setting("Mob", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Animal", this, false));
   }
}
