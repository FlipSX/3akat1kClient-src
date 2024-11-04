package SimpleGui.components;

import Caesium.ClickGui;
import Caesium.Panel;
import Caesium.components.GuiComponent;
import Caesium.components.GuiFrame;
import Caesium.components.listeners.ComponentListener;
import Caesium.util.RenderUtil;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.hud.ClickGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GuiButton implements SimpleGui {
   public static int expandedID = -1;
   private int id;
   private String text;
   private ArrayList<ActionListener> clickListeners = new ArrayList();
   private ArrayList<GuiComponent> guiComponents = new ArrayList();
   private int width;
   private int textWidth;
   private int posX;
   private int posY;

   public GuiButton(String text) {
      this.text = text;
      this.textWidth = Panel.fR.getStringWidth(text);
      this.id = ++ClickGui.compID;
   }

   public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
      this.posX = posX;
      this.posY = posY;
      this.width = width;
      int height = this.getHeight();
      this.renderCaesium(posX, posY, width, height);
   }

   private void renderCaesium(int posX, int posY, int width, int height) {
      RenderUtil.drawRect(posX, posY, posX + width - 1, posY + height, Panel.black100);
      int color = Panel.fontColor;
      BloodWare var10000 = BloodWare.instance;
      if (BloodWare.moduleManager.getModule(this.getText()).isToggled()) {
         color = ClickGUI.getColor();
      }

      Panel.fR.drawStringWithShadow(this.getText(), (float)(posX + width / 2 - Panel.fR.getStringWidth(this.getText()) / 2), (float)(posY + 2), color);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      Iterator var4;
      if (GuiFrame.dragID == -1 && RenderUtil.isHovered(this.posX, this.posY, this.width, this.getHeight(), mouseX, mouseY)) {
         if (mouseButton == 1) {
            expandedID = expandedID != this.id ? this.id : -1;
         } else if (mouseButton == 0) {
            var4 = this.clickListeners.iterator();

            while(var4.hasNext()) {
               ActionListener listener = (ActionListener)var4.next();
               listener.actionPerformed(new ActionEvent(this, this.id, "click", System.currentTimeMillis(), 0));
            }
         }
      }

      if (expandedID == this.id) {
         var4 = this.guiComponents.iterator();

         while(var4.hasNext()) {
            GuiComponent component = (GuiComponent)var4.next();
            component.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

   }

   public void keyTyped(int keyCode, char typedChar) {
      if (expandedID == this.id) {
         Iterator var3 = this.guiComponents.iterator();

         while(var3.hasNext()) {
            GuiComponent component = (GuiComponent)var3.next();
            component.keyTyped(keyCode, typedChar);
         }
      }

   }

   public int getWidth() {
      return 5 + this.textWidth;
   }

   public int getHeight() {
      return Panel.fR.FONT_HEIGHT + 3;
   }

   public boolean allowScroll() {
      return true;
   }

   public String getText() {
      return this.text;
   }

   public int getButtonID() {
      return this.id;
   }

   public ArrayList<GuiComponent> getComponents() {
      return this.guiComponents;
   }

   public void addClickListener(ActionListener actionlistener) {
      this.clickListeners.add(actionlistener);
   }

   public void addExtendListener(ComponentListener listener) {
      listener.addComponents();
      this.guiComponents.addAll(listener.getComponents());
   }
}
