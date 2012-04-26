package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealOverTime {
	
	public static Tournamental plugin;
	public HealOverTime(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "Das Target erhaelt einen HoT. Wenn kein Spieler im Target hottet sich der Caster selbst";
    }

    public static String getName()
    {
        return "healovertime";
    }
	
	public static void onCast(Player p, Player target){
		//.
		//[RMB]
		if (HasPlayerAbility.hasAbility(p, "healovertime")) {
			if (p.getFoodLevel() > 0){																					//If Food = 6+
				if ((plugin.getClassesConfig().getDouble("Classes.Cooldown.Heal.HealoverTime") + 150 < p.getWorld().getFullTime())){ //If cooldown ok
					int time = 30;		
				
					if (p.getInventory().getHelmet() == (new ItemStack (314, 1))){
						time = time + 10;	
					}
					if (p.getInventory().getChestplate() == (new ItemStack (315, 1))){
						time = time + 20;	
					}
					if (p.getInventory().getLeggings() == (new ItemStack (316, 1))){
						time = time + 20;	
					}
					if (p.getInventory().getBoots() == (new ItemStack (317, 1))){
						time = time + 10;	
					}
					if (p.getInventory().contains(2264)){
						time = time + 20;	
					}
					p.setFoodLevel(p.getFoodLevel() - 1);															//Food = Food -6
					plugin.getClassesConfig().set("Classes.Cooldown.Heal.HealoverTime", p.getWorld().getFullTime());			//Cd set back
					target.sendMessage(ChatColor.DARK_RED + "get hot");												//Message to target
				   	p.sendMessage(ChatColor.DARK_AQUA + target.getDisplayName() + "hotted");							//Message to caster(Player)
					//target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, sum, 1));
				}else{p.sendMessage(ChatColor.YELLOW + "Cooldown!");}												//CD goes wrong
			}else{ p.sendMessage(ChatColor.YELLOW + "Not enough Mana!");}											//not enough Mana
		}else{																										//If not a Heal
			if (p.getFoodLevel() > 2){																				//If Food 6+
				int sum = 20;																						//2er Dot
				p.setFoodLevel(p.getFoodLevel() - 3);																//Food = Food -6
				plugin.getClassesConfig().set("Classes.Cooldown.Heal.HeloverTime", p.getWorld().getFullTime());				//Cd set back
				target.sendMessage(ChatColor.DARK_RED + "get hot");													//Message to target
			   	p.sendMessage(ChatColor.DARK_AQUA + target.getDisplayName() + "hotted");							//Message to caster(Player)
				target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, sum, 1));
			}else{p.sendMessage(ChatColor.YELLOW + "Not enough Mana!");}												//not enough Mana
		}
	}

}
