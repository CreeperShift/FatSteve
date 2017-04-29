package creepgaming.creepershift.fatsteve.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectEncumbrance extends PotionEffect{

    private final Potion potion;
    /** The duration of the potion effect */
    private int duration;
    /** The amplifier of the potion effect */
    private int amplifier;
	
	public PotionEffectEncumbrance(Potion potionIn, int durationIn, int amplifierIn) {
		super(potionIn, durationIn, amplifierIn);
		potion = potionIn;
		duration = durationIn;
		amplifier = amplifierIn;
	}

	@Override
	public boolean doesShowParticles() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public Potion getPotion()
    {
        return this.potion;
    }
	
	@Override
    public int getDuration()
    {
        return this.duration;
    }
	
	@Override
    public int getAmplifier()
    {
        return this.amplifier;
    }

	@Override
	public boolean onUpdate(EntityLivingBase entityIn)    {
        if (this.duration > 0)
        {
                this.performEffect(entityIn);

            this.deincrementDuration();
        }

        return this.duration > 0;
    }
	
	
    private int deincrementDuration()
    {
        return --this.duration;
    }

	@Override
	public void performEffect(EntityLivingBase entityIn) {
		
	        if (this.duration > 0)
	        {
	            this.potion.performEffect(entityIn, this.amplifier);
	            
	        }
	    }
	
	
	

}
