package creepgaming.creepershift.fatsteve.util;

import java.util.HashMap;

import creepgaming.creepershift.fatsteve.FatSteve;
import creepgaming.creepershift.fatsteve.config.Config;
import creepgaming.creepershift.fatsteve.integration.immersiveengineering.IEWeightUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WeightUtils {

	/**
	 * While we have default values for items & blocks, we also have a bunch of
	 * custom weights. We store them in a HashMap so we can retrieve them
	 * quickly.
	 */
	private static HashMap<Item, Integer> weightMap;
	private static HashMap<String, Material> materialMap;
	private static HashMap<Material, Integer> materialWeightMap;

	/**
	 * Used to calculate the weight of an item or block.
	 */
	public static int calculateWeight(ItemStack stack) {

		if (stack == null)
			return 0;
		Item item = stack.getItem();

		if (FatSteve.externalWeight) {
			if (item == Item.REGISTRY.getObject(new ResourceLocation("immersiveengineering:woodenDevice0")) || item == Item.REGISTRY.getObject(new ResourceLocation("immersiveengineering:metalDevice0"))){
				int w = IEWeightUtils.processIEItem(stack);
				
				if (getWeightMap().containsKey(item)) {
					return getWeightMap().get(item) + w;
				}

				if (item instanceof ItemBlock) {
					return calculateBlock((ItemBlock) item) + w;
				}
				if (item instanceof Item) {
					return calculateItem(item) + w;
				}
			}
		}

		if (getWeightMap().containsKey(item)) {
			return getWeightMap().get(item);
		}

		if (item instanceof ItemBlock) {
			return calculateBlock((ItemBlock) item);
		}
		if (item instanceof Item) {
			return calculateItem(item);
		}

		return 0;
	}

	/*
	 * Calculates the weight of a block.
	 */
	private static int calculateBlock(ItemBlock item) {
		Block block = item.block;
		return calculateBlock(block.getDefaultState());
	}

	/*
	 * Calculates the weight of a block.
	 */
	public static int calculateBlock(IBlockState state) {

		if (Config.byMaterial) {
			Material mat = state.getMaterial();

			if (materialMap.containsKey(mat)) {
				return getMaterialWeightMap().get(materialMap.get(mat));
			}

		}
		return Config.blockWeight;
	}

	public static void polutateMaterialMap() {
		materialMap = new HashMap<>();
		materialMap.put("Material.AIR", Material.AIR);
		materialMap.put("Material.ANVIL", Material.ANVIL);
		materialMap.put("Material.CACTUS", Material.CACTUS);
		materialMap.put("Material.CAKE", Material.CAKE);
		materialMap.put("Material.CARPET", Material.CARPET);
		materialMap.put("Material.CIRCUITS", Material.CIRCUITS);
		materialMap.put("Material.CLAY", Material.CLAY);
		materialMap.put("Material.CLOTH", Material.CLOTH);
		materialMap.put("Material.CORAL", Material.CORAL);
		materialMap.put("Material.CRAFTED_SNOW", Material.CRAFTED_SNOW);
		materialMap.put("Material.DRAGON_EGG", Material.DRAGON_EGG);
		materialMap.put("Material.FIRE", Material.FIRE);
		materialMap.put("Material.GLASS", Material.GLASS);
		materialMap.put("Material.GOURD", Material.GOURD);
		materialMap.put("Material.GRASS", Material.GRASS);
		materialMap.put("Material.GROUND", Material.GROUND);
		materialMap.put("Material.ICE", Material.ICE);
		materialMap.put("Material.IRON", Material.IRON);
		materialMap.put("Material.LAVA", Material.LAVA);
		materialMap.put("Material.LEAVES", Material.LEAVES);
		materialMap.put("Material.PACKED_ICE", Material.PACKED_ICE);
		materialMap.put("Material.PISTON", Material.PISTON);
		materialMap.put("Material.PLANTS", Material.PLANTS);
		materialMap.put("Material.PORTAL", Material.PORTAL);
		materialMap.put("Material.REDSTONE_LIGHT", Material.REDSTONE_LIGHT);
		materialMap.put("Material.ROCK", Material.ROCK);
		materialMap.put("Material.SAND", Material.SAND);
		materialMap.put("Material.SNOW", Material.SNOW);
		materialMap.put("Material.SPONGE", Material.SPONGE);
		materialMap.put("Material.TNT", Material.TNT);
		materialMap.put("Material.VINE", Material.VINE);
		materialMap.put("Material.WATER", Material.WATER);
		materialMap.put("Material.WEB", Material.WEB);
		materialMap.put("Material.WOOD", Material.WOOD);
	}

	/*
	 * Calculates the weight of an item.
	 */
	private static int calculateItem(Item item) {

		return Config.itemWeight;
	}

	/*
	 * Getters for our Maps
	 */
	public static HashMap<String, Material> getMaterialMap() {
		if (materialMap == null) {
			polutateMaterialMap();
		}
		return materialMap;

	}

	public static HashMap<Item, Integer> getWeightMap() {

		if (weightMap == null)
			weightMap = new HashMap<>();

		return weightMap;
	}

	public static HashMap<Material, Integer> getMaterialWeightMap() {

		if (materialWeightMap == null)
			materialWeightMap = new HashMap<>();

		return materialWeightMap;
	}

}
