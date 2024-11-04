package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColorUtils;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class NameTags extends Module {
   public static List Modes = new ArrayList();
   private FontRenderer fontRenderer;

   @SubscribeEvent
   public void onRenderWorld(RenderWorldLastEvent renderWorldLastEvent) {
      String valString = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Modes").getValString();
      Iterator var3;
      Entity entity3;
      double n27;
      double n28;
      double n29;
      float n30;
      int n22;
      int n23;
      int n24;
      Iterator var16;
      Enchantment enchantment7;
      int getEnchantmentLevel7;
      Enchantment enchantment8;
      int getEnchantmentLevel8;
      Iterator var23;
      ItemStack itemStack4;
      int n25;
      Iterator var27;
      if (Objects.equals(valString, "Blood")) {
         var3 = mc.world.loadedEntityList.iterator();

         while(true) {
            while(true) {
               do {
                  do {
                     if (!var3.hasNext()) {
                        return;
                     }

                     entity3 = (Entity)var3.next();
                  } while(!(entity3 instanceof EntityPlayer));
               } while(entity3 == mc.player);

               n27 = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosX;
               n28 = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosY;
               n29 = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * (double)renderWorldLastEvent.getPartialTicks() - mc.getRenderManager().viewerPosZ;
               GL11.glPushMatrix();
               GL11.glDisable(2929);
               GL11.glDisable(3553);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.disableLighting();
               GlStateManager.enableBlend();
               n30 = Math.min(Math.max(1.2F * mc.player.getDistance(entity3) * 0.15F, 1.25F), 6.0F) * 0.015F;
               GL11.glTranslatef((float)n27, (float)n28 + entity3.height + 0.7F, (float)n29);
               GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glScalef(-n30, -n30, -n30);
               String str = "BloodWare";
               if (FriendManager.FRIENDS.contains(entity3.getName())) {
                  n22 = (int)(((EntityPlayer)entity3).func_110143_aJ() / ((EntityPlayer)entity3).func_110138_aP() * 100.0F);
                  Gui.drawRect(-mc.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append("BloodWare").append(" ").append(n22).append("%"))) / 2 - 2, -2, mc.fontRenderer.getStringWidth("BloodWare") / 2 + 16, 10, FriendManager.FRIENDS.contains(entity3.getName()) ? Color.green.getRGB() : Integer.MIN_VALUE);
                  mc.fontRenderer.drawString(String.valueOf((new StringBuilder()).append("BloodWare").append(" ").append(TextFormatting.GREEN).append(n22).append("%")), -this.center(String.valueOf((new StringBuilder()).append("BloodWare").append(" ").append(TextFormatting.DARK_GREEN).append(n22).append("%"))), 1, -1);
                  n23 = -mc.fontRenderer.getStringWidth(entity3.getName()) / 2 - 8;
                  if (Item.getIdFromItem(((EntityPlayer)entity3).inventory.getCurrentItem().getItem()) != 0) {
                     mc.getRenderItem().zLevel = -100.0F;
                     mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity3).inventory.getCurrentItem(), n23 - 2, -20);
                     mc.getRenderItem().zLevel = 0.0F;
                     n24 = -30;

                     for(var16 = EnchantmentHelper.getEnchantments(((EntityPlayer)entity3).inventory.getCurrentItem()).keySet().iterator(); var16.hasNext(); n24 -= 12) {
                        enchantment7 = (Enchantment)var16.next();
                        getEnchantmentLevel7 = EnchantmentHelper.getEnchantmentLevel(enchantment7, ((EntityPlayer)entity3).inventory.getCurrentItem());
                        mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)), (float)(n23 + 6 - this.center(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)))), (float)n24, -1);
                     }

                     n23 += 15;
                  }

                  for(var23 = entity3.getArmorInventoryList().iterator(); var23.hasNext(); n23 += 17) {
                     itemStack4 = (ItemStack)var23.next();
                     mc.getRenderItem().zLevel = -100.0F;
                     mc.getRenderItem().renderItemIntoGUI(new ItemStack(itemStack4.getItem()), n23, -20);
                     mc.getRenderItem().zLevel = 0.0F;
                     n25 = -30;

                     for(var27 = EnchantmentHelper.getEnchantments(itemStack4).keySet().iterator(); var27.hasNext(); n25 -= 12) {
                        enchantment8 = (Enchantment)var27.next();
                        getEnchantmentLevel8 = EnchantmentHelper.getEnchantmentLevel(enchantment8, itemStack4);
                        mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment8.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel8)), (float)(n23 + 9 - this.center(enchantment8.getName().substring(12).charAt(0) + getEnchantmentLevel8)), (float)n25, -1);
                     }
                  }

                  GL11.glEnable(2929);
                  GL11.glPopMatrix();
               } else {
                  n22 = (int)(((EntityPlayer)entity3).func_110143_aJ() / ((EntityPlayer)entity3).func_110138_aP() * 100.0F);
                  Gui.drawRect(-mc.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(n22).append("%"))) / 2 - 2, -2, mc.fontRenderer.getStringWidth(entity3.getName()) / 2 + 16, 10, FriendManager.FRIENDS.contains(entity3.getName()) ? Color.green.getRGB() : Integer.MIN_VALUE);
                  mc.fontRenderer.drawString(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(TextFormatting.GREEN).append(n22).append("%")), -this.center(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(TextFormatting.DARK_GREEN).append(n22).append("%"))), 1, -1);
                  n23 = -mc.fontRenderer.getStringWidth(entity3.getName()) / 2 - 8;
                  if (Item.getIdFromItem(((EntityPlayer)entity3).inventory.getCurrentItem().getItem()) != 0) {
                     mc.getRenderItem().zLevel = -100.0F;
                     mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity3).inventory.getCurrentItem(), n23 - 2, -20);
                     mc.getRenderItem().zLevel = 0.0F;
                     n24 = -30;

                     for(var16 = EnchantmentHelper.getEnchantments(((EntityPlayer)entity3).inventory.getCurrentItem()).keySet().iterator(); var16.hasNext(); n24 -= 12) {
                        enchantment7 = (Enchantment)var16.next();
                        getEnchantmentLevel7 = EnchantmentHelper.getEnchantmentLevel(enchantment7, ((EntityPlayer)entity3).inventory.getCurrentItem());
                        mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)), (float)(n23 + 6 - this.center(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)))), (float)n24, -1);
                     }

                     n23 += 15;
                  }

                  for(var23 = entity3.getArmorInventoryList().iterator(); var23.hasNext(); n23 += 17) {
                     itemStack4 = (ItemStack)var23.next();
                     mc.getRenderItem().zLevel = -100.0F;
                     mc.getRenderItem().renderItemIntoGUI(new ItemStack(itemStack4.getItem()), n23, -20);
                     mc.getRenderItem().zLevel = 0.0F;
                     n25 = -30;

                     for(var27 = EnchantmentHelper.getEnchantments(itemStack4).keySet().iterator(); var27.hasNext(); n25 -= 12) {
                        enchantment8 = (Enchantment)var27.next();
                        getEnchantmentLevel8 = EnchantmentHelper.getEnchantmentLevel(enchantment8, itemStack4);
                        mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment8.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel8)), (float)(n23 + 9 - this.center(enchantment8.getName().substring(12).charAt(0) + getEnchantmentLevel8)), (float)n25, -1);
                     }
                  }

                  GL11.glEnable(2929);
                  GL11.glPopMatrix();
               }
            }
         }
      } else {
         int k;
         if (Objects.equals(valString, "BloodV1")) {
            var3 = mc.world.loadedEntityList.iterator();

            while(true) {
               while(true) {
                  do {
                     do {
                        if (!var3.hasNext()) {
                           return;
                        }

                        entity3 = (Entity)var3.next();
                     } while(!(entity3 instanceof EntityPlayer));
                  } while(entity3 == mc.player);

                  n27 = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosX;
                  n28 = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosY;
                  n29 = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosZ;
                  GL11.glPushMatrix();
                  GL11.glDisable(2929);
                  GL11.glDisable(3553);
                  GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                  GlStateManager.disableLighting();
                  GlStateManager.enableBlend();
                  n30 = Math.min(Math.max(1.2F * mc.player.getDistance(entity3) * 0.15F, 1.25F), 6.0F) * 0.015F;
                  GL11.glTranslatef((float)n27, (float)n28 + entity3.height + 0.6F, (float)n29);
                  GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
                  GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                  GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                  GL11.glScalef(-n30, -n30, -n30);
                  boolean n26;
                  if (FriendManager.FRIENDS.contains(entity3.getName())) {
                     k = (int)(((EntityPlayer)entity3).func_110143_aJ() / ((EntityPlayer)entity3).func_110138_aP() * 100.0F);
                     n22 = (int)((float)(mc.fontRenderer.getStringWidth(entity3.getName()) / 2 + 16 - (-mc.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(k).append("%"))) / 2 - 2)) / ((EntityPlayer)entity3).func_110138_aP());
                     RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append("Protect ").append(k).append("%"))) / 2 - 2), -2.0F, (float)(this.fontRenderer.getStringWidth("Protect") / 2 + 16), 10.0F, Integer.MIN_VALUE);
                     RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append("Protect ").append(k).append("%"))) / 2 - 2), 10.0F, (float)(-this.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append("Protect ").append(k).append("%"))) / 2) + (float)n22 * ((EntityPlayer)entity3).func_110143_aJ(), 12.0F, ColorUtils.getHealthColor((EntityLivingBase)entity3).getRGB());
                     this.fontRenderer.drawString(String.valueOf((new StringBuilder()).append("Protect ").append(TextFormatting.GREEN).append(k).append("%")), 0 - this.getcenter(String.valueOf((new StringBuilder()).append("Protect ").append(TextFormatting.GREEN).append(k).append("%"))), 1, -1);
                     n23 = -mc.fontRenderer.getStringWidth(entity3.getName()) / 2 - 8;
                     if (Item.getIdFromItem(((EntityPlayer)entity3).inventory.getCurrentItem().getItem()) != 0) {
                        mc.getRenderItem().zLevel = -100.0F;
                        mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity3).func_184586_b(EnumHand.MAIN_HAND), n23 - 2, -20);
                        mc.getRenderItem().zLevel = 0.0F;
                        n24 = -30;

                        for(var16 = EnchantmentHelper.getEnchantments(((EntityPlayer)entity3).inventory.getCurrentItem()).keySet().iterator(); var16.hasNext(); n24 -= 12) {
                           enchantment7 = (Enchantment)var16.next();
                           getEnchantmentLevel7 = EnchantmentHelper.getEnchantmentLevel(enchantment7, ((EntityPlayer)entity3).inventory.getCurrentItem());
                           mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)), (float)(n23 + 6 - this.getcenter(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)))), (float)n24, -1);
                        }

                        n23 += 15;
                     }

                     for(var23 = entity3.getArmorInventoryList().iterator(); var23.hasNext(); n23 += 17) {
                        itemStack4 = (ItemStack)var23.next();
                        mc.getRenderItem().zLevel = -100.0F;
                        mc.getRenderItem().renderItemIntoGUI(itemStack4, n23, -20);
                        mc.getRenderItem().zLevel = 0.0F;
                        n25 = -30;

                        for(var27 = EnchantmentHelper.getEnchantments(itemStack4).keySet().iterator(); var27.hasNext(); n25 -= 12) {
                           enchantment8 = (Enchantment)var27.next();
                           getEnchantmentLevel8 = EnchantmentHelper.getEnchantmentLevel(enchantment8, itemStack4);
                           mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment8.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel8)), (float)(n23 + 9 - this.getcenter(enchantment8.getName().substring(12).charAt(0) + getEnchantmentLevel8)), (float)n25, -1);
                        }
                     }

                     n26 = false;
                     GL11.glEnable(2929);
                     GL11.glPopMatrix();
                  } else {
                     k = (int)(((EntityPlayer)entity3).func_110143_aJ() / ((EntityPlayer)entity3).func_110138_aP() * 100.0F);
                     n22 = (int)((float)(mc.fontRenderer.getStringWidth(entity3.getName()) / 2 + 16 - (-mc.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(k).append("%"))) / 2 - 2)) / ((EntityPlayer)entity3).func_110138_aP());
                     RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(k).append("%"))) / 2 - 2), -2.0F, (float)(this.fontRenderer.getStringWidth(entity3.getName()) / 2 + 16), 10.0F, Integer.MIN_VALUE);
                     RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(k).append("%"))) / 2 - 2), 10.0F, (float)(-this.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(k).append("%"))) / 2) + (float)n22 * ((EntityPlayer)entity3).func_110143_aJ(), 12.0F, ColorUtils.getHealthColor((EntityLivingBase)entity3).getRGB());
                     this.fontRenderer.drawString(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(TextFormatting.GREEN).append(k).append("%")), 0 - this.getcenter(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(TextFormatting.GREEN).append(k).append("%"))), 1, -1);
                     n23 = -mc.fontRenderer.getStringWidth(entity3.getName()) / 2 - 8;
                     if (Item.getIdFromItem(((EntityPlayer)entity3).inventory.getCurrentItem().getItem()) != 0) {
                        mc.getRenderItem().zLevel = -100.0F;
                        mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity3).func_184586_b(EnumHand.MAIN_HAND), n23 - 2, -20);
                        mc.getRenderItem().zLevel = 0.0F;
                        n24 = -30;

                        for(var16 = EnchantmentHelper.getEnchantments(((EntityPlayer)entity3).inventory.getCurrentItem()).keySet().iterator(); var16.hasNext(); n24 -= 12) {
                           enchantment7 = (Enchantment)var16.next();
                           getEnchantmentLevel7 = EnchantmentHelper.getEnchantmentLevel(enchantment7, ((EntityPlayer)entity3).inventory.getCurrentItem());
                           mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)), (float)(n23 + 6 - this.getcenter(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)))), (float)n24, -1);
                        }

                        n23 += 15;
                     }

                     for(var23 = entity3.getArmorInventoryList().iterator(); var23.hasNext(); n23 += 17) {
                        itemStack4 = (ItemStack)var23.next();
                        mc.getRenderItem().zLevel = -100.0F;
                        mc.getRenderItem().renderItemIntoGUI(itemStack4, n23, -20);
                        mc.getRenderItem().zLevel = 0.0F;
                        n25 = -30;

                        for(var27 = EnchantmentHelper.getEnchantments(itemStack4).keySet().iterator(); var27.hasNext(); n25 -= 12) {
                           enchantment8 = (Enchantment)var27.next();
                           getEnchantmentLevel8 = EnchantmentHelper.getEnchantmentLevel(enchantment8, itemStack4);
                           mc.fontRenderer.drawStringWithShadow(String.valueOf((new StringBuilder()).append(String.valueOf(enchantment8.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel8)), (float)(n23 + 9 - this.getcenter(enchantment8.getName().substring(12).charAt(0) + getEnchantmentLevel8)), (float)n25, -1);
                        }
                     }

                     n26 = false;
                     GL11.glEnable(2929);
                     GL11.glPopMatrix();
                  }
               }
            }
         } else if (Objects.equals(valString, "BloodV2")) {
            var3 = mc.world.loadedEntityList.iterator();

            while(true) {
               while(true) {
                  do {
                     do {
                        if (!var3.hasNext()) {
                           return;
                        }

                        entity3 = (Entity)var3.next();
                     } while(!(entity3 instanceof EntityPlayer));
                  } while(entity3 == mc.player);

                  n27 = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosX;
                  n28 = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosY;
                  n29 = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * (double)mc.getRenderPartialTicks() - mc.getRenderManager().viewerPosZ;
                  GL11.glPushMatrix();
                  GL11.glDisable(2929);
                  GL11.glEnable(3042);
                  GL11.glBlendFunc(770, 771);
                  GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                  n30 = Math.min(Math.max(1.2F * mc.player.getDistance(entity3) * 0.15F, 1.25F), 6.0F) * 0.015F;
                  GL11.glTranslatef((float)n27, (float)n28 + entity3.height + 0.6F, (float)n29);
                  GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
                  GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                  GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                  GL11.glScalef(-n30, -n30, -n30);
                  k = (int)(((EntityPlayer)entity3).func_110143_aJ() / ((EntityPlayer)entity3).func_110138_aP() * 100.0F);
                  float n31 = (float)(mc.fontRenderer.getStringWidth(String.valueOf((new StringBuilder()).append(entity3.getDisplayName().getUnformattedText()).append(TextFormatting.GREEN).append(" HP: ").append(k))) + 10);
                  RenderUtils.drawShadowRect((double)(-(n31 / 2.0F)), 0.0D, (double)(n31 / 2.0F), 15.0D, 3);
                  RenderUtils.drawRect(-(n31 / 2.0F), 0.0F, n31 / 2.0F, 15.0F, (new Color(30, 30, 30)).getRGB());
                  n23 = (int)(n31 - 4.0F);
                  RenderUtils.drawShadowRect((double)(-(n31 / 2.0F) + 2.0F), 11.0D, (double)(-(n31 / 2.0F) + 2.0F + (float)n23 / ((EntityPlayer)entity3).func_110138_aP() * ((EntityPlayer)entity3).func_110143_aJ()), 13.0D, 1);
                  RenderUtils.drawRect(-(n31 / 2.0F) + 2.0F, 11.0F, -(n31 / 2.0F) + 2.0F + (float)n23 / ((EntityPlayer)entity3).func_110138_aP() * ((EntityPlayer)entity3).func_110143_aJ(), 13.0F, BloodWare.color.getRGB());
                  int n34;
                  ArrayList list2;
                  RenderItem getRenderItem2;
                  ItemStack itemStack6;
                  ItemStack e2;
                  Iterator var33;
                  if (FriendManager.FRIENDS.contains(entity3.getName())) {
                     this.fontRenderer.drawString(String.valueOf((new StringBuilder()).append("Protect ").append(TextFormatting.GREEN).append(k).append("%")), 0 - this.getcenter(String.valueOf((new StringBuilder()).append("Protect ").append(TextFormatting.GREEN).append(k).append("%"))), 1, -1);
                     list2 = new ArrayList();
                     if (!(((EntityPlayer)entity3).func_184586_b(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                        list2.add(((EntityPlayer)entity3).func_184586_b(EnumHand.MAIN_HAND));
                     }

                     var16 = entity3.getArmorInventoryList().iterator();

                     while(var16.hasNext()) {
                        e2 = (ItemStack)var16.next();
                        if (!(e2.getItem() instanceof ItemAir)) {
                           list2.add(e2);
                        }
                     }

                     if (!(((EntityPlayer)entity3).func_184586_b(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                        list2.add(((EntityPlayer)entity3).func_184586_b(EnumHand.OFF_HAND));
                     }

                     n34 = -(list2.size() * 16 / 2);

                     for(var33 = list2.iterator(); var33.hasNext(); n34 += 16) {
                        itemStack6 = (ItemStack)var33.next();
                        getRenderItem2 = mc.getRenderItem();
                        GlStateManager.pushMatrix();
                        GlStateManager.enableBlend();
                        RenderHelper.enableStandardItemLighting();
                        getRenderItem2.zLevel = -100.0F;
                        getRenderItem2.renderItemIntoGUI(itemStack6, n34, -20);
                        getRenderItem2.zLevel = 0.0F;
                        RenderHelper.disableStandardItemLighting();
                        GlStateManager.enableAlpha();
                        GlStateManager.disableBlend();
                        GlStateManager.disableLighting();
                        GlStateManager.popMatrix();
                        GlStateManager.pushMatrix();
                        GlStateManager.disableLighting();
                        GlStateManager.disableDepth();
                        GlStateManager.popMatrix();
                     }

                     GL11.glEnable(2929);
                     GL11.glColor3f(255.0F, 255.0F, 255.0F);
                     GL11.glEnable(2929);
                     GL11.glPopMatrix();
                  } else {
                     this.fontRenderer.drawString(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(TextFormatting.GREEN).append(k).append("%")), 0 - this.getcenter(String.valueOf((new StringBuilder()).append(entity3.getName()).append(" ").append(TextFormatting.GREEN).append(k).append("%"))), 1, -1);
                     list2 = new ArrayList();
                     if (!(((EntityPlayer)entity3).func_184586_b(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                        list2.add(((EntityPlayer)entity3).func_184586_b(EnumHand.MAIN_HAND));
                     }

                     var16 = entity3.getArmorInventoryList().iterator();

                     while(var16.hasNext()) {
                        e2 = (ItemStack)var16.next();
                        if (!(e2.getItem() instanceof ItemAir)) {
                           list2.add(e2);
                        }
                     }

                     if (!(((EntityPlayer)entity3).func_184586_b(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                        list2.add(((EntityPlayer)entity3).func_184586_b(EnumHand.OFF_HAND));
                     }

                     n34 = -(list2.size() * 16 / 2);

                     for(var33 = list2.iterator(); var33.hasNext(); n34 += 16) {
                        itemStack6 = (ItemStack)var33.next();
                        getRenderItem2 = mc.getRenderItem();
                        GlStateManager.pushMatrix();
                        GlStateManager.enableBlend();
                        RenderHelper.enableStandardItemLighting();
                        getRenderItem2.zLevel = -100.0F;
                        getRenderItem2.renderItemIntoGUI(itemStack6, n34, -20);
                        getRenderItem2.zLevel = 0.0F;
                        RenderHelper.disableStandardItemLighting();
                        GlStateManager.enableAlpha();
                        GlStateManager.disableBlend();
                        GlStateManager.disableLighting();
                        GlStateManager.popMatrix();
                        GlStateManager.pushMatrix();
                        GlStateManager.disableLighting();
                        GlStateManager.disableDepth();
                        GlStateManager.popMatrix();
                     }

                     GL11.glEnable(2929);
                     GL11.glColor3f(255.0F, 255.0F, 255.0F);
                     GL11.glEnable(2929);
                     GL11.glPopMatrix();
                  }
               }
            }
         }
      }
   }

   public int getcenter(String s) {
      return mc.fontRenderer.getStringWidth(s) / 2;
   }

   public int getcenter(int i) {
      return mc.fontRenderer.getStringWidth(String.valueOf(i)) / 2;
   }

   public int center(String s) {
      return mc.fontRenderer.getStringWidth(s) / 2;
   }

   public NameTags() {
      super("NameTags", Category.RENDER);
      this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
      Modes.add("Blood");
      Modes.add("BloodV1");
      Modes.add("BloodV2");
      BloodWare.instance.settingsManager.rSetting(new Setting("Modes", this, "Blood", (ArrayList)Modes));
   }

   public int center(int i) {
      return mc.fontRenderer.getStringWidth(String.valueOf(i)) / 2;
   }
}
