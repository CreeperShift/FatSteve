package creepgaming.creepershift.fatsteve.gui;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import creepgaming.creepershift.fatsteve.Reference;
import creepgaming.creepershift.fatsteve.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class WeightOverlay extends Gui{
	
	public static int weight = 0;
	public static int maxWeight  = 0;
	
	/* This line tells Minecraft/Forge where your texture is. The first argument is your MODID,
	   * and the second argument is the path to your texture starting at "resources/assets/MODID"
	   *
	   * In this case, the location of the texture is
	   *
	   *   "resources/assets/MODID/textures/gui/advanced_overlay.png"
	   */
	  private final static ResourceLocation overlayBar = new ResourceLocation(Reference.MODID,
	          "textures/gui/icon.png");

	  /* These two variables describe the size of the bar */
	  private final static int BAR_WIDTH = 32;
	  private final static int BAR_HEIGHT = 18;
	 // private final static int BAR_SPACING_ABOVE_EXP_BAR = 3;  // pixels between the BAR and the Experience Bar below it

	  /* Sometimes you want to include extra information from the game. This instance of
	   * Minecraft will let you access the World and EntityPlayer objects which is more than
	   * enough for most purposes. It also contains some helper objects for OpenGL which can be
	   * used for drawing things.
	   *
	   * To actually get the instance of Minecraft, you should pass it in through the constructor.
	   * It is possible to import Minecraft and use Minecraft.getMinecraft() here, but this won't
	   * always be possible if you want to include information that's part of another class.
	   */
	  private Minecraft mc;

	  public WeightOverlay(Minecraft mc) {
	    this.mc = mc;
	    maxWeight = Config.baseMaxWeight;
	  }

	  /* This helper method will render the bar */
	  public void renderStatusBar(int screenWidth, int screenHeight) {
		  
	    /* This object draws text using the Minecraft font */
	    FontRenderer fr = mc.fontRendererObj;
	    DecimalFormat d = new DecimalFormat("#,###");
	    
	    /* Saving the current state of OpenGL so that I can restore it when I'm done */
	    GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
	    GL11.glPushMatrix();

	    
	    
	      /* Set the rendering color to white */
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	      /* This method tells OpenGL to draw with the custom texture */
	    mc.renderEngine.bindTexture(overlayBar);

	    
	    
	    
	    
	    
	    // we will draw the status bar just above the hotbar.
	    //  obtained by inspecting the vanilla hotbar rendering code
	    final int vanillaExpLeftX = screenWidth / 2 - 125; // leftmost edge of the experience bar
	    final int vanillaExpTopY = screenHeight -2;  // top of the experience bar

	    
	    
	    
	      /* Shift our rendering origin to just above the experience bar
	       * The top left corner of the screen is x=0, y=0
	       */
	    GL11.glTranslatef(vanillaExpLeftX, vanillaExpTopY - BAR_HEIGHT, 0);

	      /* Draw a part of the image file at the current position
	       *
	       * The first two arguments are the x,y position that you want to draw the texture at
	       * (with respect to the current transformations).
	       *
	       * The next four arguments specify what part of the image file to draw, in the order below:
	       *
	       *   1. Left-most side
	       *   2. Top-most side
	       *   3. Right-most side
	       *   4. Bottom-most side
	       *
	       * The units of these four arguments are pixels in the image file. These arguments will form a
	       * rectangle, which is then "cut" from your image and used as the texture
	       *
	       * This line draws the background of the custom bar
	       */
	    drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);

	      /* This line draws the outline effect that corresponds to how much armor the player has.
	       * I slide the right-most side of the rectangle using the player's armor value.
	       */
	    float f = (BAR_HEIGHT)*Math.min(1, ((float)weight/maxWeight));
	    
	    if(weight <= maxWeight)
	    drawTexturedModalRect(0, ((int)(18  - f)), 32, ((int)(18  - f)), 32 , 18);
	    else
	    drawTexturedModalRect(0, 0, 64, 0, 32 , 18);	
	    
	    
/*	    GL11.glPopMatrix();

	    GL11.glPushMatrix();	    
*/
	        /* Move to the right end of the bar, minus a few pixels. */
	    GL11.glTranslatef(33, -6, 0);

	        /* The default minecraft font is too big, so I scale it down a bit. */
	    GL11.glPushMatrix();
	    GL11.glScalef(0.45f, 0.45f, 1);

	          /* This generates the string that I want to draw. */
	    String s = d.format(weight) + "/" + d.format(maxWeight);

	          /* If the player has the absorption effect, draw the string in gold color, otherwise
	           * draw the string in white color. For each case, I call drawString twice, once to
	           * draw the shadow, and once for the actual string.
	           */
	      fr.drawString(s, -fr.getStringWidth(s) + 1, 2, 0x4D0000);
	      fr.drawString(s, -fr.getStringWidth(s), 1, 0xFFFFFF);
	    GL11.glPopMatrix();
	    GL11.glPopMatrix();
	    GL11.glPopAttrib();
	  }
	
	

}
