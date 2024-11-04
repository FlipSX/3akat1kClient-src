package com.Blood.Ware.module.hud;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColorUtils;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.TimerUtils;
import com.google.common.base.Strings;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import scala.reflect.runtime.Settings.BooleanSetting;

public class HUD extends Module {
   public int oYpos;
   float animX = 0.0F;
   public int oHeight;
   float animY = 1.0F;
   public static ArrayList<String> Modes = new ArrayList();
   private static RenderItem kappita;
   public int oWidth;
   TimerUtils timer = new TimerUtils();
   public int width;
   private Entity target;
   private static final RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
   private int y;
   public int oXpos;
   public int height;
   private final FontRenderer fr;
   private ArrayList<Module> modulesSorted;
   BooleanSetting info;
   String text = "connecting";
   private float anima;
   float animYMot = 1.4F;
   private int x;
   float animXMot = 1.4F;

   public HUD() {
      super("HUD", Category.HUD);
      this.fr = Minecraft.getMinecraft().fontRenderer;
      Modes.add("Blood");
      Modes.add("NeverDies");
      Modes.add("Skeet");
      BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "Skeet", Modes));
      BloodWare.instance.settingsManager.rSetting(new Setting("InfoWorld", this, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("Horizontal", this, 30.0D, 0.0D, 810.0D, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Vertical", this, 60.0D, 0.0D, 490.0D, true));
   }

   public static void drawRectangleCorrectly(int n, int n2, int n3, int n4, int n5) {
      GL11.glLineWidth(1.0F);
      Gui.drawRect(n, n2, n + n3, n2 + n4, n5);
   }

   public static int RGBtoHex(int n, int n2, int n3) {
      return n << 16 | n2 << 8 | n3;
   }

   private int rainbow(long n, float n2) {
      Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((float)(System.nanoTime() + n) / 2.0E10F % 1.0F + 100000.0F, 1.0F, 1.0F)), 16));
      return (new Color((float)color.getRed() / 255.0F * n2, (float)color.getGreen() / 255.0F * n2, (float)color.getBlue() / 255.0F * n2, (float)color.getAlpha() / 255.0F)).getRGB();
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

   public void sortList() {
      BloodWare var10003 = BloodWare.instance;
      (this.modulesSorted = new ArrayList(BloodWare.moduleManager.getModuleList())).sort(HUD::lambda$sortList$0);
   }

   @SubscribeEvent
   public void InfoCOMP(Text text) {
      boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName((Module)this, "InfoWorld").getValBoolean();
      float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Horizontal").getValDouble();
      float n2 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Vertical").getValDouble();
      if (text.getType() == ElementType.TEXT && valBoolean) {
         String value = String.valueOf((new StringBuilder()).append("").append(Minecraft.getDebugFPS()));
         String value2 = String.valueOf((new StringBuilder()).append("").append(Math.round(mc.player.posX)));
         String value3 = String.valueOf((new StringBuilder()).append("").append(Math.round(mc.player.posY)));
         String value4 = String.valueOf((new StringBuilder()).append("").append(Math.round(mc.player.posZ)));
         mc.fontRenderer.drawStringWithShadow("X: ", n, n2, BloodWare.color.getRGB());
         mc.fontRenderer.drawStringWithShadow(value2, n + 10.0F, n2, -1);
         mc.fontRenderer.drawStringWithShadow("Y: ", n + 30.0F + (float)mc.fontRenderer.getStringWidth(value2) - 17.0F, n2, BloodWare.color.getRGB());
         mc.fontRenderer.drawStringWithShadow(value3, n + 40.0F + (float)mc.fontRenderer.getStringWidth(value2) - 17.0F, n2, -1);
         mc.fontRenderer.drawStringWithShadow("Z: ", n + 66.0F + (float)mc.fontRenderer.getStringWidth(value2) - 23.0F + (float)mc.fontRenderer.getStringWidth(value3) - 17.0F, n2, BloodWare.color.getRGB());
         mc.fontRenderer.drawStringWithShadow(value4, n + 76.0F + (float)mc.fontRenderer.getStringWidth(value2) - 23.0F + (float)mc.fontRenderer.getStringWidth(value3) - 17.0F, n2, -1);
         mc.fontRenderer.drawStringWithShadow("FPS: ", n, n2 + 11.0F, BloodWare.color.getRGB());
         mc.fontRenderer.drawStringWithShadow(value, n + 22.0F, n2 + 11.0F, -1);
      }

   }

   public static int raindbow(int n) {
      return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + (long)n) / 20L)) % 360.0D / 360.0D), 0.5F, 1.0F).getRGB();
   }

   @SubscribeEvent
   public void onOverlayRender(Text text) {
      String valString = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Mode").getValString();
      int n15;
      int index;
      String s3;
      int n10;
      int n17;
      int n18;
      int rainbowChams;
      int var10002;
      int l;
      if (valString.equals("Blood")) {
         if (text.getType() != ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
         }

         int[] rainbow = getRainbow(5, 0.1F);
         RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
         this.sortList();
         ArrayList<String> list = new ArrayList();
         Iterator var24 = this.modulesSorted.iterator();

         while(var24.hasNext()) {
            Module module = (Module)var24.next();
            if (!module.getName().equalsIgnoreCase("HUD") && module.isToggled() && module.visible) {
               list.add(module.getName());
            }
         }

         int FONT_HEIGHT = mc.fontRenderer.FONT_HEIGHT;
         new ScaledResolution(Minecraft.getMinecraft());
         long n = 0L;
         n15 = 0;
         ScaledResolution scaledResolution2 = new ScaledResolution(mc);
         int[] array = new int[]{1};
         l = 0;

         for(index = 0; index < list.size(); ++index) {
            l += mc.fontRenderer.FONT_HEIGHT + 5;
         }

         for(index = 0; index < list.size(); ++index) {
            s3 = (String)list.get(index);
            if (!Strings.isNullOrEmpty(s3)) {
               n10 = mc.fontRenderer.FONT_HEIGHT;
               n17 = scaledResolution2.getScaledWidth() - 2 - mc.fontRenderer.getStringWidth(s3);
               n18 = 1 + (n10 + 2) * index;
               rainbowChams = ColorUtils.astolfoColors(array[0] * 15, l);
               BloodWare.color = new Color(rainbowChams);
               if (index == 0) {
               }

               Gui.drawRect(n17 + mc.fontRenderer.getStringWidth(s3), n18 - 1, n17 + mc.fontRenderer.getStringWidth(s3) + 1, n18 + 11, rainbowChams);
               RenderUtil.drawRect((double)(n17 - 2), (double)n18, (double)(n17 + mc.fontRenderer.getStringWidth(s3)), (double)(n18 + 11), 1140850688);
               mc.fontRenderer.getStringWidth(s3);
               if (index == this.modulesSorted.size() - 1) {
                  RenderUtil.drawGlow((double)(n17 - 2), (double)(n18 + 10), (double)(n17 + mc.fontRenderer.getStringWidth(s3)), (double)(n18 + 11), rainbowChams, 1000.0D);
               }

               mc.fontRenderer.drawString(s3, n17, n18 + 1, rainbowChams);
               ++n;
               ++n15;
               int n6 = false;
               var10002 = array[0]++;
            }
         }

         this.modulesSorted.clear();
      } else {
         ArrayList list3;
         Iterator var4;
         Module module3;
         long n13;
         int n14;
         int n16;
         int FONT_HEIGHT5;
         if (valString.equals("NeverDies")) {
            if (text.getType() != ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
               return;
            }

            this.sortList();
            list3 = new ArrayList();
            var4 = this.modulesSorted.iterator();

            while(var4.hasNext()) {
               module3 = (Module)var4.next();
               if (!module3.getName().equalsIgnoreCase("HUD") && module3.isToggled() && module3.visible) {
                  list3.add(module3.getName());
               }
            }

            FONT_HEIGHT5 = mc.fontRenderer.FONT_HEIGHT;
            new ScaledResolution(Minecraft.getMinecraft());
            n13 = 0L;
            n14 = 0;
            ScaledResolution scaledResolution4 = new ScaledResolution(mc);
            int[] array3 = new int[]{1};
            n16 = 0;

            for(l = 0; l < list3.size(); ++l) {
               n16 += mc.fontRenderer.FONT_HEIGHT + 5;
            }

            for(l = 0; l < list3.size(); ++l) {
               String s2 = (String)list3.get(l);
               if (!Strings.isNullOrEmpty(s2)) {
                  int FONT_HEIGHT4 = mc.fontRenderer.FONT_HEIGHT;
                  n10 = scaledResolution4.getScaledWidth() - 1 - mc.fontRenderer.getStringWidth(s2);
                  n17 = 1 + (FONT_HEIGHT4 + 2) * l;
                  n18 = ColorUtils.astolfoColors(array3[0] * 15, n16);
                  BloodWare.color = new Color(n18);
                  if (l == 0) {
                  }

                  Gui.drawRect(n10 + mc.fontRenderer.getStringWidth(s2), n17 - 1, n10 + mc.fontRenderer.getStringWidth(s2) + 1, n17 + 11, n18);
                  RenderUtil.drawRect((double)(n10 - 2), (double)n17, (double)(n10 + mc.fontRenderer.getStringWidth(s2)), (double)(n17 + 11), 1140850688);
                  mc.fontRenderer.getStringWidth(s2);
                  if (l == this.modulesSorted.size() - 1) {
                     RenderUtil.drawGlow((double)(n10 - 2), (double)(n17 + 10), (double)(n10 + mc.fontRenderer.getStringWidth(s2)), (double)(n17 + 11), n18, 1000.0D);
                  }

                  mc.fontRenderer.drawString(s2, n10, n17 + 1, n18);
                  ++n13;
                  ++n14;
                  int n12 = false;
                  var10002 = array3[0]++;
               }
            }

            this.modulesSorted.clear();
         } else if (valString.equals("Skeet")) {
            if (text.getType() != ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
               return;
            }

            this.sortList();
            list3 = new ArrayList();
            var4 = this.modulesSorted.iterator();

            while(var4.hasNext()) {
               module3 = (Module)var4.next();
               if (!module3.getName().equalsIgnoreCase("HUD") && module3.isToggled() && module3.visible) {
                  list3.add(module3.getName());
               }
            }

            FONT_HEIGHT5 = mc.fontRenderer.FONT_HEIGHT;
            new ScaledResolution(Minecraft.getMinecraft());
            n13 = 0L;
            n14 = 0;
            n15 = 0;
            int getStringWidth = 0;
            n16 = 0;
            ScaledResolution scaledResolution6 = new ScaledResolution(mc);

            for(index = 0; index < list3.size(); ++index) {
               s3 = (String)list3.get(index);
               if (!Strings.isNullOrEmpty(s3)) {
                  n10 = mc.fontRenderer.FONT_HEIGHT;
                  n17 = scaledResolution6.getScaledWidth() - 1 - mc.fontRenderer.getStringWidth(s3);
                  n18 = 1 + (n10 + 2) * index;
                  rainbowChams = getRainbowChams(6000, -15);
                  if (index == 0) {
                     Gui.drawRect(n17 - 2, n18 - 1, n17 + mc.fontRenderer.getStringWidth(s3), n18, rainbowChams);
                  }

                  Gui.drawRect(n17 - 2, n18, n17 + mc.fontRenderer.getStringWidth(s3), n18 + 11, 1140850688);
                  Gui.drawRect(n17 - 3, n18 - 1, n17 - 2, n18 + 11, rainbowChams);
                  Gui.drawRect(n15, n16, n15 + (getStringWidth - mc.fontRenderer.getStringWidth(s3)), n16 + 1, rainbowChams);
                  getStringWidth = mc.fontRenderer.getStringWidth(s3);
                  n15 = n17 - 2;
                  n16 = n18 + 10;
                  Gui.drawRect(n17 + mc.fontRenderer.getStringWidth(s3), n18 - 1, n17 + mc.fontRenderer.getStringWidth(s3) + 1, n18 + 11, rainbowChams);
                  if (index == this.modulesSorted.size() - 1) {
                     Gui.drawRect(n17 - 2, n18 + 10, n17 + mc.fontRenderer.getStringWidth(s3), n18 + 11, rainbowChams);
                  }

                  mc.fontRenderer.drawString(s3, n17, n18 + 1, rainbowChams);
                  ++n13;
                  ++n14;
               }
            }

            this.modulesSorted.clear();
         }
      }

   }

   public static int getRainbowChams(int n, int n2) {
      return Color.getHSBColor((float)((System.currentTimeMillis() * 1L + (long)(n2 / 2)) % (long)n * 2L) / (float)n, 1.0F, 1.0F).getRGB();
   }

   private static int lambda$sortList$0(Module module, Module module2) {
      if (mc.fontRenderer.getStringWidth(module.getName()) < mc.fontRenderer.getStringWidth(module2.getName())) {
         return 1;
      } else {
         return mc.fontRenderer.getStringWidth(module.getName()) > mc.fontRenderer.getStringWidth(module2.getName()) ? -1 : 0;
      }
   }

   public void drawitem(ItemStack itemStack, int n, int n2) {
      GlStateManager.enableDepth();
      itemRender.zLevel = 200.0F;
      itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
      itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, itemStack, n, n2, "");
      GlStateManager.enableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.disableDepth();
      if (itemStack.getCount() != 0 && itemStack.getCount() != 1) {
         this.fr.drawString(String.valueOf((new StringBuilder()).append("").append(itemStack.getCount())), n + 13, n2 + 10, Color.white.getRGB());
      } else {
         this.fr.drawString("", n + 13, n2 + 10, Color.white.getRGB());
      }

   }
}
