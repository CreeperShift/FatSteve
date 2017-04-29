package creepgaming.creepershift.fatsteve.potion;

import creepgaming.creepershift.fatsteve.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionEncumbrance extends Potion{
	
	private final ResourceLocation iconTexture;

	public PotionEncumbrance(boolean isBadEffectIn, int liquidColorIn, String name) {
		super(isBadEffectIn, liquidColorIn);
		setPotionName(this, name);
		iconTexture = new ResourceLocation(Reference.MODID, "textures/potions/" + name + ".png");
	}

	
	
	
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}





	public static void setPotionName(Potion potion, String potionName){
		potion.setRegistryName(Reference.MODID, potionName);
		potion.setPotionName("effect." + potion.getRegistryName().toString());
			
	}

	@Override
	public void performEffect(EntityLivingBase entityIn, int p_76394_2_) {
		
		if (entityIn instanceof EntityPlayer){
			((EntityPlayer)entityIn).addExhaustion(0.005F);
		}
		
		
	}



	@Override
	public void affectEntity(Entity source, Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier,
			double health) {
		// TODO Auto-generated method stub
		super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
	}



	@Override
	public boolean hasStatusIcon(){
		return true;
	}
	
	/**
	 * Called to draw the this Potion onto the player's inventory when it's active.
	 * This can be used to e.g. render Potion icons from your own texture.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param effect the active PotionEffect
	 * @param mc     the Minecraft instance, for convenience
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		if (mc.currentScreen != null) {
			mc.getTextureManager().bindTexture(iconTexture);
			Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
		}
	}

	
	/**
	 * Called to draw the this Potion onto the player's ingame HUD when it's active.
	 * This can be used to e.g. render Potion icons from your own texture.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param effect the active PotionEffect
	 * @param mc     the Minecraft instance, for convenience
	 * @param alpha  the alpha value, blinks when the potion is about to run out
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
	}
	
	
	
}
