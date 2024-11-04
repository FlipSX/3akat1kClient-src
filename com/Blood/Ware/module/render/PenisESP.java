package com.Blood.Ware.module.render;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import java.util.Iterator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

public class PenisESP extends Module {
   public void esp(EntityPlayer entityPlayer, double n, double n2, double n3) {
      GL11.glDisable(2896);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glDepthMask(true);
      GL11.glLineWidth(1.0F);
      GL11.glTranslated(n, n2, n3);
      GL11.glRotatef(-entityPlayer.rotationYaw, 0.0F, entityPlayer.height, 0.0F);
      GL11.glTranslated(-n, -n2, -n3);
      GL11.glTranslated(n, n2 + (double)(entityPlayer.height / 2.0F) - 0.22499999403953552D, n3);
      GL11.glColor4f(1.38F, 0.55F, 2.38F, 1.0F);
      GL11.glTranslated(0.0D, 0.0D, 0.07500000298023224D);
      Cylinder cylinder = new Cylinder();
      cylinder.setDrawStyle(100013);
      cylinder.draw(0.1F, 0.11F, 0.4F, 25, 20);
      GL11.glColor4f(1.38F, 0.85F, 1.38F, 1.0F);
      GL11.glTranslated(0.0D, 0.0D, -0.12500000298023223D);
      GL11.glTranslated(-0.09000000074505805D, 0.0D, 0.0D);
      Sphere sphere = new Sphere();
      sphere.setDrawStyle(100013);
      sphere.draw(0.14F, 10, 20);
      GL11.glTranslated(0.16000000149011612D, 0.0D, 0.0D);
      Sphere sphere2 = new Sphere();
      sphere2.setDrawStyle(100013);
      sphere2.draw(0.14F, 10, 20);
      GL11.glColor4f(1.35F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslated(-0.07000000074505806D, 0.0D, 0.589999952316284D);
      Sphere sphere3 = new Sphere();
      sphere3.setDrawStyle(100013);
      sphere3.draw(0.13F, 15, 20);
      GL11.glDepthMask(true);
      GL11.glDisable(2848);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
   }

   public void onEnable() {
      super.onEnable();
   }

   public PenisESP() {
      super("PenisESP", Category.RENDER);
   }

   public void onDisable() {
      super.onDisable();
   }

   @SubscribeEvent
   public void onWorldRender(RenderWorldLastEvent renderWorldLastEvent) {
      Iterator var2 = mc.world.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity next = (Entity)var2.next();
         if (next instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer)next;
            double n = (double)renderWorldLastEvent.getPartialTicks();
            double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n - mc.getRenderManager().viewerPosX;
            double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n - mc.getRenderManager().viewerPosY;
            double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n - mc.getRenderManager().viewerPosZ;
            GL11.glPushMatrix();
            RenderHelper.disableStandardItemLighting();
            this.esp(entity, d, d2, d3);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }

   }
}
