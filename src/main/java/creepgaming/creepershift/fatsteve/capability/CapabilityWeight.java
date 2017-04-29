package creepgaming.creepershift.fatsteve.capability;

import javax.annotation.Nullable;

import creepgaming.creepershift.fatsteve.Reference;
import creepgaming.creepershift.fatsteve.api.IWeightCapability;
import creepgaming.creepershift.fatsteve.util.CapabilityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityWeight {
	
	
	/**
	 * The Capability Instance.
	 */
	
	@CapabilityInject(IWeightCapability.class)
	public static final Capability<IWeightCapability> WEIGHT_CAPABILITY = null;
	
	
	/**
	 * The default EnumFacing for this capability.
	 */
	public static final EnumFacing DEFAULT_FACING = null;
	
	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "Weight");
	
	
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IWeightCapability.class, new Capability.IStorage<IWeightCapability>() {
			@Override
			public NBTBase writeNBT(Capability<IWeightCapability> capability, IWeightCapability instance, EnumFacing side) {
				return new NBTTagInt(instance.getCurrentWeight());
			}

			
			
			@Override
			public void readNBT(Capability<IWeightCapability> capability, IWeightCapability instance, EnumFacing side, NBTBase nbt) {
				instance.setWeight(((NBTTagInt) nbt).getInt());
			}
		}, () -> new Weight(null));
	}
	
	
	@Nullable
	public static IWeightCapability getWeight(EntityPlayer player){
		return CapabilityUtils.getCapability(player, WEIGHT_CAPABILITY, DEFAULT_FACING);
	}

	
	
	public static ICapabilityProvider createProvider(IWeightCapability weight){
		return new SimpleCapabilityProvider<>(WEIGHT_CAPABILITY, DEFAULT_FACING, weight);
	}
	
	
	/**
	 * Event handler for the {@link IMaxHealth} capability.
	 */
	@Mod.EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof EntityPlayer) {
				final Weight weight = new Weight((EntityPlayer) event.getObject());
				event.addCapability(ID, createProvider(weight));
			}
		}

		/**
		 * Copy the player's bonus max health when they respawn after dying or returning from the end.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			final IWeightCapability oldWeight = getWeight(event.getOriginal());
			final IWeightCapability newWeight = getWeight(event.getEntityPlayer());

			if (newWeight != null && oldWeight != null) {
				newWeight.setWeight(oldWeight.getCurrentWeight());
			}
		}
	}
	
	
}
