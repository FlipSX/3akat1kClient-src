package com.Blood.Ware.events.event;

import java.util.Iterator;

public class ArrayHelper<T> implements Iterable<T> {
   private T[] elements;

   public ArrayHelper(T[] array) {
      this.elements = array;
   }

   public ArrayHelper() {
      this.elements = (Object[])(new Object[0]);
   }

   public void add(T t) {
      if (t != null) {
         Object[] array = new Object[this.size() + 1];

         for(int i = 0; i < array.length; ++i) {
            if (i < this.size()) {
               array[i] = this.get(i);
            } else {
               array[i] = t;
            }
         }

         this.set((Object[])array);
      }

   }

   public boolean contains(T t) {
      Object[] array;
      int lenght = (array = this.array()).length;

      for(int i = 0; i < lenght; ++i) {
         T entry = array[i];
         if (entry.equals(t)) {
            return true;
         }
      }

      return false;
   }

   public void remove(T t) {
      if (this.contains(t)) {
         Object[] array = new Object[this.size() - 1];
         boolean b = true;

         for(int i = 0; i < this.size(); ++i) {
            if (b && this.get(i).equals(t)) {
               b = false;
            } else {
               array[b ? i : i - 1] = this.get(i);
            }
         }

         this.set((Object[])array);
      }

   }

   public T[] array() {
      return this.elements;
   }

   public int size() {
      return this.array().length;
   }

   public void set(T[] array) {
      this.elements = array;
   }

   public T get(int index) {
      return this.array()[index];
   }

   public void clear() {
      this.elements = (Object[])(new Object[0]);
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Iterator<T> iterator() {
      return new Iterator<T>() {
         private int index = 0;

         public boolean hasNext() {
            return this.index < ArrayHelper.this.size() && ArrayHelper.this.get(this.index) != null;
         }

         public T next() {
            return ArrayHelper.this.get(this.index++);
         }

         public void remove() {
            ArrayHelper.this.remove(ArrayHelper.this.get(this.index));
         }
      };
   }
}
