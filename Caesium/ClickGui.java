package Caesium;

import Caesium.components.Frame;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtil;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public class ClickGui extends GuiScreen {
   public static int compID = 0;
   private ArrayList<Frame> frames = new ArrayList();
   private final FontRenderer fr;

   public ClickGui() {
      this.fr = Minecraft.getMinecraft().fontRenderer;
      compID = 0;
   }

   protected void addFrame(Frame frame) {
      if (!this.frames.contains(frame)) {
         this.frames.add(frame);
      }

   }

   protected ArrayList<Frame> getFrames() {
      return this.frames;
   }

   public void initGui() {
      Iterator var1 = this.frames.iterator();

      while(var1.hasNext()) {
         Frame frame = (Frame)var1.next();
         frame.initialize();
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      Iterator var4 = this.frames.iterator();

      while(var4.hasNext()) {
         Frame frame = (Frame)var4.next();
         frame.mouseClicked(mouseX, mouseY, mouseButton);
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      Iterator var3 = this.frames.iterator();

      while(var3.hasNext()) {
         Frame frame = (Frame)var3.next();
         frame.keyTyped(keyCode, typedChar);
      }

   }

   public void drawinfo(int mouseX, int mouseY) {
      int xpos = 600;
      int ypos = 280;
      int g = 0;
      this.fontRenderer.drawStringWithShadow("Friends", (float)(xpos + this.fontRenderer.getStringWidth("Friends") / 2), (float)(ypos - this.fontRenderer.FONT_HEIGHT), ClickGUI.getColor());
      RenderUtil.drawRect((double)xpos, (double)ypos, (double)(xpos + 100), (double)(ypos + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), (new Color(50, 50, 50, 190)).getRGB());
      RenderUtil.drawNewRect((double)(xpos - 1), (double)ypos, (double)xpos, (double)(ypos + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), ClickGUI.getColor());
      RenderUtil.drawNewRect((double)(xpos + 100 - 1), (double)ypos, (double)(xpos + 100), (double)(ypos + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), ClickGUI.getColor());
      RenderUtil.drawGlow((double)xpos, (double)(ypos - 1), (double)(xpos + 100), (double)ypos, ClickGUI.getColor(), 250.0D);
      RenderUtil.drawGlow((double)xpos, (double)(ypos + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT - 1), (double)(xpos + 100), (double)(ypos + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), ClickGUI.getColor(), 250.0D);

      for(Iterator var6 = FriendManager.FRIENDS.iterator(); var6.hasNext(); g += this.fontRenderer.FONT_HEIGHT) {
         String friends = (String)var6.next();
         int butX1 = 0 + xpos;
         int butX2 = 100 + xpos;
         int butY1 = g + ypos;
         int butY2 = g + this.fontRenderer.FONT_HEIGHT + ypos;
         if (mouseX > butX1 && mouseX < butX2 && mouseY > butY1 && mouseY < butY2) {
            this.fontRenderer.drawString(friends, xpos + 50 - this.fontRenderer.getStringWidth(friends) / 2, g + ypos, ClickGUI.getColor());
            if (Mouse.isButtonDown(1)) {
               FriendManager.toggleFriend(friends);
               return;
            }
         } else {
            this.fontRenderer.drawString(friends, xpos + 50 - this.fontRenderer.getStringWidth(friends) / 2, g + ypos, Color.white.getRGB());
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      new ScaledResolution(this.mc);
      Gui.drawRect(64, 556, 0, 524, (new Color(37, 37, 37, 255)).getRGB());
      Gui.drawRect(0, 525, 64, 527, ClickGUI.getColor());
      this.mc.fontRenderer.drawString("By3akat1k", 4, 529, Panel.fontColor);
      Iterator var5 = this.frames.iterator();

      while(var5.hasNext()) {
         Frame frame = (Frame)var5.next();
         frame.render(mouseX, mouseY);
         this.drawinfo(mouseX, mouseY);
      }

   }
}
