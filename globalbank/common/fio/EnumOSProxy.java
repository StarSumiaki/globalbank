package globalbank.common.fio;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public enum EnumOSProxy {
	LINUX,
	SOLARIS,
	WINDOWS,
	MACOS,
	UNKNOWN;
}
