package me.Chryb.Tournamental.MagicalSpells;

import java.util.List;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class CombatAoE {
	
	public static Tournamental plugin;
	public CombatAoE(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "Der Spieler schadet allen Entitys in einem kleinen Radius";
    }

    public static String getName()
    {
        return "combataoe";
    }
	
    public static void onCast(Player p){
    	if (HasPlayerAbility.hasAbility(p, "combat_aoe")){
    
    		//[Shift]+[LMB]
    		if (HasPlayerAbility.hasAbility(p, "combataoe")){
    			if (p.getFoodLevel() > 2){																					
    				if ((plugin.getPlayersConfig().getDouble("Players."+ p.getName() +".Cooldown.Combat.CombatAoE") + 30 < p.getWorld().getFullTime())){ //If cooldown ok
    					plugin.getPlayersConfig().set("Players." + p.getName() + ".Cooldown.Combat.CombatAoE", p.getWorld().getFullTime()); 	//Cd set back
    					List<Entity> list = p.getNearbyEntities(4, 4, 4);
    					  for (int i=0; i < list.size(); i++){
    						Entity e = list.get(i);
    						if (e instanceof Monster){
    							Monster monster = (Monster)e;
    						    monster.setHealth(monster.getHealth()-3);
    						    p.sendMessage(ChatColor.GOLD + "CombatAoE!!");
    						}
    						if (e instanceof Player){
    							Player player = (Player)e;
    							player.setHealth(player.getHealth()-3);
    							p.sendMessage(ChatColor.GOLD + "CombatAoE!!");
    						}
    					  }
    					} else { p.sendMessage(ChatColor.YELLOW + "Cooldown!");}
    				} else { p.sendMessage(ChatColor.BLUE + "Not enough Energy!");}
    			} else { p.sendMessage(ChatColor.RED + "No perm!!!"); }									
    		}
    }

}
