package creepgaming.creepershift.fatsteve;

import java.util.ArrayList;

import creepgaming.creepershift.fatsteve.api.IWeightCapability;
import creepgaming.creepershift.fatsteve.capability.CapabilityWeight;
import creepgaming.creepershift.fatsteve.util.WeightUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class WeightTest {

	public static ArrayList<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();

	public static void calcWeight(EntityPlayer player) {

		final IWeightCapability cap = CapabilityWeight.getWeight(player);

		if (player instanceof EntityPlayerMP) {

			if (cap != null) {

				cap.setWeight();
			}
		}
	}

	/**
	 * Calculates the weight of an itemstack and adds/removes it.
	 * 
	 * @param player
	 *            The player
	 * @param stack
	 *            The ItemStack
	 * @param remove
	 *            Should it add or substract?
	 */
	public static void calcItemstack(EntityPlayer player, ItemStack stack, boolean remove) {

		final IWeightCapability cap = CapabilityWeight.getWeight(player);

		if (player instanceof EntityPlayerMP) {

			if (cap != null) {

				int weight = 0;
				weight += (WeightUtils.calculateWeight(stack) * stack.stackSize);
				if (!remove)
					cap.addWeight(weight);
				else
					cap.removeWeight(weight);
			}
		}
	}
	
	public static void calcItemstack(EntityPlayer player, ItemStack stack, boolean remove, int stacksize) {

		final IWeightCapability cap = CapabilityWeight.getWeight(player);

		if (player instanceof EntityPlayerMP) {

			if (cap != null) {

				int weight = 0;
				weight += (WeightUtils.calculateWeight(stack) * stacksize);
				if (!remove)
					cap.addWeight(weight);
				else
					cap.removeWeight(weight);
			}
		}
	}
	
	
	public static void placedBlock(EntityPlayer player, IBlockState state){
		
		final IWeightCapability cap = CapabilityWeight.getWeight(player);

		if (player instanceof EntityPlayerMP) {

			if (cap != null) {

					cap.removeWeight(WeightUtils.calculateBlock(state));
			}
		}
	}
	
}
