package com.Blood.Ware.module.combat;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.util.Comparator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AimAssist extends Module {
   public AimAssist() {
      super("AimAssist", Category.COMBAT);
      BloodWare.instance.settingsManager.rSetting(new Setting("Range", this, 3.0D, 1.0D, 10.0D, true));
   }

   @SubscribeEvent
   public void onUpdate(RenderWorldLastEvent e) {
      double range = (double)((float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "Range").getValDouble());
      EntityPlayer target = (EntityPlayer)mc.world.playerEntities.stream().filter((entityPlayer) -> {
         return entityPlayer != mc.player;
      }).min(Comparator.comparing((entityPlayer) -> {
         return entityPlayer.getDistance(mc.player);
      })).filter((entityPlayer) -> {
         return (double)entityPlayer.getDistance(mc.player) <= range;
      }).orElse((Object)null);
      if (target != null && mc.player.canEntityBeSeen(target) && !FriendManager.isFriend(this.getName())) {
         mc.player.rotationYaw = this.rotations(target)[0];
         mc.player.rotationPitch = this.rotations(target)[1];
      }

   }

   public float[] rotations(EntityPlayer entity) {
      double x = entity.posX - mc.player.posX;
      double y = entity.posY - (mc.player.posY + (double)mc.player.getEyeHeight()) + 1.5D;
      double z = entity.posZ - mc.player.posZ;
      double u = (double)MathHelper.sqrt(x * x + z * z);
      float u2 = (float)(MathHelper.atan2(z, x) * 57.29577951308232D - 90.0D);
      float u3 = (float)(-MathHelper.atan2(y, u) * 57.29577951308232D);
      return new float[]{u2, u3};
   }
}
