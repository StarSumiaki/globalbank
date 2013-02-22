package globalbank.common.ui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGlobalBank extends Slot {

	/** Is this slot accessible? */
	private boolean accessible;
	
	public SlotGlobalBank( IInventory inventory, int slotIndex, int xPos, int yPos, boolean accessible ) {
		super( inventory, slotIndex, xPos, yPos );
		this.accessible = accessible;
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return this.accessible;
	}
	
	public boolean isAccessible() {
		return this.accessible;
	}
}
