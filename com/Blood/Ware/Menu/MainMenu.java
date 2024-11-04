package com.Blood.Ware.Menu;

import com.Blood.Ware.Menu.Tools.changeUser;
import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen {
   private static final ResourceLocation texture = new ResourceLocation("texture.png");

   public void initGui() {
      int i = this.height / 4 + 48;
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, i + 72 + 12, 98, 20, "Options"));
      this.buttonList.add(new GuiButton(1, this.width / 2 + 2, i + 72 + 12, 98, 20, "Quit"));
      this.buttonList.add(new GuiButton(2, this.width / 2 + -100, i + 72 - 12, 200, 20, "Change User"));
      this.buttonList.add(new GuiButton(4, this.width / 2 - 100, i + 72 - 34, 200, 20, "Multiplayer"));
      this.buttonList.add(new GuiButton(5, this.width / 2 - 100, i + 72 - 58, 200, 20, "Singleplayer"));
   }

   protected void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
      }

      if (button.id == 1) {
         this.mc.shutdown();
      }

      if (button.id == 2) {
         this.mc.displayGuiScreen(new changeUser());
      }

      if (button.id == 4) {
         this.mc.displayGuiScreen(new GuiMultiplayer(this));
      }

      if (button.id == 5) {
         this.mc.displayGuiScreen(new GuiWorldSelection(this));
      }

      super.actionPerformed(button);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawDefaultBackground();
      this.mc.renderEngine.bindTexture(texture);
      Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, this.width, this.height, this.width, this.height, (float)this.width, (float)this.height);
      drawLogo.drawString(5.0D, "3akat1kClient", (float)(this.width / 10 - this.fontRenderer.getStringWidth("3akat1kClient") / 2), (float)(this.height / 20), (new Color(3183152)).getRGB());
      Iterator var4 = this.buttonList.iterator();

      while(var4.hasNext()) {
         GuiButton guiButton = (GuiButton)var4.next();
         guiButton.drawButton(this.mc, mouseX, mouseY, partialTicks);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
