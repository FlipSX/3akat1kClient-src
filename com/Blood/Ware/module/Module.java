package com.Blood.Ware.module;

import com.Blood.Ware.notifications.Type;
import com.Blood.Ware.notifications.notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.common.MinecraftForge;

public abstract class Module {
   protected static Minecraft mc = Minecraft.getMinecraft();
   private String name;
   private String description;
   private int key;
   private Category category;
   private boolean toggled;
   public boolean visible = true;
   private boolean hud;

   public Module(String name, Category category) {
      this.name = name;
      this.description = this.description;
      this.key = 0;
      this.category = category;
      this.toggled = false;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getKey() {
      return this.key;
   }

   public void setKey(int key) {
      this.key = key;
   }

   public boolean isToggled() {
      return this.toggled;
   }

   public void setToggled(boolean toggled) {
      if (this.toggled != toggled) {
         this.toggled = toggled;
         if (this.toggled) {
            this.onEnable();
         } else {
            this.onDisable();
         }

      }
   }

   public void toggle() {
      this.toggled = !this.toggled;
      if (this.toggled) {
         this.onEnableR();
      } else {
         this.onDisableR();
         this.onDisable();
      }

   }

   public void onEnable() {
      MinecraftForge.EVENT_BUS.register(this);
      notifications.add(this.name, TextFormatting.GREEN + "Enable!", Type.Green);
   }

   public void onEnableR() {
      this.onEnable();
   }

   public void onDisableR() {
      this.onDisable();
   }

   public void onDisable() {
      MinecraftForge.EVENT_BUS.unregister(this);
      notifications.add(this.name, TextFormatting.RED + "Disable!", Type.Red);
   }

   public String getName() {
      return this.name;
   }

   public Category getCategory() {
      return this.category;
   }

   protected void onCameraSetup(CameraSetup event) {
   }

   public boolean isToggler() {
      return this.toggled;
   }

   public boolean isHud() {
      return this.hud;
   }

   public void setHud(boolean hud) {
      this.hud = hud;
   }
}
