package com.Blood.Ware.module.render;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;

public class SkyColour extends Module {
   public SkyColour() {
      super("SkyColour", Category.OUTHER);
      this.addSlider("Red", this, 21, 0, 255, true);
      this.addSlider("Green", this, 191, 0, 255, true);
      this.addSlider("Blue", this, 219, 0, 255, true);
   }

   private void addSlider(String red, SkyColour skyColour, int i, int i1, int i2, boolean b) {
   }
}
