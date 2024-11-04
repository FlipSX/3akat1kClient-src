package com.Blood.Ware.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ChatUtils {
   private static final String prefix;

   public static void message(String msg) {
      Minecraft.getMinecraft().player.sendMessage(new TextComponentString(prefix + msg));
   }

   static {
      prefix = TextFormatting.GRAY + "[" + TextFormatting.BLUE + "3akat1k+jeklout<3" + TextFormatting.GRAY + "]: " + TextFormatting.WHITE;
   }
}
