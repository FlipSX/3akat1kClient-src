package Castom.comp;

import Castom.CastomGui;
import Castom.setting.Setting;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import java.awt.Color;

public class Combo extends Comp {
   public Combo(double x, double y, CastomGui parent, Module module, Setting setting) {
      this.x = x;
      this.y = y;
      this.parent = parent;
      this.module = module;
      this.setting = setting;
   }

   public void drawScreen(int n, int n2) {
      super.drawScreen(n, n2);
      RenderUtil.drawRect(this.parent.posX + this.x - 70.0D, this.parent.posY + this.y, this.parent.posX + this.x + 20.0D, this.parent.posY + this.y + 10.0D, this.setting.getValBoolean() ? (new Color(230, 10, 230)).getRGB() : (new Color(59, 59, 59)).getRGB());
      CastomFontUtils.fr2.drawString(String.valueOf((new StringBuilder()).append(this.setting.getName()).append(": ").append(this.setting.getValString())), (float)((int)(this.parent.posX + this.x - 64.0D)), (float)((int)(this.parent.posY + this.y + 3.0D)), (new Color(200, 200, 200)).getRGB());
   }

   public void mouseClicked(int n, int n2, int n3) {
      super.mouseClicked(n, n2, n3);
      if (this.isInside(n, n2, this.parent.posX + this.x - 70.0D, this.parent.posY + this.y, this.parent.posX + this.x + 20.0D, this.parent.posY + this.y + 10.0D) && n3 == 0) {
         if (this.parent.modeIndex + 1 >= this.setting.getOptions().size()) {
            this.parent.modeIndex = 0;
         } else {
            CastomGui parent = this.parent;
            ++parent.modeIndex;
         }

         this.setting.setValString((String)this.setting.getOptions().get(this.parent.modeIndex));
      }

   }
}
