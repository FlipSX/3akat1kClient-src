package com.Blood.Ware.utils;

import com.Blood.Ware.utils.search.Blockinfo;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
   private static final Frustum frustrum = new Frustum();
   public static boolean click;
   private float spin;
   private float cumSize;
   private static final int GL_BLEND = 0;
   private static final int GL_DEPTH_TEST = 0;

   public static ResourceLocation drawPic(double n, double n2, double n3, double n4, ResourceLocation resourceLocation) {
      GlStateManager.enableAlpha();
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      getBuffer.pos(n, n2 + n4, 0.0D).tex(0.0D, 1.0D).color(255, 255, 255, 255).endVertex();
      getBuffer.pos(n + n3, n2 + n4, 0.0D).tex(1.0D, 1.0D).color(255, 255, 255, 255).endVertex();
      getBuffer.pos(n + n3, n2, 0.0D).tex(1.0D, 0.0D).color(255, 255, 255, 255).endVertex();
      getBuffer.pos(n, n2, 0.0D).tex(0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
      getInstance.draw();
      GlStateManager.disableAlpha();
      return resourceLocation;
   }

   public static void drawCircle228(float n, float n2, float n3, int n4, int n5) {
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
      GL11.glLineWidth(2.5F);
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

   public static void drawRectStatic(int n, int n2, int n3, int n4, Color color) {
      Gui.drawRect(n, n2, n3, n4, color.getRGB());
   }

   public static void drawSector2(double n, double n2, int n3, int n4, int n5) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      GL11.glBegin(6);
      glColor((new Color(0, 0, 0, 100)).getRGB());
      GL11.glVertex2d(n, n2);
      glColor((new Color(0, 0, 0, 0)).getRGB());

      for(int i = n3; i <= n4; ++i) {
         GL11.glVertex2d(n + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)n5, n2 + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)n5);
      }

      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableAlpha();
      GlStateManager.shadeModel(7424);
   }

   public static void drawESP(Entity entity, float n, float n2, float n3, float n4, float n5) {
      try {
         double viewerPosX = Wrapper.mc.getRenderManager().viewerPosX;
         double viewerPosY = Wrapper.mc.getRenderManager().viewerPosY;
         double viewerPosZ = Wrapper.mc.getRenderManager().viewerPosZ;
         double n6 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)n5 - viewerPosX;
         double n7 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)n5 + (double)(entity.height / 2.0F) - viewerPosY;
         double n8 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)n5 - viewerPosZ;
         float playerViewY = Wrapper.mc.getRenderManager().playerViewY;
         float playerViewX = Wrapper.mc.getRenderManager().playerViewX;
         boolean b = Wrapper.mc.getRenderManager().options.thirdPersonView == 2;
         GL11.glPushMatrix();
         GlStateManager.translate(n6, n7, n8);
         GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate((float)(b ? -1 : 1) * playerViewX, 1.0F, 0.0F, 0.0F);
         GL11.glEnable(3042);
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glLineWidth(1.0F);
         GL11.glBlendFunc(770, 771);
         GL11.glEnable(2848);
         GL11.glColor4f(n, n2, n3, n4);
         GL11.glBegin(1);
         GL11.glVertex3d(0.0D, 1.0D, 0.0D);
         GL11.glVertex3d(-0.5D, 0.5D, 0.0D);
         GL11.glVertex3d(0.0D, 1.0D, 0.0D);
         GL11.glVertex3d(0.5D, 0.5D, 0.0D);
         GL11.glVertex3d(0.0D, 0.0D, 0.0D);
         GL11.glVertex3d(-0.5D, 0.5D, 0.0D);
         GL11.glVertex3d(0.0D, 0.0D, 0.0D);
         GL11.glVertex3d(0.5D, 0.5D, 0.0D);
         GL11.glEnd();
         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glEnable(2896);
         GL11.glDisable(2848);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
      } catch (Exception var21) {
         var21.printStackTrace();
      }

   }

   public static void setColor(Color color) {
      GL11.glColor4d((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), (double)((float)color.getAlpha() / 255.0F));
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

   public static void customScaledObject2D(float n, float n2, float n3, float n4, float n5, float n6) {
      GL11.glTranslated((double)(n3 / 2.0F), (double)(n4 / 2.0F), 1.0D);
      GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0F * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0F * -n5), 1.0D);
      GL11.glScaled((double)n5, (double)n5, 0.0D);
   }

   public static void drawDownShadow(float n, float n2, float n3, float n4) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      GL11.glBegin(7);
      glColor((new Color(0, 0, 0, 100)).getRGB());
      GL11.glVertex2d((double)n3, (double)n4);
      glColor((new Color(0, 0, 0, 0)).getRGB());
      GL11.glVertex2d((double)n3, (double)n2);
      GL11.glVertex2d((double)n, (double)n2);
      glColor((new Color(0, 0, 0, 100)).getRGB());
      GL11.glVertex2d((double)n, (double)n4);
      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableAlpha();
      GlStateManager.shadeModel(7424);
   }

   public static void drawShadowRect(double n, double n2, double n3, double n4, int n5) {
      drawGradientRect(n, n2 - (double)n5, n3, n2, false, true, (new Color(0, 0, 0, 100)).getRGB(), (new Color(0, 0, 0, 0)).getRGB());
      drawGradientRect(n, n4, n3, n4 + (double)n5, false, false, (new Color(0, 0, 0, 100)).getRGB(), (new Color(0, 0, 0, 0)).getRGB());
      drawSector2(n3, n4, 0, 90, n5);
      drawSector2(n3, n2, 90, 180, n5);
      drawSector2(n, n2, 180, 270, n5);
      drawSector2(n, n4, 270, 360, n5);
      drawGradientRect(n - (double)n5, n2, n, n4, true, true, (new Color(0, 0, 0, 100)).getRGB(), (new Color(0, 0, 0, 0)).getRGB());
      drawGradientRect(n3, n2, n3 + (double)n5, n4, true, false, (new Color(0, 0, 0, 100)).getRGB(), (new Color(0, 0, 0, 0)).getRGB());
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

   public static void drawGradientRect(double n, double n2, double n3, double n4, boolean b, int n5, int n6) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      glColor(n5);
      GL11.glBegin(7);
      if (b) {
         GL11.glVertex2d(n, n2);
         GL11.glVertex2d(n, n4);
         glColor(n6);
         GL11.glVertex2d(n3, n4);
         GL11.glVertex2d(n3, n2);
      } else {
         GL11.glVertex2d(n, n2);
         glColor(n6);
         GL11.glVertex2d(n, n4);
         GL11.glVertex2d(n3, n4);
         glColor(n5);
         GL11.glVertex2d(n3, n2);
      }

      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableAlpha();
      GlStateManager.shadeModel(7424);
   }

   public static void renderItem(ItemStack itemStack, int n, int n2) {
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.enableDepth();
      RenderHelper.enableGUIStandardItemLighting();
      MinecraftHelper.mc.getRenderItem().zLevel = -100.0F;
      MinecraftHelper.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
      MinecraftHelper.mc.getRenderItem().renderItemOverlays(MinecraftHelper.mc.fontRenderer, itemStack, n, n2);
      MinecraftHelper.mc.getRenderItem().zLevel = 0.0F;
      RenderHelper.disableStandardItemLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableDepth();
   }

   public static void drawRectSized(float n, float n2, float n3, float n4, int n5) {
      drawRect(n, n2, n + n3, n2 + n4, n5);
   }

   public static double lerp(double n, double n2, double n3) {
      return (1.0D - n3) * n + n3 * n2;
   }

   public static void draw2DRect(int n, int n2, int n3, int n4, int n5) {
      Tessellator getInstance = Tessellator.getInstance();
      BufferBuilder getBuffer = getInstance.getBuffer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      getBuffer.begin(7, DefaultVertexFormats.POSITION);
      getBuffer.pos((double)n, (double)(n2 + n4), (double)n5).endVertex();
      getBuffer.pos((double)(n + n3), (double)(n2 + n4), (double)n5).endVertex();
      getBuffer.pos((double)(n + n3), (double)n2, (double)n5).endVertex();
      getBuffer.pos((double)n, (double)n2, (double)n5).endVertex();
      getInstance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void setupColor(Color color, int n) {
      GL11.glColor4d((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), (double)((float)n / 255.0F));
   }

   public static void drawImage(ResourceLocation resourceLocation, int n, int n2, int n3, int n4, int n5) {
      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      glColor(n5);
      MinecraftHelper.mc.getTextureManager().bindTexture(resourceLocation);
      Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0F, 0.0F, n3, n4, (float)n3, (float)n4);
      GlStateManager.disableBlend();
      GlStateManager.enableDepth();
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

   public static void drawGradient(double n, double n2, double n3, double n4, int n5, int n6) {
      float n7 = (float)(n5 >> 24 & 255) / 255.0F;
      float n8 = (float)(n5 >> 16 & 255) / 255.0F;
      float n9 = (float)(n5 >> 8 & 255) / 255.0F;
      float n10 = (float)(n5 & 255) / 255.0F;
      float n11 = (float)(n6 >> 24 & 255) / 255.0F;
      float n12 = (float)(n6 >> 16 & 255) / 255.0F;
      float n13 = (float)(n6 >> 8 & 255) / 255.0F;
      float n14 = (float)(n6 & 255) / 255.0F;
      RenderUtil.glRenderStart();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(n8, n9, n10, n7);
      GL11.glVertex2d(n3, n2);
      GL11.glVertex2d(n, n2);
      GL11.glColor4f(n12, n13, n14, n11);
      GL11.glVertex2d(n, n4);
      GL11.glVertex2d(n3, n4);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glShadeModel(7424);
      GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
      RenderUtil.glRenderStop();
   }

   public static void drawGradientRect(double n, double n2, double n3, double n4, boolean b, boolean b2, int n5, int n6) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      GL11.glBegin(7);
      glColor(n5);
      if (b) {
         if (b2) {
            GL11.glVertex2d(n3, n4);
            GL11.glVertex2d(n3, n2);
            glColor(n6);
            GL11.glVertex2d(n, n2);
            GL11.glVertex2d(n, n4);
         } else {
            GL11.glVertex2d(n, n2);
            GL11.glVertex2d(n, n4);
            glColor(n6);
            GL11.glVertex2d(n3, n4);
            GL11.glVertex2d(n3, n2);
         }
      } else if (b2) {
         GL11.glVertex2d(n3, n4);
         glColor(n6);
         GL11.glVertex2d(n3, n2);
         GL11.glVertex2d(n, n2);
         glColor(n5);
         GL11.glVertex2d(n, n4);
      } else {
         GL11.glVertex2d(n, n2);
         glColor(n6);
         GL11.glVertex2d(n, n4);
         GL11.glVertex2d(n3, n4);
         glColor(n5);
         GL11.glVertex2d(n3, n2);
      }

      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableAlpha();
      GlStateManager.shadeModel(7424);
   }

   public static void blockEspFrame(BlockPos blockPos, float n, float n2, float n3) {
      double n4 = (double)blockPos.func_177958_n() - MinecraftHelper.mc.getRenderManager().viewerPosX;
      double n5 = (double)blockPos.func_177956_o() - MinecraftHelper.mc.getRenderManager().viewerPosY;
      double n6 = (double)blockPos.func_177952_p() - MinecraftHelper.mc.getRenderManager().viewerPosZ;
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GlStateManager.color(n, n2, n3, 1.0F);
      RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(n4, n5, n6, n4 + 1.0D, n5 + 1.0D, n6 + 1.0D));
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
   }

   public static void blockEspBox(BlockPos blockPos, double n, double n2, double n3) {
      double n4 = (double)blockPos.func_177958_n() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
      double n5 = (double)blockPos.func_177956_o() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
      double n6 = (double)blockPos.func_177952_p() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(0);
      GL11.glLineWidth(2.0F);
      GL11.glDisable(3553);
      GL11.glDisable(0);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glColor4d(n, n2, n3, 0.15000000596046448D);
      RenderUtil.drawColorBox(new AxisAlignedBB(n4, n5, n6, n4 + 1.0D, n5 + 1.0D, n6 + 1.0D), 0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glDepthMask(false);
      GL11.glColor4d(n, n2, n3, 0.15000000596046448D);
      RenderUtil.drawColorBox(new AxisAlignedBB(n4, n5, n6, n4 + 1.0D, n5 + 1.0D, n6 + 1.0D), 0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glEnable(3553);
      GL11.glEnable(0);
      GL11.glEnable(2896);
      GL11.glDepthMask(true);
      GL11.glDisable(0);
      GL11.glPopMatrix();
   }

   public static void trace(Minecraft minecraft, Entity entity, float n, int n2) {
      if (minecraft.getRenderManager().renderViewEntity != null) {
         GL11.glDisable(2929);
         GL11.glDisable(2896);
         GL11.glLineWidth(2.0F);
         GL11.glPushMatrix();
         GL11.glDepthMask(false);
         GL11.glColor4d(0.0D, n2 == 1 ? 1.0D : 0.0D, 0.0D, 1.0D);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(3553);
         GL11.glBegin(1);
         RenderManager getRenderManager = minecraft.getRenderManager();
         Vec3d rotateYaw = (new Vec3d(0.0D, 0.0D, 1.0D)).rotatePitch(-((float)Math.toRadians((double)minecraft.player.rotationPitch))).rotateYaw(-((float)Math.toRadians((double)minecraft.player.rotationYaw)));
         GL11.glVertex3d(rotateYaw.x, (double)minecraft.player.getEyeHeight() + rotateYaw.y, rotateYaw.z);
         GL11.glVertex3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)n - getRenderManager.viewerPosX, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)n - getRenderManager.viewerPosY + 0.25D, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)n - getRenderManager.viewerPosZ);
         GL11.glEnd();
         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glPopMatrix();
      }

   }

   public static void trace(Minecraft minecraft, Blockinfo blockinfo) {
      if (minecraft.getRenderManager().renderViewEntity != null) {
         GL11.glDisable(2929);
         GL11.glDisable(2896);
         GL11.glLineWidth(2.0F);
         GL11.glPushMatrix();
         GL11.glDepthMask(false);
         GL11.glColor4d((double)blockinfo.getR(), (double)blockinfo.getG(), (double)blockinfo.getB(), 1.0D);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(3553);
         GL11.glBegin(1);
         RenderManager RenderManager = minecraft.getRenderManager();
         Vec3d rotateYaw = (new Vec3d(0.0D, 0.0D, 1.0D)).rotatePitch(-((float)Math.toRadians((double)minecraft.player.rotationPitch))).rotateYaw(-((float)Math.toRadians((double)minecraft.player.rotationYaw)));
         GL11.glVertex3d(rotateYaw.x, (double)minecraft.player.getEyeHeight() + rotateYaw.y, rotateYaw.z);
         GL11.glVertex3d((double)blockinfo.getX() - RenderManager.viewerPosX, (double)blockinfo.getY() - RenderManager.viewerPosY, (double)blockinfo.getZ() - RenderManager.viewerPosZ);
         GL11.glEnd();
         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glPopMatrix();
      }

   }

   public static void glColor(int n) {
      GlStateManager.color((float)(n >> 16 & 255) / 255.0F, (float)(n >> 8 & 255) / 255.0F, (float)(n & 255) / 255.0F, (float)(n >> 24 & 255) / 255.0F);
   }

   public static void drawBorderedRect(double n, double n2, double n3, double n4, float n5, int n6, int n7) {
      drawRect((float)n, (float)n2, (float)n3, (float)n4, n7);
      float n8 = (float)(n6 >> 24 & 255) / 255.0F;
      float n9 = (float)(n6 >> 16 & 255) / 255.0F;
      float n10 = (float)(n6 >> 8 & 255) / 255.0F;
      float n11 = (float)(n6 & 255) / 255.0F;
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glColor4f(n9, n10, n11, n8);
      GL11.glLineWidth(n5);
      GL11.glBegin(1);
      GL11.glVertex2d(n, n2);
      GL11.glVertex2d(n, n4);
      GL11.glVertex2d(n3, n4);
      GL11.glVertex2d(n3, n2);
      GL11.glVertex2d(n, n2);
      GL11.glVertex2d(n3, n2);
      GL11.glVertex2d(n, n4);
      GL11.glVertex2d(n3, n4);
      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glPopMatrix();
   }

   public static void drawEntityOnScreen(float n, float n2, float n3, EntityLivingBase entityLivingBase) {
      GlStateManager.pushMatrix();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.enableColorMaterial();
      GlStateManager.translate(n, n2, 50.0F);
      GlStateManager.scale(-n3, n3, n3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan((double)(entityLivingBase.rotationPitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
      getRenderManager.setPlayerViewY(180.0F);
      getRenderManager.setRenderShadow(false);
      getRenderManager.renderEntity(entityLivingBase, 0.0D, 0.0D, 0.0D, entityLivingBase.rotationYaw, 1.0F, false);
      getRenderManager.setRenderShadow(true);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.depthMask(false);
      GlStateManager.disableDepth();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.popMatrix();
   }

   public static void putVertex3d(Vec3d vec3d) {
      GL11.glVertex3d(vec3d.x, vec3d.y, vec3d.z);
   }

   public static void drawRect(float n, float n2, float n3, float n4, int n5) {
      float n6 = (float)(n5 >> 24 & 255) / 255.0F;
      float n7 = (float)(n5 >> 16 & 255) / 255.0F;
      float n8 = (float)(n5 >> 8 & 255) / 255.0F;
      float n9 = (float)(n5 & 255) / 255.0F;
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glColor4f(n7, n8, n9, n6);
      GL11.glBegin(7);
      GL11.glVertex2d((double)n3, (double)n2);
      GL11.glVertex2d((double)n, (double)n2);
      GL11.glVertex2d((double)n, (double)n4);
      GL11.glVertex2d((double)n3, (double)n4);
      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glPopMatrix();
   }

   public static void drawUpShadow(float n, float n2, float n3, float n4) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.shadeModel(7425);
      GL11.glBegin(7);
      glColor((new Color(0, 0, 0, 100)).getRGB());
      GL11.glVertex2d((double)n, (double)n2);
      glColor((new Color(0, 0, 0, 0)).getRGB());
      GL11.glVertex2d((double)n, (double)n4);
      GL11.glVertex2d((double)n3, (double)n4);
      glColor((new Color(0, 0, 0, 100)).getRGB());
      GL11.glVertex2d((double)n3, (double)n2);
      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableAlpha();
      GlStateManager.shadeModel(7424);
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

   public static void FillLine(Entity entity, AxisAlignedBB axisAlignedBB) {
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      RenderGlobal.renderFilledBox(axisAlignedBB, 0.0F, 1.0F, 0.0F, 0.3F);
      RenderGlobal.drawSelectionBoundingBox(axisAlignedBB, 0.0F, 1.0F, 0.0F, 0.8F);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
   }

   public static void glColor(Color color, float n) {
      GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, n);
   }

   public static void scissorRect(float n, float n2, float n3, double n4) {
      ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
      int getScaleFactor = scaledResolution.getScaleFactor();
      GL11.glScissor((int)(n * (float)getScaleFactor), (int)(((double)((float)scaledResolution.getScaledHeight()) - n4) * (double)((float)getScaleFactor)), (int)((n3 - n) * (float)getScaleFactor), (int)((n4 - (double)n2) * (double)((float)getScaleFactor)));
   }

   public static Vec3d getRenderPos(double n, double n2, double n3) {
      n -= MinecraftHelper.mc.getRenderManager().viewerPosX;
      n2 -= MinecraftHelper.mc.getRenderManager().viewerPosY;
      n3 -= MinecraftHelper.mc.getRenderManager().viewerPosZ;
      return new Vec3d(n, n2, n3);
   }

   public static void renderEntity(EntityLivingBase entityLivingBase, int n, int n2, int n3) {
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(true);
      GL11.glPushAttrib(524288);
      GL11.glDisable(3089);
      GlStateManager.clear(256);
      GL11.glPopAttrib();
      GlStateManager.enableDepth();
      GlStateManager.disableAlpha();
      GlStateManager.pushMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GuiInventory.drawEntityOnScreen(n2, n3, n, 1.0F, 1.0F, entityLivingBase);
      GlStateManager.popMatrix();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
   }

   public static void drawEntityESP(Entity entity, Color color) {
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(0);
      GL11.glLineWidth(1.0F);
      GL11.glDisable(3553);
      GL11.glDisable(0);
      GL11.glDepthMask(false);
      GL11.glColor4d((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), 0.15000000596046448D);
      RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
      RenderUtil.drawColorBox(new AxisAlignedBB(-0.05D - entity.posX + (entity.posX - getRenderManager.viewerPosX), -entity.posY + (entity.posY - getRenderManager.viewerPosY), -0.05D - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ), 0.05D - entity.posX + (entity.posX - getRenderManager.viewerPosX), 0.1D - entity.posY + (entity.posY - getRenderManager.viewerPosY), 0.05D - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ)), 0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glColor4d(0.0D, 0.0D, 0.0D, 0.5D);
      RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(-0.05D - entity.posX + (entity.posX - getRenderManager.viewerPosX), -entity.posY + (entity.posY - getRenderManager.viewerPosY), -0.05D - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ), 0.05D - entity.posX + (entity.posX - getRenderManager.viewerPosX), 0.1D - entity.posY + (entity.posY - getRenderManager.viewerPosY), 0.05D - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ)));
      GL11.glLineWidth(2.0F);
      GL11.glEnable(3553);
      GL11.glEnable(0);
      GL11.glDepthMask(true);
      GL11.glDisable(0);
      GL11.glPopMatrix();
   }

   public static void drawboxESP5(BlockPos blockPos, Color color) {
      double n = (double)blockPos.func_177958_n();
      double viewerPosX = Wrapper.mc.getRenderManager().viewerPosX;
      double viewerPosY = Wrapper.mc.getRenderManager().viewerPosY;
      double viewerPosZ = Wrapper.mc.getRenderManager().viewerPosZ;
      Minecraft.getMinecraft().getRenderManager();
      double n2 = n - viewerPosX;
      double n3 = (double)blockPos.func_177956_o();
      Minecraft.getMinecraft().getRenderManager();
      double n4 = n3 - viewerPosY;
      double n5 = (double)blockPos.func_177952_p();
      Minecraft.getMinecraft().getRenderManager();
      double n6 = n5 - viewerPosZ;
      GL11.glBlendFunc(770, 771);
      Tessellator.getInstance().getBuffer();
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(1.0F);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      GL11.glDepthMask(true);
      GL11.glColor4d((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), 0.25D);
      Minecraft.getMinecraft().getRenderManager();
      RenderUtil.drawColorBox(new AxisAlignedBB(n2, n4, n6, n2 + 1.0D, n4 + 1.0D, n6 + 1.0D), 0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glColor4d(0.4000000059604645D, 0.6000000238418579D, 1.0D, 1.0D);
      GL11.glLineWidth(2.0F);
      GL11.glEnable(3553);
      GL11.glEnable(2896);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void customScaledObject2D(float n, float n2, float n3, float n4, float n5) {
      GL11.glTranslated((double)(n3 / 2.0F), (double)(n4 / 2.0F), 1.0D);
      GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0F * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0F * -n5), 1.0D);
      GL11.glScaled((double)n5, (double)n5, 0.0D);
   }

   public static void drawImage(ResourceLocation resourceLocation, int n, int n2, int n3, int n4) {
      GL11.glEnable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPushMatrix();
      Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
      Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0F, 0.0F, n3, n4, (float)n3, (float)n4);
      GL11.glPopMatrix();
   }

   public static void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void drawImage(ResourceLocation resourceLocation, float x, float y, float width, float height, Color color) {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      setColor(color.getRGB());
      Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
      Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, 0.0F, 0.0F, (int)width, (int)height, width, height);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
   }

   public static void setColor(int color) {
      GL11.glColor4ub((byte)(color >> 16 & 255), (byte)(color >> 8 & 255), (byte)(color & 255), (byte)(color >> 24 & 255));
   }

   public static void drawImage(ResourceLocation resourceLocation, float x, float y, float width, float height) {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDepthMask(false);
      GL11.glColor4f(0.6F, 0.6F, 0.6F, 1.0F);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
      Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, 0.0F, 0.0F, (int)width, (int)height, width, height);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GlStateManager.resetColor();
   }

   public static void glColor(Color color) {
      GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
   }
}
