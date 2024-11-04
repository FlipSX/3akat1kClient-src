package Castom;

import Castom.comp.CheckBox;
import Castom.comp.Combo;
import Castom.comp.Comp;
import Castom.comp.Slider;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.MathUtils;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.RenderItem;
import org.lwjgl.input.Mouse;

public class CastomGui extends GuiScreen {
   private float curAlpha;
   public Category selectedCategory;
   public static boolean binding;
   public double width;
   public ArrayList comps = new ArrayList();
   private static RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
   float anim = 0.0F;
   public int modeIndex;
   public double dragX;
   public double x;
   public double posX = (double)(this.getScaledRes().getScaledWidth() / 2 - 150);
   public double posY = (double)(this.getScaledRes().getScaledHeight() / 2 - 100);
   public double height;
   public double dragY;
   public boolean dragging = false;
   public double y;
   private boolean editing;
   private Module selectedModule;
   public Module mod;

   public void setBinding(boolean binding) {
      CastomGui.binding = binding;
   }

   protected void mouseClicked(int n, int n2, int n3) throws IOException {
      super.mouseClicked(n, n2, n3);
      if (this.isInside(n, n2, this.posX, this.posY - 10.0D, this.width, this.posY) && n3 == 0) {
         this.dragging = true;
         this.dragX = (double)n - this.posX;
         this.dragY = (double)n2 - this.posY;
      }

      int n4 = 0;
      Category[] var5 = Category.values();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Category selectedCategory = var5[var7];
         if (this.isInside(n, n2, this.posX, this.posY + 1.0D + (double)n4, this.posX + 60.0D, this.posY + 15.0D + (double)n4) && n3 == 0) {
            this.selectedCategory = selectedCategory;
         }

         n4 += 15;
      }

      int n5 = 0;
      BloodWare var10000 = BloodWare.instance;

      Iterator iterator3;
      for(iterator3 = BloodWare.moduleManager.getModules(this.selectedCategory).iterator(); iterator3.hasNext(); n5 += 15) {
         Module selectedModule = (Module)iterator3.next();
         if (this.isInside(n, n2, this.posX + 65.0D, this.posY + 1.0D + (double)n5, this.posX + 125.0D, this.posY + 15.0D + (double)n5)) {
            if (n3 == 0) {
               selectedModule.toggle();
            }

            if (n3 == 1) {
               int n6 = 3;
               this.comps.clear();
               if (BloodWare.instance.settingsManager.getSettingsByMod(selectedModule) != null) {
                  Iterator var9 = BloodWare.instance.settingsManager.getSettingsByMod(selectedModule).iterator();

                  while(var9.hasNext()) {
                     Setting setting = (Setting)var9.next();
                     this.selectedModule = selectedModule;
                     this.setBinding(true);
                     if (setting.isCombo()) {
                        this.comps.add(new Combo(275.0D, (double)n6, this, this.selectedModule, setting));
                        n6 += 15;
                     }

                     if (setting.isCheck()) {
                        this.comps.add(new CheckBox(275.0D, (double)n6, this, this.selectedModule, setting));
                        n6 += 13;
                     }

                     if (setting.isSlider()) {
                        this.comps.add(new Slider(275.0D, (double)n6, this, this.selectedModule, setting));
                        n6 += 18;
                     }
                  }
               }
            }
         }
      }

      iterator3 = this.comps.iterator();

