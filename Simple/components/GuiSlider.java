package Simple.components;

import Caesium.Panel;
import Caesium.components.listeners.ValueListener;
import Caesium.util.MathUtil;
import Caesium.util.RenderUtil;
import com.Blood.Ware.module.hud.ClickGUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class GuiSlider implements GuiComponent {
   private static int dragId = -1;
   private int round;
   private int id;
   private int width;
   private int posX;
   private int posY;
   private float min;
   private float max;
   private float current;
   private double c;
   private boolean wasSliding;
   private boolean hovered;
   private String text;
   private ArrayList<ValueListener> valueListeners = new ArrayList();

   public GuiSlider(String text, float min, float max, float current, int round) {
      this.text = text;
      this.min = min;
      this.max = max;
      this.current = current;
      this.c = (double)(current / max);
      this.round = round;
      this.id = ++Panel.compID;
   }

   public void addValueListener(ValueListener vallistener) {
      this.valueListeners.add(vallistener);
   }

   public static float clamp(float val, float min, float max) {
      return Math.max(min, Math.min(max, val));
   }

   public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
      this.posX = posX;
      this.posY = posY;
      this.width = width;
      this.hovered = RenderUtil.isHovered(posX, posY, width, this.getHeight(), mouseX, mouseY);
      double diff;
      double percent;
      if (this.hovered && wheelY != 0) {
         diff = this.min < 0.0F ? (double)Math.abs(this.min - this.max) : (double)(this.max - this.min);
         percent = (double)(wheelY / 15);
         this.current = this.round == 0 ? (float)((int)clamp((float)((double)this.current + percent), this.min, this.max)) : clamp((float)((double)this.current + percent * (diff / 100.0D)), this.min, this.max);
      }

      ValueListener listener;
      Iterator var13;
      if (Mouse.isButtonDown(0) && (dragId == this.id || dragId == -1) && this.hovered) {
         if (mouseX < posX + 2) {
            this.current = this.min;
         } else if (mouseX > posX + width) {
            this.current = this.max;
         } else {
            diff = (double)(this.max - this.min);
            percent = (double)this.min + (double)clamp((float)((double)(mouseX - posX + 3) / (double)width), 0.0F, 1.0F) * diff;
            this.current = this.round == 0 ? (float)((int)percent) : (float)percent;
         }

         dragId = this.id;
         var13 = this.valueListeners.iterator();

         while(var13.hasNext()) {
            listener = (ValueListener)var13.next();
            listener.valueUpdated(this.current);
         }

         this.wasSliding = true;
      } else if (!Mouse.isButtonDown(0) && this.wasSliding) {
         var13 = this.valueListeners.iterator();

         while(var13.hasNext()) {
            listener = (ValueListener)var13.next();
            listener.valueChanged(this.current);
         }

         dragId = -1;
         this.wasSliding = false;
      }

      percent = (double)((this.current - this.min) / (this.max - this.min));
      String var11 = Panel.theme;
      byte var12 = -1;
      switch(var11.hashCode()) {
      case -2087928939:
         if (var11.equals("Caesium")) {
            var12 = 0;
         }
      default:
         switch(var12) {
         case 0:
            this.renderCaesium(percent);
         default:
         }
      }
   }

   private void renderCaesium(double percent) {
      String value = this.round == 0 ? "" + Math.round(this.current) : "" + MathUtil.round(this.current, this.round);
      Color color = new Color(ClickGUI.getColor());
      Panel.fR.drawStringWithShadow(this.text + ":", (float)(this.posX + 3), (float)(this.posY + 3), Panel.fontColor);
      Panel.fR.drawStringWithShadow(value, (float)(this.posX + this.width - Panel.fR.getStringWidth(value) - 3), (float)(this.posY + 3), Panel.fontColor);
      Gui.drawRect(this.posX, this.posY + Panel.fR.FONT_HEIGHT + 3, this.posX + this.width - 2, this.posY + Panel.fR.FONT_HEIGHT + 5, Color.black.getRGB());
      com.Blood.Ware.utils.RenderUtil.drawVGradientRect((float)this.posX, (float)(this.posY + Panel.fR.FONT_HEIGHT + 3), (float)((int)(percent * (double)this.width - 2.0D) + this.posX), (float)(this.posY + Panel.fR.FONT_HEIGHT + 5), color.darker().getRGB(), color.brighter().getRGB());
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
   }

   public void keyTyped(int keyCode, char typedChar) {
   }

   public int getWidth() {
      return Panel.fR.getStringWidth(this.text + (this.round == 0 ? (float)Math.round(this.current) : MathUtil.round(this.current, this.round))) + 68;
   }

   public int getHeight() {
      return Panel.fR.FONT_HEIGHT + 6;
   }

   public boolean allowScroll() {
      return !this.hovered;
   }
}
