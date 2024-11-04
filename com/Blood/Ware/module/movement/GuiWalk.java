package com.Blood.Ware.module.movement;

import Bobr.BobrGui;
import Caesium.ClickGui;
import Castom.CastomGui;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class GuiWalk extends Module {
   public void onDisable() {
      super.onDisable();
   }

   public void onEnable() {
      super.onEnable();
   }

   @SubscribeEvent
   public void onKeyUpdate(InputUpdateEvent inputUpdateEvent) {
      boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Sneak").getValBoolean();
      if (mc.world != null && mc.player != null && (mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiIngameMenu || mc.currentScreen instanceof GuiOptions || mc.currentScreen instanceof BobrGui || mc.currentScreen instanceof ClickGui || mc.currentScreen instanceof CastomGui)) {
         MovementInput movementInput4;
         if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
            movementInput4 = mc.player.movementInput;
            ++movementInput4.moveForward;
            mc.player.movementInput.forwardKeyDown = true;
         }

         if (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
            movementInput4 = mc.player.movementInput;
            --movementInput4.moveForward;
            mc.player.movementInput.backKeyDown = true;
         }

         if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
            movementInput4 = mc.player.movementInput;
            --movementInput4.moveStrafe;
            mc.player.movementInput.rightKeyDown = true;
         }

         if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
            movementInput4 = mc.player.movementInput;
            ++movementInput4.moveStrafe;
            mc.player.movementInput.rightKeyDown = true;
         }

         mc.player.movementInput.jump = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
         mc.player.movementInput.sneak = valBoolean && Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode());
         if (mc.player.movementInput.sneak) {
            MovementInput var10000 = mc.player.movementInput;
            var10000.moveStrafe *= 0.3F;
            var10000 = mc.player.movementInput;
            var10000.moveForward *= 0.3F;
         }
      }

   }

   public GuiWalk() {
      super("GuiWalk", Category.MOVEMENT);
      BloodWare.instance.settingsManager.rSetting(new Setting("Sneak", this, false));
   }
}
