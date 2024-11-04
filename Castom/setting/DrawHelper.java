package Castom.setting;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class DrawHelper {
   private static int test;
   protected static float zLevel;
   private static int time;
   private static boolean anim;
   private static float alpheble;
   private static float animtest;
   private static final Frustum frustrum = new Frustum();
   private static Minecraft mc = Minecraft.getMinecraft();

   private static boolean isInViewFrustrum(AxisAlignedBB axisAlignedBB) {
      Entity getRenderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      frustrum.setPosition(getRenderViewEntity.posX, getRenderViewEntity.posY, getRenderViewEntity.posZ);
      return frustrum.isBoundingBoxInFrustum(axisAlignedBB);
   }

   public static int toRGBA(float n, float n2, float n3, float n4) {
      return toRGBA((int)(n * 255.0F), (int)(n2 * 255.0F), (int)(n3 * 255.0F), (int)(n4 * 255.0F));
   }

   public static int reAlpha(int rgb, float a) {
      Color color = new Color(rgb);
      return (new Color(0.003921569F * (float)color.getRed(), 0.003921569F * (float)color.getGreen(), 0.003921569F * (float)color.getBlue(), a)).getRGB();
   }

   public static void enableSmoothLine(float n) {
      GL11.glDisable(3008);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glEnable(2884);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glLineWidth(n);
   }

   public static void startSmooth() {
      GL11.glEnable(2848);
      GL11.glEnable(2881);
      GL11.glEnable(2832);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glHint(3153, 4354);
   }

   public static void enableGL2D() {
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static Color getGradientOffset(Color color, Color color2, double n) {
      double n3;
      if (n > 1.0D) {
         n3 = n % 1.0D;
         n = (int)n % 2 == 0 ? n3 : 1.0D - n3;
      }

      n3 = 1.0D - n;
      return new Color((int)((double)color.getRed() * n3 + (double)color2.getRed() * n), (int)((double)color.getGreen() * n3 + (double)color2.getGreen() * n), (int)((double)color.getBlue() * n3 + (double)color2.getBlue() * n));
   }

   public static int getColor(int n, int n2, int n3) {
      return getColor(n, n2, n3, 255);
   }

   public static int getColor(int n, int n2) {
      return getColor(n, n, n, n2);
   }

   public static void drawRect2(double n, double n2, double n3, double n4, int n5) {
      drawRect(n, n2, n + n3, n2 + n4, n5);
   }

   public static double interpolate(double n, double n2, double n3) {
      return n2 + (n - n2) * n3;
   }

   public static int getTeamColor(Entity entity) {
      return entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf((new StringBuilder()).append("пїЅf[пїЅcRпїЅf]пїЅc").append(entity.getName()))) ? getColor((new Color(255, 60, 60)).getRGB()) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf((new StringBuilder()).append("пїЅf[пїЅ9BпїЅf]пїЅ9").append(entity.getName()))) ? getColor((new Color(60, 60, 255)).getRGB()) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf((new StringBuilder()).append("пїЅf[пїЅeYпїЅf]пїЅe").append(entity.getName()))) ? getColor((new Color(255, 255, 60)).getRGB()) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf((new StringBuilder()).append("пїЅf[пїЅaGпїЅf]пїЅa").append(entity.getName()))) ? getColor((new Color(60, 255, 60)).getRGB()) : getColor((new Color(255, 255, 255)).getRGB()))));
   }

   public static void drawRect(double n, double n2, double n3, double n4, int n5) {
      double n7;
      if (n < n3) {
         n7 = n;
         n = n3;
         n3 = n7;
      }

      if (n2 < n4) {
         n7 = n2;
         n2 = n4;
         n4 = n7;
      }

      float n8 = (float)(n5 >> 24 & 255) / 255.0F;
      float n9 = (float)(n5 >> 16 & 255) / 255.0F;
      float n10 = (float)(n5 >> 8 & 255) / 255.0F;
      float n11 = (float)(n5 & 255) / 255.0F;
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(n9, n10, n11, n8);
      getBuffer.begin(7, DefaultVertexFormats.POSITION);
      getBuffer.pos(n, n4, 0.0D).endVertex();
      getBuffer.pos(n3, n4, 0.0D).endVertex();
      getBuffer.pos(n3, n2, 0.0D).endVertex();
      getBuffer.pos(n, n2, 0.0D).endVertex();
      getInstance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static int getRandomColor() {
      char[] charArray = "012345678".toCharArray();
      String value = "0x";

      for(int i = 0; i < 6; ++i) {
         value = String.valueOf((new StringBuilder()).append(value).append(charArray[(new Random()).nextInt(charArray.length)]));
      }

      return Integer.decode(value);
   }

   public static void drawSelectionBoundingBox(AxisAlignedBB axisAlignedBB) {
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      getBuffer.begin(3, DefaultVertexFormats.POSITION);
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getInstance.draw();
   }

   public static int astolfoColors5(float n, float n2, float saturation, float n3) {
      float n4 = 1800.0F;

      float n5;
      for(n5 = (float)(System.currentTimeMillis() % (long)((int)n4)) + (n2 - n) * n3; n5 > n4; n5 -= n4) {
      }

      float n6 = n5 / n4;
      if ((double)n6 > 0.5D) {
         n6 = 0.5F - (n6 - 0.5F);
      }

      return Color.HSBtoRGB(n6 + 0.5F, saturation, 1.0F);
   }

   public static Color TwoColoreffect(Color color, Color color2, double n) {
      float clamp = MathHelper.clamp((float)Math.sin(18.84955592153876D * (n / 4.0D % 1.0D)) / 2.0F + 0.5F, 0.0F, 1.0F);
      return new Color(lerp((float)color.getRed() / 255.0F, (float)color2.getRed() / 255.0F, clamp), lerp((float)color.getGreen() / 255.0F, (float)color2.getGreen() / 255.0F, clamp), lerp((float)color.getBlue() / 255.0F, (float)color2.getBlue() / 255.0F, clamp));
   }

   public static int[] getFractionIndicies(float[] array, float n) {
      int[] array2 = new int[2];

      int n2;
      for(n2 = 0; n2 < array.length && array[n2] <= n; ++n2) {
      }

      if (n2 >= array.length) {
         n2 = array.length - 1;
      }

      array2[0] = n2 - 1;
      array2[1] = n2;
      return array2;
   }

   public static Color rainbow2(int n, float s, float b) {
      return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + (long)n) / 16L)) % 360.0D / 360.0D), s, b);
   }

   public static void drawImage(ResourceLocation resourceLocation, int n, int n2, int n3, int n4, int color) {
      GL11.glPushMatrix();
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      setColor(color);
      mc.getTextureManager().bindTexture(resourceLocation);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
      Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0F, 0.0F, n3, n4, (float)n3, (float)n4);
      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glPopMatrix();
   }

   public static void prepareScissorBox(float n, float n2, float n3, float n4) {
      ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
      int getScaleFactor = scaledResolution.getScaleFactor();
      GL11.glScissor((int)(n * (float)getScaleFactor), (int)(((float)scaledResolution.getScaledHeight() - n4) * (float)getScaleFactor), (int)((n3 - n) * (float)getScaleFactor), (int)((n4 - n2) * (float)getScaleFactor));
   }

   public static Color astolfoColor(int n, int n2) {
      float n3 = 2900.0F;

      float n4;
      for(n4 = (float)(System.currentTimeMillis() % (long)((int)n3)) + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {
      }

      float n5 = n4 / n3;
      if ((double)n5 > 0.5D) {
         n5 = 0.5F - (n5 - 0.5F);
      }

      return new Color(n5 + 0.5F, 0.5F, 1.0F);
   }

   public static void drawFilledBox(AxisAlignedBB axisAlignedBB) {
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      getBuffer.begin(7, DefaultVertexFormats.POSITION);
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      getInstance.draw();
   }

   public static void drawOutline(AxisAlignedBB axisAlignedBB, float n, Color color) {
      GL11.glPushMatrix();
      GL11.glLineWidth(n);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
      getInstance.draw();
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glPopMatrix();
   }

   public static int getColor(int n, int n2, int n3, int n4) {
      return 0 | n4 << 24 | n << 16 | n2 << 8 | n3;
   }

   public static int rainbow(int n, float s, float b) {
      return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + (long)n) / 16L)) % 360.0D / 360.0D), s, b).getRGB();
   }

   public static void disableSmoothLine() {
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glEnable(3008);
      GL11.glDepthMask(true);
      GL11.glCullFace(1029);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static Color getRainbow(int n, int n2) {
      return Color.getHSBColor((float)((System.currentTimeMillis() + (long)n) % (long)n2) / (float)n2, 0.7F, 1.0F);
   }

   public static float lerp(float n, float n2, float n3) {
      return n + n3 * (n2 - n);
   }

   public static void glColor(Color color, int n) {
      glColor(color, (float)n / 255.0F);
   }

   public static void endSmooth() {
      GL11.glDisable(2848);
      GL11.glDisable(2881);
      GL11.glEnable(2832);
   }

   public static void drawGradientSideways(double n, double n2, double n3, double n4, int n5, int n6) {
      float n7 = (float)(n5 >> 24 & 255) / 255.0F;
      float n8 = (float)(n5 >> 16 & 255) / 255.0F;
      float n9 = (float)(n5 >> 8 & 255) / 255.0F;
      float n10 = (float)(n5 & 255) / 255.0F;
      float n11 = (float)(n6 >> 24 & 255) / 255.0F;
      float n12 = (float)(n6 >> 16 & 255) / 255.0F;
      float n13 = (float)(n6 >> 8 & 255) / 255.0F;
      float n14 = (float)(n6 & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(n8, n9, n10, n7);
      GL11.glVertex2d(n, n2);
      GL11.glVertex2d(n, n4);
      GL11.glColor4f(n12, n13, n14, n11);
      GL11.glVertex2d(n3, n4);
      GL11.glVertex2d(n3, n2);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void drawVGradientRect(float n, float n2, float n3, float n4, int n5, int n6) {
      float n7 = (float)(n5 >> 24 & 255) / 255.0F;
      float n8 = (float)(n5 >> 16 & 255) / 255.0F;
      float n9 = (float)(n5 >> 8 & 255) / 255.0F;
      float n10 = (float)(n5 & 255) / 255.0F;
      float n11 = (float)(n6 >> 24 & 255) / 255.0F;
      float n12 = (float)(n6 >> 16 & 255) / 255.0F;
      float n13 = (float)(n6 >> 8 & 255) / 255.0F;
      float n14 = (float)(n6 & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      getBuffer.pos((double)n3, (double)n2, 0.0D).color(n8, n9, n10, n7).endVertex();
      getBuffer.pos((double)n, (double)n2, 0.0D).color(n8, n9, n10, n7).endVertex();
      getBuffer.pos((double)n, (double)n4, 0.0D).color(n12, n13, n14, n11).endVertex();
      getBuffer.pos((double)n3, (double)n4, 0.0D).color(n12, n13, n14, n11).endVertex();
      getInstance.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static int getColor(int n) {
      return getColor(n, n, n, 255);
   }

   public static Color blend(Color color, Color color2, double n) {
      float n2 = (float)n;
      float n3 = 1.0F - n2;
      float[] compArray = new float[3];
      float[] compArray2 = new float[3];
      color.getColorComponents(compArray);
      color2.getColorComponents(compArray2);
      float r = compArray[0] * n2 + compArray2[0] * n3;
      float g = compArray[1] * n2 + compArray2[1] * n3;
      float b = compArray[2] * n2 + compArray2[2] * n3;
      if (r < 0.0F) {
         r = 0.0F;
      } else if (r > 255.0F) {
         r = 255.0F;
      }

      if (g < 0.0F) {
         g = 0.0F;
      } else if (g > 255.0F) {
         g = 255.0F;
      }

      if (b < 0.0F) {
         b = 0.0F;
      } else if (b > 255.0F) {
         b = 255.0F;
      }

      Color color3 = null;

      try {
         color3 = new Color(r, g, b);
      } catch (IllegalArgumentException var13) {
         NumberFormat.getNumberInstance();
      }

      return color3;
   }

   public static void drawNewRect(double n, double n2, double n3, double n4, int n5) {
      double n7;
      if (n < n3) {
         n7 = n;
         n = n3;
         n3 = n7;
      }

      if (n2 < n4) {
         n7 = n2;
         n2 = n4;
         n4 = n7;
      }

      float n8 = (float)(n5 >> 24 & 255) / 255.0F;
      float n9 = (float)(n5 >> 16 & 255) / 255.0F;
      float n10 = (float)(n5 >> 8 & 255) / 255.0F;
      float n11 = (float)(n5 & 255) / 255.0F;
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.color(n9, n10, n11, n8);
      getBuffer.begin(7, DefaultVertexFormats.POSITION);
      getBuffer.pos(n, n4, 0.0D).endVertex();
      getBuffer.pos(n3, n4, 0.0D).endVertex();
      getBuffer.pos(n3, n2, 0.0D).endVertex();
      getBuffer.pos(n, n2, 0.0D).endVertex();
      getInstance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void glColor(Color color, float n) {
      GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, n);
   }

   public static Color fade(Color color, int n, int n2) {
      float[] hsbvals = new float[3];
      Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbvals);
      hsbvals[2] = (0.5F + 0.5F * Math.abs(((float)(System.currentTimeMillis() % 2000L) / 1000.0F + (float)n / (float)n2 * 2.0F) % 2.0F - 1.0F)) % 2.0F;
      return new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]));
   }

   public static int setColor(int n) {
      float n2 = (float)(n >> 24 & 255) / 255.0F;
      GL11.glColor4f((float)(n >> 16 & 255) / 255.0F, (float)(n >> 8 & 255) / 255.0F, (float)(n & 255) / 255.0F, n2 == 0.0F ? 1.0F : n2);
      return n;
   }

   public static Color getHealthColor(EntityLivingBase entityLivingBase) {
      float getHealth = entityLivingBase.getHealth();
      float[] array = new float[]{0.0F, 0.15F, 0.55F, 0.7F, 0.9F};
      Color[] array2 = new Color[]{new Color(133, 0, 0), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN};
      float n = getHealth / entityLivingBase.getMaxHealth();
      return getHealth >= 0.0F ? blendColors(array, array2, n).brighter() : array2[0];
   }

   public static void drawHead(ResourceLocation resourceLocation, int n, int n2, int n3, int n4) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      mc.getTextureManager().bindTexture(resourceLocation);
      Gui.drawScaledCustomSizeModalRect(n, n2, 8.0F, 8.0F, 8, 8, n3, n4, 64.0F, 64.0F);
   }

   public static void drawFilledCircle(int n, int n2, float n3, Color color) {
      int n4 = true;
      double n5 = 0.12566370614359174D;
      GL11.glPushAttrib(8192);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glBegin(6);

      for(int i = 0; i < 50; ++i) {
         float n6 = (float)((double)n3 * Math.sin((double)i * 0.12566370614359174D));
         float n7 = (float)((double)n3 * Math.cos((double)i * 0.12566370614359174D));
         GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
         GL11.glVertex2f((float)n + n6, (float)n2 + n7);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnd();
      GL11.glPopAttrib();
   }

   public static void drawGradientRect(double n, double n2, double n3, double n4, int n5, int n6) {
      float n7 = (float)(n5 >> 24 & 255) / 255.0F;
      float n8 = (float)(n5 >> 16 & 255) / 255.0F;
      float n9 = (float)(n5 >> 8 & 255) / 255.0F;
      float n10 = (float)(n5 & 255) / 255.0F;
      float n11 = (float)(n6 >> 24 & 255) / 255.0F;
      float n12 = (float)(n6 >> 16 & 255) / 255.0F;
      float n13 = (float)(n6 >> 8 & 255) / 255.0F;
      float n14 = (float)(n6 & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      getBuffer.pos(n3, n2, (double)zLevel).color(n8, n9, n10, n7).endVertex();
      getBuffer.pos(n, n2, (double)zLevel).color(n8, n9, n10, n7).endVertex();
      getBuffer.pos(n, n4, (double)zLevel).color(n12, n13, n14, n11).endVertex();
      getBuffer.pos(n3, n4, (double)zLevel).color(n12, n13, n14, n11).endVertex();
      getInstance.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static int astolfoColors4(float n, float n2, float saturation) {
      float n3 = 1800.0F;

      float n4;
      for(n4 = (float)(System.currentTimeMillis() % (long)((int)n3)) + (n2 - n) * 12.0F; n4 > n3; n4 -= n3) {
      }

      float n5 = n4 / n3;
      if ((double)n5 > 0.5D) {
         n5 = 0.5F - (n5 - 0.5F);
      }

      return Color.HSBtoRGB(n5 + 0.5F, saturation, 1.0F);
   }

   public static Color setAlpha(Color color, int clamp) {
      clamp = MathHelper.clamp(clamp, 0, 255);
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), clamp);
   }

   public static void drawPolygonPart(double n, double n2, int n3, int n4, int n5, int n6) {
      float n7 = (float)(n5 >> 24 & 255) / 255.0F;
      float n8 = (float)(n5 >> 16 & 255) / 255.0F;
      float n9 = (float)(n5 >> 8 & 255) / 255.0F;
      float n10 = (float)(n5 & 255) / 255.0F;
      float n11 = (float)(n6 >> 24 & 255) / 255.0F;
      float n12 = (float)(n6 >> 16 & 255) / 255.0F;
      float n13 = (float)(n6 >> 8 & 255) / 255.0F;
      float n14 = (float)(n6 & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      getBuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
      getBuffer.pos(n, n2, 0.0D).color(n8, n9, n10, n7).endVertex();

      for(int i = n4 * 90; i <= n4 * 90 + 90; ++i) {
         double n15 = 6.283185307179586D * (double)i / 360.0D + Math.toRadians(180.0D);
         getBuffer.pos(n + Math.sin(n15) * (double)n3, n2 + Math.cos(n15) * (double)n3, 0.0D).color(n12, n13, n14, n11).endVertex();
      }

      getInstance.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static boolean isInViewFrustrum(Entity entity) {
      return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
   }

   public static Color blendColors(float[] array, Color[] array2, float n) {
      if (array == null) {
         throw new IllegalArgumentException("Fractions can't be null");
      } else if (array2 == null) {
         throw new IllegalArgumentException("Colours can't be null");
      } else if (array.length != array2.length) {
         throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
      } else {
         int[] fractionIndicies = getFractionIndicies(array, n);
         float[] array3 = new float[]{array[fractionIndicies[0]], array[fractionIndicies[1]]};
         Color[] array4 = new Color[]{array2[fractionIndicies[0]], array2[fractionIndicies[1]]};
         return blend(array4[0], array4[1], (double)(1.0F - (n - array3[0]) / (array3[1] - array3[0])));
      }
   }

   public static int astolfoColors(int n, int n2) {
      float n3 = 2900.0F;

      float n4;
      for(n4 = (float)(System.currentTimeMillis() % (long)((int)n3)) + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {
      }

      float n5;
      if ((double)(n5 = n4 / n3) > 0.5D) {
         n5 = 0.5F - (n5 - 0.5F);
      }

      return Color.HSBtoRGB(n5 + 0.5F, 0.5F, 1.0F);
   }

   public static final void drawSmoothRect(float n, float n2, float n3, float n4, int n5) {
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      drawRect((double)n, (double)n2, (double)n3, (double)n4, n5);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      drawRect((double)(n * 2.0F - 1.0F), (double)(n2 * 2.0F), (double)(n * 2.0F), (double)(n4 * 2.0F - 1.0F), n5);
      drawRect((double)(n * 2.0F), (double)(n2 * 2.0F - 1.0F), (double)(n3 * 2.0F), (double)(n2 * 2.0F), n5);
      drawRect((double)(n3 * 2.0F), (double)(n2 * 2.0F), (double)(n3 * 2.0F + 1.0F), (double)(n4 * 2.0F - 1.0F), n5);
      drawRect((double)(n * 2.0F), (double)(n4 * 2.0F - 1.0F), (double)(n3 * 2.0F), (double)(n4 * 2.0F), n5);
      GL11.glDisable(3042);
      GL11.glScalef(2.0F, 2.0F, 2.0F);
   }

   public static Color astolfoColors45(float n, float n2, float s, float n3) {
      float n4 = 1800.0F;

      float n5;
      for(n5 = (float)(System.currentTimeMillis() % (long)((int)n4)) + (n2 - n) * n3; n5 > n4; n5 -= n4) {
      }

      float n6 = n5 / n4;
      if ((double)n6 > 0.5D) {
         n6 = 0.5F - (n6 - 0.5F);
      }

      return Color.getHSBColor(n6 + 0.5F, s, 1.0F);
   }

   public static int toRGBA(int n, int n2, int n3, int n4) {
      return (n << 16) + (n2 << 8) + (n3 << 0) + (n4 << 24);
   }

   public static final void color(double n, double n2, double n3, double n4) {
      GL11.glColor4d(n, n2, n3, n4);
   }

   public static int color(int r, int g, int b, int a) {
      int a = 255;
      return (new Color(r, g, b, a)).getRGB();
   }

   public static final void color(Color white) {
      if (white == null) {
         white = Color.white;
      }

      color((double)((float)white.getRed() / 255.0F), (double)((float)white.getGreen() / 255.0F), (double)((float)white.getBlue() / 255.0F), (double)((float)white.getAlpha() / 255.0F));
   }

   public static void drawGlow(double n, double n2, double n3, double n4, int n5) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      drawVGradientRect((float)((int)n), (float)((int)n2), (float)((int)n3), (float)((int)(n2 + (n4 - n2) / 2.0D)), setAlpha(new Color(n5), 0).getRGB(), n5);
      drawVGradientRect((float)((int)n), (float)((int)(n2 + (n4 - n2) / 2.0D)), (float)((int)n3), (float)((int)n4), n5, setAlpha(new Color(n5), 0).getRGB());
      int n6 = (int)((n4 - n2) / 2.0D);
      drawPolygonPart(n, n2 + (n4 - n2) / 2.0D, n6, 0, n5, setAlpha(new Color(n5), 0).getRGB());
      drawPolygonPart(n, n2 + (n4 - n2) / 2.0D, n6, 1, n5, setAlpha(new Color(n5), 0).getRGB());
      drawPolygonPart(n3, n2 + (n4 - n2) / 2.0D, n6, 2, n5, setAlpha(new Color(n5), 0).getRGB());
      drawPolygonPart(n3, n2 + (n4 - n2) / 2.0D, n6, 3, n5, setAlpha(new Color(n5), 0).getRGB());
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static int astolfo(int n, float n2) {
      float n3 = 3000.0F;

      float abs;
      for(abs = Math.abs((float)(System.currentTimeMillis() % (long)n) + -n2 / 21.0F * 2.0F); abs > n3; abs -= n3) {
      }

      float n4;
      if ((double)(n4 = abs / n3) > 0.5D) {
         n4 = 0.5F - (n4 - 0.5F);
      }

      return Color.HSBtoRGB(n4 + 0.5F, 0.5F, 1.0F);
   }

   public static class Colors {
      public static final int RED = DrawHelper.toRGBA(255, 0, 0, 255);
      public static final int BLUE = DrawHelper.toRGBA(0, 0, 255, 255);
      public static final int BLACK = DrawHelper.toRGBA(0, 0, 0, 255);
      public static final int GRAY = DrawHelper.toRGBA(127, 127, 127, 255);
      public static final int DARK_RED = DrawHelper.toRGBA(64, 0, 0, 255);
      public static final int PURPLE = DrawHelper.toRGBA(163, 73, 163, 255);
      public static final int ORANGE = DrawHelper.toRGBA(255, 128, 0, 255);
      public static final int WHITE = DrawHelper.toRGBA(255, 255, 255, 255);
      public static final int YELLOW = DrawHelper.toRGBA(255, 255, 0, 255);
      public static final int GREEN = DrawHelper.toRGBA(0, 255, 0, 255);
   }
}
