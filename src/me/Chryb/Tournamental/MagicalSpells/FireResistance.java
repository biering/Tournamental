package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireResistance {
	
	public static Tournamental plugin;
	public FireResistance(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "The Player is a short time fire resistance!";
    }

    public static String getName()
    {
        return "fireresistance";
    }
	
	public static void onCast(Player p, Player target){
		//Der Spieler erhält kurze Zeit über komplette Feuerresistenz
		//Kosten = 2
		//[LMB]
		if (HasPlayerAbility.hasAbility(p, "fireresi")) { 	//If class = heal
			if (p.getFoodLevel() > 5){																					//If Food = 6+
				if ((plugin.getClassesConfig().getDouble("Classes.Cooldown.Heal.Fireresi") + 20000 < p.getWorld().getFullTime())){ //If cooldown ok
					p.setFoodLevel(p.getFoodLevel() - 6);															//Food = Food -6
					plugin.getClassesConfig().set("Classes.Cooldown.Heal.Fireresi", p.getWorld().getFullTime());			//Cd set back
				   	p.sendMessage(ChatColor.GOLD + target.getDisplayName() + "got FireResistance");							//Message to caster(Player)
					target.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 100));
				}else{p.sendMessage(ChatColor.YELLOW + "Cooldown!");}												//CD goes wrong
			}else{ p.sendMessage(ChatColor.YELLOW + "Not enough Mana!");}											//not enough Mana
		}else{																										//If not a Heal
			if (p.getFoodLevel() > 11){																				//If Food 6+
				p.setFoodLevel(p.getFoodLevel() - 12);																//Food = Food -6
				plugin.getClassesConfig().set("Classes.Cooldown.Heal.Fireresi", p.getWorld().getFullTime());			//Cd set back
			   	p.sendMessage(ChatColor.GOLD + target.getDisplayName() + "got FireResistance");							//Message to caster(Player)
				target.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 200));
			}else{p.sendMessage(ChatColor.YELLOW + "Not enough Mana!");}												//not enough Mana
		}
	}

}
