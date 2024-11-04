package Simple.components;

public interface GuiComponent {
   void render(int var1, int var2, int var3, int var4, int var5, int var6);

   void mouseClicked(int var1, int var2, int var3);

   void keyTyped(int var1, char var2);

   int getWidth();

   int getHeight();

   boolean allowScroll();
}
