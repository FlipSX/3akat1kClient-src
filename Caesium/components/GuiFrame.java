package Caesium.components;

import Caesium.ClickGui;
import Caesium.Panel;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.input.Mouse;

public class GuiFrame implements Frame {
   private ArrayList<GuiButton> buttons = new ArrayList();
   private boolean isExpaned;
   private boolean isDragging;
   private int id;
   private int posX;
   private int posY;
   private int prevPosX;
   private int prevPosY;
   private int scrollHeight;
   public static int dragID;
   private String title;

   public GuiFrame(String title, int posX, int posY, boolean expanded) {
      this.title = title;
      this.posX = posX;
      this.posY = posY;
      this.isExpaned = expanded;
      this.id = ++ClickGui.compID;
      this.scrollHeight = 0;
   }

   public void render(int mouseX, int mouseY) {
      String var3 = Panel.theme;
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -2087928939:
         if (var3.equals("Caesium")) {
            var4 = 0;
         }
      default:
         switch(var4) {
         case 0:
            this.renderCaesium(mouseX, mouseY);
         default:
         }
      }
   }

   private void renderCaesium(int mouseX, int mouseY) {
      int color = ClickGUI.getColor();
      int fontColor = Panel.fontColor;
      int width = Math.max(Panel.FRAME_WIDTH, Panel.fR.getStringWidth(this.title) + 15);
      if (this.isDragging && Mouse.isButtonDown(0)) {
         this.posX = mouseX - this.prevPosX;
         this.posY = mouseY - this.prevPosY;
         dragID = this.id;
      } else {
         this.isDragging = false;
         dragID = -1;
      }

      GuiButton button;
      for(Iterator var6 = this.buttons.iterator(); var6.hasNext(); width = Math.max(width, button.getWidth() + 15)) {
         button = (GuiButton)var6.next();
      }

      RenderUtil.drawVGradientRect((float)(this.posX + 1), (float)(this.posY - 5), (float)(this.posX + width), (float)(this.posY + 12), ClickGUI.getColor(), ClickGUI.getColor2());
      Panel.fR.drawStringWithShadow(this.title, (float)(this.posX + width / 2 - Panel.fR.getStringWidth(this.title) / 2), (float)this.posY, fontColor);
      Panel.fR.drawStringWithShadow(this.isExpaned ? "-" : "+", (float)(this.posX + width - Panel.fR.getStringWidth(this.isExpaned ? "-" : "+") - 4), (float)this.posY, fontColor);
      if (this.isExpaned) {
         int height = 0;
         int background = Panel.grey40_240;

         GuiButton button;
         for(Iterator var8 = this.buttons.iterator(); var8.hasNext(); height += button.getHeight()) {
            button = (GuiButton)var8.next();
            button.render(this.posX + 1, this.posY + height + 12, width, mouseX, mouseY, 0);
            ArrayList components;
            if (button.getButtonID() == GuiButton.expandedID && !(components = button.getComponents()).isEmpty()) {
               int xOffset = 10;
               int yOffset = 0;
               boolean allowScroll = true;
               Iterator var14 = components.iterator();

               while(var14.hasNext()) {
                  GuiComponent component = (GuiComponent)var14.next();
                  xOffset = Math.max(xOffset, component.getWidth());
                  yOffset += component.getHeight();
                  if (!component.allowScroll()) {
                     allowScroll = false;
                  }
               }

               int left = this.posX + width + 2;
               int right = left + xOffset;
               int top = this.posY + height + 12;
               int bottom = top + yOffset + 1;
               int wheelY = Mouse.getDWheel() * -1 / 8;
               if (bottom + this.scrollHeight < 30) {
                  wheelY *= -1;
                  this.scrollHeight += 10;
               }

               if (allowScroll) {
                  this.scrollHeight += wheelY;
               }

               Caesium.util.RenderUtil.drawRect(left + 1, top + 1 + this.scrollHeight, right, bottom + this.scrollHeight, Panel.black100);
               int height2 = 0;

               GuiComponent component;
               for(Iterator var20 = components.iterator(); var20.hasNext(); height2 += component.getHeight()) {
                  component = (GuiComponent)var20.next();
                  component.render(left, top + height2 + 2 + this.scrollHeight, xOffset, mouseX, mouseY, wheelY);
               }

               Caesium.util.RenderUtil.drawVerticalLine(left, top + this.scrollHeight, bottom + this.scrollHeight, color);
               Caesium.util.RenderUtil.drawVerticalLine(right, top + this.scrollHeight, bottom + this.scrollHeight, color);
               Caesium.util.RenderUtil.drawHorizontalLine(left, right, top + this.scrollHeight, color);
               Caesium.util.RenderUtil.drawHorizontalLine(left, right, bottom + this.scrollHeight, color);
            }
         }

         Caesium.util.RenderUtil.drawHorizontalLine(this.posX + 1, this.posX + width - 1, this.posY + height + 12, color);
         Caesium.util.RenderUtil.drawVerticalLine(this.posX + width, this.posY - 5, this.posY + height + 14, Panel.black100);
         Caesium.util.RenderUtil.drawVerticalLine(this.posX + width, this.posY - 4, this.posY + height + 14, Panel.black100);
         Caesium.util.RenderUtil.drawVerticalLine(this.posX + width + 1, this.posY - 4, this.posY + height + 15, Panel.black100);
         Caesium.util.RenderUtil.drawHorizontalLine(this.posX + 2, this.posX + width - 1, this.posY + height + 13, Panel.black100);
         Caesium.util.RenderUtil.drawHorizontalLine(this.posX + 2, this.posX + width - 1, this.posY + height + 13, Panel.black100);
         Caesium.util.RenderUtil.drawHorizontalLine(this.posX + 3, this.posX + width, this.posY + height + 14, Panel.black100);
      }

   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      int width = Panel.FRAME_WIDTH;
      if (this.isExpaned) {
         Iterator var5 = this.buttons.iterator();

         while(var5.hasNext()) {
            GuiButton button = (GuiButton)var5.next();
            width = Math.max(width, button.getWidth());
            button.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      if (Caesium.util.RenderUtil.isHovered(this.posX, this.posY, width, 13, mouseX, mouseY)) {
         if (mouseButton == 0) {
            this.prevPosX = mouseX - this.posX;
            this.prevPosY = mouseY - this.posY;
            this.isDragging = true;
            dragID = this.id;
         } else if (mouseButton == 1) {
            this.isExpaned = !this.isExpaned;
            this.scrollHeight = 0;
            this.isDragging = false;
            dragID = -1;
         }
      }

   }

   public void keyTyped(int keyCode, char typedChar) {
      if (this.isExpaned) {
         Iterator var3 = this.buttons.iterator();

         while(var3.hasNext()) {
            GuiButton button = (GuiButton)var3.next();
            button.keyTyped(keyCode, typedChar);
         }
      }

   }

   public void initialize() {
   }

   public void addButton(GuiButton button) {
      if (!this.buttons.contains(button)) {
         this.buttons.add(button);
      }

   }

   public int getButtonID() {
      return this.id;
   }

   public boolean isExpaned() {
      return this.isExpaned;
   }

   public int getPosX() {
      return this.posX;
   }

   public int getPosY() {
      return this.posY;
   }

   public String getTitle() {
      return this.title;
   }
}