      while(iterator3.hasNext()) {
         ((Comp)iterator3.next()).mouseClicked(n, n2, n3);
      }

   }

   public boolean isInside(int n, int n2, double n3, double n4, double n5, double n6) {
      return (double)n > n3 && (double)n < n5 && (double)n2 > n4 && (double)n2 < n6;
   }

   public void initGui() {
      super.initGui();
      this.anim = 0.0F;
      this.dragging = false;
   }

   public void setEditing(boolean editing) {
      this.editing = editing;
   }

   public void drawScreen(int n, int n2, float n3) {
      super.drawScreen(n, n2, n3);
      if (this.dragging) {
         this.posX = (double)n - this.dragX;
         this.posY = (double)n2 - this.dragY;
      }

      this.width = this.posX + 456.0D;
      this.height = this.posY + 380.0D;
      float n4 = 255.0F;
      int n5 = true;
      if (this.curAlpha < 254.0F) {
         ++this.curAlpha;
      } else if (this.curAlpha > 254.0F && this.curAlpha != 255.0F) {
         this.curAlpha = 255.0F;
      } else if (this.curAlpha != 255.0F) {
         this.curAlpha = 255.0F;
      }

      this.anim = MathUtils.lerp(this.anim, 1.0F, 0.13F);
      RenderUtil.drawRect(this.posX - 1.0D, this.posY - 6.0D, this.width + 1.0D, this.height + 1.0D, ClickGUI.getColor());
      RenderUtil.drawRect(this.posX, this.posY, this.width, this.height, (new Color(45, 45, 45, 249)).getRGB());
      int n6 = 0;
      Category[] var7 = Category.values();
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         Category category = var7[var9];
         RenderUtil.drawRect(this.posX, this.posY + 1.0D + (double)n6, this.posX + 60.0D, this.posY + 15.0D + (double)n6, category.equals(this.selectedCategory) ? (new Color(46, 85, 243, 156)).getRGB() : (new Color(28, 28, 28, 250)).getRGB());
         CastomFontUtils.fr3.drawString(category.name(), (float)((int)this.posX + 2), (float)((int)(this.posY + 5.0D) + n6), (new Color(170, 170, 170)).getRGB());
         n6 += 15;
      }

      int n7 = 0;
      BloodWare var10000 = BloodWare.instance;

      Iterator iterator2;
      for(iterator2 = BloodWare.moduleManager.getModules(this.selectedCategory).iterator(); iterator2.hasNext(); n7 += 15) {
         Module module = (Module)iterator2.next();
         RenderUtil.drawRect(this.posX + 65.0D, this.posY + 1.0D + (double)n7, this.posX + 150.0D, this.posY + 15.0D + (double)n7, module.isToggled() ? (new Color(46, 85, 234, 155)).getRGB() : (new Color(28, 28, 28, 249)).getRGB());
         CastomFontUtils.fr3.drawString(module.getName(), (float)((int)this.posX + 67), (float)((int)(this.posY + 5.0D) + n7), (new Color(170, 170, 170)).getRGB());
      }

      Gui.drawRect((int)(this.posX + 455.0D), (int)(this.posY + 360.0D), (int)(this.posX + 323.0D), (int)(this.posY + 320.5D), (new Color(31, 31, 31, 250)).getRGB());
      CastomFontUtils.fr.drawStringWithShadow("BloodWare", (float)(this.posX + 353.0D), (float)(this.posY + 329.0D), ClickGUI.getColor());
      Gui.drawRect((int)(this.posX + 300.0D), (int)(this.posY + 370.0D), (int)(this.posX + 200.0D), (int)(this.posY + 2.0D), (new Color(31, 31, 31, 250)).getRGB());
      CastomFontUtils.fr.drawStringWithShadow("Name:" + this.mc.getSession().getUsername(), (float)(this.posX + 353.0D), (float)(this.posY + 339.5D), Color.white.getRGB());

      try {
         this.mc.getTextureManager().bindTexture(((NetHandlerPlayClient)Objects.requireNonNull(this.mc.getConnection())).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin());
         Gui.drawScaledCustomSizeModalRect((int)(this.posX + 328.0D), (int)(this.posY + 330.0D), 8.0F, 8.0F, 8, 8, 22, 22, 64.0F, 64.0F);
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      iterator2 = this.comps.iterator();

      while(iterator2.hasNext()) {
         ((Comp)iterator2.next()).drawScreen(n, n2);
      }

      this.drawinfo(n, n2);
      this.player();
   }

   public ScaledResolution getScaledRes() {
      return new ScaledResolution(Minecraft.getMinecraft());
   }

   public boolean isEditing() {
      return this.editing;
   }

   public void player() {
      try {
         this.mc.getTextureManager().bindTexture(((NetHandlerPlayClient)Objects.requireNonNull(this.mc.getConnection())).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin());
         RenderUtils.renderEntity(this.mc.player, 70, (int)(this.posX + 375.0D), (int)(this.posY + 230.0D));
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void drawinfo(int mouseX, int mouseY) {
      int xpos = (int)(this.posX + 511.0D);
      int ypos = (int)(this.posY + 18.0D);
      int g = 5;
      RenderUtil.drawRect(this.posX + 636.0D, this.posY - 6.0D, this.width + 29.0D, this.height + 1.0D, ClickGUI.getColor());
      RenderUtil.drawRect(this.posX + 635.0D, this.posY, this.width + 30.0D, this.height, (new Color(31, 31, 31, 250)).getRGB());
      CastomFontUtils.fr2.drawStringWithShadow("Freands", (float)(this.posX + 540.0D), (float)(this.posY + 10.0D), Color.white.getRGB());

      for(Iterator var6 = FriendManager.FRIENDS.iterator(); var6.hasNext(); g += this.fontRenderer.FONT_HEIGHT) {
         String friends = (String)var6.next();
         int butX1 = 0 + xpos;
         int butX2 = 100 + xpos;
         int butY1 = g + ypos;
         int butY2 = g + this.fontRenderer.FONT_HEIGHT + ypos;
         if (mouseX > butX1 && mouseX < butX2 && mouseY > butY1 && mouseY < butY2) {
            CastomFontUtils.fr2.drawString(friends, (float)(xpos + 50 - this.fontRenderer.getStringWidth(friends) / 2), (float)(g + ypos), ClickGUI.getColor());
            if (Mouse.isButtonDown(1)) {
               FriendManager.toggleFriend(friends);
               return;
            }
         } else {
            CastomFontUtils.fr2.drawString(friends, (float)(xpos + 50 - this.fontRenderer.getStringWidth(friends) / 2), (float)(g + ypos), Color.white.getRGB());
         }
      }

   }

   protected void mouseReleased(int n, int n2, int n3) {
      super.mouseReleased(n, n2, n3);
      this.dragging = false;
      Iterator iterator = this.comps.iterator();

      while(iterator.hasNext()) {
         ((Comp)iterator.next()).mouseReleased(n, n2, n3);
      }

   }

   protected void keyTyped(char c, int n) throws IOException {
      super.keyTyped(c, n);
      Iterator iterator = this.comps.iterator();

      while(iterator.hasNext()) {
         ((Comp)iterator.next()).keyTyped(c, n);
      }

   }

   public CastomGui() {
      this.width = this.posX + 456.0D;
      this.height += 380.0D;
      this.selectedCategory = Category.COMBAT;
      this.x = 100.0D;
      this.y = 200.0D;
   }
}
