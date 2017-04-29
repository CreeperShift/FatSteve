package creepgaming.creepershift.fatsteve.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PotionHandler {

	public static Potion encumbrance;
	
	
	public static void init(){
		
		registerPotion("encumbrance", true, 0xFFFFFF);
	}
	
	
	
	
	private static Potion registerPotion(String name, boolean badEffect, int potionColor){
		
	encumbrance = new PotionEncumbrance(badEffect, potionColor, name);
	GameRegistry.register(encumbrance);
	encumbrance.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
	return encumbrance;	
	}
	
	
	
}







