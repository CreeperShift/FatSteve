package creepgaming.creepershift.fatsteve.enchantment;

import javax.annotation.Nullable;

import creepgaming.creepershift.fatsteve.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnchantmentWeight extends Enchantment{

	private static EnchantmentWeight INSTANCE;
	
	protected EnchantmentWeight() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{EntityEquipmentSlot.CHEST});
		setRegistryName(Reference.MODID, "weightenchant");
		setName("weightenchant");
	}

	@Nullable
	public static EnchantmentWeight instance(){
		return INSTANCE;
	}
	
	
	public static void register(){
		INSTANCE = new EnchantmentWeight();
		GameRegistry.register(INSTANCE);
	}
	
    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
	@Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 1 + 10 * (enchantmentLevel - 1);
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
	@Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
	@Override
    public int getMaxLevel()
    {
        return 3;
    }


	@Override
	public boolean canApply(ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
}
