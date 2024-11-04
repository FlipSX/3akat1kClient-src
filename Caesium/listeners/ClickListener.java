package Caesium.listeners;

import Caesium.components.GuiButton;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener {
   private GuiButton button;

   public ClickListener(GuiButton button) {
      this.button = button;
   }

   public void actionPerformed(ActionEvent event) {
      BloodWare var10000 = BloodWare.instance;
      Module m = BloodWare.moduleManager.getModule(this.button.getText());
      m.toggle();
   }
}
