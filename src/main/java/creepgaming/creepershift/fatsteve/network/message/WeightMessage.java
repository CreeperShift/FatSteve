package creepgaming.creepershift.fatsteve.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class WeightMessage implements IMessage{

	
	private int weight;
	private int maxWeight;
	private boolean messageIsValid;
	
	public WeightMessage(int weight, int maxWeight){
		this.weight = weight;
		this.maxWeight = maxWeight;
		messageIsValid = true;
	}
	
	public boolean isMessageValid(){
		return messageIsValid;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getMaxWeight(){
		return maxWeight;
	}
	
	public WeightMessage(){
		messageIsValid = false;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			weight = buf.readInt();
			maxWeight = buf.readInt();
	}catch(IndexOutOfBoundsException e){
		System.err.println("Error reading weight message: " + e);
		return;
	}
		messageIsValid=true;
		
	}


	@Override
	public void toBytes(ByteBuf buf) {

		if(!messageIsValid) return;
		buf.writeInt(weight);
		buf.writeInt(maxWeight);
		
		
	}
	
	
	
	
	
	
	
	
}
