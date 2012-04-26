package me.Chryb.Tournamental.Utilities;

import me.Chryb.Tournamental.Tournamental;

public class GetPlayersClass {
	
	public static Tournamental plugin;
	public GetPlayersClass(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getClass(String p){
		return plugin.getPlayersConfig().getString("Players." + p + ".class");
	}

}
