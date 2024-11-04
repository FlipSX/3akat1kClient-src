package Castom.comp;

import Castom.CastomGui;
import Castom.setting.Setting;
import com.Blood.Ware.module.Module;

public class Comp {
   public double y;
   public CastomGui parent;
   public Setting setting;
   public String displayString;
   public double x;
   public double y2;
   public double x2;
   public Module module;

   public void keyTyped(char c, int n) {
   }

   public void drawScreen(int n, int n2) {
   }

   public boolean isInside(int n, int n2, double n3, double n4, double n5, double n6) {
      return (double)n > n3 && (double)n < n5 && (double)n2 > n4 && (double)n2 < n6;
   }

   public void mouseClicked(int n, int n2, int n3) {
   }

   public void mouseReleased(int n, int n2, int n3) {
   }
}
