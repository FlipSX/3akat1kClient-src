package com.Blood.Ware;

import Bobr.BobrGui;
import Castom.CastomGui;
import com.Blood.Ware.events.event.EventManager;
import com.Blood.Ware.manager.ModuleManager;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.notifications.Type;
import com.Blood.Ware.notifications.notifications;
import com.Blood.Ware.settings.SettingsManager;
import com.Blood.Ware.utils.ScaleUtils;
import com.Blood.Ware.utils.config.CommandCfg;
import com.Blood.Ware.utils.config.ConfigManager;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Session;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class BloodWare {
   public static String name = "3akat1kClient 1.12.2";
   public static String cName = "3akat1kClient 1.12.2";
   public static String FULL_name = "3akat1kClient 1.12.2";
   private static final BloodWare INSTANCE = new BloodWare();
   public GuiScreen SimpleGui;
   public static ScaleUtils scale = new ScaleUtils(2);
   public static ModuleManager moduleManager;
   public static EventManager eventManager;
   public static BloodWare instance;
   public SettingsManager settingsManager;
   public ConfigManager configManager;
   public static Color color;
   public static BobrGui bobrGui;
   public static CastomGui CastomGui;

   public static void startup() {
      Display.setTitle(name);
   }

   public static void setSession(Session s) {
      Class mc = Minecraft.getMinecraft().getClass();

      try {
         Field session = null;
         Field[] var3 = mc.getDeclaredFields();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Field f = var3[var5];
            if (f.getType().isInstance(s)) {
               session = f;
            }
         }

         if (session == null) {
            throw new IllegalStateException("Session Null");
         }

         session.setAccessible(true);
         session.set(Minecraft.getMinecraft(), s);
         session.setAccessible(false);
         name = "3akat1kClient 1.12.2 | User: " + Minecraft.getMinecraft().getSession().getUsername();
         Display.setTitle(name);
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   public Minecraft mc() {
      return Minecraft.getMinecraft();
   }

   public void init() throws Exception {
      MinecraftForge.EVENT_BUS.register(this);
      this.settingsManager = new SettingsManager();
      moduleManager = new ModuleManager();
      this.configManager = new ConfigManager();
      ClientCommandHandler.instance.func_71560_a(new CommandCfg());
      bobrGui = new BobrGui();
      CastomGui = new CastomGui();
      notifications.add(String.valueOf(TextFormatting.RED), "successful injection!", Type.OK);
   }

   @SubscribeEvent
   public void key(KeyInputEvent e) {
      if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
         try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
               int keyCode = Keyboard.getEventKey();
               if (keyCode <= 0) {
                  return;
               }

               ModuleManager var10000 = moduleManager;
               Iterator var3 = ModuleManager.modules.iterator();

               while(var3.hasNext()) {
                  Module m = (Module)var3.next();
                  if (m.getKey() == keyCode && keyCode > 0) {
                     m.toggle();
                  }
               }
            }
         } catch (Exception var5) {
            var5.printStackTrace();
         }

      }
   }

   static {
      color = Color.red;
   }
}
