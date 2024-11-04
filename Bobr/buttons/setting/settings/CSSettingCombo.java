package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.Gui;

public class CSSettingCombo extends CSSetting {
   public ArrayList<CSSettingComboValue> values = new ArrayList();

   public CSSettingCombo(int x, int y, int width, int height, Setting s) {
      super(x, y, width, height, s);
      this.initValues();
   }

   private void initValues() {
      int x = this.x;
      int y = this.y + this.height;

      for(Iterator var3 = this.set.getOptions().iterator(); var3.hasNext(); y += this.mc.fontRenderer.FONT_HEIGHT + 2) {
         String s = (String)var3.next();
         CSSettingComboValue value = new CSSettingComboValue(x, y, 70, this.mc.fontRenderer.FONT_HEIGHT + 2, this.set, s);
         this.values.add(value);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      RenderUtil.drawGlow((double)this.x, (double)this.y, (double)(this.x + this.width), (double)(this.y + this.height), ClickGUI.getColor(), 230.0D);
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Integer.MIN_VALUE);
      this.fr.drawString(this.displayString, this.x + this.width / 2 - this.fr.getStringWidth(this.displayString) / 2, this.y + 1, Integer.MAX_VALUE);
      Iterator var4 = this.values.iterator();

      while(var4.hasNext()) {
         CSSettingComboValue value = (CSSettingComboValue)var4.next();
         value.drawScreen(mouseX, mouseY);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      Iterator var4 = this.values.iterator();

      while(var4.hasNext()) {
         CSSettingComboValue value = (CSSettingComboValue)var4.next();
         value.mouseClicked(mouseX, mouseY, mouseButton);
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }
}
