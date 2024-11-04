package Castom.comp;

import Castom.CastomGui;
import Castom.setting.Setting;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import java.awt.Color;

public class CheckBox extends Comp {
   public void mouseClicked(int n, int n2, int n3) {
      super.mouseClicked(n, n2, n3);
      if (this.isInside(n, n2, this.parent.posX + this.x - 70.0D, this.parent.posY + this.y, this.parent.posX + this.x + 10.0D - 70.0D, this.parent.posY + this.y + 8.5D) && n3 == 0) {
         this.setting.setValBoolean(!this.setting.getValBoolean());
      }

   }

   public CheckBox(double x, double y, CastomGui parent, Module module, Setting setting) {
      this.x = x;
      this.y = y;
      this.parent = parent;
      this.module = module;
      this.setting = setting;
   }

   public void drawScreen(int n, int n2) {
      super.drawScreen(n, n2);
      RenderUtil.drawRect(this.parent.posX + this.x - 70.0D, this.parent.posY + this.y, this.parent.posX + this.x + 10.0D - 70.0D, this.parent.posY + this.y + 8.5D, this.setting.getValBoolean() ? (new Color(10, 179, 230)).getRGB() : (new Color(61, 61, 61)).getRGB());
      CastomFontUtils.fr2.drawString(this.setting.getName(), (float)((int)(this.parent.posX + this.x - 55.0D)), (float)((int)(this.parent.posY + this.y + 0.5D)), (new Color(200, 200, 200)).getRGB());
   }
}
