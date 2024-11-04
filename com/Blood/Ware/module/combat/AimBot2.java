package com.Blood.Ware.module.combat;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.FovUtils;
import com.Blood.Ware.utils.TimerUtils;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class AimBot2 extends Module {
   public float[] facing;
   public String[] gunlist = new String[]{""};
   public TimerUtils timer = new TimerUtils();
   public TimerUtils timer2 = new TimerUtils();
   private float[] lastFacing;

   public AimBot2() {
      super("AimBot2", Category.COMBAT);
      BloodWare.instance.settingsManager.rSetting(new Setting("Range", this, 300.0D, 0.0D, 300.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Predict", this, 6.1D, 0.0D, 7.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("VPredict", this, 300.0D, 0.0D, 300.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("FOV", this, 48.0D, 1.0D, 360.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Smooth", this, 5.0D, 1.0D, 20.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("InstantLock", this, false));
      this.lastFacing = new float[]{0.0F, 0.0F};
   }

   public float[] getPredict(Entity e) {
      float VPredict = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "VPredict").getValDouble();
      float Predict = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Predict").getValDouble();
      float Range = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Range").getValDouble();
      double n = (double)mc.getRenderPartialTicks();
      double d = e.lastTickPosX + (e.posX - e.lastTickPosX) * n;
      double d2 = e.lastTickPosY + (e.posY - e.lastTickPosY) * n;
      double d3 = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * n;
      double xDiff = e.posX - e.prevPosX;
      double zDiff = e.posZ - e.prevPosZ;
      float predict = Predict + this.getDistance(e) / Range;
      double WillPosX = d + xDiff * (double)predict;
      double WillPosZ = d3 + zDiff * (double)predict;
      double WillPosY = VPredict != 0.0F ? d2 + (double)(this.getDistance(e) / VPredict) : d2;
      return new float[]{(float)WillPosX, (float)WillPosZ, (float)WillPosY};
   }

   private float getDistance(Entity e) {
      double deltaX = e.posX - mc.player.posX;
      double deltaY = e.posY - mc.player.posY;
      double deltaZ = e.posZ - mc.player.posZ;
      return (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
   }

   public static float[] faceHead(float posX, float posY, float posZ, float yawSpeed, float pitchSpeed) {
      double var4 = (double)posX - Minecraft.getMinecraft().player.posX;
      double var5 = (double)posZ - Minecraft.getMinecraft().player.posZ;
      double heightOffset = BloodWare.moduleManager.getModule("BAim").isToggled() ? 1.5D : 1.86D;
      double var6 = (double)posY + heightOffset - (Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight());
      double var7 = (double)MathHelper.sqrt(var4 * var4 + var5 * var5);
      float var8 = (float)(Math.atan2(var5, var4) * 180.0D / 3.141592653589793D) - 90.0F;
      float var9 = (float)(-(Math.atan2(var6 - 0.15D, var7) * 180.0D / 3.141592653589793D));
      float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6F + 0.2F;
      float gcd = f * f * f * 1.2F;
      float pitch = updateRotation(Minecraft.getMinecraft().player.rotationPitch, var9, pitchSpeed);
      float yaw = updateRotation(Minecraft.getMinecraft().player.rotationYaw, var8, yawSpeed);
      yaw -= yaw % gcd;
      pitch -= pitch % gcd;
      return new float[]{yaw, pitch};
   }

   public static float updateRotation(float current, float intended, float speed) {
      float f = MathHelper.wrapDegrees(intended - current);
      if (f > speed) {
         f = speed;
      }

      if (f < -speed) {
         f = -speed;
      }

      return current + f;
   }

   private boolean lambdagetTarget(Entity entity) {
      return this.attackCheck(entity);
   }

   public Entity getTarget() {
      if (mc.player != null && !mc.player.isDead) {
         List<Entity> list = (List)mc.world.loadedEntityList.stream().filter((entity) -> {
            return entity != mc.player;
         }).filter((entity) -> {
            return !entity.isDead;
         }).filter(this::lambdagetTarget).sorted(Comparator.comparing(FovUtils::getDistanceFromMouse)).collect(Collectors.toList());
         return list.isEmpty() ? null : (Entity)list.get(0);
      } else {
         return null;
      }
   }

   public boolean attackCheck(Entity target) {
      return target instanceof EntityPlayer && !FriendManager.FRIENDS.contains(target.getName()) && mc.player.canEntityBeSeen(target);
   }

   private float lerp(float start, float end, float percent) {
      return start + percent * (end - start);
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingUpdateEvent e) {
      EntityPlayer target = (EntityPlayer)this.getTarget();
      if (target != null && !FriendManager.FRIENDS.contains(target.getName()) && FovUtils.isInAttackFOV(target, (int)BloodWare.instance.settingsManager.getSettingByName((Module)this, "FOV").getValDouble())) {
         this.facing = this.getPredict(target);
         this.facing = faceHead(this.facing[0], this.facing[2], this.facing[1], 360.0F, 360.0F);
         boolean instantLock = BloodWare.instance.settingsManager.getSettingByName((Module)this, "InstantLock").getValBoolean();
         if (instantLock) {
            mc.player.rotationYaw = this.facing[0];
            mc.player.rotationPitch = this.facing[1];
         } else if (this.timer2.isDelay(1L)) {
            float smoothValue = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Smooth").getValDouble();
            float scaledSmoothValue = smoothValue / 100.0F;
            float lerpedYaw = this.lerp(mc.player.rotationYaw, this.facing[0], scaledSmoothValue);
            float lerpedPitch = this.lerp(mc.player.rotationPitch, this.facing[1], scaledSmoothValue);
            mc.player.rotationYaw = lerpedYaw;
            mc.player.rotationPitch = lerpedPitch;
            this.timer2.setLastMS();
         }
      }

   }

   @SubscribeEvent
   public void onRender(Post event) {
      double p = BloodWare.instance.settingsManager.getSettingByName((Module)this, "FOV").getValDouble() / (double)Minecraft.getMinecraft().gameSettings.fovSetting;
      ScaledResolution scaledResolution = event.getResolution();
      drawCircle228((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), (float)((int)(p * 485.0D)), Color.RED.getRGB(), 360, 0.5F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void drawCircle228(float n, float n2, float n3, int n4, int n5, float width) {
      float n6 = (float)(n4 >> 24 & 255) / 255.0F;
      float n7 = (float)(n4 >> 16 & 255) / 255.0F;
      float n8 = (float)(n4 >> 8 & 255) / 255.0F;
      float n9 = (float)(n4 & 255) / 255.0F;
      boolean glIsEnabled = GL11.glIsEnabled(3042);
      boolean glIsEnabled2 = GL11.glIsEnabled(2848);
      boolean glIsEnabled3 = GL11.glIsEnabled(3553);
      if (!glIsEnabled) {
         GL11.glEnable(3042);
      }

      if (!glIsEnabled2) {
         GL11.glEnable(2848);
      }

      if (glIsEnabled3) {
         GL11.glDisable(3553);
      }

      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(n7, n8, n9, n6);
      GL11.glLineWidth(width);
      GL11.glBegin(3);

      for(int i = 0; i <= n5; ++i) {
         GL11.glVertex2d((double)n + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)n3, (double)n2 + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)n3);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      if (glIsEnabled3) {
         GL11.glEnable(3553);
      }

      if (!glIsEnabled2) {
         GL11.glDisable(2848);
      }

      if (!glIsEnabled) {
         GL11.glDisable(3042);
      }

   }
}
