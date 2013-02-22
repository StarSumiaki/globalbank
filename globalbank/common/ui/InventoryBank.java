package globalbank.common.ui;

import globalbank.common.fio.DataSaver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryBank extends InventoryBasic
{
	private DataSaver ds = new DataSaver();
	private String owner = "";

	public InventoryBank()
	{
		super("container.bank", 27);
	}

	public void loadInventoryFromFile( String owner )
	{
		int var2;
		this.owner = owner;

		for (var2 = 0; var2 < this.getSizeInventory(); ++var2)
		{
			this.setInventorySlotContents(var2, (ItemStack)null);
		}

		
		NBTTagCompound nbt = ds.loadModInfo( owner + "_global.dat" );
		if( nbt != null && nbt.hasKey( "BankItems" ) ) {
			NBTTagList par1NBTTagList = nbt.getTagList( "BankItems" );
			for (var2 = 0; var2 < par1NBTTagList.tagCount(); ++var2) {
				NBTTagCompound var3 = (NBTTagCompound)par1NBTTagList.tagAt(var2);
				int var4 = var3.getByte("Slot") & 255;

				if (var4 >= 0 && var4 < this.getSizeInventory()) {
					this.setInventorySlotContents(var4, ItemStack.loadItemStackFromNBT(var3));
				}
			}
		}
	}
		
	public NBTTagList saveInventoryToFile( String owner )
	{
		NBTTagList var1 = new NBTTagList("BankItems");

		for (int var2 = 0; var2 < this.getSizeInventory(); ++var2)
		{
			ItemStack var3 = this.getStackInSlot(var2);

			if (var3 != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var2);
				var3.writeToNBT(var4);
				var1.appendTag(var4);
			}
		}
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag( "BankItems", var1 );
		ds.saveModInfo( nbt, owner + "_global.dat" );
		return var1;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return true;
	}

	public void openChest()
	{
	}

	public void closeChest()
	{
		this.saveInventoryToFile( this.owner );
		super.closeChest();
	}
}
