package com.Blood.Ware.utils.config;

import com.Blood.Ware.BloodWare;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandCfg extends CommandBase {
   public String getName() {
      return "cfg";
   }

   public List<String> getAliases() {
      List<String> aliases = new ArrayList();
      aliases.add("config");
      return aliases;
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getUsage(ICommandSender sender) {
      return "/cfg <save/load> <name>";
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (args.length < 2) {
         throw new WrongUsageException(this.getUsage(sender), new Object[0]);
      } else {
         String var4 = args[0];
         byte var5 = -1;
         switch(var4.hashCode()) {
         case 3327206:
            if (var4.equals("load")) {
               var5 = 1;
            }
            break;
         case 3522941:
            if (var4.equals("save")) {
               var5 = 0;
            }
         }

         switch(var5) {
         case 0:
            BloodWare.instance.configManager.saveCFG(args[1]);
            break;
         case 1:
            BloodWare.instance.configManager.loadCFG(args[1]);
            break;
         default:
            throw new WrongUsageException(this.getUsage(sender), new Object[0]);
         }

      }
   }

   public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         List<String> options = new ArrayList();
         options.add("save");
         options.add("load");
         return options;
      } else {
         return Collections.emptyList();
      }
   }
}
