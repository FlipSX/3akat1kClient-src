package Castom.setting;

import com.Blood.Ware.module.Module;
import java.util.ArrayList;

public class Setting {
   private String mode;
   private double min;
   private String sval;
   private double dval;
   private boolean bval;
   private double max;
   private ArrayList options;
   private Module parent;
   private String name;
   private boolean onlyint = false;

   public double getValDouble() {
      if (this.onlyint) {
         this.dval = (double)((int)this.dval);
      }

      return this.dval;
   }

   public double getMin() {
      return this.min;
   }

   public String getValString() {
      return this.sval;
   }

   public boolean isSlider() {
      return this.mode.equalsIgnoreCase("Slider");
   }

   public boolean isCombo() {
      return this.mode.equalsIgnoreCase("Combo");
   }

   public String getName() {
      return this.name;
   }

   public Setting(String name, Module parent, boolean bval) {
      this.name = name;
      this.parent = parent;
      this.bval = bval;
      this.mode = "Check";
   }

   public boolean isCheck() {
      return this.mode.equalsIgnoreCase("Check");
   }

   public ArrayList getOptions() {
      return this.options;
   }

   public Setting(String name, Module parent, double dval, double min, double max, boolean onlyint) {
      this.name = name;
      this.parent = parent;
      this.dval = dval;
      this.min = min;
      this.max = max;
      this.onlyint = onlyint;
      this.mode = "Slider";
   }

   public boolean getValBoolean() {
      return this.bval;
   }

   public Module getParentMod() {
      return this.parent;
   }

   public void setValString(String sval) {
      this.sval = sval;
   }

   public Setting() {
   }

   public void setValBoolean(boolean bval) {
      this.bval = bval;
   }

   public Setting(String name, Module parent, String sval, ArrayList options) {
      this.name = name;
      this.parent = parent;
      this.sval = sval;
      this.options = options;
      this.mode = "Combo";
   }

   public double getMax() {
      return this.max;
   }

   public void setValDouble(double dval) {
      this.dval = dval;
   }

   public boolean onlyInt() {
      return this.onlyint;
   }
}
