package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InvViewer extends Module {
   @SubscribeEvent
   public void onOverlayRender(RenderGameOverlayEvent renderGameOverlayEvent) {
      new ScaledResolution(mc);
      if (renderGameOverlayEvent.getType() == ElementType.TEXT) {
         float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Horizontal").getValDouble();
         float n2 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Vertical").getValDouble();
         GlStateManager.pushMatrix();
         RenderHelper.enableGUIStandardItemLighting();
         RenderUtils.drawShadowRect((double)n, (double)n2, (double)(n + 145.0F), (double)(n2 + 48.0F), 2);
         GlStateManager.resetColor();

         for(int i = 0; i < 27; ++i) {
            ItemStack itemStack = (ItemStack)mc.player.field_71071_by.mainInventory.get(i + 9);
            float n3 = n + (float)(i % 9 * 16);
            float n4 = n2 + (float)(i / 9 * 16);
            mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, (int)n3, (int)n4);
            mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, itemStack, (int)n3, (int)n4, (String)null);
         }

         RenderHelper.disableStandardItemLighting();
         mc.getRenderItem().zLevel = 0.0F;
         GlStateManager.popMatrix();
      }

   }

   public InvViewer() {
      super("InvViewer", Category.Misc);
      BloodWare.instance.settingsManager.rSetting(new Setting("Horizontal", this, 387.0D, 0.0D, 810.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Vertical", this, 424.0D, 0.0D, 490.0D, true));
   }
}
