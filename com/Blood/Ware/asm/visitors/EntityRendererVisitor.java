package com.Blood.Ware.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class EntityRendererVisitor extends ClassVisitor {
   final String UPDATE_CAMERA;
   final String UPDATE_CAMERA_DESCRIPTOR;
   boolean isObfuscated;

   public EntityRendererVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
      super(327680, classVisitor);
      this.UPDATE_CAMERA = isObfuscated ? "a" : "updateCameraAndRender";
      this.UPDATE_CAMERA_DESCRIPTOR = isObfuscated ? "(FJ)V" : "(FJ)V";
      this.isObfuscated = isObfuscated;
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
      if (name.equals(this.UPDATE_CAMERA) && desc.equals(this.UPDATE_CAMERA_DESCRIPTOR)) {
         mv = new EntityRendererVisitor.UpdateCameraAndRendererVisitor((MethodVisitor)mv, this.isObfuscated);
         System.out.println("Method " + this.UPDATE_CAMERA + " transformed");
      }

      return (MethodVisitor)mv;
   }

   public static class UpdateCameraAndRendererVisitor extends MethodVisitor {
      boolean line1033;
      boolean line1039;
      boolean isObfuscated;

      public UpdateCameraAndRendererVisitor(MethodVisitor mv, boolean isObfuscated) {
         super(327680, mv);
         this.isObfuscated = isObfuscated;
      }

      public void visitLineNumber(int line, Label start) {
         super.visitLineNumber(line, start);
         this.line1033 = line == 1033;
         this.line1039 = line == 1039;
      }

      public void visitFieldInsn(int opcode, String owner, String name, String desc) {
         if ((this.line1033 || this.line1039) && owner.equals(this.isObfuscated ? "bib" : "net/minecraft/client/Minecraft")) {
            this.mv.visitMethodInsn(182, this.isObfuscated ? "bib" : "net/minecraft/client/Minecraft", this.isObfuscated ? "aa" : "getRenderViewEntity", this.isObfuscated ? "()Lvg;" : "()Lnet/minecraft/entity/Entity;", false);
         } else {
            super.visitFieldInsn(opcode, owner, name, desc);
         }

      }

      public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
         if (!this.line1033 && !this.line1039) {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
         } else {
            this.mv.visitMethodInsn(182, this.isObfuscated ? "vg" : "net/minecraft/entity/Entity", this.isObfuscated ? "c" : "turn", "(FF)V", false);
         }

      }
   }
}
