package com.Blood.Ware.module.hud;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArmorHUD extends Module {
   private static RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();

   public ArmorHUD() {
      super("ArmorHUD", Category.HUD);
   }

   @SubscribeEvent
   public void onRender(Text event) {
      GlStateManager.enableTexture2D();
      ScaledResolution resolution = new ScaledResolution(mc);
      int i = resolution.getScaledWidth() / 2;
      int iteration = 0;
      int y = resolution.getScaledHeight() - 55 - 10;
      Iterator var6 = mc.player.field_71071_by.armorInventory.iterator();

      while(var6.hasNext()) {
         ItemStack is = (ItemStack)var6.next();
         ++iteration;
         if (!is.isEmpty()) {
            int x = i - 90 + (9 - iteration) * 20 + 2;
            GlStateManager.enableDepth();
            itemRender.zLevel = 200.0F;
            itemRender.renderItemAndEffectIntoGUI(is, x, y);
            itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, is, x, y, "");
            itemRender.zLevel = 0.0F;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            String s = is.getCount() > 1 ? is.getCount() + "" : "";
            mc.fontRenderer.drawStringWithShadow(s, (float)(x + 19 - 2 - mc.fontRenderer.getStringWidth(s)), (float)(y + 9), 16777215);
            float green = ((float)is.getMaxDamage() - (float)is.getItemDamage()) / (float)is.getMaxDamage();
            float red = 1.0F - green;
            int dmg = 100 - (int)(red * 100.0F);
            mc.fontRenderer.drawStringWithShadow(dmg + "", (float)(x + 8 - mc.fontRenderer.getStringWidth(dmg + "") / 2), (float)(y - 11), toHex((int)(red * 255.0F), (int)(green * 255.0F), 0));
         }
      }

      GlStateManager.enableDepth();
      GlStateManager.disableLighting();
   }

   public static int toHex(int r, int g, int b) {
      return -16777216 | (r & 255) << 16 | (g & 255) << 8 | b & 255;
   }
}
