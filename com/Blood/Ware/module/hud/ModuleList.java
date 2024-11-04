package com.Blood.Ware.module.hud;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.ModuleManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.MathUtils;
import com.Blood.Ware.utils.RenderUtils;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ModuleList extends Module {
   ArrayList<ModuleList.SubModule> modules = new ArrayList();

   public ModuleList() {
      super("ArrayList", Category.HUD);
   }

   public void onDisable() {
      super.onDisable();
   }

   @SubscribeEvent
   public void onOverlayRender(Text text) {
      int x = text.getResolution().getScaledWidth() - 15;
      int y = true;
      Iterator var4 = this.modules.iterator();

      while(true) {
         while(var4.hasNext()) {
            ModuleList.SubModule module = (ModuleList.SubModule)var4.next();
            if (module.getModule().isToggled() && module.getModule().visible) {
               module.setAnim(MathUtils.lerp(module.getAnim(), 1.0F, 0.1F));
               module.setY((int)MathUtils.lerp((float)module.getY(), (float)(CastomFontUtils.fr4.getHeight() + 10), 0.1F));
            } else {
               module.setAnim(MathUtils.lerp(module.getAnim(), 0.0F, 0.1F));
               module.setY((int)MathUtils.lerp((float)module.getY(), 0.0F, 0.1F));
            }
         }

         this.modules.sort(new Comparator<ModuleList.SubModule>() {
            public int compare(ModuleList.SubModule o1, ModuleList.SubModule o2) {
               return CastomFontUtils.fr4.getStringWidth(o2.getModule().getName()) - CastomFontUtils.fr4.getStringWidth(o1.getModule().getName());
            }
         });
         int i = 0;

         ModuleList.SubModule module2;
         for(Iterator var12 = this.modules.iterator(); var12.hasNext(); i += module2.getY()) {
            module2 = (ModuleList.SubModule)var12.next();
            GL11.glPushMatrix();
            float oXpos = (float)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName()));
            float oYpos = (float)(22 + i);
            float oWidth = (float)CastomFontUtils.fr4.getStringWidth(module2.getModule().getName());
            float oHeight = 10.0F;
            GL11.glTranslated((double)oWidth, 10.0D, 1.0D);
            GL11.glTranslated((double)(-oXpos * module2.getAnim() + oXpos + oWidth * -module2.getAnim()), (double)(-oYpos * 1.0F + oYpos + -10.0F), 1.0D);
            GL11.glScaled((double)module2.getAnim(), 1.0D, 0.0D);
            RenderUtils.drawShadowRect((double)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName())), (double)(22 + i), (double)x, (double)(22 + i + 10), 2);
            RenderUtils.drawRect((float)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName())), (float)(22 + i), (float)x, (float)(22 + i + 9), (new Color(30, 30, 30, 200)).getRGB());
            CastomFontUtils.fr4.drawString(module2.getModule().getName(), (float)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName())), (float)(22 + i), ClickGUI.getColor());
            GL11.glPopMatrix();
         }

         return;
      }
   }

   public void onEnable() {
      super.onEnable();
      int x = true;
      this.modules.clear();
      ModuleManager var10000 = BloodWare.moduleManager;
      Iterator var2 = ModuleManager.modules.iterator();

      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         this.modules.add(new ModuleList.SubModule(module));
      }

   }

   static class SubModule {
      Module module;
      int y = 0;
      float anim = 0.0F;

      public SubModule(Module module) {
         this.module = module;
      }

      public float getAnim() {
         return this.anim;
      }

      public void setAnim(float anim) {
         this.anim = anim;
      }

      public Module getModule() {
         return this.module;
      }

      public int getY() {
         return this.y;
      }

      public void setY(int y) {
         this.y = y;
      }
   }
}
