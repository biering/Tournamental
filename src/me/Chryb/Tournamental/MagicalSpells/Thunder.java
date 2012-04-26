package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.entity.Player;

public class Thunder {
	
	public static Tournamental plugin;
	public Thunder(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "Create a thunder at your looking position";
    }

    public static String getName()
    {
        return "thunder";
    }
	
	public static void onCast(Player p){
	    if (HasPlayerAbility.hasAbility(p, "thunder")){
		   p.getWorld().strikeLightning(p.getTargetBlock(null, Integer.MAX_VALUE).getLocation());
		}
    }
}