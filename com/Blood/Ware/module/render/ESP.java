package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ESP extends Module {
   public static List listA = new ArrayList();
   public static List Heal = new ArrayList();
   public static List Modes = new ArrayList();

   public static void renderEntityBoundingBox(Entity entity, float n, float n2, float n3, float n4) {
      RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
      Vec3d interpolateEntity = interpolateEntity(entity);
      GlStateManager.glLineWidth(5.0F);
      RenderGlobal.drawSelectionBoundingBox((new AxisAlignedBB(interpolateEntity.x - (double)(entity.width / 2.0F), interpolateEntity.y, interpolateEntity.z - (double)(entity.width / 2.0F), interpolateEntity.x + (double)(entity.width / 2.0F), interpolateEntity.y + (double)entity.height, interpolateEntity.z + (double)(entity.width / 2.0F))).offset(-getRenderManager.viewerPosX, -getRenderManager.viewerPosY, -getRenderManager.viewerPosZ), n, n2, n3, n4);
      GlStateManager.glLineWidth(1.0F);
   }

   public void onDisable() {
      super.onDisable();
      Iterator var1 = mc.world.loadedEntityList.iterator();

      while(var1.hasNext()) {
         Entity entity = (Entity)var1.next();
         if (entity instanceof EntityPlayer && entity.isGlowing()) {
            entity.setGlowing(false);
         }
      }

   }

   public static void renderEntityFilledBoundingBox(Entity entity, float n, float n2, float n3, float n4) {
      RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
      Vec3d interpolateEntity = interpolateEntity(entity);
      RenderGlobal.renderFilledBox((new AxisAlignedBB(interpolateEntity.x - (double)(entity.width / 2.0F), interpolateEntity.y, interpolateEntity.z - (double)(entity.width / 2.0F), interpolateEntity.x + (double)(entity.width / 2.0F), interpolateEntity.y + (double)entity.height, interpolateEntity.z + (double)(entity.width / 2.0F))).offset(-getRenderManager.viewerPosX, -getRenderManager.viewerPosY, -getRenderManager.viewerPosZ), n, n2, n3, n4);
   }

   public void onEnable() {
      super.onEnable();
   }

   public static Vec3d interpolateEntity(Entity entity) {
      double n = (double)Minecraft.getMinecraft().getRenderPartialTicks();
      return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
   }

   public ESP() {
      super("ESP", Category.RENDER);
      Modes.add("3D");
      Modes.add("2D");
      Modes.add("NoneCode");
      Modes.add("Glowing");
      Modes.add("Corners");
      Modes.add("OnHeal");
      BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "2D", (ArrayList)Modes));
      BloodWare.instance.settingsManager.rSetting(new Setting("Health[2D]", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("healthValue[2D]", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Box[2D]", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Tag[2D]", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Show item[2D]", this, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("3DAlpha[3D]", this, 0.7D, 0.0D, 1.0D, false));
      Heal.add("Right");
      Heal.add("Left");
      BloodWare.instance.settingsManager.rSetting(new Setting("PosHp[2D]", this, "Right", (ArrayList)Heal));
      BloodWare.instance.settingsManager.rSetting(new Setting("redBox[2D]", this, 0.8D, 0.1D, 255.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("greenBox[2D]", this, 0.8D, 0.1D, 255.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("blueBox[2D]", this, 0.8D, 0.1D, 255.0D, false));
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldLastEvent renderWorldLastEvent) {
      String valString = BloodWare.instance.settingsManager.getSettingByName((Module)this, "PosHp[2D]").getValString();
      String valString2 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Mode").getValString();
      boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Health[2D]").getValBoolean();
      boolean valBoolean2 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "healthValue[2D]").getValBoolean();
      boolean valBoolean3 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Box[2D]").getValBoolean();
      boolean valBoolean4 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Tag[2D]").getValBoolean();
      boolean valBoolean5 = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Show item[2D]").getValBoolean();
      float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "3DAlpha[3D]").getValDouble();
      float n2 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "redBox[2D]").getValDouble();
      float n3 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "greenBox[2D]").getValDouble();
      float n4 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "blueBox[2D]").getValDouble();
      Iterator iterator;
      Entity entity7;
      double n15;
      double n16;
      if (Objects.equals(valString2, "2D")) {
         iterator = mc.world.loadedEntityList.iterator();
         if (iterator.hasNext()) {
            entity7 = (Entity)iterator.next();
            Iterator var15 = mc.world.loadedEntityList.iterator();

            while(true) {
               Entity entity2;
               do {
                  do {
                     if (!var15.hasNext()) {
                        return;
                     }

                     entity2 = (Entity)var15.next();
                  } while(!(entity2 instanceof EntityPlayer));
               } while(entity2 == mc.world.loadedEntityList);

               n15 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosX;
               n16 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosY;
               double n7 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosZ;
               GL11.glPushMatrix();
               GL11.glLineWidth(1.5F);
               GL11.glTranslated(n15, n16, n7);
               GL11.glDisable(3553);
               GL11.glDisable(2929);
               GL11.glRotated((double)(-mc.getRenderManager().playerViewY), 0.0D, 1.0D, 0.0D);
               if (valBoolean3) {
                  if (FriendManager.FRIENDS.contains(entity2.getName())) {
                     GL11.glColor4d(0.0D, 255.0D, 0.0D, 255.0D);
                     GL11.glBegin(3);
                     GL11.glVertex3d(0.55D, -0.2D, 0.0D);
                     GL11.glVertex3d(0.55D, (double)entity2.height + 0.2D, 0.0D);
                     GL11.glVertex3d((double)entity2.width - 1.15D, (double)entity2.height + 0.2D, 0.0D);
                     GL11.glVertex3d((double)entity2.width - 1.15D, -0.2D, 0.0D);
                     GL11.glVertex3d(0.55D, -0.2D, 0.0D);
                     GL11.glEnd();
                  } else {
                     GL11.glColor4d((double)n2, (double)n3, (double)n4, 255.0D);
                     GL11.glBegin(3);
                     GL11.glVertex3d(0.55D, -0.2D, 0.0D);
                     GL11.glVertex3d(0.55D, (double)entity2.height + 0.2D, 0.0D);
                     GL11.glVertex3d((double)entity2.width - 1.15D, (double)entity2.height + 0.2D, 0.0D);
                     GL11.glVertex3d((double)entity2.width - 1.15D, -0.2D, 0.0D);
                     GL11.glVertex3d(0.55D, -0.2D, 0.0D);
                     GL11.glEnd();
                  }
               }

               if (valBoolean) {
                  Color color2;
                  if (Objects.equals(valString, "Right")) {
                     color2 = Color.GREEN.darker();
                     if (((EntityPlayer)entity2).func_110143_aJ() >= 16.0F) {
                        color2 = Color.GREEN.darker();
                     } else if (((EntityPlayer)entity2).func_110143_aJ() >= 8.0F && ((EntityPlayer)entity2).func_110143_aJ() <= 16.0F) {
                        color2 = Color.YELLOW;
                     } else if (((EntityPlayer)entity2).func_110143_aJ() > 0.0F && ((EntityPlayer)entity2).func_110143_aJ() <= 8.0F) {
                        color2 = Color.RED;
                     }

                     GL11.glLineWidth(6.0F);
                     GL11.glBegin(3);
                     GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
                     GL11.glVertex3d(-0.7D, -0.2D, 0.0D);
                     GL11.glVertex3d(-0.7D, (double)entity2.height + 0.2D, 0.0D);
                     GL11.glEnd();
                     GL11.glBegin(3);
                     GL11.glColor4d((double)((float)color2.getRed() / 255.0F), (double)((float)color2.getGreen() / 255.0F), (double)((float)color2.getBlue() / 255.0F), (double)((float)color2.getAlpha() / 255.0F));
                     GL11.glVertex3d(-0.7D, -0.2D, 0.0D);
                     GL11.glVertex3d(-0.7D, (double)(((EntityLivingBase)entity2).getHealth() / ((EntityLivingBase)entity2).getMaxHealth()) * ((double)entity2.height + 0.2D), 0.0D);
                     GL11.glVertex3d(-0.7D, -0.2D, 0.0D);
                     GL11.glEnd();
                  } else if (Objects.equals(valString, "Left")) {
                     color2 = Color.GREEN.darker();
                     if (((EntityPlayer)entity2).func_110143_aJ() >= 16.0F) {
                        color2 = Color.GREEN.darker();
                     } else if (((EntityPlayer)entity2).func_110143_aJ() >= 8.0F && ((EntityPlayer)entity2).func_110143_aJ() <= 16.0F) {
                        color2 = Color.YELLOW;
                     } else if (((EntityPlayer)entity2).func_110143_aJ() > 0.0F && ((EntityPlayer)entity2).func_110143_aJ() <= 8.0F) {
                        color2 = Color.RED;
                     }

                     GL11.glLineWidth(6.0F);
                     GL11.glBegin(3);
                     GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
                     GL11.glVertex3d(0.7D, -0.2D, 0.0D);
                     GL11.glVertex3d(0.7D, (double)entity2.height + 0.2D, 0.0D);
                     GL11.glEnd();
                     GL11.glBegin(3);
                     GL11.glColor4d((double)((float)color2.getRed() / 255.0F), (double)((float)color2.getGreen() / 255.0F), (double)((float)color2.getBlue() / 255.0F), (double)((float)color2.getAlpha() / 255.0F));
                     GL11.glVertex3d(0.7D, -0.2D, 0.0D);
                     GL11.glVertex3d(0.7D, (double)(((EntityLivingBase)entity2).getHealth() / ((EntityLivingBase)entity2).getMaxHealth()) * ((double)entity2.height + 0.2D), 0.0D);
                     GL11.glVertex3d(0.7D, -0.2D, 0.0D);
                     GL11.glEnd();
                  }
               }

               GL11.glScaled(-0.013000000268220901D, -0.013000000268220901D, -0.013000000268220901D);
               if (valBoolean4) {
                  GL11.glEnable(3553);
                  mc.fontRenderer.drawStringWithShadow(entity2.getName(), (float)(1 - mc.fontRenderer.getStringWidth(entity2.getName()) / 2), -170.0F, -1);
                  GL11.glDisable(3553);
               }

               if (valBoolean2 && valBoolean) {
                  GL11.glEnable(3553);
                  mc.fontRenderer.drawStringWithShadow(String.valueOf((int)(((EntityPlayer)entity2).func_110143_aJ() / ((EntityPlayer)entity2).func_110138_aP() * 100.0F)), (float)(-50 - mc.fontRenderer.getStringWidth(String.valueOf((int)(((EntityPlayer)entity2).func_110143_aJ() / ((EntityPlayer)entity2).func_110138_aP() * 100.0F)))), (float)((int)((double)(((EntityLivingBase)entity2).getHealth() / ((EntityLivingBase)entity2).getMaxHealth()) * ((double)entity2.height + 0.2D))), -1);
                  GL11.glDisable(3553);
               }

               if (valBoolean5 && !(((EntityPlayer)entity2).func_184586_b(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock) && !(((EntityPlayer)entity2).func_184586_b(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                  GL11.glEnable(3553);
                  mc.fontRenderer.drawStringWithShadow(((EntityPlayer)entity2).func_184586_b(EnumHand.MAIN_HAND).getDisplayName(), (float)(1 - mc.fontRenderer.getStringWidth(((EntityPlayer)entity2).func_184586_b(EnumHand.MAIN_HAND).getDisplayName()) / 2), 20.0F, -1);
                  GL11.glDisable(3553);
               }

               GL11.glEnable(2929);
               GL11.glEnable(3553);
               GL11.glPopMatrix();
            }
         }
      } else if (Objects.equals(valString2, "Glowing")) {
         iterator = mc.world.loadedEntityList.iterator();

         while(iterator.hasNext()) {
            entity7 = (Entity)iterator.next();
            if (entity7 instanceof EntityPlayer && entity7 != mc.player && !entity7.isGlowing()) {
               entity7.setGlowing(true);
            }
         }
      } else if (Objects.equals(valString2, "3D")) {
         GlStateManager.pushMatrix();
         GlStateManager.disableTexture2D();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableDepth();
         iterator = mc.world.loadedEntityList.iterator();

         while(iterator.hasNext()) {
            entity7 = (Entity)iterator.next();
            if (entity7 instanceof EntityPlayer && entity7 != mc.player) {
               renderEntityBoundingBox(entity7, (float)Color.RED.getRed() / 255.0F, (float)Color.RED.getGreen() / 255.0F, (float)Color.RED.getBlue() / 255.0F, n);
            }
         }

         GlStateManager.enableDepth();
         GlStateManager.disableBlend();
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         GlStateManager.popMatrix();
      } else {
         double n14;
         if (Objects.equals(valString2, "NoneCode")) {
            iterator = mc.world.loadedEntityList.iterator();

            while(true) {
               do {
                  do {
                     if (!iterator.hasNext()) {
                        return;
                     }

                     entity7 = (Entity)iterator.next();
                  } while(!(entity7 instanceof EntityPlayer));
               } while(entity7 == mc.player && mc.gameSettings.thirdPersonView != 1);

               n14 = entity7.lastTickPosX + (entity7.posX - entity7.lastTickPosX) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosX;
               n15 = entity7.lastTickPosY + (entity7.posY - entity7.lastTickPosY) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosY;
               n16 = entity7.lastTickPosZ + (entity7.posZ - entity7.lastTickPosZ) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosZ;
               GL11.glPushMatrix();
               GL11.glDisable(2929);
               GL11.glDisable(3553);
               GL11.glTranslatef((float)n14, (float)n15, (float)n16);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glLineWidth(3.0F);
               GL11.glColor3f(0.0F, 0.0F, 0.0F);
               GL11.glBegin(3);
               GL11.glVertex3d((double)entity7.width + 0.1D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.1D, (double)entity7.height + 0.4D, 0.0D);
               GL11.glVertex3d((double)(-entity7.width) - 0.1D, (double)entity7.height + 0.4D, 0.0D);
               GL11.glVertex3d((double)(-entity7.width) - 0.1D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.1D, -0.4D, 0.0D);
               GL11.glEnd();
               GL11.glLineWidth(0.7F);
               GL11.glColor3f(255.0F, 255.0F, 255.0F);
               GL11.glBegin(3);
               GL11.glVertex3d((double)entity7.width + 0.1D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.1D, (double)entity7.height + 0.4D, 0.0D);
               GL11.glVertex3d((double)(-entity7.width) - 0.1D, (double)entity7.height + 0.4D, 0.0D);
               GL11.glVertex3d((double)(-entity7.width) - 0.1D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.1D, -0.4D, 0.0D);
               GL11.glEnd();
               GL11.glColor3f(0.0F, 0.0F, 0.0F);
               GL11.glLineWidth(3.0F);
               GL11.glBegin(3);
               GL11.glVertex3d((double)entity7.width + 0.4D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.4D, (double)entity7.height + 0.4D, 0.0D);
               GL11.glEnd();
               GL11.glColor3f((float)Color.GRAY.getRed(), (float)Color.GRAY.getGreen(), (float)Color.GRAY.getBlue());
               GL11.glLineWidth(0.7F);
               GL11.glBegin(3);
               GL11.glVertex3d((double)entity7.width + 0.4D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.4D, (double)entity7.height + 0.4D, 0.0D);
               GL11.glEnd();
               GL11.glColor3f(0.0F, 255.0F, 0.0F);
               GL11.glBegin(3);
               GL11.glVertex3d((double)entity7.width + 0.4D, -0.4D, 0.0D);
               GL11.glVertex3d((double)entity7.width + 0.4D, (double)(((EntityPlayer)entity7).func_110143_aJ() / ((EntityPlayer)entity7).func_110138_aP()) * ((double)entity7.height + 0.4D), 0.0D);
               GL11.glEnd();
               GL11.glBegin(3);
               GL11.glEnd();
               GL11.glEnable(2929);
               GL11.glEnable(3553);
               GL11.glColor3f(255.0F, 255.0F, 255.0F);
               GL11.glPopMatrix();
            }
         }

         if (Objects.equals(valString2, "Corners")) {
            iterator = mc.world.loadedEntityList.iterator();

            while(iterator.hasNext()) {
               entity7 = (Entity)iterator.next();
               if (entity7 instanceof EntityPlayer && entity7 != mc.player) {
                  n14 = entity7.lastTickPosX + (entity7.posX - entity7.lastTickPosX) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosX;
                  n15 = entity7.lastTickPosY + (entity7.posY - entity7.lastTickPosY) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosY;
                  n16 = entity7.lastTickPosZ + (entity7.posZ - entity7.lastTickPosZ) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosZ;
                  GL11.glPushMatrix();
                  GL11.glDisable(2929);
                  GL11.glDisable(3553);
                  GL11.glTranslated(n14, n15, n16);
                  GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                  GL11.glLineWidth(3.0F);
                  GL11.glColor3f(0.0F, 0.0F, 0.0F);
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)entity7.width, 0.2D, 0.0D);
                  GL11.glVertex3d((double)entity7.width, -0.3D, 0.0D);
                  GL11.glVertex3d(0.15D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)(-entity7.width), 0.2D, 0.0D);
                  GL11.glVertex3d((double)(-entity7.width), -0.3D, 0.0D);
                  GL11.glVertex3d(-0.15D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3d(0.15D, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)entity7.width, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)entity7.width, (double)entity7.height - 0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3d(-0.15D, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)(-entity7.width), (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)(-entity7.width), (double)entity7.height - 0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glLineWidth(1.0F);
                  GL11.glColor3f((float)Color.CYAN.darker().getRed(), (float)Color.CYAN.darker().getGreen(), (float)Color.CYAN.darker().getBlue());
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)entity7.width, 0.2D, 0.0D);
                  GL11.glVertex3d((double)entity7.width, -0.3D, 0.0D);
                  GL11.glVertex3d(0.15D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)(-entity7.width), 0.2D, 0.0D);
                  GL11.glVertex3d((double)(-entity7.width), -0.3D, 0.0D);
                  GL11.glVertex3d(-0.15D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3d(0.15D, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)entity7.width, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)entity7.width, (double)entity7.height - 0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3d(-0.15D, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)(-entity7.width), (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)(-entity7.width), (double)entity7.height - 0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glColor3f(0.0F, 0.0F, 0.0F);
                  GL11.glLineWidth(3.0F);
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)entity7.width + 0.2D, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)entity7.width + 0.2D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glColor3f(255.0F, 255.0F, 255.0F);
                  GL11.glLineWidth(1.0F);
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)entity7.width + 0.2D, (double)entity7.height + 0.3D, 0.0D);
                  GL11.glVertex3d((double)entity7.width + 0.2D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glColor3f(0.0F, 255.0F, 0.0F);
                  GL11.glLineWidth(1.0F);
                  GL11.glBegin(3);
                  GL11.glVertex3d((double)entity7.width + 0.2D, (double)(((EntityPlayer)entity7).func_110143_aJ() / ((EntityPlayer)entity7).func_110138_aP()) * ((double)entity7.height + 0.3D), 0.0D);
                  GL11.glVertex3d((double)entity7.width + 0.2D, -0.3D, 0.0D);
                  GL11.glEnd();
                  GL11.glColor3f(1.0F, 1.0F, 1.0F);
                  GL11.glDisable(2960);
                  GL11.glEnable(2929);
                  GL11.glEnable(3553);
                  GL11.glPopMatrix();
               }
            }
         } else if (Objects.equals(valString2, "OnHeal")) {
            iterator = mc.world.loadedEntityList.iterator();

            while(iterator.hasNext()) {
               entity7 = (Entity)iterator.next();
               if (entity7 instanceof EntityPlayer && entity7 != mc.player) {
                  n14 = entity7.lastTickPosX + (entity7.posX - entity7.lastTickPosX) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosX;
                  n15 = entity7.lastTickPosY + (entity7.posY - entity7.lastTickPosY) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosY;
                  n16 = entity7.lastTickPosZ + (entity7.posZ - entity7.lastTickPosZ) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosZ;
                  GL11.glPushMatrix();
                  GL11.glDisable(2929);
                  GL11.glDisable(3553);
                  GL11.glTranslatef((float)n14, (float)n15, (float)n16);
                  GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
                  GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                  GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                  GL11.glLineWidth(4.7F);
                  GL11.glColor3f(0.0F, 0.0F, 0.0F);
                  GL11.glBegin(3);
                  GL11.glVertex3f(0.4F, 0.0F, 0.0F);
                  GL11.glVertex3f(0.4F, entity7.height, 0.0F);
                  GL11.glEnd();
                  GL11.glColor3f(0.0F, 255.0F, 0.0F);
                  GL11.glLineWidth(0.8F);
                  GL11.glColor3f(255.0F, 255.0F, 255.0F);
                  GL11.glBegin(3);
                  GL11.glVertex3f(0.4F, 0.0F, 0.0F);
                  GL11.glVertex3f(0.4F, entity7.height, 0.0F);
                  GL11.glColor3f(0.0F, 255.0F, 0.0F);
                  GL11.glEnd();
                  GL11.glBegin(3);
                  GL11.glVertex3f(0.4F, 0.0F, 0.0F);
                  GL11.glVertex3f(0.4F, ((EntityPlayer)entity7).func_110143_aJ() / ((EntityPlayer)entity7).func_110138_aP() * entity7.height, 0.0F);
                  GL11.glEnd();
                  GL11.glEnable(3553);
                  GL11.glEnable(2929);
                  GL11.glColor3f(255.0F, 255.0F, 255.0F);
                  GL11.glPopMatrix();
               }
            }
         }
      }

   }
}
