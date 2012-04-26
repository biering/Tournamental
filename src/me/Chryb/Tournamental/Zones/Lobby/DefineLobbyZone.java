package me.Chryb.Tournamental.Zones.Lobby;

import me.Chryb.Tournamental.Tournamental;

import org.bukkit.util.Vector;

public class DefineLobbyZone {
	
	public static Tournamental plugin;
	public DefineLobbyZone(Tournamental instance){
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
		String Lobby = "Zones." + zone + ".main.lobby";
		plugin.getZonesConfig().set(Lobby + ".pos1", pos1);
		plugin.getZonesConfig().set(Lobby + ".pos2", pos2);
		plugin.getZonesConfig().options().copyDefaults(true);
		plugin.saveZonesConfig();
	}

}
