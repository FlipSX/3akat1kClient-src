package Castom.comp;

import Castom.CastomGui;
import Castom.setting.Setting;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Comp {
   private double renderWidth;
   private double renderWidth2;
   private boolean dragging = false;

   private double roundToPlace(double val, int newScale) {
      if (newScale < 0) {
         throw new IllegalArgumentException();
      } else {
         return (new BigDecimal(val)).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
      }
   }

   public void mouseClicked(int n, int n2, int n3) {
      super.mouseClicked(n, n2, n3);
      if (this.isInside(n, n2, this.parent.posX + this.x - 70.0D, this.parent.posY + this.y + 10.0D, this.parent.posX + this.x - 70.0D + this.renderWidth2, this.parent.posY + this.y + 16.0D) && n3 == 0) {
         this.dragging = true;
      }

   }

   public void drawScreen(int n, int n2) {
      super.drawScreen(n, n2);
      double min = this.setting.getMin();
      double max = this.setting.getMax();
      double a = 90.0D;
      this.renderWidth = 90.0D * (this.setting.getValDouble() - min) / (max - min);
      this.renderWidth2 = 90.0D * (this.setting.getMax() - min) / (max - min);
      double min2 = Math.min(90.0D, Math.max(0.0D, (double)n - (this.parent.posX + this.x - 70.0D)));
      if (this.dragging) {
         if (min2 == 0.0D) {
            this.setting.setValDouble(this.setting.getMin());
         } else {
            this.setting.setValDouble(this.roundToPlace(min2 / 90.0D * (max - min) + min, 1));
         }
      }

      RenderUtil.drawRect(this.parent.posX + this.x - 70.0D, this.parent.posY + this.y + 10.0D, this.parent.posX + this.x - 70.0D + this.renderWidth2, this.parent.posY + this.y + 16.0D, (new Color(10, 131, 230)).darker().getRGB());
      RenderUtil.drawRect(this.parent.posX + this.x - 70.0D, this.parent.posY + this.y + 10.0D, this.parent.posX + this.x - 70.0D + this.renderWidth, this.parent.posY + this.y + 16.0D, (new Color(10, 164, 230)).getRGB());
      CastomFontUtils.fr2.drawString(String.valueOf((new StringBuilder()).append(this.setting.getName()).append(": ").append(this.setting.getValDouble())), (float)((int)(this.parent.posX + this.x - 65.0D)), (float)((int)(this.parent.posY + this.y + 4.0D)), -1);
   }

   public void mouseReleased(int n, int n2, int n3) {
      super.mouseReleased(n, n2, n3);
      this.dragging = false;
   }

   public Slider(double x, double y, CastomGui parent, Module module, Setting setting) {
      this.x = x;
      this.y = y;
      this.parent = parent;
      this.module = module;
      this.setting = setting;
   }
}
