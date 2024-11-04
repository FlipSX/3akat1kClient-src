package com.Blood.Ware.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class VisGraphVisitor extends ClassVisitor {
   final String SET_OPAQUE;
   final String SET_OPAQUE_DESCRIPTOR;

   public VisGraphVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
      super(327680, classVisitor);
      this.SET_OPAQUE = isObfuscated ? "a" : "setOpaqueCube";
      this.SET_OPAQUE_DESCRIPTOR = isObfuscated ? "(Let;)V" : "(Lnet/minecraft/util/math/BlockPos;)V";
   }

   public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
      MethodVisitor mv = this.cv.visitMethod(i, s, s1, s2, strings);
      if (s.equals(this.SET_OPAQUE) && s1.equals(this.SET_OPAQUE_DESCRIPTOR)) {
         mv = new VisGraphVisitor.SetOpaqueCubeVisitor((MethodVisitor)mv);
         System.out.println("Method " + this.SET_OPAQUE + " transformed");
      }

      return (MethodVisitor)mv;
   }

   public static class SetOpaqueCubeVisitor extends MethodVisitor {
      public SetOpaqueCubeVisitor(MethodVisitor methodVisitor) {
         super(327680, methodVisitor);
      }

      public void visitCode() {
         super.visitCode();
         Label l0 = new Label();
         this.mv.visitLabel(l0);
         this.mv.visitLineNumber(29, l0);
         this.mv.visitMethodInsn(184, "ru/internali/utils/EventFactory", "setOpaqueCube", "()Z", false);
         Label l1 = new Label();
         this.mv.visitJumpInsn(153, l1);
         Label l2 = new Label();
         this.mv.visitLabel(l2);
         this.mv.visitLineNumber(30, l2);
         this.mv.visitInsn(177);
         this.mv.visitLabel(l1);
         this.mv.visitLineNumber(32, l1);
         this.mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }
   }
}
