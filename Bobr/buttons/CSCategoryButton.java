package Bobr.buttons;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.ModuleManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CSCategoryButton extends CSButton {
   public static boolean binding;
   public Category category;
   public CSModButton currentMod;
   public ArrayList<CSModButton> buttons = new ArrayList();

   public CSCategoryButton(int x, int y, int width, int height, int color, String displayString, Category category) {
      super(x, y, width, height, color, displayString);
      this.category = category;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      BloodWare var10000;
      int var7;
      label22: {
         if (!this.isHovered(mouseX, mouseY)) {
            var10000 = BloodWare.instance;
            if (BloodWare.bobrGui.currentCategory != this) {
               var7 = this.color;
               break label22;
            }
         }

         var7 = ClickGUI.getColor();
      }

      int color = var7;
      this.fr.drawString(this.displayString, this.x, this.y, color);
      Iterator var5 = this.buttons.iterator();

      while(var5.hasNext()) {
         CSModButton csm = (CSModButton)var5.next();
         var10000 = BloodWare.instance;
         if (BloodWare.bobrGui.currentCategory == this) {
            csm.drawScreen(mouseX, mouseY, partialTicks);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void onKeyPress(int typedChar, int keyCode) {
   }

   public boolean isHovered(int mouseX, int mouseY) {
      boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
      boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
      return hoveredx && hoveredy;
   }

   public void setBinding(boolean binding) {
      CSCategoryButton.binding = binding;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      Iterator var4 = this.buttons.iterator();

      while(var4.hasNext()) {
         CSModButton cmb = (CSModButton)var4.next();
         cmb.mouseClicked(mouseX, mouseY, mouseButton);
         if (mouseButton == 1 && cmb.isHovered(mouseX, mouseY)) {
            this.currentMod = cmb;
         }

         if (mouseButton == 2 && cmb.isHovered(mouseX, mouseY)) {
            this.setBinding(true);
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void initButton() {
      this.buttons.clear();
      int x = this.x + 65;
      int y = 110;
      int i = 0;

      while(true) {
         BloodWare var10001 = BloodWare.instance;
         ModuleManager var7 = BloodWare.moduleManager;
         if (i >= ModuleManager.modules.size()) {
            super.initButton();
            return;
         }

         BloodWare var10000 = BloodWare.instance;
         ModuleManager var6 = BloodWare.moduleManager;
         Module m = (Module)ModuleManager.modules.get(i);
         if (m.getCategory() == this.category) {
            CSModButton csm = new CSModButton(x, y, this.fr.getStringWidth(m.getName()), this.fr.FONT_HEIGHT, -1, m.getName(), m);
            this.buttons.add(csm);
            y += 10;
         }

         ++i;
      }
   }

   private boolean isCurrentPanel() {
      BloodWare var10000 = BloodWare.instance;
      return BloodWare.bobrGui.currentCategory == this;
   }
}
