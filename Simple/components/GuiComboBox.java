package Simple.components;

import Caesium.Panel;
import Caesium.components.listeners.ComboListener;
import Caesium.util.RenderUtil;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class GuiComboBox implements Caesium.components.GuiComponent {
   private Setting setting;
   private boolean extended;
   private int height;
   private int posX;
   private int posY;
   private int width;
   private ArrayList<ComboListener> comboListeners = new ArrayList();

   public GuiComboBox(Setting setting) {
      this.setting = setting;
   }

   public void addComboListener(ComboListener comboListener) {
      this.comboListeners.add(comboListener);
   }

   public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
      this.posX = posX;
      this.posY = posY;
      this.width = width;
      String var7 = Panel.theme;
      byte var8 = -1;
      switch(var7.hashCode()) {
      case -2087928939:
         if (var7.equals("Caesium")) {
            var8 = 0;
         }
      default:
         switch(var8) {
         case 0:
            this.renderCaesium();
         default:
         }
      }
   }

   public void renderCaesium() {
      if (this.extended) {
         RenderUtil.drawRect(this.posX, this.posY, this.posX + this.width, this.posY + Panel.fR.FONT_HEIGHT + 2, Panel.grey40_240);
         RenderUtil.drawHorizontalLine(this.posX, this.posX + this.width, this.posY, Panel.black195);
         RenderUtil.drawHorizontalLine(this.posX, this.posX + this.width, this.posY + Panel.fR.FONT_HEIGHT + 2, (new Color(0, 0, 0, 150)).getRGB());
         Panel.fR.drawStringWithShadow(this.setting.getName() + "  -", (float)(this.posX + this.width / 2 - Panel.fR.getStringWidth(this.setting.getName() + "  -") / 2), (float)(this.posY + 2), Panel.fontColor);
         int innerHeight = Panel.fR.FONT_HEIGHT + 5;

         for(Iterator var2 = this.setting.getOptions().iterator(); var2.hasNext(); innerHeight += Panel.fR.FONT_HEIGHT + 2) {
            String combo = (String)var2.next();
            if (this.setting.getValString().equalsIgnoreCase(combo)) {
               Panel.fR.drawStringWithShadow(combo, (float)(this.posX + 10), (float)(this.posY + innerHeight), ClickGUI.getColor());
            } else {
               Panel.fR.drawStringWithShadow(combo, (float)(this.posX + 5), (float)(this.posY + innerHeight), Panel.fontColor);
            }
         }

         this.height = innerHeight + 2;
      } else {
         RenderUtil.drawRect(this.posX, this.posY, this.posX + this.width, this.posY + Panel.fR.FONT_HEIGHT + 2, Panel.grey40_240);
         RenderUtil.drawHorizontalLine(this.posX, this.posX + this.width, this.posY, Panel.black195);
         RenderUtil.drawHorizontalLine(this.posX, this.posX + this.width, this.posY + Panel.fR.FONT_HEIGHT + 2, Panel.black195);
         Panel.fR.drawStringWithShadow(this.setting.getName() + "  +", (float)(this.posX + this.width / 2 - Panel.fR.getStringWidth(this.setting.getName() + "  +") / 2), (float)(this.posY + 2), Panel.fontColor);
         this.height = Panel.fR.FONT_HEIGHT + 4;
      }

   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (RenderUtil.isHovered(this.posX, this.posY, this.width, Panel.fR.FONT_HEIGHT + 2, mouseX, mouseY)) {
         boolean var4 = this.extended = !this.extended;
      }

      if (this.extended && RenderUtil.isHovered(this.posX, this.posY + Panel.fR.FONT_HEIGHT + 2, this.width, (Panel.fR.FONT_HEIGHT + 2) * this.setting.getOptions().size(), mouseX, mouseY) && mouseButton == 0) {
         int h = Panel.fR.FONT_HEIGHT + 2;

         for(int i = 1; i <= this.setting.getOptions().size() + 1; ++i) {
            if (RenderUtil.isHovered(this.posX, this.posY + h * i, this.width, h * i, mouseX, mouseY)) {
               this.setting.setValString((String)this.setting.getOptions().get(i - 1));
            }
         }

         Iterator var8 = this.comboListeners.iterator();

         while(var8.hasNext()) {
            ComboListener comboListener = (ComboListener)var8.next();
            comboListener.comboChanged(this.setting.getValString());
         }
      }

   }

   public void keyTyped(int keyCode, char typedChar) {
   }

   public int getWidth() {
      return 0;
   }

   public int getHeight() {
      return this.height;
   }

   public boolean allowScroll() {
      return true;
   }

   public Setting getSetting() {
      return this.setting;
   }

   public void setSetting(Setting setting) {
      this.setting = setting;
   }
}
