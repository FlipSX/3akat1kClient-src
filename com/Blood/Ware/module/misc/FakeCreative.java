package com.Blood.Ware.module.misc;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraft.world.GameType;

public class FakeCreative extends Module {
   public GameType oldType;

   public FakeCreative() {
      super("FakeCreative", Category.Misc);
   }

   public void onEnable() {
      if (!this.nullCheck()) {
         this.oldType = mc.playerController.getCurrentGameType();
         if (mc.player != null) {
            mc.playerController.setGameType(GameType.CREATIVE);
         } else {
            this.toggle();
         }

      }
   }

   private boolean nullCheck() {
      return false;
   }

   public void onDisable() {
      if (!this.nullCheck()) {
         if (mc.player != null) {
            if (this.oldType == GameType.SURVIVAL) {
               mc.playerController.setGameType(GameType.SURVIVAL);
            }

            if (this.oldType == GameType.SPECTATOR) {
               mc.playerController.setGameType(GameType.SPECTATOR);
            }

            if (this.oldType == GameType.ADVENTURE) {
               mc.playerController.setGameType(GameType.ADVENTURE);
            }

            if (this.oldType == GameType.CREATIVE) {
               mc.playerController.setGameType(GameType.CREATIVE);
            }
         }

      }
   }
}
