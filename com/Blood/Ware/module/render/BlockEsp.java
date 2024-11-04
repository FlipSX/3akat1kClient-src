package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;
import com.Blood.Ware.utils.search.Blockinfo;
import com.Blood.Ware.utils.search.Search;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEsp extends Module {
   public static boolean trace;
   public static CopyOnWriteArrayList<Blockinfo> render = new CopyOnWriteArrayList();
   public static ArrayList<IBlockState> search_blocks = new ArrayList();
   public static ArrayList<Block> search_blocks1 = new ArrayList();
   Thread search;

   public BlockEsp() {
      super("BlockESP", Category.RENDER);
      BloodWare.instance.settingsManager.rSetting(new Setting("UpdateTime", this, 2.0D, 0.1D, 10.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("Trace", this, true));
      BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "ore", new ArrayList<String>() {
         {
            this.add("ore");
            this.add("cloth");
            this.add("pumpking");
         }
      }));
   }

   public void onEnable() {
      super.onEnable();
      render = new CopyOnWriteArrayList();
      this.search = new Search();
      this.search.start();
   }

   @SubscribeEvent
   public void onRender(RenderWorldLastEvent e) {
      trace = BloodWare.instance.settingsManager.getSettingByName((Module)this, "Trace").getValBoolean();

      try {
         ArrayList<Blockinfo> render = new ArrayList(BlockEsp.render);
         Iterator var3 = render.iterator();

         while(var3.hasNext()) {
            Blockinfo bi = (Blockinfo)var3.next();
            RenderUtils.blockEspFrame(new BlockPos(bi.getX(), bi.getY(), bi.getZ()), bi.getR(), bi.getG(), bi.getB());
            if (trace) {
               RenderUtils.trace(mc, bi);
            }
         }
      } catch (Throwable var5) {
      }

   }

   public void onDisable() {
      super.onDisable();
      this.search.interrupt();
   }
}
