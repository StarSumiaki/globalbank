package globalbank.common.network;

import cpw.mods.fml.common.network.IGuiHandler;

import globalbank.common.ui.ContainerBank;
import globalbank.common.ui.GuiBank;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class GuiHandling implements IGuiHandler {

	@Override
	public Object getServerGuiElement( int id, EntityPlayer p, World w, int x, int y, int z) {
		if( w.getBlockId( x, y, z ) == globalbank.common.GlobalBank.bankID )
			return new ContainerBank( p.inventory, w, x, y, z, true );
		else if( w.getBlockId( x, y, z ) == globalbank.common.GlobalBank.receiverID )
			return new ContainerBank( p.inventory, w, x, y, z, false );

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer p, World w, int x, int y, int z) {
		if( w.getBlockId( x, y, z ) == globalbank.common.GlobalBank.bankID )
			return new GuiBank( p.inventory, w, x, y, z, true );
		else if( w.getBlockId( x, y, z ) == globalbank.common.GlobalBank.receiverID )
			return new GuiBank( p.inventory, w, x, y, z, false );

		return null;
	}

}
