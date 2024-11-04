package com.Blood.Ware.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class FovUtils {
   public static boolean isInAttackFOV(Entity entity, int fov) {
      return getDistanceFromMouse(entity) <= fov;
   }

   public static int getDistanceFromMouse(Entity entity) {
      float[] neededRotations = getNeededRotations(entity);
      if (neededRotations != null) {
         float neededYaw = Minecraft.getMinecraft().player.rotationYaw - neededRotations[0];
         float neededPitch = Minecraft.getMinecraft().player.rotationPitch - neededRotations[1];
         float distanceFromMouse = MathHelper.sqrt(neededYaw * neededYaw + neededPitch * neededPitch);
         return (int)distanceFromMouse;
      } else {
         return -1;
      }
   }

   private static float[] getNeededRotations(Entity vec) {
      Vec3d eyesPos = getEyesPos();
      double diffX = vec.posX - eyesPos.x;
      double diffY = vec.posY - eyesPos.y;
      double diffZ = vec.posZ - eyesPos.z;
      double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
      float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
      float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
      return new float[]{Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapDegrees(yaw - Minecraft.getMinecraft().player.rotationYaw), Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapDegrees(pitch - Minecraft.getMinecraft().player.rotationPitch)};
   }

   private static Vec3d getEyesPos() {
      return new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ);
   }

   public static String getPlayerName(EntityPlayer player) {
      return player.getGameProfile() != null ? player.getGameProfile().getName() : "null";
   }
}
