package com.Blood.Ware.module.movement;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class Sprint extends Module {
   public Sprint() {
      super("Sprint", Category.MOVEMENT);
   }

   @SubscribeEvent
   public void onPlayerTick(PlayerTickEvent e) {
      KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
   }

   public void onDisable() {
      super.onDisable();
      KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
   }
}
