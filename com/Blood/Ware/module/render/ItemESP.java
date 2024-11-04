package com.Blood.Ware.module.render;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.RenderUtils;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemESP extends Module {
   AxisAlignedBB box;

   public ItemESP() {
      super("ItemsESP", Category.RENDER);
   }

   @SubscribeEvent
   public void onRender(RenderWorldLastEvent e) {
      Iterator var2 = mc.world.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity entity = (Entity)var2.next();
         if (entity instanceof EntityItem) {
            this.box = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05D - entity.posX + ((double)((float)((double)((float)entity.lastTickPosX) + (entity.posX - entity.lastTickPosX) * (double)Minecraft.getMinecraft().getRenderPartialTicks())) - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + ((double)((float)((double)((float)entity.lastTickPosY) + (entity.posY - entity.lastTickPosY) * (double)Minecraft.getMinecraft().getRenderPartialTicks())) - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - 0.05D - entity.posZ + ((double)((float)((double)((float)entity.lastTickPosZ) + (entity.posZ - entity.lastTickPosZ) * (double)Minecraft.getMinecraft().getRenderPartialTicks())) - Minecraft.getMinecraft().getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX + 0.05D - entity.posX + ((double)((float)((double)((float)entity.lastTickPosX) + (entity.posX - entity.lastTickPosX) * (double)Minecraft.getMinecraft().getRenderPartialTicks())) - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY + 0.1D - entity.posY + ((double)((float)((double)((float)entity.lastTickPosY) + (entity.posY - entity.lastTickPosY) * (double)Minecraft.getMinecraft().getRenderPartialTicks())) - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().maxZ + 0.05D - entity.posZ + ((double)((float)((double)((float)entity.lastTickPosZ) + (entity.posZ - entity.lastTickPosZ) * (double)Minecraft.getMinecraft().getRenderPartialTicks())) - Minecraft.getMinecraft().getRenderManager().viewerPosZ));
            RenderUtils.FillLine(entity, this.box);
         }
      }

   }
}
