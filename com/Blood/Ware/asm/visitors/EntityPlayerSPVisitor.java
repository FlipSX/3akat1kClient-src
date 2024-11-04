package com.Blood.Ware.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class EntityPlayerSPVisitor extends ClassVisitor {
   final String IS_USER;
   final String IS_USER_DESC;
   final String MOVE;
   final String MOVE_DESC;
   final String ON_UPDATE_WALKING_PLAYER;
   final String ON_UPDATE_WALKING_PLAYER_DESC;

   public EntityPlayerSPVisitor(ClassVisitor cv, boolean isObfuscated) {
      super(327680, cv);
      this.IS_USER = isObfuscated ? "cZ" : "isUser";
      this.IS_USER_DESC = "()Z";
      this.MOVE = isObfuscated ? "a" : "move";
      this.MOVE_DESC = isObfuscated ? "(Lvv;DDD)V" : "(Lnet/minecraft/entity/MoverType;DDD)V";
      this.ON_UPDATE_WALKING_PLAYER = isObfuscated ? "N" : "onUpdateWalkingPlayer";
      this.ON_UPDATE_WALKING_PLAYER_DESC = "()V";
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
      if (name.equals(this.IS_USER) && desc.equals(this.IS_USER_DESC)) {
         System.out.println("Method " + name + " transformed");
         mv = new EntityPlayerSPVisitor.IsUserVisitor((MethodVisitor)mv);
      } else if (name.equals(this.MOVE) && desc.equals(this.MOVE_DESC)) {
         System.out.println("Method " + name + " transformed");
         mv = new EntityPlayerSPVisitor.MoveVisitor((MethodVisitor)mv);
      } else if (name.equals(this.ON_UPDATE_WALKING_PLAYER) && desc.equals(this.ON_UPDATE_WALKING_PLAYER_DESC)) {
         System.out.println("Method " + name + " transformed");
         mv = new EntityPlayerSPVisitor.OnUpdateWalkingPlayer((MethodVisitor)mv);
      }

      return (MethodVisitor)mv;
   }

   public static class OnUpdateWalkingPlayer extends MethodVisitor {
      public OnUpdateWalkingPlayer(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitCode() {
         super.visitCode();
         this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "onUpdateWalkingPlayer", "()Z", false);
         Label l1 = new Label();
         this.mv.visitJumpInsn(153, l1);
         this.mv.visitInsn(177);
         this.mv.visitLabel(l1);
      }
   }

   public static class MoveVisitor extends MethodVisitor {
      public MoveVisitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitCode() {
         this.mv.visitTypeInsn(187, "ru/terrar/bobr/events/PlayerMoveEvent");
         this.mv.visitInsn(89);
         this.mv.visitVarInsn(24, 2);
         this.mv.visitVarInsn(24, 4);
         this.mv.visitVarInsn(24, 6);
         this.mv.visitMethodInsn(183, "ru/internali/events/PlayerMoveEvent", "<init>", "(DDD)V", false);
         this.mv.visitVarInsn(58, 8);
         this.mv.visitFieldInsn(178, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;");
         this.mv.visitVarInsn(25, 8);
         this.mv.visitMethodInsn(182, "net/minecraftforge/fml/common/eventhandler/EventBus", "post", "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false);
         Label l0 = new Label();
         this.mv.visitJumpInsn(153, l0);
         this.mv.visitInsn(177);
         this.mv.visitLabel(l0);
         this.mv.visitFrame(1, 1, new Object[]{"ru/internali/events/PlayerMoveEvent"}, 0, (Object[])null);
         this.mv.visitVarInsn(25, 8);
         this.mv.visitFieldInsn(180, "ru/internali/events/PlayerMoveEvent", "x", "D");
         this.mv.visitVarInsn(57, 2);
         this.mv.visitVarInsn(25, 8);
         this.mv.visitFieldInsn(180, "ru/internali/events/PlayerMoveEvent", "y", "D");
         this.mv.visitVarInsn(57, 4);
         this.mv.visitVarInsn(25, 8);
         this.mv.visitFieldInsn(180, "ru/internali/events/PlayerMoveEvent", "z", "D");
         this.mv.visitVarInsn(57, 6);
         super.visitCode();
      }
   }

   public static class IsUserVisitor extends MethodVisitor {
      public IsUserVisitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitInsn(int opcode) {
         if (opcode == 172) {
            this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "isUser", "()Z", false);
         }

         super.visitInsn(opcode);
      }
   }
}
