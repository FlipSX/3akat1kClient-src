package com.Blood.Ware.settings;

import com.Blood.Ware.module.Module;
import java.util.ArrayList;
import java.util.Iterator;

public class SettingsManager {
   private ArrayList<Setting> settings = new ArrayList();

   public void rSetting(Setting in) {
      this.settings.add(in);
   }

   public void fSetting(Setting in) {
      this.settings.add(in);
   }

   public ArrayList<Setting> getSettings() {
      return this.settings;
   }

   public ArrayList<Setting> getSettingsByMod(Module mod) {
      ArrayList<Setting> out = new ArrayList();
      Iterator var3 = this.getSettings().iterator();

      while(var3.hasNext()) {
         Setting s = (Setting)var3.next();
         if (s.getParentMod().equals(mod)) {
            out.add(s);
         }
      }

      if (out.isEmpty()) {
         return null;
      } else {
         return out;
      }
   }

   public Setting getSettingByName(String name, String range) {
      Iterator var3 = this.getSettings().iterator();

      Setting setting;
      do {
         if (!var3.hasNext()) {
            System.out.println("Setting not found! (" + name + ").");
            return null;
         }

         setting = (Setting)var3.next();
      } while(!setting.getName().equalsIgnoreCase(name));

      return setting;
   }

   public Setting getSettingByName(Module mod, String name) {
      Iterator var3 = this.getSettings().iterator();

      Setting set;
      do {
         if (!var3.hasNext()) {
            System.err.println("[Tutorial] Error Setting NOT found: '" + name + "'!");
            return null;
         }

         set = (Setting)var3.next();
      } while(!set.getName().equalsIgnoreCase(name) || set.getParentMod() != mod);

      return set;
   }
}
