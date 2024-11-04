package com.Blood.Ware.asm;

import java.util.Map;
import javax.annotation.Nullable;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@MCVersion("1.12.2")
@TransformerExclusions({"com.Blood.Ware.asm"})
public class internaliCoreMod implements IFMLLoadingPlugin {
   public String[] getASMTransformerClass() {
      return new String[]{"com.Blood.Ware.asm.InternaliClassTransformer"};
   }

   public String getModContainerClass() {
      return null;
   }

   @Nullable
   public String getSetupClass() {
      return null;
   }

   public void injectData(Map<String, Object> data) {
   }

   public String getAccessTransformerClass() {
      return null;
   }
}
