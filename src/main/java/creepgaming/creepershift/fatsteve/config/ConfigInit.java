package creepgaming.creepershift.fatsteve.config;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import creepgaming.creepershift.fatsteve.FatSteve;
import creepgaming.creepershift.fatsteve.Reference;
import creepgaming.creepershift.fatsteve.proxy.CommonProxy;
import creepgaming.creepershift.fatsteve.util.WeightUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ConfigInit {

	
	public static void init(){

		@SuppressWarnings("unused")
		CustomConfig weightConf = new CustomConfig(CommonProxy.directory.getPath(), "fatsteve-custom.txt", Reference.CUSTOM_STRING,
				(p) -> {

					try {

						List<String> list = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
						int count = 0;
						for (String s : list) {
							if (s.charAt(0) == '*')
								continue;
							String[] splits = s.trim().split("/");
							int weight = Integer.parseInt(splits[1]);
							ResourceLocation loc = new ResourceLocation(splits[0]);

							/*
							 * We register the respective weight changes into
							 * our HashMaps.
							 */
							if (Block.REGISTRY.containsKey(loc)) {
								/*
								 * 
								 * Blocks
								 */
								WeightUtils.getWeightMap().put(Item.getItemFromBlock(Block.REGISTRY.getObject(loc)), weight);
								count++;
							} else if (Item.REGISTRY.containsKey(loc)) {
								/*
								 * 
								 * Items
								 */
								WeightUtils.getWeightMap().put(Item.REGISTRY.getObject(loc), weight);
								count++;
							} else {
								FatSteve.logger.error("COULD NOT FIND THE BLOCK: " + splits[0]);
							}
						}
						FatSteve.logger.info("Registered " + count + " custom weights for items/blocks.");

					} catch (Exception ex) {
						FatSteve.logger
								.error("FAILED TO READ CUSTOM WEIGHT LIST. PLEASE MAKE SURE THERE ARE NO ERRORS.");
						FatSteve.logger.catching(ex);
					}

				});

		if (Config.byMaterial) {
			@SuppressWarnings("unused")
			CustomConfig materialConfig = new CustomConfig(CommonProxy.directory.getPath(), "fatsteve-materials.txt",
					Reference.MATERIAL_STRING, (p) -> {

						try {

							List<String> list = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
							int count = 0;
							for (String s : list) {
								if (s.charAt(0) == '*')
									continue;
								String[] splits = s.trim().split("/");
								int weight = Integer.parseInt(splits[1]);
								ResourceLocation loc = new ResourceLocation(splits[0]);

								/*
								 * We register the respective weight changes
								 * into our HashMaps.
								 */

								if (WeightUtils.getMaterialMap().containsKey(splits[0])) {
									/*
									 * 
									 * Materials
									 */
									WeightUtils.getMaterialWeightMap().put(WeightUtils.getMaterialMap().get(splits[0]),
											weight);
									count++;
								} else {
									FatSteve.logger.error("COULD NOT FIND THE MATERIAL: " + splits[0]);
								}
							}
							FatSteve.logger.info("Registered " + count + " custom weights for materials.");

						} catch (Exception ex) {
							FatSteve.logger.error(
									"FAILED TO READ CUSTOM MATERIAL WEIGHT LIST. PLEASE MAKE SURE THERE ARE NO ERRORS.");
							FatSteve.logger.catching(ex);
						}

					});

		}
	}
}
