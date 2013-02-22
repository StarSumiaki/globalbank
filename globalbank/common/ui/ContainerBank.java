package globalbank.common.ui;

import globalbank.common.ui.SlotGlobalBank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerBank extends Container
{
	private InventoryBank lowerChestInventory = new InventoryBank( );
	private int numRows;
	private boolean canPut;

	public ContainerBank( InventoryPlayer ip, World w, int x, int y, int z, boolean canPut )
	{
		this.lowerChestInventory.loadInventoryFromFile( ip.player.username );
		this.canPut = canPut;
		this.numRows = 3;
		int var3 = (this.numRows - 4) * 18;
		int var4;
		int var5;

		for (var4 = 0; var4 < this.numRows; ++var4)
		{
			for (var5 = 0; var5 < 9; ++var5)
			{
				this.addSlotToContainer(new SlotGlobalBank(lowerChestInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18, canPut));
			}
		}

		for (var4 = 0; var4 < 3; ++var4)
		{
			for (var5 = 0; var5 < 9; ++var5)
			{
				this.addSlotToContainer(new Slot(ip, var5 + var4 * 9 + 9, 8 + var5 * 18, 103 + var4 * 18 + var3));
			}
		}

		for (var4 = 0; var4 < 9; ++var4)
		{
			this.addSlotToContainer(new Slot( ip, var4, 8 + var4 * 18, 161 + var3));
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		if( !canPut )
			return null;
			
		ItemStack var3 = null;
		
		Slot var4 = (Slot)this.inventorySlots.get(par2);

		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 < this.numRows * 9)
			{
				if (!this.mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var5, 0, this.numRows * 9, false))
			{
				return null;
			}

			if (var5.stackSize == 0)
			{
				var4.putStack((ItemStack)null);
			}
			else
			{
				var4.onSlotChanged();
			}
		}

		return var3;
	}

	/**
	 * Callback for when the crafting gui is closed.
	 */
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
	{
		super.onCraftGuiClosed(par1EntityPlayer);
		this.lowerChestInventory.closeChest();
	}		
}
