package globalbank.common.fio;

import java.io.*;
import java.util.List;
import org.lwjgl.Sys;

import net.minecraft.server.MinecraftServer;

public class DirectoryHelper {

	public static boolean isServerAJar( ) {
		return MinecraftServer.getServer( ).isDedicatedServer( );
	}

	public static File getDotMinecraft( ) {
		String dirName = "minecraft";
		String var1 = System.getProperty("user.home", ".");
		File var2;

		switch (EnumOSHelperProxy.field_90049_a[getOs().ordinal()])
		{
			case 1:
			case 2:
				var2 = new File(var1, '.' + dirName + '/');
				break;

			case 3:
				String var3 = System.getenv("APPDATA");

				if (var3 != null)
				{
					var2 = new File(var3, "." + dirName + '/');
				}
				else
				{
					var2 = new File(var1, '.' + dirName + '/');
				}

				break;

			case 4:
				var2 = new File(var1, "Library/Application Support/" + dirName);
				break;

			default:
				var2 = new File(var1, dirName + '/');
		}

		if (!var2.exists() && !var2.mkdirs())
		{
			throw new RuntimeException("The working directory could not be created: " + var2);
		}
		else
		{
			return var2;
		}
	}

	public static EnumOSProxy getOs()
	{
		String var0 = System.getProperty("os.name").toLowerCase();
		return var0.contains("win") ? EnumOSProxy.WINDOWS : (var0.contains("mac") ? EnumOSProxy.MACOS : (var0.contains("solaris") ? EnumOSProxy.SOLARIS : (var0.contains("sunos") ? EnumOSProxy.SOLARIS : (var0.contains("linux") ? EnumOSProxy.LINUX : (var0.contains("unix") ? EnumOSProxy.LINUX : EnumOSProxy.UNKNOWN)))));
	}
}
