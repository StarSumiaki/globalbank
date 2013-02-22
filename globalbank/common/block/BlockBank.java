package globalbank.common.block;

import globalbank.common.GlobalBank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBank extends Block {

	private boolean global;

	public BlockBank(int par1, boolean global)
	{
		super(par1, Material.rock);
		this.global = global;
		//this.blockIndexInTexture = 37;
		this.setCreativeTab(CreativeTabs.tabDecorations);
		//this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		setTextureFile( "/globalbank/gfx/blocks.png" );
	}
	
	public int getBlockTextureFromSide(int par1) {
		if( global )
			return par1 == 1 ? 1 : (par1 == 0 ? 1 : 2);
		else
			return par1 == 1 ? 1 : (par1 == 0 ? 1 : 0);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return true;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return this.blockID;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	/**
	 * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
	 */
	protected boolean canSilkHarvest()
	{
		return false;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
			if (par1World.isBlockNormalCube(par2, par3 + 1, par4))
			{
				return true;
			}
			else if (par1World.isRemote)
			{
				return true;
			}
			else
			{
				par5EntityPlayer.openGui(GlobalBank.instance, 0, par1World, par2, par3, par4);
				return true;
			}
	}
	
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}
}
