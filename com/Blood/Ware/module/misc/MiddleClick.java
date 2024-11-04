package com.Blood.Ware.module.misc;

import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.notifications.Type;
import com.Blood.Ware.notifications.notifications;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent;
import org.lwjgl.input.Mouse;

public class MiddleClick extends Module {
   public MiddleClick() {
      super("MCF", Category.Misc);
   }

   @SubscribeEvent
   public void onMiddleClick(MouseInputEvent event) {
      if (mc.player != null && mc.world != null) {
         if (Mouse.getEventButtonState() && Mouse.getEventButton() == 2 && mc.objectMouseOver.entityHit instanceof EntityPlayer) {
            this.clickFriend();
         }

      }
   }

   private void clickFriend() {
      String name = mc.objectMouseOver.entityHit.getName();
      if (FriendManager.isFriend(name)) {
         FriendManager.FRIENDS.remove(name);
         notifications.add(TextFormatting.RED + name, "Remove from Friend list", Type.OK);
      } else {
         FriendManager.FRIENDS.add(name);
         notifications.add(TextFormatting.GREEN + name, "add in Friends list", Type.OK);
      }

   }
}
