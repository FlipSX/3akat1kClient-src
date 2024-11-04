package com.Blood.Ware.module.render;

import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WallHackPlayer extends Module {
   public WallHackPlayer() {
      super("WallHackPlayer", Category.RENDER);
   }

   void render(Entity entity, float ticks) {
      try {
         if (entity == null || entity == Minecraft.getMinecraft().player) {
            return;
         }

         if (entity == Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            return;
         }

         Minecraft.getMinecraft().entityRenderer.disableLightmap();
         Minecraft.getMinecraft().getRenderManager().renderEntityStatic(entity, ticks, false);
         Minecraft.getMinecraft().entityRenderer.enableLightmap();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldLastEvent event) {
      GlStateManager.clear(256);
      RenderHelper.enableStandardItemLighting();
      Iterator var2 = Minecraft.getMinecraft().world.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity entity = (Entity)var2.next();
         if (entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().getRenderViewEntity()) {
            if (FriendManager.isFriend(entity.getName())) {
               this.render(entity, event.getPartialTicks());
            } else {
               this.render(entity, event.getPartialTicks());
            }
         }
      }

   }
}
