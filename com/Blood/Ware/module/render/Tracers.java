package com.Blood.Ware.module.render;

import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtils;
import java.awt.Color;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Tracers extends Module {
   public Tracers() {
      super("Tracers", Category.RENDER);
   }

   public void onDisable() {
      super.onDisable();
   }

   public void onEnable() {
      super.onEnable();
   }

   @SubscribeEvent
   public void onRender(RenderWorldLastEvent event) {
      mc.gameSettings.viewBobbing = false;
      GL11.glPushMatrix();
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GL11.glLineWidth(1.0E-6F);
      Iterator var2 = mc.world.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity entity = (Entity)var2.next();
         if (entity != mc.player && entity instanceof EntityPlayer) {
            assert mc.getRenderViewEntity() != null;

            mc.player.getDistance(entity);
            double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - mc.getRenderManager().viewerPosX;
            double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - mc.getRenderManager().viewerPosY;
            double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - mc.getRenderManager().viewerPosZ;
            Vec3d vec3d = new Vec3d(0.0D, 0.0D, 1.0D);
            vec3d = vec3d.rotatePitch(-((float)Math.toRadians((double)mc.player.rotationPitch)));
            Vec3d vec3d2 = vec3d.rotateYaw(-((float)Math.toRadians((double)mc.player.rotationYaw)));
            GL11.glBegin(2);
            if (FriendManager.isFriend(entity.getName())) {
               RenderUtils.glColor(ClickGUI.getColor());
            } else {
               RenderUtils.glColor(Color.white.getRGB());
            }

            GL11.glVertex3d(vec3d2.x, (double)mc.player.getEyeHeight() + vec3d2.y, vec3d2.z);
            GL11.glVertex3d(d, d2 + 1.1D, d3);
            GL11.glEnd();
         }
      }

      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glPopMatrix();
   }
}
