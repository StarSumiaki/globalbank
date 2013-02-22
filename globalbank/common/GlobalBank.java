package globalbank.common;

import globalbank.common.block.BlockBank;
import globalbank.common.network.GuiHandling;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.MinecraftForgeClient;

@Mod( modid="StarShadesMods_GlobalBank", name="GlobalBank", version="1.1 [MC 1.4.7]" )
@NetworkMod( clientSideRequired=true, serverSideRequired=true )
public class GlobalBank {

	public static GlobalBank instance;
	public static int bankID = 3500;
	public static int receiverID = 3501;
	
	public static Block bank;
	public static Block receiver;
	
	@PreInit
	public void preInit( FMLPreInitializationEvent event ) {
		Configuration config = new Configuration( event.getSuggestedConfigurationFile() );
		config.load( );
		bankID = Integer.parseInt( config.get( "bankBlockID", Configuration.CATEGORY_BLOCK, 3500 ).value );
		receiverID = Integer.parseInt( config.get( "receiverBlockID", Configuration.CATEGORY_BLOCK, 3501 ).value );
		config.save( );
		MinecraftForgeClient.preloadTexture("/globalbank/gfx/blocks.png");
	}
	
	
	@Init
	public void load( FMLInitializationEvent event ) {
		instance = this;
        NetworkRegistry.instance().registerGuiHandler( this, new GuiHandling( ) );
		
		bank = new BlockBank( bankID, true ).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("globalbank");
		GameRegistry.registerBlock( bank );
		LanguageRegistry.addName( bank, "GlobalBank IWRT" );
		
		receiver = new BlockBank( receiverID, false ).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("globalbank_receiver");
		GameRegistry.registerBlock( receiver );
		LanguageRegistry.addName( receiver, "GlobalBank IWRT Receiver" );
		
		GameRegistry.addRecipe( new ItemStack( bank ), new Object[] {
			"DSD",
			"SES",
			"DSD",
			'S', Block.whiteStone,
			'D', Item.diamond,
			'E', Item.netherStar
		} );
		
		GameRegistry.addRecipe( new ItemStack( receiver ), new Object[] {
			"CCC",
			"DED",
			"CCC",
			'C', Block.cobblestone,
			'D', Item.diamond,
			'E', Item.enderPearl
		} );
	}
}
