package me.Chryb.Tournamental.MagicalSpells;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

public class Slow {
	
	public static Tournamental plugin;
	public Slow(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "Grundidee ist, dass alle Gegner in einem kleinen Radius verlangsamt werden";
    }

    public static String getName()
    {
        return "slow";
    }
	
	 public static void onCast(Player p){
			//
			//[Shift]+[RMB]
		if (HasPlayerAbility.hasAbility(p, "Slow")){
			if (p.getFoodLevel() > 2){																					
				if ((plugin.getPlayersConfig().getDouble("Players."+ p.getName() +".Cooldown.Combat.Slow") + 30 < p.getWorld().getFullTime())){ //If cooldown ok
					plugin.getPlayersConfig().set("Players." + p.getName() + ".Cooldown.Combat.Slow", p.getWorld().getFullTime()); 	//Cd set back
					List<Entity> list = p.getNearbyEntities(4, 4, 4);
					int size = list.size();
					  for (int i=0; i < size; i++){
						Entity e = list.get(i);
						if (e instanceof Monster){
							Monster monster = (Monster)e;
						    monster.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 1));
						    p.sendMessage(ChatColor.GOLD + "Slowed!!");
						}
						if (e instanceof Player){
							Player player = (Player)e;
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 1));
							p.sendMessage(ChatColor.GOLD + "Slowed!!");
						}
					  }
					} else { p.sendMessage(ChatColor.YELLOW + "Cooldown!");}
				} else { p.sendMessage(ChatColor.BLUE + "Not enough Energy!");}
			} else { p.sendMessage(ChatColor.RED + "No perm!!!"); }									
		}
	


}
