package Castom.setting;

import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public final class RenderHelper {
   public static void customScaledObject2D(float n, float n2, float n3, float n4, float n5, float n6) {
      GL11.glTranslated((double)(n3 / 2.0F), (double)(n4 / 2.0F), 1.0D);
      GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0F * -n5), (double)(-n2 * n6 + n2 + n4 / 2.0F * -n6), 1.0D);
      GL11.glScaled((double)n5, (double)n6, 0.0D);
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

   public static void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void drawFullCircle(double n, double n2, double n3, int n4) {
      n3 *= 2.0D;
      n *= 2.0D;
      n2 *= 2.0D;
      float n5 = (float)(n4 >> 24 & 255) / 255.0F;
      float n6 = (float)(n4 >> 16 & 255) / 255.0F;
      float n7 = (float)(n4 >> 8 & 255) / 255.0F;
      float n8 = (float)(n4 & 255) / 255.0F;
      enableGL2D();
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glColor4f(n6, n7, n8, n5);
      GL11.glBegin(6);

      for(int i = 0; i <= 360; ++i) {
         GL11.glVertex2d(n + Math.sin((double)i * 3.141592653589793D / 180.0D) * n3, n2 + Math.cos((double)i * 3.141592653589793D / 180.0D) * n3);
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      disableGL2D();
   }

   public static void customScaledObject2D(float n, float n2, float n3, float n4, float n5) {
      GL11.glTranslated((double)(n3 / 2.0F), (double)(n4 / 2.0F), 1.0D);
      GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0F * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0F * -n5), 1.0D);
      GL11.glScaled((double)n5, (double)n5, 0.0D);
   }

   public static void drawImage(ResourceLocation resourceLocation, float n, float n2, float n3, float n4, Color color) {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      DrawHelper.setColor(color.getRGB());
      Gui.drawModalRectWithCustomSizedTexture((int)n, (int)n2, 0.0F, 0.0F, (int)n3, (int)n4, n3, n4);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
   }
}
