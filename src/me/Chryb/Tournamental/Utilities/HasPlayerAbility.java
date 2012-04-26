package me.Chryb.Tournamental.Utilities;

import java.util.List;

import org.bukkit.entity.Player;

import me.Chryb.Tournamental.Tournamental;

public class HasPlayerAbility {
	
	public static Tournamental plugin;
	public HasPlayerAbility(Tournamental instance){
		 plugin = instance;
		}
	
	public static boolean hasAbility(Player p, String a){
		String pa = p.getName();
		if (GetPlayersClass.getClass(pa).equalsIgnoreCase("godclass")){
			List<String> list = plugin.getClassesConfig().getStringList("Classes.Godclass.Abilities");
			int size = list.size();
			int counter = 0;
			while (counter < size){
			String str = list.get(counter);
			if (str.equalsIgnoreCase(a)){
				return true;
			}
			counter++;
			}
			return false;
		}
		if (GetPlayersClass.getClass(pa).equalsIgnoreCase("mage")){
			List<String> list = plugin.getClassesConfig().getStringList("Classes.Mage.Abilities");
			int size = list.size();
			int counter = 0;
			while (counter < size){
			String str = list.get(counter);
			if (str.equalsIgnoreCase(a)){
				return true;
			}
			counter++;
			}
			return false;
		}
		
		if (GetPlayersClass.getClass(pa).equalsIgnoreCase("combat")){
			List<String> list = plugin.getClassesConfig().getStringList("Classes.Combat.Abilities");
			int size = list.size();
			int counter = 0;
			while (counter < size){
			String str = list.get(counter);
			if (str.equalsIgnoreCase(a)){
				return true;
			}
			counter++;
			}
			return false;
		}
		
		if (GetPlayersClass.getClass(pa).equalsIgnoreCase("tank")){
			List<String> list = plugin.getClassesConfig().getStringList("Classes.Tank.Abilities");
			int size = list.size();
			int counter = 0;
			while (counter < size){
			String str = list.get(counter);
			if (str.equalsIgnoreCase(a)){
				return true;
			}
			counter++;
			}
			return false;
		}
		
		if (GetPlayersClass.getClass(pa).equalsIgnoreCase("archer")){
			List<String> list = plugin.getClassesConfig().getStringList("Classes.Archer.Abilities");
			int size = list.size();
			int counter = 0;
			while (counter < size){
			String str = list.get(counter);
			if (str.equalsIgnoreCase(a)){
				return true;
			}
			counter++;
			}
			return false;
		}
		
		if (GetPlayersClass.getClass(pa).equalsIgnoreCase("heal")){
			List<String> list = plugin.getClassesConfig().getStringList("Classes.Heal.Abilities");
			int size = list.size();
			int counter = 0;
			while (counter < size){
			String str = list.get(counter);
			if (str.equalsIgnoreCase(a)){
				return true;
			}
			counter++;
			}
			return false;
		}
		return false;
	}
	

}
