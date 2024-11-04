package Bobr.util;

import org.lwjgl.opengl.GL11;

public final class RenderHelper {
   public static void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void enableGL2D() {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static void drawFullCircle(double d, double e, double r, int c) {
      r *= 2.0D;
      d *= 2.0D;
      e *= 2.0D;
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f2 = (float)(c >> 16 & 255) / 255.0F;
      float f3 = (float)(c >> 8 & 255) / 255.0F;
      float f4 = (float)(c & 255) / 255.0F;
      enableGL2D();
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glColor4f(f2, f3, f4, f);
      GL11.glBegin(6);

      for(int i = 0; i <= 360; ++i) {
         double x = Math.sin((double)i * 3.141592653589793D / 180.0D) * r;
         double y = Math.cos((double)i * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d(d + x, e + y);
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      disableGL2D();
   }
}
