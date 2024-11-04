package com.Blood.Ware.events.event;

import com.Blood.Ware.events.EventTarget;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EventManager {
   private static final Map<Class<? extends Event>, ArrayHelper<Data>> REGISTRY_MAP = new HashMap();

   public void register(Object o) {
      Method[] var2 = o.getClass().getDeclaredMethods();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Method method = var2[var4];
         if (!isMethodBad(method)) {
            this.register(method, o);
         }
      }

   }

   public void register(Object o, Class<? extends Event> clazz) {
      Method[] var3 = o.getClass().getDeclaredMethods();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Method method = var3[var5];
         if (!isMethodBad(method, clazz)) {
            this.register(method, o);
         }
      }

   }

   private void register(Method method, Object o) {
      Class<?> clazz = method.getParameterTypes()[0];
      final Data methodData = new Data(o, method, ((EventTarget)method.getAnnotation(EventTarget.class)).value());
      if (!methodData.target.isAccessible()) {
         methodData.target.setAccessible(true);
      }

      if (REGISTRY_MAP.containsKey(clazz)) {
         if (!((ArrayHelper)REGISTRY_MAP.get(clazz)).contains(methodData)) {
            ((ArrayHelper)REGISTRY_MAP.get(clazz)).add(methodData);
            this.sortListValue(clazz);
         }
      } else {
         REGISTRY_MAP.put(clazz, new ArrayHelper<Data>() {
            {
               this.add(methodData);
            }
         });
      }

   }

   public void unregister(Object o) {
      Iterator var2 = REGISTRY_MAP.values().iterator();

      while(var2.hasNext()) {
         ArrayHelper<Data> flexibalArray = (ArrayHelper)var2.next();
         Iterator var4 = flexibalArray.iterator();

         while(var4.hasNext()) {
            Data methodData = (Data)var4.next();
            if (methodData.source.equals(o)) {
               flexibalArray.remove(methodData);
            }
         }
      }

      this.cleanMap(true);
   }

   public void unregister(Object o, Class<? extends Event> clazz) {
      if (REGISTRY_MAP.containsKey(clazz)) {
         Iterator var3 = ((ArrayHelper)REGISTRY_MAP.get(clazz)).iterator();

         while(var3.hasNext()) {
            Data methodData = (Data)var3.next();
            if (methodData.source.equals(o)) {
               ((ArrayHelper)REGISTRY_MAP.get(clazz)).remove(methodData);
            }
         }

         this.cleanMap(true);
      }

   }

   public void cleanMap(boolean b) {
      Iterator iterator = REGISTRY_MAP.entrySet().iterator();

      while(true) {
         do {
            if (!iterator.hasNext()) {
               return;
            }
         } while(b && !((ArrayHelper)((Entry)iterator.next()).getValue()).isEmpty());

         iterator.remove();
      }
   }

   public void removeEnty(Class<? extends Event> clazz) {
      Iterator iterator = REGISTRY_MAP.entrySet().iterator();

      while(iterator.hasNext()) {
         if (((Class)((Entry)iterator.next()).getKey()).equals(clazz)) {
            iterator.remove();
            break;
         }
      }

   }

   private void sortListValue(Class<? extends Event> clazz) {
      ArrayHelper<Data> flexibleArray = new ArrayHelper();
      byte[] var3 = Priority.VALUE_ARRAY;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         byte b = var3[var5];
         Iterator var7 = ((ArrayHelper)REGISTRY_MAP.get(clazz)).iterator();

         while(var7.hasNext()) {
            Data methodData = (Data)var7.next();
            if (methodData.priority == b) {
               flexibleArray.add(methodData);
            }
         }
      }

      REGISTRY_MAP.put(clazz, flexibleArray);
   }

   private static boolean isMethodBad(Method method) {
      return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
   }

   private static boolean isMethodBad(Method method, Class<? extends Event> clazz) {
      return isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
   }

   public static ArrayHelper<Data> get(Class<? extends Event> clazz) {
      return (ArrayHelper)REGISTRY_MAP.get(clazz);
   }

   public static void shutdown() {
      REGISTRY_MAP.clear();
   }
}
