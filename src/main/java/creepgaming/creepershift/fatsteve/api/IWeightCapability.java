package creepgaming.creepershift.fatsteve.api;


/**
 * 
 * A capability to store inventory weight of a player.
 * 
 * @author CreeperShift
 * 
 *
 */


public interface IWeightCapability {

	/**
	 * The max Carryweight
	 * @return max Carryweight
	 */
	int getMaxWeight();
	
	
	/**
	 * Sets the max Carryweight
	 * @param new max Carryweight
	 * WARNING: SETTING THE MAX WEIGHT CAN CAUSE ISSUES WITH OTHER MODS/ITEMS/BLOCKS THAT ADD/SUBTRACT MAXWEIGHT
	 * IT IS MUCH BETTER AND SAFER TO USE addBonusMaxWeight();
	 */
	void setMaxWeight(int weightIn);
	
	
	/**
	 * Get current inventory weight
	 * @return The current inventory weight.
	 */
	int getCurrentWeight();
	
	/**
	 * Sets inventory weight
	 * @param weight The inventory weight
	 */
	void setWeight(int weightIn);
	
	
	/**
	 * Adds to inventory weight
	 * @param weight The inventory weight to add
	 */
	void addWeight(int weightIn);
	
	/**
	 * Removes inventory weight
	 * @param weight The inventory weight to remove
	 */
	void removeWeight(int weightIn);


	/**
	 * Queues a recalculation of the entire inventory.
	 * Only used in case we cannot use the more performant methods above.
	 * No params are needed as the recalculations happen with the current player entity.
	 */
	void setWeight();


	/**
	 * Adds bonus maxWeight.
	 * maxWeight is calculated from bonusMaxWeight and baseMaxWeight.
	 * @param weightIn weight to add
	 */
	void addBonusMaxWeight(int weightIn);
	
	
	/**
	 * Removes bonus maxWeight.
	 * maxWeight is calculated from bonusMaxWeight and baseMaxWeight.
	 * @param weightIn weight to remove
	 * @param goNegative Should we go lower than 0 bonusWeight? Useful if we're just removing bonus carryWeight without trying to lower it beyond the base.
	 */
	void removeBonusMaxWeight(int weightIn, boolean goNegative);
	
	/**
	 * Returns the current bonusMaxWeight.
	 * @return bonusMaxWeight
	 */
	int getBonusMaxWeight();
	
	int getArmorBonus();
	
	void setArmorBonus(int weightIn);
}
