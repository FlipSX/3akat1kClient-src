package com.Blood.Ware.utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderUtil implements MinecraftHelper {
   private static final Frustum frustrum = new Frustum();
   private static final double DOUBLE_PI = 6.283185307179586D;

   public static double interpolate(double current, double old, double scale) {
      return old + (current - old) * scale;
   }

   public static void glRenderStart() {
      GL11.glPushMatrix();
      GL11.glPushAttrib(1048575);
      GL11.glEnable(3042);
      GL11.glDisable(2884);
      GL11.glDisable(3553);
   }

   public static void glRenderStop() {
      GL11.glEnable(3553);
      GL11.glEnable(2884);
      GL11.glDisable(3042);
      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public static void drawCircle(float x, float y, float radius, int color) {
      float f = (float)(color >> 24 & 255) / 255.0F;
      float f1 = (float)(color >> 16 & 255) / 255.0F;
      float f2 = (float)(color >> 8 & 255) / 255.0F;
      float f3 = (float)(color & 255) / 255.0F;
      boolean flag = GL11.glIsEnabled(3042);
      boolean flag1 = GL11.glIsEnabled(2848);
      boolean flag2 = GL11.glIsEnabled(3553);
      if (!flag) {
         GL11.glEnable(3042);
      }

      if (!flag1) {
         GL11.glEnable(2848);
      }

      if (flag2) {
         GL11.glDisable(3553);
      }

      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(9);

      for(int i = 0; i <= 360; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      if (flag2) {
         GL11.glEnable(3553);
      }

      if (!flag1) {
         GL11.glDisable(2848);
      }

      if (!flag) {
         GL11.glDisable(3042);
      }

   }

   public static void drawCircle228(float x, float y, float radius, int color, int jopaSlona) {
      float f = (float)(color >> 24 & 255) / 255.0F;
      float f1 = (float)(color >> 16 & 255) / 255.0F;
      float f2 = (float)(color >> 8 & 255) / 255.0F;
      float f3 = (float)(color & 255) / 255.0F;
      boolean flag = GL11.glIsEnabled(3042);
      boolean flag1 = GL11.glIsEnabled(2848);
      boolean flag2 = GL11.glIsEnabled(3553);
      if (!flag) {
         GL11.glEnable(3042);
      }

      if (!flag1) {
         GL11.glEnable(2848);
      }

      if (flag2) {
         GL11.glDisable(3553);
      }

      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glLineWidth(2.5F);
      GL11.glBegin(3);

      for(int i = 0; i <= jopaSlona; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      if (flag2) {
         GL11.glEnable(3553);
      }

      if (!flag1) {
         GL11.glDisable(2848);
      }

      if (!flag) {
         GL11.glDisable(3042);
      }

   }

   public static void drawCircle228(float x, float y, float radius, int lineWidth, int outsideC, int insideC, int jopaSlona) {
      drawCircle228(x, y, radius, insideC, jopaSlona);
   }

   public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor) {
      drawRect((double)xPos, (double)yPos, (double)width, (double)height, bgColor);
      float f = (float)(lineColor >> 24 & 255) / 255.0F;
      float f1 = (float)(lineColor >> 16 & 255) / 255.0F;
      float f2 = (float)(lineColor >> 8 & 255) / 255.0F;
      float f3 = (float)(lineColor & 255) / 255.0F;
      glRenderStart();
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glLineWidth(lineWidth);
      GL11.glEnable(2848);
      GL11.glBegin(1);
      GL11.glVertex2d((double)xPos, (double)yPos);
      GL11.glVertex2d((double)width, (double)yPos);
      GL11.glVertex2d((double)width, (double)yPos);
      GL11.glVertex2d((double)width, (double)height);
      GL11.glVertex2d((double)width, (double)height);
      GL11.glVertex2d((double)xPos, (double)height);
      GL11.glVertex2d((double)xPos, (double)height);
      GL11.glVertex2d((double)xPos, (double)yPos);
      GL11.glEnd();
      glRenderStop();
   }

   public static void setColor(Color c) {
      GL11.glColor4d((double)((float)c.getRed() / 255.0F), (double)((float)c.getGreen() / 255.0F), (double)((float)c.getBlue() / 255.0F), (double)((float)c.getAlpha() / 255.0F));
   }

   public static void drawUnfilledCircle(float x, float y, float radius, float lineWidth, int color) {
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      float f3 = (float)(color >> 24 & 255) / 255.0F;
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GlStateManager.enableBlend();
      GL11.glColor4f(f, f1, f2, f3);
      GL11.glLineWidth(lineWidth);
      GL11.glBegin(2);

      for(int i = 0; i <= 360; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
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

   public static void drawBorderedCircle(float x, float y, float radius, int lineWidth, int outsideC, int insideC) {
      drawCircle(x, y, radius, insideC);
      drawUnfilledCircle(x, y, radius, (float)lineWidth, outsideC);
   }

   public static float convertColor(int count, int color) {
      return (float)(color >> count & 255) / 255.0F;
   }

   public static void drawOctagon(float xPos, float yPos, float width, float height, float length, float angle, int color) {
      float f = convertColor(24, color);
      float f1 = convertColor(16, color);
      float f2 = convertColor(8, color);
      float f3 = convertColor(0, color);
      glRenderStart();
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(9);
      GL11.glVertex2d((double)(xPos + length), (double)yPos);
      GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
      GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
      GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
      GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
      GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)(xPos + length), (double)yPos);
      GL11.glEnd();
      glRenderStop();
   }

   public static void drawLinesAroundPlayer(Entity entity, double radius, float partialTicks, int points, double width, int color, float hight) {
      GL11.glPushMatrix();
      enableGL2D3();
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glDisable(2929);
      GL11.glLineWidth((float)width);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glBegin(3);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks - renderManager.viewerPosX;
      double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks - renderManager.viewerPosY + (double)hight;
      double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks - renderManager.viewerPosZ;
      color228(color);

      for(int i = 0; i <= points; ++i) {
         GL11.glVertex3d(x + radius * Math.cos((double)i * 6.283185307179586D / (double)points), y, z + radius * Math.sin((double)i * 6.283185307179586D / (double)points));
      }

      GL11.glEnd();
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glEnable(2929);
      GL11.glEnable(3553);
      disableGL2D3();
      GL11.glPopMatrix();
   }

   public static void renderItem(ItemStack itemStack, int x, int y) {
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.enableDepth();
      RenderHelper.enableGUIStandardItemLighting();
      mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
      mc.getRenderItem().renderItemOverlays(mc.fontRenderer, itemStack, x, y);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableDepth();
   }

   public static void drawRectWithEdge(double x, double y, double width, double height, Color color, Color color2) {
      drawRect(x, y, x + width, y + height, color.getRGB());
      int c = color2.getRGB();
      drawRect(x - 1.0D, y, x, y + height, c);
      drawRect(x + width, y, x + width + 1.0D, y + height, c);
      drawRect(x - 1.0D, y - 1.0D, x + width + 1.0D, y, c);
      drawRect(x - 1.0D, y + height, x + width + 1.0D, y + height + 1.0D, c);
   }

   public static void drawRoundedRect(double x, double y, double x1, double y1, int borderC, int insideC) {
      drawRect(x + 0.5D, y, x1 - 0.5D, y + 0.5D, insideC);
      drawRect(x + 0.5D, y1 - 0.5D, x1 - 0.5D, y1, insideC);
      drawRect(x, y + 0.5D, x1, y1 - 0.5D, insideC);
   }

   public static void drawRoundedRect(int xCoord, int yCoord, int xSize, int ySize, int colour) {
      int width = xCoord + xSize;
      int height = yCoord + ySize;
      drawRect((double)(xCoord + 1), (double)yCoord, (double)(width - 1), (double)height, colour);
      drawRect((double)xCoord, (double)(yCoord + 1), (double)width, (double)(height - 1), colour);
   }

   public static void drawBoundingBox(AxisAlignedBB axisalignedbb) {
      GL11.glBegin(7);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
      GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      GL11.glEnd();
   }

   public static void drawFilledCircle(int xx, int yy, float radius, Color color) {
      int sections = 6;
      double dAngle = 6.283185307179586D / (double)sections;
      GL11.glPushAttrib(8192);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glBegin(6);

      for(int i = 0; i < sections; ++i) {
         float x = (float)((double)radius * Math.sin((double)i * dAngle));
         float y = (float)((double)radius * Math.cos((double)i * dAngle));
         GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
         GL11.glVertex2f((float)xx + x, (float)yy + y);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnd();
      GL11.glPopAttrib();
   }

   public static final void drawSmoothRect(float left, float top, float right, float bottom, int color) {
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      drawRect((double)left, (double)top, (double)right, (double)bottom, color);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      drawRect((double)(left * 2.0F - 1.0F), (double)(top * 2.0F), (double)(left * 2.0F), (double)(bottom * 2.0F - 1.0F), color);
      drawRect((double)(left * 2.0F), (double)(top * 2.0F - 1.0F), (double)(right * 2.0F), (double)(top * 2.0F), color);
      drawRect((double)(right * 2.0F), (double)(top * 2.0F), (double)(right * 2.0F + 1.0F), (double)(bottom * 2.0F - 1.0F), color);
      drawRect((double)(left * 2.0F), (double)(bottom * 2.0F - 1.0F), (double)(right * 2.0F), (double)(bottom * 2.0F), color);
      GL11.glDisable(3042);
      GL11.glScalef(2.0F, 2.0F, 2.0F);
   }

   public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
      GL11.glEnable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPushMatrix();
      Minecraft.getMinecraft().getTextureManager().bindTexture(image);
      Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, width, height, (float)width, (float)height);
      disableGL2D();
      GL11.glPopMatrix();
   }

   public static int raindbow(int deley) {
      double raindbowState = Math.ceil((double)((System.currentTimeMillis() + (long)deley) / 20L));
      raindbowState %= 360.0D;
      return Color.getHSBColor((float)(raindbowState / 360.0D), 0.5F, 1.0F).getRGB();
   }

   public static void drawCircle(float cx, double cy, float r, int minus, int c) {
      GL11.glPushMatrix();
      cx *= 2.0F;
      cy *= 2.0D;
      GlStateManager.glLineWidth(6.0F);
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      float theta = 0.017453292F;
      float p = (float)Math.cos((double)theta);
      float s = (float)Math.sin((double)theta);
      float x = r *= 2.0F;
      float y = 0.0F;
      GL11.glEnable(2848);
      enableGL2D();
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glPointSize(6.0F);
      GL11.glBegin(3);
      int[] counter = new int[]{1};

      for(int ii = 0; ii < 360 - minus; ++ii) {
         c = raindbow(counter[0] * 10);
         f = (float)(c >> 24 & 255) / 255.0F;
         f1 = (float)(c >> 16 & 255) / 255.0F;
         f2 = (float)(c >> 8 & 255) / 255.0F;
         f3 = (float)(c & 255) / 255.0F;
         GL11.glColor4f(f1, f2, f3, f);
         GL11.glVertex2f(x + cx, (float)((double)y + cy));
         float t = x;
         x = p * x - s * y;
         y = s * t + p * y;
         int var10002 = counter[0]++;
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      disableGL2D();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.glLineWidth(1.0F);
      GL11.glPopMatrix();
   }

   public static void prepareScissorBox(float x, float y, float x2, float y2) {
      ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
      int factor = scale.getScaleFactor();
      GL11.glScissor((int)(x * (float)factor), (int)(((float)scale.getScaledHeight() - y2) * (float)factor), (int)((x2 - x) * (float)factor), (int)((y2 - y) * (float)factor));
   }

   public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
      float f = (float)(col1 >> 24 & 255) / 255.0F;
      float f1 = (float)(col1 >> 16 & 255) / 255.0F;
      float f2 = (float)(col1 >> 8 & 255) / 255.0F;
      float f3 = (float)(col1 & 255) / 255.0F;
      float f4 = (float)(col2 >> 24 & 255) / 255.0F;
      float f5 = (float)(col2 >> 16 & 255) / 255.0F;
      float f6 = (float)(col2 >> 8 & 255) / 255.0F;
      float f7 = (float)(col2 & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glVertex2d(left, top);
      GL11.glVertex2d(left, bottom);
      GL11.glColor4f(f5, f6, f7, f4);
      GL11.glVertex2d(right, bottom);
      GL11.glVertex2d(right, top);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static void drawPolygonPart(double x, double y, int radius, int part, int color, int endcolor) {
      float alpha = (float)(color >> 24 & 255) / 255.0F;
      float red = (float)(color >> 16 & 255) / 255.0F;
      float green = (float)(color >> 8 & 255) / 255.0F;
      float blue = (float)(color & 255) / 255.0F;
      float alpha2 = (float)(endcolor >> 24 & 255) / 255.0F;
      float red2 = (float)(endcolor >> 16 & 255) / 255.0F;
      float green2 = (float)(endcolor >> 8 & 255) / 255.0F;
      float blue2 = (float)(endcolor & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
      bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
      double TWICE_PI = 6.283185307179586D;

      for(int i = part * 90; i <= part * 90 + 90; ++i) {
         double angle = 6.283185307179586D * (double)i / 360.0D + Math.toRadians(180.0D);
         bufferbuilder.pos(x + Math.sin(angle) * (double)radius, y + Math.cos(angle) * (double)radius, 0.0D).color(red2, green2, blue2, alpha2).endVertex();
      }

      tessellator.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static void drawVGradientRect(float left, float top, float right, float bottom, int startColor, int endColor) {
      float f = (float)(startColor >> 24 & 255) / 255.0F;
      float f2 = (float)(startColor >> 16 & 255) / 255.0F;
      float f3 = (float)(startColor >> 8 & 255) / 255.0F;
      float f4 = (float)(startColor & 255) / 255.0F;
      float f5 = (float)(endColor >> 24 & 255) / 255.0F;
      float f6 = (float)(endColor >> 16 & 255) / 255.0F;
      float f7 = (float)(endColor >> 8 & 255) / 255.0F;
      float f8 = (float)(endColor & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
      bufferbuilder.pos((double)right, (double)top, 0.0D).color(f2, f3, f4, f).endVertex();
      bufferbuilder.pos((double)left, (double)top, 0.0D).color(f2, f3, f4, f).endVertex();
      bufferbuilder.pos((double)left, (double)bottom, 0.0D).color(f6, f7, f8, f5).endVertex();
      bufferbuilder.pos((double)right, (double)bottom, 0.0D).color(f6, f7, f8, f5).endVertex();
      tessellator.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static Color injectAlpha(Color color, int alpha) {
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
   }

   public static void drawGlow(double x, double y, double x1, double y1, int color, double alpha) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      drawVGradientRect((float)((int)x), (float)((int)y), (float)((int)x1), (float)((int)(y + (y1 - y) / 2.0D)), injectAlpha(new Color(color), 0).getRGB(), injectAlpha(new Color(color), (int)alpha).getRGB());
      drawVGradientRect((float)((int)x), (float)((int)(y + (y1 - y) / 2.0D)), (float)((int)x1), (float)((int)y1), injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
      int radius = (int)((y1 - y) / 2.0D);
      drawPolygonPart(x, y + (y1 - y) / 2.0D, radius, 0, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
      drawPolygonPart(x, y + (y1 - y) / 2.0D, radius, 1, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
      drawPolygonPart(x1, y + (y1 - y) / 2.0D, radius, 2, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
      drawPolygonPart(x1, y + (y1 - y) / 2.0D, radius, 3, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
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

   public static void relativeRect(float left, float top, float right, float bottom, int color) {
      float f3;
      if (left < right) {
         f3 = left;
         left = right;
         right = f3;
      }

      if (top < bottom) {
         f3 = top;
         top = bottom;
         bottom = f3;
      }

      f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(f, f1, f2, f3);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
      bufferBuilder.pos((double)left, (double)bottom, 0.0D).endVertex();
      bufferBuilder.pos((double)right, (double)bottom, 0.0D).endVertex();
      bufferBuilder.pos((double)right, (double)top, 0.0D).endVertex();
      bufferBuilder.pos((double)left, (double)top, 0.0D).endVertex();
      tessellator.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void drawBorderedRect(double left, double top, double right, double bottom, double borderWidth, int insideColor, int borderColor, boolean borderIncludedInBounds) {
      drawRect(left - (!borderIncludedInBounds ? borderWidth : 0.0D), top - (!borderIncludedInBounds ? borderWidth : 0.0D), right + (!borderIncludedInBounds ? borderWidth : 0.0D), bottom + (!borderIncludedInBounds ? borderWidth : 0.0D), borderColor);
      drawRect(left + (borderIncludedInBounds ? borderWidth : 0.0D), top + (borderIncludedInBounds ? borderWidth : 0.0D), right - (borderIncludedInBounds ? borderWidth : 0.0D), bottom - (borderIncludedInBounds ? borderWidth : 0.0D), insideColor);
   }

   public static void drawRect(double left, double top, double right, double bottom, int color) {
      double j;
      if (left < right) {
         j = left;
         left = right;
         right = j;
      }

      if (top < bottom) {
         j = top;
         top = bottom;
         bottom = j;
      }

      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(f, f1, f2, f3);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
      bufferBuilder.pos(left, bottom, 0.0D).endVertex();
      bufferBuilder.pos(right, bottom, 0.0D).endVertex();
      bufferBuilder.pos(right, top, 0.0D).endVertex();
      bufferBuilder.pos(left, top, 0.0D).endVertex();
      tessellator.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void drawRectOpacity(double left, double top, double right, double bottom, float opacity) {
      double j;
      if (left < right) {
         j = left;
         left = right;
         right = j;
      }

      if (top < bottom) {
         j = top;
         top = bottom;
         bottom = j;
      }

      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(0.1F, 0.1F, 0.1F, opacity);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
      bufferBuilder.pos(left, bottom, 0.0D).endVertex();
      bufferBuilder.pos(right, bottom, 0.0D).endVertex();
      bufferBuilder.pos(right, top, 0.0D).endVertex();
      bufferBuilder.pos(left, top, 0.0D).endVertex();
      tessellator.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void drawNewRect(double left, double top, double right, double bottom, int color) {
      double j;
      if (left < right) {
         j = left;
         left = right;
         right = j;
      }

      if (top < bottom) {
         j = top;
         top = bottom;
         bottom = j;
      }

      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder vertexbuffer = tessellator.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.color(f, f1, f2, f3);
      vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(left, bottom, 0.0D).endVertex();
      vertexbuffer.pos(right, bottom, 0.0D).endVertex();
      vertexbuffer.pos(right, top, 0.0D).endVertex();
      vertexbuffer.pos(left, top, 0.0D).endVertex();
      tessellator.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static boolean isInViewFrustrum(Entity entity) {
      return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
   }

   public static void drawLinesAroundPlayer(Entity entity, double radius, float partialTicks, int points, float width, int color) {
      GL11.glPushMatrix();
      enableGL2D3();
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glDisable(2929);
      GL11.glLineWidth(width);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glBegin(3);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks - renderManager.viewerPosX;
      double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks - renderManager.viewerPosY;
      double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks - renderManager.viewerPosZ;
      color228(color);

      for(int i = 0; i <= points; ++i) {
         GL11.glVertex3d(x + radius * Math.cos((double)i * 6.283185307179586D / (double)points), y, z + radius * Math.sin((double)i * 6.283185307179586D / (double)points));
      }

      GL11.glEnd();
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glEnable(2929);
      GL11.glEnable(3553);
      disableGL2D3();
      GL11.glPopMatrix();
   }

   public static void enableGL2D3() {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static void disableGL2D3() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void color228(int color) {
      GL11.glColor4ub((byte)(color >> 16 & 255), (byte)(color >> 8 & 255), (byte)(color & 255), (byte)(color >> 24 & 255));
   }

   private static boolean isInViewFrustrum(AxisAlignedBB bb) {
      Entity current = mc.getRenderViewEntity();
      frustrum.setPosition(current.posX, current.posY, current.posZ);
      return frustrum.isBoundingBoxInFrustum(bb);
   }

   public static void glColor(int hex) {
      float alpha = (float)(hex >> 24 & 255) / 255.0F;
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      GL11.glColor4f(red, green, blue, alpha);
   }

   public static void blockEsp(BlockPos blockPos, Color c, double length, double length2) {
      double d = (double)blockPos.func_177958_n();
      double x = d - mc.getRenderManager().viewerPosX;
      double d2 = (double)blockPos.func_177956_o();
      Minecraft.getMinecraft().getRenderManager();
      double y = d2 - mc.getRenderManager().viewerPosY;
      double d3 = (double)blockPos.func_177952_p();
      Minecraft.getMinecraft().getRenderManager();
      double z = d3 - mc.getRenderManager().viewerPosZ;
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glColor4d((double)((float)c.getRed() / 255.0F), (double)((float)c.getGreen() / 255.0F), (double)((float)c.getBlue() / 255.0F), 0.15D);
      drawColorBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0D, z + length), 0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glColor4d(0.0D, 0.0D, 0.0D, 0.5D);
      drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0D, z + length));
      GL11.glLineWidth(2.0F);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void blockEspFrame(BlockPos blockPos, double red, double green, double blue) {
      double d = (double)blockPos.func_177958_n();
      double x = d - mc.getRenderManager().viewerPosX;
      double d2 = (double)blockPos.func_177956_o();
      Minecraft.getMinecraft().getRenderManager();
      double y = d2 - mc.getRenderManager().viewerPosY;
      double d3 = (double)blockPos.func_177952_p();
      Minecraft.getMinecraft().getRenderManager();
      double z = d3 - mc.getRenderManager().viewerPosZ;
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(1.0F);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glColor4d(red, green, blue, 0.5D);
      drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
   }

   public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder vertexbuffer = tessellator.getBuffer();
      vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
      tessellator.draw();
      vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
      tessellator.draw();
      vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
      vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
      tessellator.draw();
   }

   public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
      Tessellator ts = Tessellator.getInstance();
      BufferBuilder vb = ts.getBuffer();
      vb.begin(7, DefaultVertexFormats.POSITION_TEX);
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      ts.draw();
      vb.begin(7, DefaultVertexFormats.POSITION_TEX);
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      ts.draw();
      vb.begin(7, DefaultVertexFormats.POSITION_TEX);
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      ts.draw();
      vb.begin(7, DefaultVertexFormats.POSITION_TEX);
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      ts.draw();
      vb.begin(7, DefaultVertexFormats.POSITION_TEX);
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      ts.draw();
      vb.begin(7, DefaultVertexFormats.POSITION_TEX);
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
      ts.draw();
   }

   public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor, int borderColor) {
      drawRect(x + width, y + width, x1 - width, y1 - width, internalColor);
      drawRect(x + width, y, x1 - width, y + width, borderColor);
      drawRect(x, y, x + width, y1, borderColor);
      drawRect(x1 - width, y, x1, y1, borderColor);
      drawRect(x + width, y1 - width, x1 - width, y1, borderColor);
   }
}
