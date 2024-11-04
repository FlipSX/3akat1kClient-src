package Bobr;

import Bobr.buttons.CSCategoryButton;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class BobrGui extends GuiScreen {
   private float curAlpha;
   private float anim;
   public ArrayList<CSCategoryButton> buttons = new ArrayList();
   public CSCategoryButton currentCategory;
   public int x = 100;
   public int y = 100;
   public int width;
   public int height;
   public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

   public BobrGui() {
      this.width = this.sr.getScaledWidth() - 100;
      this.height = this.sr.getScaledHeight() - 100;
      this.x = 100;
      this.y = 100;
      this.width = this.sr.getScaledWidth() - 100;
      this.height = this.sr.getScaledHeight() - 100;
   }

   private void initButtons() {
      this.buttons.clear();
      int x = 110;
      int y = 110;
      Category[] var3 = Category.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Category c = var3[var5];
         String categoryname = c.name().substring(0, 1).toUpperCase() + c.name().substring(1, c.name().length()).toLowerCase();
         CSCategoryButton cscb = new CSCategoryButton(x, y, this.mc.fontRenderer.getStringWidth(categoryname), this.mc.fontRenderer.FONT_HEIGHT, -1, categoryname, c);
         this.buttons.add(cscb);
         y += 15;
      }

   }

   public ScaledResolution getScaledRes() {
      return new ScaledResolution(Minecraft.getMinecraft());
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      --this.anim;
      float alpha = 150.0F;
      int step = (int)(alpha / 100.0F);
      if (this.curAlpha < alpha - (float)step) {
         this.curAlpha += (float)step;
      } else if (this.curAlpha > alpha - (float)step && this.curAlpha != alpha) {
         this.curAlpha = (float)((int)alpha);
      } else if (this.curAlpha != alpha) {
         this.curAlpha = (float)((int)alpha);
      }

      new Color(0, 0, 0, 0);
      this.func_73733_a(0, 0, this.getScaledRes().getScaledWidth(), this.getScaledRes().getScaledHeight(), (new Color(0, 0, 0, 0)).getRGB(), ClickGUI.getColor());
      RenderUtils.drawImage(new ResourceLocation("kuriyama.png"), (float)this.sr.getScaledWidth() + 200.0F, (float)this.sr.getScaledHeight() - 75.0F, 345.0F, 380.0F);
      RenderUtil.drawNewRect((double)this.x, (double)this.height, (double)this.width, (double)this.height + 0.5D, ClickGUI.getColor());
      RenderUtil.drawNewRect((double)this.width, (double)(this.y - this.fontRenderer.FONT_HEIGHT * 2), (double)this.width + 0.5D, (double)this.height, ClickGUI.getColor());
      Gui.drawRect(this.x, this.y - this.fontRenderer.FONT_HEIGHT * 2, this.width, this.height, (new Color(50, 50, 50, 190)).getRGB());
      Iterator var7 = this.buttons.iterator();

      while(var7.hasNext()) {
         CSCategoryButton cb = (CSCategoryButton)var7.next();
         cb.drawScreen(mouseX, mouseY, partialTicks);
      }

      Gui.drawRect(this.x + 65, this.y, this.x + 67, this.height, ClickGUI.getColor());
      Gui.drawRect(this.x + 165, this.y, this.x + 167, this.height, ClickGUI.getColor());
      this.drawinfo(mouseX, mouseY);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      Iterator var3 = this.buttons.iterator();

      while(var3.hasNext()) {
         CSCategoryButton cb = (CSCategoryButton)var3.next();
         cb.keyTyped(typedChar, keyCode);
      }

      super.keyTyped(typedChar, keyCode);
   }

   public void drawinfo(int mouseX, int mouseY) {
      int xpos = 600;
      int ypos = 100;
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

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      boolean anyhovered = true;

      CSCategoryButton cb;
      for(Iterator var5 = this.buttons.iterator(); var5.hasNext(); cb.mouseClicked(mouseX, mouseY, mouseButton)) {
         cb = (CSCategoryButton)var5.next();
         if (cb.isHovered(mouseX, mouseY) && mouseButton == 0) {
            anyhovered = true;
            this.currentCategory = cb;
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      Iterator var4 = this.buttons.iterator();

      while(var4.hasNext()) {
         CSCategoryButton cb = (CSCategoryButton)var4.next();
         cb.mouseReleased(mouseX, mouseY, state);
      }

      super.mouseReleased(mouseX, mouseY, state);
   }

   public void initGui() {
      this.initButtons();
      this.anim = 0.0F;
      this.x = 100;
      this.y = 100;
      this.width = 500;
      this.height = 350;
      Iterator var1 = this.buttons.iterator();

      while(var1.hasNext()) {
         CSCategoryButton cb = (CSCategoryButton)var1.next();
         cb.initButton();
      }

      super.initGui();
   }
}
