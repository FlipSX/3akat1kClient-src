package Bobr.buttons;

import Bobr.buttons.setting.CSSetting;
import Bobr.buttons.setting.settings.CSSettingCheck;
import Bobr.buttons.setting.settings.CSSettingCombo;
import Bobr.buttons.setting.settings.CSSettingDouble;
import Bobr.buttons.setting.settings.KeyBind;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class CSModButton extends CSButton {
   public static String old_name;
   public Module mod;
   public static boolean first = false;
   public static boolean binding;
   public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
   public ArrayList<CSSetting> settings = new ArrayList();

   public CSModButton(int x, int y, int width, int height, int color, String displayString, Module mod) {
      super(x, y, width, height, color, displayString);
      this.mod = mod;
      this.initSettings();
   }

   private void initSettings() {
      int y = 110;
      int x = this.x + 100;

      for(int i = 0; i < BloodWare.instance.settingsManager.getSettings().size(); ++i) {
         Setting s = (Setting)BloodWare.instance.settingsManager.getSettings().get(i);
         if (s.getParentMod() == this.mod) {
            if (s.isCheck()) {
               CSSettingCheck check = new CSSettingCheck(x, y, y, x, s);
               this.settings.add(check);
               y += 13;
            }

            if (s.isSlider()) {
               CSSettingDouble doubleset = new CSSettingDouble(x, y, 0, 0, s);
               this.settings.add(doubleset);
               y += 15;
            }

            if (s.isCombo()) {
               CSSettingCombo combo = new CSSettingCombo(x, y, 70, this.mc.fontRenderer.FONT_HEIGHT + 2, s);
               this.settings.add(combo);

               for(int i1 = 0; i1 < s.getOptions().size(); ++i1) {
                  y += this.fr.FONT_HEIGHT + 2;
                  if (y > 100 + this.sr.getScaledWidth() - 220) {
                     y = 0;
                     x += this.mc.fontRenderer.getStringWidth(s.getName()) + 50;
                  }
               }

               y += this.fr.FONT_HEIGHT + 5;
            }

            if (y > 100 + this.sr.getScaledWidth() - 220) {
               y = 0;
               x += this.mc.fontRenderer.getStringWidth(s.getName()) + 50;
            }
         }
      }

      KeyBind key = new KeyBind(x, y, 70, this.mc.fontRenderer.FONT_HEIGHT + 2, this.mod);
      this.settings.add(key);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      int color = this.isHovered(mouseX, mouseY) ? ClickGUI.getColor() : -1;
      if (this.mod.isToggled()) {
         color = ClickGUI.getColor();
      }

      if (this.isCurrentMod()) {
         color = ClickGUI.getColor();
      }

      this.fr.drawString(this.displayString, this.x, this.y, color);
      Iterator var5 = this.settings.iterator();

      while(var5.hasNext()) {
         CSSetting cs = (CSSetting)var5.next();
         if (this.isCurrentMod()) {
            cs.drawScreen(mouseX, mouseY, partialTicks);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void setBinding(boolean binding) {
      CSModButton.binding = binding;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.isHovered(mouseX, mouseY)) {
         label43: {
            BloodWare var10000;
            if (mouseButton == 0 && this.isHovered(mouseX, mouseY)) {
               var10000 = BloodWare.instance;
               if (BloodWare.bobrGui.currentCategory != null) {
                  var10000 = BloodWare.instance;
                  if (BloodWare.bobrGui.currentCategory.category == this.mod.getCategory()) {
                     this.mod.toggle();
                     break label43;
                  }
               }
            }

            if (mouseButton == 2 && this.isHovered(mouseX, mouseY)) {
               var10000 = BloodWare.instance;
               if (BloodWare.bobrGui.currentCategory != null) {
                  var10000 = BloodWare.instance;
                  if (BloodWare.bobrGui.currentCategory.category == this.mod.getCategory()) {
                     this.setBinding(true);
                     break label43;
                  }
               }
            }

            if (mouseButton == 1) {
            }
         }
      }

      Iterator var4 = this.settings.iterator();

      while(var4.hasNext()) {
         CSSetting cs = (CSSetting)var4.next();
         if (this.isCurrentMod()) {
            cs.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public boolean isHovered(int mouseX, int mouseY) {
      boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
      boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
      return hoveredx && hoveredy;
   }

   private boolean isCurrentMod() {
      BloodWare var10000 = BloodWare.instance;
      boolean var1;
      if (BloodWare.bobrGui.currentCategory != null) {
         var10000 = BloodWare.instance;
         if (BloodWare.bobrGui.currentCategory.currentMod != null) {
            var10000 = BloodWare.instance;
            if (BloodWare.bobrGui.currentCategory.currentMod == this) {
               var1 = true;
               return var1;
            }
         }
      }

      var1 = false;
      return var1;
   }

   public void initButton() {
      this.initSettings();
      super.initButton();
   }
}
