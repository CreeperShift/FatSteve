package creepgaming.creepershift.fatsteve.proxy;

import java.io.File;
import creepgaming.creepershift.fatsteve.capability.CapabilityWeight;
import creepgaming.creepershift.fatsteve.config.Config;
import creepgaming.creepershift.fatsteve.config.ConfigInit;
import creepgaming.creepershift.fatsteve.enchantment.EnchantmentHandler;
import creepgaming.creepershift.fatsteve.event.EventHandler;
import creepgaming.creepershift.fatsteve.potion.PotionHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy {

	public static SimpleNetworkWrapper simpleNetworkWrapper;
	public static Configuration config;
	public static File blockFile;
	public static File directory;
	
	public static boolean ieLoaded = false;
	

	public void init(FMLInitializationEvent e) {
		CapabilityWeight.register();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		EnchantmentHandler.init();
	}

	public void preinit(FMLPreInitializationEvent e) {
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("FatSteveChannel");
		directory = e.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), "fatsteve.cfg"));
		Config.readConfig();
	}

	public void postinit(FMLPostInitializationEvent e) {
		PotionHandler.init();
		if (config.hasChanged()) {
			config.save();
		}
		ConfigInit.init();
		
		if(Loader.isModLoaded("immersiveengineering")){
			ieLoaded = true;
		}

	}

}
