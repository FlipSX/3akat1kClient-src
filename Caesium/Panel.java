package Caesium;

import Caesium.components.Frame;
import Caesium.components.GuiButton;
import Caesium.components.GuiFrame;
import Caesium.listeners.ClickListener;
import Caesium.listeners.ComponentsListener;
import Caesium.util.FramePosition;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.ModuleManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Panel extends ClickGui {
   public static HashMap<String, FramePosition> framePositions = new HashMap();
   public static FontRenderer fR;
   public static String theme;
   public static int FRAME_WIDTH;
   public static int color;
   public static int fontColor;
   public static int grey40_240;
   public static int black195;
   public static int black100;

   public Panel(String theme, int fontSize) {
      Panel.theme = theme;
   }

   public void ref(Category cat, int x) {
      GuiFrame frame = new GuiFrame(cat.name(), x, 50, true);
      BloodWare var10000 = BloodWare.instance;
      ModuleManager var7 = BloodWare.moduleManager;
      Iterator var4 = ModuleManager.modules.iterator();

      while(var4.hasNext()) {
         Module m = (Module)var4.next();
         if (cat == m.getCategory() && m.visible) {
            GuiButton button = new GuiButton(m.getName());
            button.addClickListener(new ClickListener(button));
            button.addExtendListener(new ComponentsListener(button));
            frame.addButton(button);
         }
      }

      this.addFrame(frame);
   }

   public void initGui() {
      int x = 25;
      this.ref(Category.COMBAT, x);
      int x = x + 135;
      this.ref(Category.MOVEMENT, x);
      x += 135;
      this.ref(Category.RENDER, x);
      x += 135;
      this.ref(Category.OUTHER, x);
      x += 135;
      this.ref(Category.HUD, x);
      super.initGui();
   }

   public void onGuiClosed() {
      if (!this.getFrames().isEmpty()) {
         Iterator var1 = this.getFrames().iterator();

         while(var1.hasNext()) {
            Frame frame = (Frame)var1.next();
            GuiFrame guiFrame = (GuiFrame)frame;
            framePositions.put(guiFrame.getTitle(), new FramePosition(guiFrame.getPosX(), guiFrame.getPosY(), guiFrame.isExpaned()));
         }
      }

   }

   static {
      fR = Minecraft.getMinecraft().fontRenderer;
      FRAME_WIDTH = 100;
      color = ClickGUI.getColor();
      fontColor = Color.white.getRGB();
      grey40_240 = (new Color(40, 40, 40, 140)).getRGB();
      black195 = (new Color(0, 0, 0, 195)).getRGB();
      black100 = (new Color(0, 0, 0, 100)).getRGB();
   }
}
