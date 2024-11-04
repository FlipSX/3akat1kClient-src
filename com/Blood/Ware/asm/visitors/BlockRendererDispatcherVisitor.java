package com.Blood.Ware.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class BlockRendererDispatcherVisitor extends ClassVisitor {
   final String RENDER_MODEL_FLAT;
   final String RENDER_BLOCK_DESCRIPTOR;

   public BlockRendererDispatcherVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
      super(327680, classVisitor);
      this.RENDER_MODEL_FLAT = isObfuscated ? "a" : "renderBlock";
      this.RENDER_BLOCK_DESCRIPTOR = isObfuscated ? "(Lawt;Let;Lamy;Lbuk;)Z" : "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)Z";
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
      if (name.equals(this.RENDER_MODEL_FLAT) && desc.equals(this.RENDER_BLOCK_DESCRIPTOR)) {
         System.out.println("Method " + name + " transformed");
         mv = new BlockRendererDispatcherVisitor.RenderBlockVisitor((MethodVisitor)mv);
      }

      return (MethodVisitor)mv;
   }

   public static class RenderBlockVisitor extends MethodVisitor {
      public RenderBlockVisitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitCode() {
         super.visitCode();
         this.mv.visitVarInsn(25, 1);
         this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "renderBlock", "(Lnet/minecraft/block/state/IBlockState;)Z", false);
         Label l1 = new Label();
         this.mv.visitJumpInsn(153, l1);
         Label l2 = new Label();
         this.mv.visitLabel(l2);
         this.mv.visitLineNumber(17, l2);
         this.mv.visitInsn(3);
         this.mv.visitInsn(172);
         this.mv.visitLabel(l1);
         this.mv.visitLineNumber(19, l1);
         this.mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }
   }
}
