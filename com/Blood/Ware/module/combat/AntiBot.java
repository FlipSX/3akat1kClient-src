package com.Blood.Ware.module.combat;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class AntiBot extends Module {
   public static List<String> BOTS = new ArrayList();
   public static boolean toggle;

   public AntiBot() {
      super("AntiBot", Category.COMBAT);
      BloodWare.instance.settingsManager.rSetting(new Setting("Remove", this, true));
   }

   public void onEnable() {
      super.onEnable();
      toggle = true;
   }

   public void onDisable() {
      super.onDisable();
      toggle = false;
      BOTS.clear();
   }

   public static boolean isBot(String nick) {
      if (toggle) {
         Iterator var1 = BOTS.iterator();

         String friend;
         do {
            if (!var1.hasNext()) {
               return false;
            }

            friend = (String)var1.next();
         } while(!friend.equalsIgnoreCase(nick));

         return true;
      } else {
         return false;
      }
   }

   public static boolean isBlockMaterial(BlockPos blockPos, Block block) {
      return getBlock(blockPos) == Blocks.AIR;
   }

   public static Block getBlock(BlockPos pos) {
      return getState(pos).getBlock();
   }

   public static IBlockState getState(BlockPos pos) {
      return mc.world.getBlockState(pos);
   }

   @SubscribeEvent
   public void onTick(PlayerTickEvent event) {
      boolean Remove = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Remove").getValBoolean();
      Iterator var3 = mc.world.loadedEntityList.iterator();

      while(var3.hasNext()) {
         Entity e = (Entity)var3.next();
         if (e != mc.player && e.ticksExisted < 5 && e instanceof EntityOtherPlayerMP && mc.player.getDistanceSq(e) <= 15.0D) {
            if (isBlockMaterial((new BlockPos(e)).down(), Blocks.AIR)) {
               BOTS.add(e.getName());
               if (Remove) {
                  mc.world.removeEntity(e);
               }
            }

            if (e.isInvisible()) {
               BOTS.add(e.getName());
               if (Remove) {
                  mc.world.removeEntity(e);
               }
            }
         }
      }

   }
}
