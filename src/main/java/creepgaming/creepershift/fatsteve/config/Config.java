package creepgaming.creepershift.fatsteve.config;

import org.apache.logging.log4j.Level;
import creepgaming.creepershift.fatsteve.FatSteve;
import creepgaming.creepershift.fatsteve.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

public class Config {

	public static int blockWeight, itemWeight, baseMaxWeight, liquidWeight;
	public static boolean cyberware, byMaterial;
	
	  public static void readConfig()
	  {
	    Configuration cfg = CommonProxy.config;
	    try
	    {
	      cfg.load();
	      initGeneralConfig(cfg);
	    }
	    catch (Exception e1)
	    {
	      FatSteve.logger.log(Level.ERROR, "Problem loading config file!", e1);
	    }
	    finally
	    {
	      if (cfg.hasChanged()) {
	        cfg.save();
	      }
	    }
	  }
	  
	  
	  private static void initGeneralConfig(Configuration cfg)
	  {
	    cfg.addCustomCategoryComment("general", "General Config");
	    blockWeight = cfg.getInt("blockWeight", "General Config", 10, 0, Integer.MAX_VALUE, "Whats the weight of a regular block?");
	    itemWeight = cfg.getInt("itemWeight", "General Config", 1, 0, Integer.MAX_VALUE, "Whats the weight of a regular item?");
	    baseMaxWeight = cfg.getInt("baseMaxWeight", "General Config", 12000, 0, Integer.MAX_VALUE, "Base value for max carryweight before being encumbered. This is before being modified by armor, enchantments and other things.");
	    liquidWeight = cfg.getInt("liquidWeight", "General Config", 100, 0, Integer.MAX_VALUE, "Base weight for liquid in 1000mb (1 bucket)");
	    byMaterial = cfg.getBoolean("byMaterial", "General Config", true, "Do we use a blocks Material to determine its weight? Excludes blocks in the custom weight list");
	    cyberware = cfg.getBoolean("cyberware", "General Config", true, "Should the cyberware module be loaded? Does nothing if cyberware isn't installed.");
	    
	  }
}
