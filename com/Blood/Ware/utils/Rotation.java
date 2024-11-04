package com.Blood.Ware.utils;

public class Rotation extends Wrapper {
   private float yaw;
   private float pitch;
   private final Rotation.Rotate rotate;

   public Rotation(float yaw, float pitch, Rotation.Rotate rotate) {
      this.yaw = yaw;
      this.pitch = pitch;
      this.rotate = rotate;
   }

   public Rotation(float yaw, float pitch) {
      this(yaw, pitch, Rotation.Rotate.NONE);
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setYaw(float in) {
      this.yaw = in;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setPitch(float in) {
      this.pitch = in;
   }

   public Rotation.Rotate getRotation() {
      return this.rotate;
   }

   public boolean isValid() {
      return !Float.isNaN(this.getYaw()) && !Float.isNaN(this.getPitch());
   }

   public static enum Rotate {
      PACKET,
      CLIENT,
      NONE;
   }
}
