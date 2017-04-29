package creepgaming.creepershift.fatsteve.integration.immersiveengineering;

import creepgaming.creepershift.fatsteve.config.Config;
import creepgaming.creepershift.fatsteve.util.WeightUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class IEWeightUtils {

	public static int processIEItem(ItemStack stack) {
		switch (stack.getMetadata()) {

		case 0:
			return calculateCrate(stack);
		case 1:
			return calculateBarrel(stack);
		case 4:
			return calculateBarrel(stack);
		default:
			return 0;
		}
	}

	/*
	 * We calculate the weight of the crate by using our regular weight util. In
	 * case the item inside is also a crate (doesn't happen with IE) it
	 * recursively calculates that too.
	 */
	public static int calculateCrate(ItemStack stackIn) {

		ItemStack[] stacks = getCrateStock(stackIn);
		int weight = 0;
		if (stacks == null)
			return weight;

		for (ItemStack stack : stacks) {
			weight += (WeightUtils.calculateWeight(stack) * stack.stackSize);
		}

		return weight;
	}

	/*
	 * Returns an itemstack array contained in the IE crate.
	 */

	public static ItemStack[] getCrateStock(ItemStack stack) {

		NBTTagCompound compound = stack.getTagCompound();
		if (compound != null) {
			NBTTagList list = compound.getTagList("inventory", 10);
			if (list != null) {
				int size = list.tagCount();
				ItemStack[] returnStack = new ItemStack[size];
				for (int i = 0; i < size; i++) {

					NBTTagCompound itemTag = list.getCompoundTagAt(i);
					int slot = itemTag.getByte("Slot") & 255;
					if (slot >= 0 && slot < size)
						returnStack[slot] = ItemStack.loadItemStackFromNBT(itemTag);
				}
				return returnStack;
			}

		}

		return null;
	}

	public static int calculateBarrel(ItemStack stack) {

			NBTTagCompound compound = stack.getTagCompound();
			if(compound == null)return 0;
			NBTTagCompound tank = compound.getCompoundTag("tank");
			if(tank  == null)return 0;
			return ((int)((tank.getInteger("Amount")*Config.liquidWeight)/1000F));
	}

}
