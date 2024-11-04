package Caesium.components;

import Caesium.Panel;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtil;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GuiToggleButton implements GuiComponent {
   private String text;
   private boolean toggled;
   private int posX;
   private int posY;
   private ArrayList<ActionListener> clickListeners = new ArrayList();

   public GuiToggleButton(String text) {
      this.text = text;
   }

   public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
      this.posX = posX;
      this.posY = posY;
      String var7 = Panel.theme;
      byte var8 = -1;
      switch(var7.hashCode()) {
      case -2087928939:
         if (var7.equals("Caesium")) {
            var8 = 0;
         }
      default:
         switch(var8) {
         case 0:
            this.renderCaesium(posX, posY);
         default:
         }
      }
   }

   private void renderCaesium(int posX, int posY) {
      RenderUtil.drawFilledCircle(posX + 8, posY + 7, 6.0F, new Color(ClickGUI.getColor()));
      if (!this.toggled) {
         RenderUtil.drawFilledCircle(posX + 8, posY + 7, 5.0F, new Color(Panel.grey40_240));
      }

      Panel.fR.drawStringWithShadow(this.text, (float)(posX + 17), (float)(posY + 3), Panel.fontColor);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      int width = Panel.fR.getStringWidth(this.text) + 10;
      if (Caesium.util.RenderUtil.isHovered(this.posX, this.posY + 2, width, this.getHeight(), mouseX, mouseY)) {
         this.toggled = !this.toggled;
         Iterator var5 = this.clickListeners.iterator();

         while(var5.hasNext()) {
            ActionListener listener = (ActionListener)var5.next();
            listener.actionPerformed(new ActionEvent(this, this.hashCode(), "click", System.currentTimeMillis(), 0));
         }
      }

   }

   public void keyTyped(int keyCode, char typedChar) {
   }

   public int getWidth() {
      return Panel.fR.getStringWidth(this.text) + 20;
   }

   public int getHeight() {
      return Panel.fR.FONT_HEIGHT + 5;
   }

   public boolean allowScroll() {
      return true;
   }

   public boolean isToggled() {
      return this.toggled;
   }

   public void setToggled(boolean toggled) {
      this.toggled = toggled;
   }

   public void addClickListener(ActionListener actionlistener) {
      this.clickListeners.add(actionlistener);
   }
}
