package me.Chryb.Tournamental.Zones.Lobby;

import java.util.List;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.MathUtil;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class InLobbyZone {
	
	public static Tournamental plugin;
	public InLobbyZone(Tournamental instance){
		 plugin = instance;
		}
	
	public static boolean query(Location l){
		List<String> zone_list = plugin.getZonesConfig().getStringList("Zones.Global");
		int counter = 0;
		int size = zone_list.size();
		while (counter < size) {
			String zone = zone_list.get(counter);
				Vector pos1 = plugin.getZonesConfig().getVector("Zones." + zone + ".main.lobby.pos1");
				Vector pos2 = plugin.getZonesConfig().getVector("Zones." + zone + ".main.lobby.pos2");
				double pos1x = pos1.getX();
				double pos1y = pos1.getY();
				double pos1z = pos1.getZ();
				double pos2x = pos2.getX();
				double pos2y = pos2.getY();
				double pos2z = pos2.getZ();
				
				if ((MathUtil.between (l.getX(), pos1x, pos2x)) && (MathUtil.between (l.getY() ,pos1y ,pos2y)) && (MathUtil.between (l.getZ() ,pos1z ,pos2z))) 
				    {
					return true;
			        }
				counter++;
			}
    	return false;
	}

}
