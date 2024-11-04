import com.Blood.Ware.BloodWare;
import com.Blood.Ware.Menu.onGuiOpenEvent;
import com.Blood.Ware.miscs.Discord;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(
   modid = "optifune",
   version = "0.1",
   name = "3akati1k"
)
public class Main {
   private Logger logger;

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      Display.setTitle("Loading " + BloodWare.name);
      this.logger = event.getModLog();
   }

   @EventHandler
   public void init(FMLInitializationEvent event) throws Exception {
      Discord.startRPC();
      BloodWare.startup();
      MinecraftForge.EVENT_BUS.register(new onGuiOpenEvent());
      BloodWare.instance = new BloodWare();
      BloodWare.instance.init();
   }
}
