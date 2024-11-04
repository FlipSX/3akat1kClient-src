package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;

public class ChinaHat extends Module {
   public static List<String> Modes = new ArrayList();

   public ChinaHat() {
      super("ChinaHat", Category.RENDER);
      Modes.add("V2");
      Modes.add("V1");
      BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "V2", (ArrayList)Modes));
   }

   @SubscribeEvent
   public void onWorldRender(RenderWorldLastEvent renderWorldLastEvent) {
      String valString = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Mode").getValString();
      double n;
      if (Objects.equals(valString, "V2")) {
         n = mc.player.isSneaking() ? -0.1D : 0.12D;
         if (mc.gameSettings.thirdPersonView == 1 || mc.gameSettings.thirdPersonView == 2) {
            GlStateManager.pushMatrix();
            GL11.glBlendFunc(770, 771);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            RenderUtils.enableSmoothLine(2.5F);
            GL11.glShadeModel(7425);
            GL11.glDisable(2884);
            GL11.glEnable(3042);
            GL11.glEnable(2929);
            GL11.glTranslatef(0.0F, (float)((double)mc.player.height + n), 0.0F);
            GL11.glRotatef(-mc.player.rotationYaw, 0.0F, 1.0F, 0.0F);
            Color white = Color.WHITE;
            Color color = new Color(105, 55, 255);
            GL11.glBegin(6);
            RenderUtils.glColor(color, 255.0F);
            GL11.glVertex3d(0.0D, 0.3D, 0.0D);

            float n3;
            for(n3 = 0.0F; (double)n3 < 360.5D; ++n3) {
               RenderUtils.glColor(ClickGUI.getColor());
               GL11.glVertex3d(Math.cos((double)n3 * 3.141592653589793D / 180.0D) * 0.66D, 0.0D, Math.sin((double)n3 * 3.141592653589793D / 180.0D) * 0.66D);
            }

            GL11.glVertex3d(0.0D, 0.3D, 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);

            for(n3 = 0.0F; (double)n3 < 360.5D; ++n3) {
               RenderUtils.glColor(ClickGUI.getColor());
               GL11.glVertex3d(Math.cos((double)n3 * 3.141592653589793D / 180.0D) * 0.66D, 0.0D, Math.sin((double)n3 * 3.141592653589793D / 180.0D) * 0.66D);
            }

            GL11.glEnd();
            GlStateManager.enableAlpha();
            RenderUtils.disableSmoothLine();
            GL11.glShadeModel(7424);
            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.resetColor();
            GlStateManager.popMatrix();
         }
      } else if (Objects.equals(valString, "V1")) {
         n = mc.player.isSneaking() ? -0.18D : 0.04D;
         GL11.glPushMatrix();
         GL11.glBlendFunc(770, 771);
         GL11.glEnable(3042);
         GL11.glDisable(3553);
         GL11.glDepthMask(false);
         GL11.glColor4f((float)Color.magenta.getRed() / 255.0F, (float)Color.magenta.getGreen() / 255.0F, (float)Color.magenta.getBlue() / 255.0F, 0.49019608F);
         GL11.glTranslatef(0.0F, (float)((double)((float)((double)mc.player.height + 0.36D)) + n), 0.0F);
         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         Cylinder cylinder = new Cylinder();
         cylinder.setDrawStyle(100000);
         cylinder.setDrawStyle(100011);
         cylinder.draw(0.0F, 0.55F, 0.3F, 150, 100);
         GL11.glEnable(3553);
         GL11.glDepthMask(true);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
      }

   }
}
