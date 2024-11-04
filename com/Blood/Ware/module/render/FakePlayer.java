package com.Blood.Ware.module.render;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FakePlayer extends Module {
   EntityOtherPlayerMP fakePlayer;

   public FakePlayer() {
      super("FakePlayer", Category.RENDER);
   }

   public void onEnable() {
      this.fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.func_146103_bH());
      this.fakePlayer.setEntityId(-1882);
      this.fakePlayer.copyLocationAndAnglesFrom(mc.player);
      this.fakePlayer.field_70759_as = mc.player.rotationYawHead;
      mc.world.addEntityToWorld(this.fakePlayer.getEntityId(), this.fakePlayer);
   }

   public void onDisable() {
      mc.world.removeEntityFromWorld(this.fakePlayer.getEntityId());
   }
}
