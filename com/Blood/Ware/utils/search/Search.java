package com.Blood.Ware.utils.search;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.render.BlockEsp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Search extends Thread {
   public void run() {
      try {
         Logger var1 = LogManager.getLogger();

         while(true) {
            String mode = BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("BlockESP"), "Mode").getValString();
            CopyOnWriteArrayList<Blockinfo> render = new CopyOnWriteArrayList();
            new ArrayList();
            Minecraft mc = Minecraft.getMinecraft();
            int radius = mc.gameSettings.renderDistanceChunks << 4;
            int minX = mc.player.getPosition().func_177958_n() - radius;
            int maxX = mc.player.getPosition().func_177958_n() + radius;
            int minY = Math.max(60, mc.player.getPosition().func_177956_o() - 92);
            int maxY = Math.min(130, mc.player.getPosition().func_177956_o() + 72);
            int minZ = mc.player.getPosition().func_177952_p() - radius;
            int maxZ = mc.player.getPosition().func_177952_p() + radius;
            ArrayList blackList = new ArrayList<Block>() {
               {
                  this.add(Blocks.AIR);
                  this.add(Blocks.BEDROCK);
                  this.add(Blocks.STONE);
                  this.add(Blocks.GRASS);
                  this.add(Blocks.DIRT);
               }
            };

            for(int chunkX = minX >> 4; chunkX <= maxX >> 4; ++chunkX) {
               int x = chunkX << 4;
               int lowBoundX = x < minX ? minX - x : 0;
               int highBoundX = x + 15 > maxX ? maxX - x : 15;

               for(int chunkZ = minZ >> 4; chunkZ <= maxZ >> 4; ++chunkZ) {
                  Chunk chunk = mc.world.getChunkFromChunkCoords(chunkX, chunkZ);
                  if (chunk.isLoaded()) {
                     ExtendedBlockStorage[] extendsList = chunk.getBlockStorageArray();
                     int z = chunkZ << 4;
                     int lowBoundZ = z < minZ ? minZ - z : 0;
                     int highBoundZ = z + 15 > maxZ ? maxZ - z : 15;

                     for(int curExtend = minY >> 4; curExtend <= maxY >> 4; ++curExtend) {
                        ExtendedBlockStorage ebs = extendsList[curExtend];
                        if (ebs != null) {
                           int y = curExtend << 4;
                           int lowBoundY = y < minY ? minY - y : 0;
                           int highBoundY = y + 15 > maxY ? maxY - y : 15;

                           for(int i = lowBoundX; i <= highBoundX; ++i) {
                              for(int j = lowBoundY; j <= highBoundY; ++j) {
                                 for(int k = lowBoundZ; k <= highBoundZ; ++k) {
                                    IBlockState currentState = ebs.get(i, j, k);
                                    if (!blackList.contains(currentState.getBlock())) {
                                       Block state;
                                       if (mode.equalsIgnoreCase("ore")) {
                                          try {
                                             Block block = ebs.get(i, j - 1, k).getBlock();
                                             if (block.equals(Blocks.AIR)) {
                                                continue;
                                             }

                                             state = ebs.get(i, j + 1, k).getBlock();
                                             if (mode.equalsIgnoreCase("ore") && !state.equals(Blocks.AIR)) {
                                                continue;
                                             }
                                          } catch (Throwable var41) {
                                             BlockPos vo = new BlockPos(i + x, j + y - 1, k + z);
                                             IBlockState v = mc.world.getBlockState(vo);
                                             if (v.getBlock().equals(Blocks.AIR)) {
                                                continue;
                                             }

                                             BlockPos vo2 = new BlockPos(i + x, j + y + 1, k + z);
                                             IBlockState v2 = mc.world.getBlockState(vo2);
                                             if (mode.equalsIgnoreCase("ore") && !v2.getBlock().equals(Blocks.AIR)) {
                                                continue;
                                             }
                                          }
                                       }

                                       if (mode.equalsIgnoreCase("ore")) {
                                          if (currentState.getBlock().equals(Blocks.IRON_ORE)) {
                                             render.add(new Blockinfo(x + i, y + j, z + k, 0.0F, 0.5F, 1.0F));
                                          } else if (currentState.getBlock().equals(Blocks.DIAMOND_ORE)) {
                                             render.add(new Blockinfo(x + i, y + j, z + k, 1.0F, 0.0F, 0.0F));
                                          } else if (currentState.getBlock().equals(Blocks.GOLD_ORE)) {
                                             render.add(new Blockinfo(x + i, y + j, z + k, 1.0F, 0.9F, 0.0F));
                                          }
                                       } else if (mode.equalsIgnoreCase("cloth")) {
                                          try {
                                             if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.TALLGRASS, "2"))) {
                                                render.add(new Blockinfo(x + i, y + j, z + k, 1.0F, 0.4F, 0.0F));
                                             }
                                          } catch (Exception var40) {
                                             BloodWare.moduleManager.getModule("BlockESP").setToggled(false);
                                          }
                                       } else if (mode.equalsIgnoreCase("pumpking")) {
                                          try {
                                             if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.BEETROOTS, "3"))) {
                                                render.add(new Blockinfo(x + i, y + j, z + k, 1.0F, 0.4F, 0.0F));
                                             }
                                          } catch (Exception var39) {
                                             BloodWare.moduleManager.getModule("BlockESP").setToggled(false);
                                          }
                                       } else {
                                          Iterator var43 = BlockEsp.search_blocks.iterator();

                                          while(var43.hasNext()) {
                                             IBlockState state = (IBlockState)var43.next();
                                             if (currentState.equals(state)) {
                                                render.add(new Blockinfo(x + i, y + j, z + k, 1.0F, 0.0F, 0.0F));
                                             }
                                          }

                                          var43 = BlockEsp.search_blocks1.iterator();

                                          while(var43.hasNext()) {
                                             state = (Block)var43.next();
                                             if (currentState.getBlock().equals(state)) {
                                                render.add(new Blockinfo(x + i, y + j, z + k, 1.0F, 0.0F, 0.0F));
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            BlockEsp.render.clear();
            BlockEsp.render = render;
            Thread.sleep((long)(BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("BlockESP"), "UpdateTime").getValDouble() * 1000.0D));
         }
      } catch (InterruptedException var42) {
         throw new RuntimeException(var42);
      }
   }
}
