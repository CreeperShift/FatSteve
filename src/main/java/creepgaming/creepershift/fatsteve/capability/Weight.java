package creepgaming.creepershift.fatsteve.capability;

import java.util.ArrayList;

import javax.annotation.Nullable;

import creepgaming.creepershift.fatsteve.api.IWeightCapability;
import creepgaming.creepershift.fatsteve.config.Config;
import creepgaming.creepershift.fatsteve.network.message.WeightMessage;
import creepgaming.creepershift.fatsteve.proxy.CommonProxy;
import creepgaming.creepershift.fatsteve.util.WeightUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class Weight implements IWeightCapability {

	public static ArrayList<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();

	/**
	 * The player this is attached to.
	 */
	private final EntityPlayer player;
	private int armorBonus;

	private int weight;

	private int maxWeightBase = Config.baseMaxWeight;
	private int bonusMaxWeight;
	private int maxWeight;

	public Weight(@Nullable EntityPlayer player) {
		this.player = player;
	}

	@Override
	public int getCurrentWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weightIn) {
		weight = weightIn;
		onWeightChange();

	}

	@Override
	public void setWeight() {
		if (player instanceof EntityPlayerMP) {

			int newWeight = 0;
			InventoryPlayer inv = player.inventory;

			if (inv != null) {

				ItemStack[] stack = inv.mainInventory;

				if (stack != null) {

					for (ItemStack item : stack) {

						if(item != null)
						newWeight += (WeightUtils.calculateWeight(item) * item.stackSize);

					}
				}
			}
			weight = newWeight;
			onWeightChange();
		}

	}

	@Override
	public void addWeight(int weightIn) {
		weight += weightIn;
		onWeightChange();

	}

	@Override
	public void removeWeight(int weightIn) {
		if (weight >= weightIn) {
			weight -= weightIn;
		} else {
			weight = 0;
		}
		onWeightChange();

	}

	@Override
	public int getMaxWeight() {
		calculateMaxWeight();
		return (maxWeight + bonusMaxWeight);
	}

	@Override
	public void setMaxWeight(int weightIn) {
		maxWeight = weightIn;
		onWeightChange();

	}

	/**
	 * When weight changes, we trigger all the changes from here.
	 * Sends packets to client
	 * Adds players to the effect array
	 * Removes players from the effect array
	 */
	
	protected void onWeightChange() {
		if (player == null)
			return;
		if (!player.getEntityWorld().isRemote) {
			WeightMessage message = new WeightMessage(weight, maxWeight);
			CommonProxy.simpleNetworkWrapper.sendTo(message, (EntityPlayerMP) player);
			if (weight >= maxWeight && !players.contains(player)) {
				players.add((EntityPlayerMP) player);

			} else if (players.contains(player)) {
				players.remove(player);
			}

		}

	}

	/**
	 * We add any bonusMaxWeight, and then recalculate maxWeight using maxBaseWeight and maxBonusWeight.
	 */
	@Override
	public void addBonusMaxWeight(int weightIn) {
			bonusMaxWeight += weightIn;
		calculateMaxWeight();
	}
	
	
	
	@Override
	public void removeBonusMaxWeight(int weightIn, boolean goNegative){
		if(goNegative)
			bonusMaxWeight -= weightIn;
		else
			bonusMaxWeight = (bonusMaxWeight < weightIn) ? 0 : (bonusMaxWeight - weightIn);
		calculateMaxWeight();
	}
	
	
	/**
	 * Calculates maxWeight from bonusMaxWeight and maxWeightBase.
	 * We do this so we don't accidentally fuck up maxWeight.
	 * Prevent it from going into negative maxWeight (Why would you want that?! )
	 */
	protected void calculateMaxWeight(){
		if(bonusMaxWeight <= 0 && bonusMaxWeight > maxWeightBase)
			maxWeight = 0 + armorBonus;
		else
			maxWeight = maxWeightBase + bonusMaxWeight+armorBonus;
		
		onWeightChange();
	}

	
	@Override
	public int getBonusMaxWeight() {
		return bonusMaxWeight;
	}

	@Override
	public int getArmorBonus() {
		return armorBonus;
	}

	@Override
	public void setArmorBonus(int weightIn) {

		armorBonus = weightIn; 
		calculateMaxWeight();
	}

}
