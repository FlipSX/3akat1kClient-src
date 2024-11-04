package com.Blood.Ware.module.hud;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColorUtils;
import com.Blood.Ware.utils.RenderUtil;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Watermark extends Module {
   public static List Modes = new ArrayList();
   private final FontRenderer fr;

   @SubscribeEvent
   public void onOverlayRender(Text text) {
      String valString = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Mode").getValString();
      if (text.getType() == ElementType.TEXT) {
         int[] rainbow3;
         String format2;
         String str2;
         String lowerCase;
         if (Objects.equals(valString, "Anim")) {
            rainbow3 = getRainbow(5, 0.1F);
            RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
            format2 = (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime());
            str2 = "3akat1k client";
            switch((int)(System.currentTimeMillis() / 700L % 42L)) {
            case 1:
               str2 = "  ";
               break;
            case 2:
               str2 = " | ";
               break;
            case 3:
               str2 = " |/ ";
               break;
            case 4:
               str2 = " |// ";
               break;
            case 5:
               str2 = "3 ";
               break;
            case 6:
               str2 = "3a3 ";
               break;
            case 7:
               str2 = "3akl ";
               break;
            case 8:
               str2 = "3aka\\ ";
               break;
            case 9:
               str2 = "3akat\\/ ";
               break;
            case 10:
               str2 = "3akat1 ";
               break;
            case 11:
               str2 = "3akat1k ";
               break;
            case 12:
               str2 = "3akat1kC| ";
               break;
            case 13:
               str2 = "3akat1kCl|2 ";
               break;
            case 14:
               str2 = "3akat1kCli|_ ";
               break;
            case 15:
               str2 = "3akat1kClien ";
               break;
            case 16:
               str2 = "3akat1kClient/ ";
               break;
            case 17:
               str2 = "3akat1kClient ";
               break;
            case 18:
               str2 = "3akat1kClient@# ";
               break;
            case 19:
               str2 = "3akat1kClient. ";
               break;
            case 20:
               str2 = "3akat1kClein|/ ";
               break;
            case 21:
               str2 = "3akat1kClie ";
               break;
            case 22:
               str2 = "3akat1kCli|/ ";
               break;
            case 23:
               str2 = "3akat1kCl. ";
               break;
            case 24:
               str2 = "3akat1kC@# ";
               break;
            case 25:
               str2 = "3akat1k ";
               break;
            case 26:
               str2 = "3akat1/ ";
               break;
            case 27:
               str2 = "3akat ";
               break;
            case 28:
               str2 = "3aka|_ ";
               break;
            case 29:
               str2 = "3ak|2 ";
               break;
            case 30:
               str2 = "3a| ";
               break;
            case 31:
               str2 = "3 ";
               break;
            case 32:
               str2 = "3 ";
               break;
            case 33:
               str2 = "3\\/ ";
               break;
            case 34:
               str2 = "3\\ ";
               break;
            case 35:
               str2 = " |// ";
               break;
            case 36:
               str2 = " |/ ";
               break;
            case 37:
               str2 = " | ";
               break;
            case 38:
               str2 = "  ";
            }

            lowerCase = String.valueOf((new StringBuilder()).append(str2).append(" | ").append(format2).append(" |  Fps ").append(Minecraft.getDebugFPS()));
            Gui.drawRect(5, 5, this.fr.getStringWidth(lowerCase) + 25, 21, (new Color(37, 37, 37, 255)).getRGB());
            Gui.drawRect(5, 9, this.fr.getStringWidth(lowerCase) + 25, 9, (new Color(20, 20, 20, 100)).getRGB());
            this.fr.drawStringWithShadow(lowerCase, 8.0F, 11.0F, -1);
         } else if (Objects.equals(valString, "Static")) {
            rainbow3 = getRainbow(5, 0.1F);
            RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
            format2 = (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime());
            str2 = "3akat1kClient";
            switch((int)(System.currentTimeMillis() / 700L % 15L)) {
            case 1:
               str2 = "3akat1kClient";
            default:
               if (Watermark.mc.isSingleplayer()) {
                  lowerCase = "localhost";
               } else {
                  lowerCase = Watermark.mc.getCurrentServerData().serverIP.toLowerCase();
               }

               String value2 = String.valueOf((new StringBuilder()).append(str2).append(" | ").append(format2).append(" |  Fps ").append(Minecraft.getDebugFPS()).append("  | ").append(lowerCase));
               Gui.drawRect(5, 5, this.fr.getStringWidth(value2) + 21, 21, (new Color(37, 37, 37, 255)).getRGB());
               Gui.drawRect(5, 9, this.fr.getStringWidth(value2) + 21, 9, (new Color(20, 20, 20, 100)).getRGB());
               this.fr.drawStringWithShadow(value2, 8.0F, 8.0F, -1);
            }
         } else if (Objects.equals(valString, "Skeet")) {
            rainbow3 = getRainbow(5, 0.1F);
            RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
            StringBuilder obj = new StringBuilder(String.valueOf((new StringBuilder()).append(new StringBuilder(String.valueOf((new StringBuilder()).append(new StringBuilder(String.valueOf((new StringBuilder()).append((new StringBuilder()).append("3akat1kClient").append(" ").append("0.1")).append(" | ").append((new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime())).append(" | Fps ").append(Minecraft.getDebugFPS())))).append(" | ").append(Watermark.mc.isSingleplayer() ? "localhost" : Watermark.mc.getCurrentServerData().serverIP.toLowerCase())))).append("  ")));
            Minecraft mc = Watermark.mc;
            lowerCase = String.valueOf(obj);
            float n = (float)(Minecraft.getMinecraft().fontRenderer.getStringWidth(lowerCase) + 6);
            int n2 = true;
            int n3 = true;
            int n4 = true;
            RenderUtil.drawRect(2.0D, 2.0D, (double)(2.0F + n + 2.0F), 22.0D, (new Color(5, 5, 5, 255)).getRGB());
            RenderUtil.drawBorderedRect(2.5D, 2.5D, (double)(2.0F + n) + 1.5D, 21.5D, 0.5D, (new Color(40, 40, 40, 255)).getRGB(), (new Color(60, 60, 60, 255)).getRGB(), true);
            RenderUtil.drawBorderedRect(4.0D, 4.0D, (double)(2.0F + n), 20.0D, 0.5D, (new Color(22, 22, 22, 255)).getRGB(), (new Color(60, 60, 60, 255)).getRGB(), true);
            RenderUtil.drawRect(4.5D, 4.5D, (double)(2.0F + n) - 0.5D, 6.5D, (new Color(9, 9, 9, 255)).getRGB());
            RenderUtil.drawGradientSideways(4.0D, 5.0D, (double)(4.0F + n / 3.0F), 6.0D, (new Color(81, 149, 219, 255)).getRGB(), (new Color(180, 49, 218, 255)).getRGB());
            RenderUtil.drawGradientSideways((double)(4.0F + n / 3.0F), 5.0D, (double)(4.0F + n / 3.0F * 2.0F), 6.0D, (new Color(180, 49, 218, 255)).getRGB(), (new Color(236, 93, 128, 255)).getRGB());
            RenderUtil.drawGradientSideways((double)(4.0F + n / 3.0F * 2.0F), 5.0D, (double)(n / 3.0F * 3.0F + 1.0F), 6.0D, (new Color(236, 93, 128, 255)).getRGB(), (new Color(235, 255, 0, 255)).getRGB());
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(lowerCase, 6.0F, 9.0F, -1);
         } else if (Objects.equals(valString, "OnetapOLD")) {
            String value4 = String.valueOf((new StringBuilder()).append("3akat1kClient | ").append((new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime())).append(" |  Fps ").append(Minecraft.getDebugFPS()));
            Gui.drawRect(5, 7, this.fr.getStringWidth(value4) + 25, 19, (new Color(37, 37, 37, 111)).getRGB());
            Gui.drawRect(5, 6, this.fr.getStringWidth(value4) + 25, 8, ColorUtils.astolfoColors((int)((double)Math.abs(System.currentTimeMillis() / 50L) / 100.0D + 0.16999999999999998D), 5));
            Gui.drawRect(5, 9, this.fr.getStringWidth(value4) + 25, 9, (new Color(20, 20, 20, 87)).getRGB());
            this.fr.drawStringWithShadow(value4, 8.0F, 9.1F, -1);
         }
      }

   }

   public static int RGBtoHex(int n, int n2, int n3) {
      return n << 16 | n2 << 8 | n3;
   }

   public Watermark() {
      super("Watermark", Category.HUD);
      this.fr = Minecraft.getMinecraft().fontRenderer;
      Modes.add("Anim");
      Modes.add("Static");
      Modes.add("Skeet");
      Modes.add("OnetapOLD");
      BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "Skeet", (ArrayList)Modes));
   }

   public static int[] getRainbow(int n, float n2) {
      int n3 = 0;
      int n4 = 0;
      int n5 = 0;
      float n6 = 6.0F * (float)((System.currentTimeMillis() - (long)Math.round(n2 * 1000.0F)) % (long)(n * 1000)) / (float)(n * 1000);
      int round = Math.round(255.0F * (n6 - (float)Math.floor((double)n6)));
      if (n6 < 1.0F) {
         n3 = 255;
         n4 = round;
      } else if (n6 < 2.0F) {
         n3 = 255 - round;
         n4 = 255;
      } else if (n6 < 3.0F) {
         n4 = 255;
         n5 = round;
      } else if (n6 < 4.0F) {
         n4 = 255 - round;
         n5 = 255;
      } else if (n6 < 5.0F) {
         n3 = round;
         n5 = 255;
      } else if (n6 < 6.0F) {
         n3 = 255;
         n5 = 255 - round;
      }

      return new int[]{n3, n4, n5};
   }
}
