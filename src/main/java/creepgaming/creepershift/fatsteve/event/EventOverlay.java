package creepgaming.creepershift.fatsteve.event;

import creepgaming.creepershift.fatsteve.gui.WeightOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventOverlay {

	
	public EventOverlay(WeightOverlay hud){
		
		weightHud = hud;
		
	}
	
	private WeightOverlay weightHud;
	
	
	  @SubscribeEvent(receiveCanceled=true)
	  public void onEvent(RenderGameOverlayEvent.Post event) {
		    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
		    if (entityPlayerSP == null) return;
		    
		    
		    switch(event.getType()){
		    case FOOD:
		    	weightHud.renderStatusBar(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
		    	break;
		    	default:
		    	break;
		    }
		  
	  }
	
	
}
