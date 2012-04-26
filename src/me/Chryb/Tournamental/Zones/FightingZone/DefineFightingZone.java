package me.Chryb.Tournamental.Zones.FightingZone;

import org.bukkit.util.Vector;

import me.Chryb.Tournamental.Tournamental;

public class DefineFightingZone {
	
	public static Tournamental plugin;
	public DefineFightingZone(Tournamental instance){
		 plugin = instance;
		}
	
	public static void define(String zone){
		Vector pos1 = new Vector();
	    pos1.setX(plugin.posx1);
		pos1.setY(plugin.posy1);
		pos1.setZ(plugin.posz1);
		
		Vector pos2 = new Vector();
		pos2.setX(plugin.posx2);
		pos2.setY(plugin.posy2);
		pos2.setZ(plugin.posz2);
		String Fight = "Zones." + zone + ".main.fight";
		plugin.getZonesConfig().set(Fight + ".pos1", pos1);
		plugin.getZonesConfig().set(Fight + ".pos2", pos2);
		plugin.getZonesConfig().options().copyDefaults(true);
		plugin.saveZonesConfig();
	}

}
