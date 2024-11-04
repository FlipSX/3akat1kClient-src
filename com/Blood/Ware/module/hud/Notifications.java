package com.Blood.Ware.module.hud;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.notifications.notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Notifications extends Module {
   private static RenderItem itemRender;
   private Entity target;

   public Notifications() {
      super("Notifications", Category.HUD);
   }

   @SubscribeEvent
   public void onRender(Text event) {
      if (event.getType() == ElementType.TEXT && !Minecraft.getMinecraft().gameSettings.showDebugInfo) {
         notifications.show();
      }
   }
}
