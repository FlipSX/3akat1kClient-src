package com.Blood.Ware.manager;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.combat.AimAssist;
import com.Blood.Ware.module.combat.AimBot;
import com.Blood.Ware.module.combat.AimBot2;
import com.Blood.Ware.module.combat.AntiBot;
import com.Blood.Ware.module.combat.BAim;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.module.hud.HUD;
import com.Blood.Ware.module.hud.Keystrokes;
import com.Blood.Ware.module.hud.Notifications;
import com.Blood.Ware.module.hud.Watermark;
import com.Blood.Ware.module.misc.FakeCreative;
import com.Blood.Ware.module.misc.FogColor;
import com.Blood.Ware.module.misc.InvViewer;
import com.Blood.Ware.module.misc.MiddleClick;
import com.Blood.Ware.module.movement.GuiWalk;
import com.Blood.Ware.module.movement.NoPush;
import com.Blood.Ware.module.movement.Sprint;
import com.Blood.Ware.module.render.BlockEsp;
import com.Blood.Ware.module.render.CameraClip;
import com.Blood.Ware.module.render.ChinaHat;
import com.Blood.Ware.module.render.ESP;
import com.Blood.Ware.module.render.FakePlayer;
import com.Blood.Ware.module.render.Freecam;
import com.Blood.Ware.module.render.FullBright;
import com.Blood.Ware.module.render.HouseEsp;
import com.Blood.Ware.module.render.ItemESP;
import com.Blood.Ware.module.render.NameTags;
import com.Blood.Ware.module.render.PenisESP;
import com.Blood.Ware.module.render.SkyColour;
import com.Blood.Ware.module.render.Tracers;
import com.Blood.Ware.module.render.ViewModel;
import com.Blood.Ware.module.render.WallHack;
import com.Blood.Ware.module.render.WallHackPlayer;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.util.ArrayList;
import java.util.Iterator;

public class ModuleManager {
   public static ArrayList<Module> modules;

   public ModuleManager() {
      (modules = new ArrayList()).clear();
      modules.add(new BlockEsp());
      modules.add(new SkyColour());
      modules.add(new CameraClip());
      modules.add(new AimBot());
      modules.add(new AimBot2());
      modules.add(new BAim());
      modules.add(new FakeCreative());
      modules.add(new ClickGUI());
      modules.add(new ESP());
      modules.add(new HouseEsp());
      modules.add(new PenisESP());
      modules.add(new ItemESP());
      modules.add(new Tracers());
      modules.add(new GuiWalk());
      modules.add(new FogColor());
      modules.add(new Freecam());
      modules.add(new FullBright());
      modules.add(new InvViewer());
      modules.add(new NameTags());
      modules.add(new WallHackPlayer());
      modules.add(new Keystrokes());
      modules.add(new HUD());
      modules.add(new ViewModel());
      modules.add(new Notifications());
      modules.add(new FakePlayer());
      modules.add(new MiddleClick());
      modules.add(new ChinaHat());
      modules.add(new Sprint());
      modules.add(new NoPush());
      modules.add(new AimAssist());
      modules.add(new AntiBot());
      modules.add(new WallHack());
      modules.add(new Watermark());

      Module var2;
      for(Iterator var1 = modules.iterator(); var1.hasNext(); var2 = (Module)var1.next()) {
      }

   }

   public static ArrayList<Class<?>> getClasses(String packageName) {
      ArrayList classes = new ArrayList();

      try {
         ClassLoader loader = Thread.currentThread().getContextClassLoader();
         UnmodifiableIterator var3 = ClassPath.from(loader).getAllClasses().iterator();

         while(var3.hasNext()) {
            ClassInfo info = (ClassInfo)var3.next();
            if (info.getName().startsWith(packageName)) {
               classes.add(info.load());
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return classes;
   }

   public Module getModule(String name) {
      Iterator var2 = modules.iterator();

      Module m;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         m = (Module)var2.next();
      } while(!m.getName().equalsIgnoreCase(name));

      return m;
   }

   public ArrayList<Module> getModuleList() {
      return modules;
   }

   public ArrayList<Module> getModulesInCategory(Category c) {
      ArrayList<Module> mods = new ArrayList();
      Iterator var3 = modules.iterator();

      while(var3.hasNext()) {
         Module m = (Module)var3.next();
         if (m.getCategory() == c) {
            mods.add(m);
         }
      }

      return mods;
   }

   public ArrayList<Module> getModules(Category category) {
      ArrayList<Module> modules2 = new ArrayList();
      Iterator var3 = modules.iterator();

      while(var3.hasNext()) {
         Module module = (Module)var3.next();
         if (module.getCategory() == category) {
            modules2.add(module);
         }
      }

      return modules2;
   }
}
