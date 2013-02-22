package globalbank.common.fio;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

import org.lwjgl.Sys;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MinecraftException;

public class DataSaver {

	private static final Logger logger = Logger.getLogger("Minecraft");

	private final File saveDirectory;
	//private File worldDirectory;

	private final long initializationTime = System.currentTimeMillis();


	public DataSaver() {
//		saveDirectory = new File(DirectoryHelper.getMinecraftDir(), "saves");
		saveDirectory = new File(DirectoryHelper.getDotMinecraft( ), "globalbank" );
		saveDirectory.mkdirs();
//		worldDirectory = new File( saveDirectory, MinecraftServer.getServer().getFolderName() );

		setSessionLock();
	}

	/**
	 * Creates a session lock file for this process
	 */
	private void setSessionLock() {
		try {
			File file = new File(saveDirectory, "pointless.lock");
			DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file));

			try {
				dataoutputstream.writeLong(initializationTime);
			}
			finally {
				dataoutputstream.close();
			}
		}
		catch (IOException ioexception) {
			ioexception.printStackTrace();
			throw new RuntimeException("Failed to check session lock, aborting");
		}
	}

	/**
	 * gets the File object corresponding to the base directory of this save (saves/404 for a save called 404 etc)
	 */
	/*protected File getWorldDirectory() {
		return worldDirectory;
	}*/

	/**
	 * Checks the session lock to prevent save collisions
	 */
	public void checkSessionLock() throws MinecraftException {
		try {
			File file = new File(saveDirectory, "pointless.lock");
			DataInputStream datainputstream = new DataInputStream(new FileInputStream(file));

			try {
				if (datainputstream.readLong() != initializationTime) {
					throw new MinecraftException("The save is being accessed from another location, aborting");
				}
			}
			finally {
				datainputstream.close();
			}
		}
		catch (IOException ioexception) {
			throw new MinecraftException("Failed to check session lock, aborting");
		}
	}

	/**
	 * Loads and returns the world info
	 */
	public NBTTagCompound loadModInfo(String saveName)
	{
		File file = new File(saveDirectory, saveName);

		if (file.exists())
		{
			try
			{
				NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file));
				return nbttagcompound;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		file = new File(saveDirectory, saveName+"_old");

		if (file.exists())
		{
			try
			{
				NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file));
				return nbttagcompound;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * saves level.dat and backs up the existing one to level.dat_old
	 */
	public void saveModInfo(NBTTagCompound saveData, String saveName)
	{
		try
		{
			File file = new File(saveDirectory, saveName+"_new");
			File file1 = new File(saveDirectory, saveName+"_old");
			File file2 = new File(saveDirectory, saveName);
			CompressedStreamTools.writeCompressed(saveData, new FileOutputStream(file));

			if (file1.exists())
			{
				file1.delete();
			}

			file2.renameTo(file1);

			if (file2.exists())
			{
				file2.delete();
			}

			file.renameTo(file2);

			if (file.exists())
			{
				file.delete();
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
