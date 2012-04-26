package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Jumpback {
	
	public static Tournamental plugin;
	public Jumpback(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "Der Spieler springt zurueck und erhaelt sehr kurze Zeit eine hoehere Laufgeschwindigkeit";
    }

    public static String getName()
    {
        return "jumpback";
    }
	
	
	public static void onCast(Player p){

		//[Shift]+[RMB]
	if (HasPlayerAbility.hasAbility(p, "Jumpback")){
		if (p.getFoodLevel() > 2){																					
			if ((plugin.getPlayersConfig().getDouble("Players."+ p.getName() +".Cooldown.Combat.Jumpback") + 30 < p.getWorld().getFullTime())){ //If cooldown ok
				plugin.getPlayersConfig().set("Players." + p.getName() + ".Cooldown.Combat.Jumpback", p.getWorld().getFullTime()); 	//Cd set back
				
				/*Vector v = p.getLocation().toVector();
				Location target_l = p.getTargetBlock(null, Integer.MAX_VALUE).getLocation();
				
				v.setX(v.getX()-target_l.getX());
				v.setY(v.getY()-target_l.getY());
				v.setZ(v.getZ()-target_l.getZ());
				
				Location l = v.toLocation(p.getWorld());
				
				p.teleport(l);*/
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 2));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 2));
				
				} else { p.sendMessage(ChatColor.YELLOW + "Cooldown!");}
			} else { p.sendMessage(ChatColor.BLUE + "Not enough Energy!");}
		} else { p.sendMessage(ChatColor.RED + "No perm!!!"); }									
	}

}
