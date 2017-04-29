package creepgaming.creepershift.fatsteve.proxy;

import creepgaming.creepershift.fatsteve.event.EventOverlay;
import creepgaming.creepershift.fatsteve.gui.WeightOverlay;
import creepgaming.creepershift.fatsteve.network.ClientMessageHandler;
import creepgaming.creepershift.fatsteve.network.message.WeightMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	@Override
	public void preinit(FMLPreInitializationEvent e) {
		super.preinit(e);
		
		CommonProxy.simpleNetworkWrapper.registerMessage(ClientMessageHandler.class, WeightMessage.class,
                0, Side.CLIENT);
		
	}

	private static WeightOverlay weightOverlay;
	
	@Override
	public void postinit(FMLPostInitializationEvent e) {
		super.postinit(e);
		weightOverlay = new WeightOverlay(Minecraft.getMinecraft());
		MinecraftForge.EVENT_BUS.register(new EventOverlay(weightOverlay));
	}

	
}
