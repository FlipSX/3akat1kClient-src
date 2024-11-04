package com.Blood.Ware.module.movement;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class NoPush extends Module {
   public NoPush() {
      super("NoPush", Category.MOVEMENT);
   }

   @SubscribeEvent
   public void onPlayerTick(PlayerTickEvent playerTickEvent) {
      mc.player.entityCollisionReduction = 1.0F;
   }
}
