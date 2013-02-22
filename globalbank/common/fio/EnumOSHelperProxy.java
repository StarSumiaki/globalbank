package globalbank.common.fio;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)

public class EnumOSHelperProxy {
	public static final int[] field_90049_a = new int[EnumOSProxy.values().length];

	static
	{
		try
		{
			field_90049_a[EnumOSProxy.LINUX.ordinal()] = 1;
		}
		catch (NoSuchFieldError var4)
		{
			;
		}

		try
		{
			field_90049_a[EnumOSProxy.SOLARIS.ordinal()] = 2;
		}
		catch (NoSuchFieldError var3)
		{
			;
		}

		try
		{
			field_90049_a[EnumOSProxy.WINDOWS.ordinal()] = 3;
		}
		catch (NoSuchFieldError var2)
		{
			;
		}

		try
		{
			field_90049_a[EnumOSProxy.MACOS.ordinal()] = 4;
		}
		catch (NoSuchFieldError var1)
		{
			;
		}
	}
}
