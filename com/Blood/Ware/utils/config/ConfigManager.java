package com.Blood.Ware.utils.config;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ChatUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;

public class ConfigManager {
   public final String path;

   public ConfigManager() {
      this.path = (new File(Minecraft.getMinecraft().mcDataDir, "3akat1kClient")).getPath();
      File paste_dir = new File(this.path);
      if (!paste_dir.exists()) {
         paste_dir.mkdir();
      }

   }

   public void saveCFG(String name) {
      try {
         String json = this.getJson();
         File cfg = new File(this.path + File.separator + name + ".json");
         if (!cfg.exists()) {
            try {
               if (cfg.createNewFile()) {
               }

               ChatUtils.message("Created " + name + ".json");
               ChatUtils.message("Error");
            } catch (IOException var18) {
               var18.printStackTrace();
            }
         }

         try {
            FileWriter writer = new FileWriter(cfg);
            Throwable var5 = null;

            try {
               writer.write(json);
               ChatUtils.message("Saved " + name + ".json");
            } catch (Throwable var17) {
               var5 = var17;
               throw var17;
            } finally {
               if (writer != null) {
                  if (var5 != null) {
                     try {
                        writer.close();
                     } catch (Throwable var16) {
                        var5.addSuppressed(var16);
                     }
                  } else {
                     writer.close();
                  }
               }

            }
         } catch (IOException var20) {
            var20.printStackTrace();
         }
      } catch (Throwable var21) {
         var21.printStackTrace();
         ChatUtils.message("Error");
      }

   }

   private String getJson() {
      StringBuilder json = new StringBuilder();

      for(Iterator var2 = BloodWare.moduleManager.getModuleList().iterator(); var2.hasNext(); json.append(";")) {
         Module module = (Module)var2.next();
         String name = module.getName();
         json.append(name);
         json.append(":");
         String toggled = String.valueOf(module.isToggled());
         json.append(toggled);
         json.append(":");
         String key = String.valueOf(module.getKey());
         json.append(key);
         json.append(":");
         ArrayList<Setting> arrayList = BloodWare.instance.settingsManager.getSettingsByMod(module);
         if (arrayList != null) {
            Iterator var8 = arrayList.iterator();

            while(var8.hasNext()) {
               Setting setting = (Setting)var8.next();
               String settings;
               if (setting.isCheck()) {
                  settings = setting.getName() + "=" + setting.getValBoolean();
               } else if (setting.isSlider()) {
                  settings = setting.getName() + "=" + setting.getValDouble();
               } else {
                  settings = setting.getName() + "=" + setting.getValString();
               }

               json.append(settings);
               json.append(":");
            }
         }
      }

      return json.toString();
   }

   public void loadCFG(String name) {
      try {
         File cfg = new File(this.path, name + ".json");
         if (!cfg.exists()) {
            ChatUtils.message("Not Found");
         } else {
            BufferedReader reader = new BufferedReader(new FileReader(cfg.getPath()));
            String line = reader.readLine();
            String[] var6 = line.split(";");
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               String cfg_module = var6[var8];

               try {
                  if (cfg_module != null) {
                     String[] settings = cfg_module.split(":");
                     if (settings.length >= 2) {
                        String module_name = settings[0];
                        boolean module_toggled = Boolean.parseBoolean(settings[1]);
                        int module_key = Integer.parseInt(settings[2]);
                        Module module = BloodWare.moduleManager.getModule(module_name);
                        module.setToggled(module_toggled);
                        module.setKey(module_key);

                        for(int n = 3; n < settings.length; ++n) {
                           String[] cfg_setting = settings[n].split("=");
                           String setting_name = cfg_setting[0];
                           Setting setting = BloodWare.instance.settingsManager.getSettingByName(module, setting_name);
                           if (setting.isCheck()) {
                              setting.setValBoolean(Boolean.parseBoolean(cfg_setting[1]));
                           } else if (setting.isSlider()) {
                              setting.setValDouble(Double.parseDouble(cfg_setting[1]));
                           } else {
                              setting.setValString(cfg_setting[1]);
                           }
                        }
                     }
                  }
               } catch (Exception var19) {
                  var19.printStackTrace();
               }
            }

            ChatUtils.message(name + " Loaded");
         }
      } catch (Exception var20) {
         var20.printStackTrace();
         ChatUtils.message("Error");
         throw new RuntimeException(var20);
      }
   }
}
