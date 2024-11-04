package Simple.listeners;

import Caesium.components.GuiButton;
import Caesium.components.GuiComboBox;
import Caesium.components.GuiGetKey;
import Caesium.components.GuiLabel;
import Caesium.components.GuiSlider;
import Caesium.components.GuiToggleButton;
import Caesium.components.listeners.ComboListener;
import Caesium.components.listeners.ComponentListener;
import Caesium.components.listeners.KeyListener;
import Caesium.components.listeners.ValueListener;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class ComponentsListener extends ComponentListener {
   private GuiButton button;

   public ComponentsListener(GuiButton button) {
      this.button = button;
   }

   public void addComponents() {
      this.add(new GuiLabel("Settings >"));
      BloodWare var10000 = BloodWare.instance;
      final Module m = BloodWare.moduleManager.getModule(this.button.getText());
      if (BloodWare.instance.settingsManager.getSettingsByMod(m) != null) {
         Iterator var2 = BloodWare.instance.settingsManager.getSettingsByMod(m).iterator();

         while(var2.hasNext()) {
            final Setting set = (Setting)var2.next();
            if (set.isSlider()) {
               GuiSlider slider = new GuiSlider(set.getName(), (float)set.getMin(), (float)set.getMax(), (float)set.getValDouble(), set.onlyInt() ? 0 : 2);
               slider.addValueListener(new ValueListener() {
                  public void valueUpdated(float value) {
                     set.setValDouble((double)value);
                  }

                  public void valueChanged(float value) {
                     set.setValDouble((double)value);
                     (new Thread(() -> {
                     })).start();
                  }
               });
               this.add(slider);
            }

            if (set.isCheck()) {
               final GuiToggleButton toggleButton = new GuiToggleButton(set.getName());
               toggleButton.setToggled(set.getValBoolean());
               toggleButton.addClickListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     set.setValBoolean(toggleButton.isToggled());
                  }
               });
               this.add(toggleButton);
            }

            if (set.isCombo()) {
               GuiComboBox comboBox = new GuiComboBox(set);
               comboBox.addComboListener(new ComboListener() {
                  public void comboChanged(String combo) {
                  }
               });
               this.add(comboBox);
            }
         }
      }

      GuiGetKey ggk = new GuiGetKey("KeyBind", m.getKey());
      ggk.addKeyListener(new KeyListener() {
         public void keyChanged(int key) {
            m.setKey(key);
            (new Thread(() -> {
            })).start();
         }
      });
      this.add(ggk);
      final GuiToggleButton toggleButton = new GuiToggleButton("Visable");
      toggleButton.setToggled(m.visible);
      toggleButton.addClickListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            m.visible = toggleButton.isToggled();
         }
      });
      this.add(toggleButton);
   }
}
