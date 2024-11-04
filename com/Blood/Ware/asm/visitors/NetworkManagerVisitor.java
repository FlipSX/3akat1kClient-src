package com.Blood.Ware.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class NetworkManagerVisitor extends ClassVisitor {
   final String SEND_PACKET;
   final String SEND_PACKET_DESCRIPTOR;
   final String SEND_PACKET_DESCRIPTOR2;
   final String CHANNEL_READ_0;
   final String CHANNEL_READ_0_DESCRIPTOR;

   public NetworkManagerVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
      super(327680, classVisitor);
      this.SEND_PACKET = isObfuscated ? "a" : "sendPacket";
      this.SEND_PACKET_DESCRIPTOR = isObfuscated ? "(Lht;)V" : "(Lnet/minecraft/network/Packet;)V";
      this.SEND_PACKET_DESCRIPTOR2 = isObfuscated ? "(Lht;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V" : "(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V";
      this.CHANNEL_READ_0 = isObfuscated ? "a" : "channelRead0";
      this.CHANNEL_READ_0_DESCRIPTOR = isObfuscated ? "(Lio/netty/channel/ChannelHandlerContext;Lht;)V" : "(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V";
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
      if (!name.equals(this.SEND_PACKET) || !desc.equals(this.SEND_PACKET_DESCRIPTOR) && !desc.equals(this.SEND_PACKET_DESCRIPTOR2)) {
         if (name.equals(this.CHANNEL_READ_0) && desc.equals(this.CHANNEL_READ_0_DESCRIPTOR)) {
            mv = new NetworkManagerVisitor.ChannelRead0Visitor((MethodVisitor)mv);
         }
      } else {
         mv = new NetworkManagerVisitor.SendPacketVisitor((MethodVisitor)mv);
         System.out.println("Method " + this.SEND_PACKET + " transformed");
      }

      return (MethodVisitor)mv;
   }

   public static class ChannelRead0Visitor extends MethodVisitor {
      public ChannelRead0Visitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitCode() {
         super.visitCode();
         this.mv.visitVarInsn(25, 2);
         this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "onReceivePacket", "(Lnet/minecraft/network/Packet;)Lnet/minecraft/network/Packet;", false);
         this.mv.visitVarInsn(58, 2);
         this.mv.visitVarInsn(25, 2);
         Label l0 = new Label();
         this.mv.visitJumpInsn(199, l0);
         this.mv.visitInsn(177);
         this.mv.visitLabel(l0);
         this.mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }
   }

   public static class SendPacketVisitor extends MethodVisitor {
      public SendPacketVisitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitCode() {
         super.visitCode();
         this.mv.visitVarInsn(25, 1);
         this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "onSendPacket", "(Lnet/minecraft/network/Packet;)Lnet/minecraft/network/Packet;", false);
         this.mv.visitVarInsn(58, 1);
         this.mv.visitVarInsn(25, 1);
         Label l0 = new Label();
         this.mv.visitJumpInsn(199, l0);
         this.mv.visitInsn(177);
         this.mv.visitLabel(l0);
         this.mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }
   }
}
