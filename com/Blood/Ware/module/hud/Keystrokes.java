package com.Blood.Ware.module.hud;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Keystrokes extends Module {
   public int color;

   public Keystrokes() {
      super("Keystrokes", Category.HUD);
      BloodWare.instance.settingsManager.rSetting(new Setting("keyX", this, 1.0D, 1.0D, 897.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("keyY", this, 1.0D, -105.0D, 340.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("mouseButtons", this, false));
   }

   @SubscribeEvent
   public void onRender(Text text) {
      new ScaledResolution(mc);
      if (text.getType() == ElementType.TEXT) {
         float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "keyX").getValDouble();
         float n2 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "keyY").getValDouble();
         boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName((Module)this, "mouseButtons").getValBoolean();
         GlStateManager.pushMatrix();
         if (!mc.gameSettings.showDebugInfo) {
            int n3 = (int)n;
            int n4 = (int)n2;
            this.color = Color.WHITE.getRGB();
            RenderUtils.drawRect((float)(n3 + 20), (float)(n4 + 111), (float)(n3 + 41), (float)(n4 + 130), -1879048192);
            RenderUtils.drawRect((float)(n3 + 1), (float)(n4 + 130), (float)(n3 + 61), (float)(n4 + 150), -1879048192);
            if (mc.gameSettings.keyBindForward.isPressed()) {
               RenderUtils.drawRect((float)(n3 + 21), (float)(n4 + 112), (float)(n3 + 40), (float)(n4 + 130), this.color);
            }

            if (mc.gameSettings.keyBindBack.isPressed()) {
               RenderUtils.drawRect((float)(n3 + 21), (float)(n4 + 131), (float)(n3 + 40), (float)(n4 + 149), this.color);
            }

            if (mc.gameSettings.keyBindLeft.isPressed()) {
               RenderUtils.drawRect((float)(n3 + 2), (float)(n4 + 131), (float)(n3 + 20), (float)(n4 + 149), this.color);
            }

            if (mc.gameSettings.keyBindRight.isPressed()) {
               RenderUtils.drawRect((float)(n3 + 41), (float)(n4 + 131), (float)(n3 + 60), (float)(n4 + 149), this.color);
            }

            if (valBoolean) {
               RenderUtils.drawRect((float)(n3 + 30), (float)(n4 + 170), (float)(n3 + 61), (float)(n4 + 150), -1879048192);
               RenderUtils.drawRect((float)(n3 + 1), (float)(n4 + 170), (float)(n3 + 30), (float)(n4 + 150), -1879048192);
               if (mc.gameSettings.keyBindAttack.isPressed()) {
                  RenderUtils.drawRect((float)(n3 + 2), (float)(n4 + 150), (float)(n3 + 30), (float)(n4 + 169), this.color);
               }

               if (mc.gameSettings.keyBindUseItem.isPressed()) {
                  RenderUtils.drawRect((float)(n3 + 60), (float)(n4 + 150), (float)(n3 + 31), (float)(n4 + 169), this.color);
               }

               mc.fontRenderer.drawStringWithShadow("LMB", (float)(n3 + 7), (float)(n4 + 156), -1);
               mc.fontRenderer.drawStringWithShadow("RMB", (float)(n3 + 37), (float)(n4 + 156), -1);
            }

            mc.fontRenderer.drawStringWithShadow("W", (float)(n3 + 28), (float)(n4 + 117), -1);
            mc.fontRenderer.drawStringWithShadow("A", (float)(n3 + 8), (float)(n4 + 136), -1);
            mc.fontRenderer.drawStringWithShadow("S", (float)(n3 + 28), (float)(n4 + 136), -1);
            mc.fontRenderer.drawStringWithShadow("D", (float)(n3 + 48), (float)(n4 + 136), -1);
         }

         GlStateManager.popMatrix();
      }

   }
}
