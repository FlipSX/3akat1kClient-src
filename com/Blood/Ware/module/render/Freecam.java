package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.events.SendPacketEvent;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.lwjgl.input.Keyboard;

public class Freecam extends Module {
   private int lastThirdPerson;
   private final Minecraft mc = Minecraft.getMinecraft();
   public static final Freecam INSTANCE = new Freecam();
   private boolean activeThisSession = false;
   public EntityOtherPlayerMP camera;

   public void onEnable() {
      super.onEnable();
      MinecraftForge.EVENT_BUS.register(this);
      this.activeThisSession = true;
      this.mc.renderGlobal.loadRenderers();
   }

   @SubscribeEvent
   public void onInput(InputUpdateEvent inputUpdateEvent) {
      float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "horizontalSpeed").getValDouble();
      float n2 = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "verticalSpeed").getValDouble();
      if (this.camera != null) {
         MovementInput movementInput = inputUpdateEvent.getMovementInput();
         Vec3d rotateYaw = (new Vec3d((double)((movementInput.leftKeyDown ? n : 0.0F) - (movementInput.rightKeyDown ? n : 0.0F)), (double)((movementInput.jump ? n2 : 0.0F) - (movementInput.sneak ? n2 : 0.0F)), (double)((movementInput.forwardKeyDown ? n : 0.0F) - (movementInput.backKeyDown ? n : 0.0F)))).rotateYaw((float)(-Math.toRadians((double)this.camera.field_70759_as)));
         this.camera.setPositionAndRotationDirect(this.camera.posX + rotateYaw.x, this.camera.posY + rotateYaw.y, this.camera.posZ + rotateYaw.z, this.camera.field_70759_as, this.camera.rotationPitch, 3, false);
         if (movementInput instanceof MovementInputFromOptions) {
            this.mc.player.moveVertical = 0.0F;
            this.mc.player.moveStrafing = 0.0F;
            this.mc.player.moveForward = 0.0F;
            this.mc.player.setJumping(false);
         }

      }
   }

   public Freecam() {
      super("Freecam", Category.OUTHER);
      BloodWare.instance.settingsManager.rSetting(new Setting("verticalspeed", this, 3.5D, 1.0D, 12.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("horizontalspeed", this, 3.5D, 1.0D, 12.0D, false));
      BloodWare.instance.settingsManager.rSetting(new Setting("rotationspeed", this, 3.0D, 1.0D, 20.0D, false));
   }

   @SubscribeEvent
   public void onPlayerTick(PlayerTickEvent playerTickEvent) {
      if (this.mc.world != null && this.mc.world.isRemote) {
         if (this.camera == null) {
            this.lastThirdPerson = this.mc.gameSettings.thirdPersonView;
            this.camera = new EntityOtherPlayerMP(this.mc.world, this.mc.getSession().getProfile());
            this.mc.world.addEntityToWorld(333393333, this.camera);
            this.camera.copyLocationAndAnglesFrom(this.mc.player);
            this.mc.setRenderViewEntity(this.camera);
            this.camera.noClip = true;
         }

         this.mc.gameSettings.thirdPersonView = 0;
         this.camera.field_71071_by = this.mc.player.field_71071_by;
         this.camera.func_70606_j(this.mc.player.getHealth());
      } else {
         this.camera = null;
      }
   }

   public void onDisable() {
      super.onDisable();
      MinecraftForge.EVENT_BUS.unregister(this);
      if (this.mc.world != null && this.mc.world.isRemote) {
         this.mc.setRenderViewEntity(this.mc.player);
         if (this.activeThisSession) {
            this.mc.gameSettings.thirdPersonView = this.lastThirdPerson;
            this.mc.world.removeEntity(this.camera);
         }
      }

      this.camera = null;
      this.activeThisSession = false;
   }

   @SubscribeEvent
   public void onKeyUpdate(InputUpdateEvent event) {
      float n = (float)BloodWare.instance.settingsManager.getSettingByName((Module)this, "rotationspeed").getValDouble();
      EntityOtherPlayerMP var10000;
      if (Keyboard.isKeyDown(203)) {
         var10000 = this.camera;
         var10000.field_70759_as -= n;
         var10000 = this.camera;
         var10000.rotationYaw -= n;
      }

      if (Keyboard.isKeyDown(205)) {
         var10000 = this.camera;
         var10000.field_70759_as += n;
         var10000 = this.camera;
         var10000.rotationYaw += n;
      }

      if (Keyboard.isKeyDown(200)) {
         var10000 = this.camera;
         var10000.rotationPitch -= n;
      }

      if (Keyboard.isKeyDown(208)) {
         var10000 = this.camera;
         var10000.rotationPitch += n;
      }

   }

   @SubscribeEvent
   public void onSendPacket(SendPacketEvent sendPacketEvent) {
      if (sendPacketEvent.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)sendPacketEvent.getPacket()).getEntityFromWorld(this.mc.world) == this.mc.player) {
         sendPacketEvent.setCanceled(true);
      }

   }
}
