package com.Blood.Ware.events.event;

import com.Blood.Ware.BloodWare;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public abstract class Event {
   private boolean cancelled;

   public Event call() {
      this.cancelled = false;
      call(this);
      return this;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean cancelled) {
      this.cancelled = cancelled;
   }

   private static void call(Event event) {
      EventManager eventManager = BloodWare.eventManager;
      ArrayHelper<Data> dataList = EventManager.get(event.getClass());
      if (dataList != null) {
         Iterator var3 = dataList.iterator();

         while(var3.hasNext()) {
            Data data = (Data)var3.next();

            try {
               data.target.invoke(data.source, event);
            } catch (IllegalAccessException var6) {
               var6.printStackTrace();
            } catch (InvocationTargetException var7) {
               var7.printStackTrace();
            }
         }
      }

   }
}
