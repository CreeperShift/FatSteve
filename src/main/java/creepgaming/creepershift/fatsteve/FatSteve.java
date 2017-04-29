package creepgaming.creepershift.fatsteve;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Logger;
import creepgaming.creepershift.fatsteve.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class FatSteve {

	@SidedProxy(clientSide = "creepgaming.creepershift.fatsteve.proxy.ClientProxy", serverSide = "creepgaming.creepershift.fatsteve.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Nonnull
	private static final FatSteve INSTANCE = new FatSteve();
	
	@Nonnull
	@Mod.InstanceFactory
	public static FatSteve instance(){
		return INSTANCE;
	}
	  public static Logger logger;
	  public static boolean externalWeight = true;
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preinit(event);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		proxy.postinit(event);
	}

}
