package creepgaming.creepershift.fatsteve.network;

import creepgaming.creepershift.fatsteve.gui.WeightOverlay;
import creepgaming.creepershift.fatsteve.network.message.WeightMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientMessageHandler implements IMessageHandler<WeightMessage, IMessage>{

	
	
	  public IMessage onMessage(final WeightMessage message, MessageContext ctx) {
		    if (ctx.side != Side.CLIENT) {
		      System.err.println("TargetEffectMessageToClient received on wrong side:" + ctx.side);
		      return null;
		    }
		    if (!message.isMessageValid()) {
		      System.err.println("TargetEffectMessageToClient was invalid" + message.toString());
		      return null;
		    }

		    // we know for sure that this handler is only used on the client side, so it is ok to assume
		    //  that the ctx handler is a client, and that Minecraft exists.
		    // Packets received on the server side must be handled differently!  See MessageHandlerOnServer

		    // This code creates a new task which will be executed by the client during the next tick,
		    //  for example see Minecraft.runGameLoop() , just under section
		    //    this.mcProfiler.startSection("scheduledExecutables");
		    //  In this case, the task is to call messageHandlerOnClient.processMessage(worldclient, message)
		    Minecraft minecraft = Minecraft.getMinecraft();
		    final WorldClient worldClient = minecraft.theWorld;
		    if(worldClient != null){
		    minecraft.addScheduledTask(() -> processMessage(worldClient, message));
		    }
		    return null;
		  }

	  
	  // This message is called from the Client thread.
	  //   It updates the weight of the players inventory
	  void processMessage(WorldClient worldClient, WeightMessage message)
	  {

		  WeightOverlay.weight = message.getWeight();
		  WeightOverlay.maxWeight = message.getMaxWeight();
		  return;
	  }
	  
	  
}
