package creepgaming.creepershift.fatsteve.event;

import java.util.ArrayList;
import java.util.Map;

import creepgaming.creepershift.fatsteve.WeightTest;
import creepgaming.creepershift.fatsteve.api.IWeightCapability;
import creepgaming.creepershift.fatsteve.capability.CapabilityWeight;
import creepgaming.creepershift.fatsteve.capability.Weight;
import creepgaming.creepershift.fatsteve.enchantment.EnchantmentWeight;
import creepgaming.creepershift.fatsteve.potion.PotionEffectEncumbrance;
import creepgaming.creepershift.fatsteve.potion.PotionHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventHandler {

	public EventHandler() {

		init();
	}

	private void init() {

	}

	/*
	 * When a player is done with a container (Chest, Furnace, machine, etc) we recalculate his inventory.
	 */
	@SubscribeEvent
	public void onContainerClose(PlayerContainerEvent.Close event) {
		WeightTest.calcWeight(event.getEntityPlayer());
	}

	/*
	 * When a player places a block, we reduce his inventory weight.
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onBlockPlace(PlaceEvent event) {
		if (!event.isCanceled())
			WeightTest.placedBlock(event.getPlayer(), event.getPlacedBlock());
	}

	/*
	 * This is mainly here for other mods. When the player finishes using an item, like the builders wand, we recalculate his inventory.
	 * TODO: Check if this is actually working.
	 */
	@SubscribeEvent
	public void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() instanceof EntityPlayer) {
			WeightTest.calcWeight((EntityPlayer) event.getEntityLiving());
		}

	}

	/*
	 * Called when a player collides with an item on the ground. Sadly it doesn't tell us whether the player was able to pick it up.
	 * TODO: Change it so it recalculates the entire inventory instead.
	 * The current implementation is buggy.
	 */
	
	@SubscribeEvent
	public void onPickUpByPlayer(ItemPickupEvent event){
		WeightTest.calcWeight(event.player);	
	}

	/*
	 * Called when a players items drop on death.
	 * TODO: Fix this
	 */
	@SubscribeEvent
	public void dropItem(PlayerDropsEvent event) {

		WeightTest.calcWeight(event.getEntityPlayer());

	}

	/*
	 * We recalculate the entire inventory weight on login.
	 * In case it gets messed up by other mods, a relog will always fix it.
	 */
	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		if (!event.player.getEntityWorld().isRemote) {
			final IWeightCapability cap = CapabilityWeight.getWeight(player);
			if (cap != null) {
				WeightTest.calcWeight(event.player);
			}
		}
	}

	/*
	 * Called when a player tosses out items.
	 */
	@SubscribeEvent
	public void onDrop(ItemTossEvent event) {
		if (!event.isCanceled())
			WeightTest.calcItemstack(event.getPlayer(), event.getEntityItem().getEntityItem(), true);
	}

	/*
	 * Used to apply our potion effect.
	 * TODO: Rewrite so it doesn't use potion effects, but applies a slowness debuff based on % of encumbrance.
	 */
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event) {

		if (!event.world.isRemote /**&& event.world.getWorldTime() % 20 == 0**/) {

			ArrayList<EntityPlayerMP> players = Weight.players;

			if (players.size() != 0) {
				for (EntityPlayer player : players) {
					if (!player.isCreative())
						player.addPotionEffect(new PotionEffectEncumbrance(PotionHandler.encumbrance, 40, 0));
				}
			}
		}

	}

	
	/*
	 * This checks if the player is wearing armor that has our enchantment on it.
	 * We apply the armorWeightBonus in the capability accordingly.
	 */
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			ItemStack stack = event.player.inventory.armorInventory[2];
			final IWeightCapability cap = CapabilityWeight.getWeight(event.player);
			if (cap != null) {
				int result = 0;
				if (stack != null) {
					Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
					for (Enchantment enchant : enchants.keySet()) {
						if (enchant == EnchantmentWeight.instance()) {
							result = enchants.get(enchant);
						}
					}
				}
				cap.setArmorBonus(2000 * result);
			}

		}

	}

}
