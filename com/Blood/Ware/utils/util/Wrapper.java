package com.Blood.Ware.utils.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.Packet;

public class Wrapper {
   public static volatile Wrapper INSTANCE = new Wrapper();

   public Minecraft getMinecraft() {
      return Minecraft.getMinecraft();
   }

   public InventoryPlayer inventory() {
      return this.player().field_71071_by;
   }

   public GameSettings mcSettings() {
      return INSTANCE.mc().gameSettings;
   }

   public void sendPacket(Packet packet) {
      this.player().connection.sendPacket(packet);
   }

   public EntityPlayerSP getLocalPlayer() {
      return INSTANCE.getMinecraft().player;
   }

   public GameSettings getGameSettings() {
      return INSTANCE.getMinecraft().gameSettings;
   }

   public PlayerControllerMP controller() {
      return INSTANCE.mc().playerController;
   }

   public Minecraft mc() {
      return Minecraft.getMinecraft();
   }

   public FontRenderer fontRenderer() {
      return INSTANCE.mc().fontRenderer;
   }

   public FontRenderer getFontRenderer() {
      return INSTANCE.getMinecraft().fontRenderer;
   }

   public WorldClient getWorld() {
      return INSTANCE.getMinecraft().world;
   }

   public WorldClient world() {
      return INSTANCE.mc().world;
   }

   public EntityPlayerSP player() {
      return INSTANCE.mc().player;
   }
}
