package com.Blood.Ware.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class BlockStateContainerVisitor extends ClassVisitor {
   final String SHOULD_SIDE;
   final String SHOULD_SIDE_DESCRIPTOR;
   final String GET_LIGHT;
   final String GET_LIGHT_DESCRIPTOR;

   public BlockStateContainerVisitor(ClassVisitor cv, boolean isObfuscated) {
      super(327680, cv);
      this.SHOULD_SIDE = isObfuscated ? "c" : "shouldSideBeRendered";
      this.SHOULD_SIDE_DESCRIPTOR = isObfuscated ? "(Lamy;Let;Lfa;)Z" : "(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z";
      this.GET_LIGHT = isObfuscated ? "j" : "getAmbientOcclusionLightValue";
      this.GET_LIGHT_DESCRIPTOR = "()F";
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
      if (name.equals(this.SHOULD_SIDE) && desc.equals(this.SHOULD_SIDE_DESCRIPTOR)) {
         mv = new BlockStateContainerVisitor.ShouldSideBeRenderedVisitor((MethodVisitor)mv);
         System.out.println("Method " + name + " transformed");
      } else if (name.equals(this.GET_LIGHT) && desc.equals(this.GET_LIGHT_DESCRIPTOR)) {
         mv = new BlockStateContainerVisitor.GetAmbientOcclusionLightValueVisitor((MethodVisitor)mv);
         System.out.println("Method " + name + " transformed");
      }

      return (MethodVisitor)mv;
   }

   public static class GetAmbientOcclusionLightValueVisitor extends MethodVisitor {
      public GetAmbientOcclusionLightValueVisitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitInsn(int opcode) {
         if (opcode == 174) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "getAmbientOcclusionLightValue", "(Lnet/minecraft/block/state/IBlockState;)F", false);
         }

         super.visitInsn(opcode);
      }
   }

   public static class ShouldSideBeRenderedVisitor extends MethodVisitor {
      public ShouldSideBeRenderedVisitor(MethodVisitor mv) {
         super(327680, mv);
      }

      public void visitInsn(int opcode) {
         if (opcode == 172) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitVarInsn(25, 1);
            this.mv.visitVarInsn(25, 2);
            this.mv.visitVarInsn(25, 3);
            this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "shouldSideBeRendered", "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z", false);
         }

         super.visitInsn(opcode);
      }
   }
}
