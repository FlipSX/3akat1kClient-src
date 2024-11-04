package com.Blood.Ware.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class MathUtils {
   public static final float deg2Rad = 0.017453292F;
   private static final Random random = new Random();

   public static double[] directionSpeedNoForward(double n) {
      Minecraft getMinecraft = Minecraft.getMinecraft();
      float moveForward = 1.0F;
      if (getMinecraft.gameSettings.keyBindLeft.isPressed() || getMinecraft.gameSettings.keyBindRight.isPressed() || getMinecraft.gameSettings.keyBindBack.isPressed() || getMinecraft.gameSettings.keyBindForward.isPressed()) {
         moveForward = getMinecraft.player.movementInput.moveForward;
      }

      float moveStrafe = getMinecraft.player.movementInput.moveStrafe;
      float n2 = getMinecraft.player.prevRotationYaw + (getMinecraft.player.rotationYaw - getMinecraft.player.prevRotationYaw) * getMinecraft.getRenderPartialTicks();
      if (moveForward != 0.0F) {
         if (moveStrafe > 0.0F) {
            n2 += (float)(moveForward > 0.0F ? -45 : 45);
         } else if (moveStrafe < 0.0F) {
            n2 += (float)(moveForward > 0.0F ? 45 : -45);
         }

         moveStrafe = 0.0F;
         if (moveForward > 0.0F) {
            moveForward = 1.0F;
         } else if (moveForward < 0.0F) {
            moveForward = -1.0F;
         }
      }

      double sin = Math.sin(Math.toRadians((double)(n2 + 90.0F)));
      double cos = Math.cos(Math.toRadians((double)(n2 + 90.0F)));
      return new double[]{(double)moveForward * n * cos + (double)moveStrafe * n * sin, (double)moveForward * n * sin - (double)moveStrafe * n * cos};
   }

   public static boolean isInteger(Double n) {
      return n == Math.floor(n) && !Double.isInfinite(n);
   }

   public static double roundToPlace(double val, int newScale) {
      if (newScale < 0) {
         throw new IllegalArgumentException();
      } else {
         return (new BigDecimal(val)).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
      }
   }

   public static double[] directionSpeed(double n) {
      Minecraft getMinecraft = Minecraft.getMinecraft();
      float moveForward = getMinecraft.player.movementInput.moveForward;
      float moveStrafe = getMinecraft.player.movementInput.moveStrafe;
      float n2 = getMinecraft.player.prevRotationYaw + (getMinecraft.player.rotationYaw - getMinecraft.player.prevRotationYaw) * getMinecraft.getRenderPartialTicks();
      if (moveForward != 0.0F) {
         if (moveStrafe > 0.0F) {
            n2 += (float)(moveForward > 0.0F ? -45 : 45);
         } else if (moveStrafe < 0.0F) {
            n2 += (float)(moveForward > 0.0F ? 45 : -45);
         }

         moveStrafe = 0.0F;
         if (moveForward > 0.0F) {
            moveForward = 1.0F;
         } else if (moveForward < 0.0F) {
            moveForward = -1.0F;
         }
      }

      double sin = Math.sin(Math.toRadians((double)(n2 + 90.0F)));
      double cos = Math.cos(Math.toRadians((double)(n2 + 90.0F)));
      return new double[]{(double)moveForward * n * cos + (double)moveStrafe * n * sin, (double)moveForward * n * sin - (double)moveStrafe * n * cos};
   }

   public static float lerp(float n, float n2, float n3) {
      return n + n3 * (n2 - n);
   }

   public static void drawUnfilledCircle(float n, float n2, float n3, float n4, int n5) {
      float n6 = (float)(n5 >> 16 & 255) / 255.0F;
      float n7 = (float)(n5 >> 8 & 255) / 255.0F;
      float n8 = (float)(n5 & 255) / 255.0F;
      float n9 = (float)(n5 >> 24 & 255) / 255.0F;
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GlStateManager.enableBlend();
      GL11.glColor4f(n6, n7, n8, n9);
      GL11.glLineWidth(n4);
      GL11.glBegin(2);

      for(int i = 0; i <= 360; ++i) {
         GL11.glVertex2d((double)n + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)n3, (double)n2 + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)n3);
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
      GlStateManager.disableBlend();
   }

   public static int getRandomInRange(int n, int n2) {
      return (int)((double)n2 + (double)(n - n2) * random.nextDouble());
   }

   public static BigDecimal round(float f, double n) {
      return (new BigDecimal(Float.toString(f))).setScale((int)n, 4);
   }

   public static double[] getDirection(float n) {
      return new double[]{-Math.sin(Math.toRadians((double)n)), Math.cos(Math.toRadians((double)n))};
   }

   public static double randomize(double n, double n2) {
      double n3 = (new Random()).nextDouble() * (n2 - n);
      if (n3 > n2) {
         n3 = n2;
      }

      double n4;
      if ((n4 = n3 + n) > n2) {
         n4 = n2;
      }

      return n4;
   }

   public static int randomize(int n, int n2) {
      return -n2 + (int)(Math.random() * (double)(n - -n2 + 1));
   }

   public static double random(double n, double n2) {
      return Math.random() * (n2 - n) + n;
   }

   public static float sqrt_double(double a) {
      return (float)Math.sqrt(a);
   }

   public static double roundToDecimalPlace(double n, double n2) {
      double n3 = n2 / 2.0D;
      double val = Math.floor(n / n2) * n2;
      return n >= val + n3 ? (new BigDecimal(Math.ceil(n / n2) * n2, MathContext.DECIMAL64)).stripTrailingZeros().doubleValue() : (new BigDecimal(val, MathContext.DECIMAL64)).stripTrailingZeros().doubleValue();
   }

   public static float clamp(float n, float n2, float n3) {
      if (n <= n2) {
         n = n2;
      }

      if (n >= n3) {
         n = n3;
      }

      return n;
   }

   public static double degToRad(double n) {
      return n * 0.01745329238474369D;
   }

   public static Vec3d interpolateVec3d(Vec3d vec3d, Vec3d vec3d2, float n) {
      return vec3d.subtract(vec3d2).scale((double)n).add(vec3d2);
   }

   public static double preciseRound(double n, double b) {
      double pow = Math.pow(10.0D, b);
      return (double)Math.round(n * pow) / pow;
   }

   public static Vec3d direction(float n) {
      return new Vec3d(Math.cos(degToRad((double)(n + 90.0F))), 0.0D, Math.sin(degToRad((double)(n + 90.0F))));
   }

   public static double randomNumber(double n, double n2) {
      return Math.random() * (n - n2) + n2;
   }

   public static double getRandomInRange(double n, double n2) {
      return n2 + (n - n2) * random.nextDouble();
   }

   public static double getNormalDouble(double val) {
      return (new BigDecimal(val)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
   }

   public static double getNormalDouble(double val, int newScale) {
      return (new BigDecimal(val)).setScale(newScale, RoundingMode.HALF_EVEN).doubleValue();
   }

   public static boolean isEven(int n) {
      return n % 2 == 0;
   }

   public static float wrapAngleTo180_float(float n) {
      n %= 360.0F;
      if (n >= 180.0F) {
         n -= 360.0F;
      }

      if (n < -180.0F) {
         n += 360.0F;
      }

      return n;
   }

   public static float random(float n, float n2) {
      return (float)(Math.random() * (double)(n2 - n) + (double)n);
   }

   public static Vec3d interpolateEntity(Entity entity) {
      double n = (double)Minecraft.getMinecraft().getRenderPartialTicks();
      return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
   }

   public static float[] constrainAngle(float[] array) {
      array[0] %= 360.0F;

      for(array[1] %= 360.0F; array[0] <= -180.0F; array[0] += 360.0F) {
      }

      while(array[1] <= -180.0F) {
         array[1] += 360.0F;
      }

      while(array[0] > 180.0F) {
         array[0] -= 360.0F;
      }

      while(array[1] > 180.0F) {
         array[1] -= 360.0F;
      }

      return array;
   }

   public static double getIncremental(double n, double n2) {
      double n3 = 1.0D / n2;
      return (double)Math.round(n * n3) / n3;
   }
}
