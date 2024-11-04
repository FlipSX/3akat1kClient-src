package Caesium.components;

import Caesium.Panel;
import Caesium.components.listeners.KeyListener;
import Caesium.util.RenderUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;

public class GuiGetKey implements GuiComponent {
   private boolean wasChanged;
   private boolean allowChange;
   private int key;
   private int posX;
   private int posY;
   private int width;
   private String text;
   private ArrayList<KeyListener> keylisteners = new ArrayList();

   public GuiGetKey(String text, int key) {
      this.text = text;
      this.key = key;
      if (key < 0) {
         this.key = 0;
      }

   }

   public void addKeyListener(KeyListener listener) {
      this.keylisteners.add(listener);
   }

   public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
      this.posX = posX;
      this.posY = posY;
      this.width = width;
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
      String keyString = Keyboard.getKeyName(this.key);
      this.wasChanged = this.allowChange ? !this.wasChanged : true;
      Panel.fR.drawStringWithShadow(this.text + ":", (float)(posX + 3), (float)(posY + 3), Panel.fontColor);
      if (this.wasChanged) {
         Panel.fR.drawStringWithShadow(keyString, (float)(posX + this.width - Panel.fR.getStringWidth(keyString) - 3), (float)(posY + 3), Panel.fontColor);
      } else {
         Panel.fR.drawString(keyString, posX + this.width - Panel.fR.getStringWidth(keyString) - 3, posY + 3, Panel.fontColor);
      }

   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      String keyString = Keyboard.getKeyName(this.key);
      int w = Panel.fR.getStringWidth(this.text);
      this.allowChange = RenderUtil.isHovered(this.posX, this.posY, this.width, 11, mouseX, mouseY);
   }

   public void keyTyped(int keyCode, char typedChar) {
      if (this.allowChange) {
         Iterator var3 = this.keylisteners.iterator();

         while(var3.hasNext()) {
            KeyListener listener = (KeyListener)var3.next();
            listener.keyChanged(keyCode);
         }

         this.key = keyCode;
         this.allowChange = false;
      }

   }

   public int getWidth() {
      return Panel.fR.getStringWidth(this.text + Keyboard.getKeyName(this.key)) + 15;
   }

   public int getHeight() {
      return Panel.fR.FONT_HEIGHT + 4;
   }

   public boolean allowScroll() {
      return true;
   }
}
