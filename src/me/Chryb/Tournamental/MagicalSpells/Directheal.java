package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Directheal {
	
	public static Tournamental plugin;
	public Directheal(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "The Player target get an instant heal.";
    }

    public static String getName()
    {
        return "directheal";
    }
	
	public static void onCast(Player p, Player target){
		//
		//[LMB]
       //double spell_cooldown = this.getClassesConfig().getDouble("Spells.Heal.Directheal.Cooldown");
		if (HasPlayerAbility.hasAbility(p, "directheal")){
			if (p.getFoodLevel() > 1){																					
				if ((plugin.getClassesConfig().getDouble("Players."+ p.getName() +".Cooldown.Heal.Directheal") + 30 < p.getWorld().getFullTime())){ //If cooldown ok
					int sum = 4;
					if (p.getInventory().getHelmet() == (new ItemStack (314, 1))){
						sum = sum + 1;}
					if (p.getInventory().getChestplate() == (new ItemStack (315, 1))){
						sum = sum + 2;}
					if (p.getInventory().getLeggings() == (new ItemStack (316, 1))){
						sum = sum + 2;}
					if (p.getInventory().getBoots() == (new ItemStack (317, 1))){
						sum = sum + 1;}
					if (p.getInventory().contains(2264)){
						sum = sum + 2;}
					p.setFoodLevel(p.getFoodLevel() - 2);															//Food = Food -2
					plugin.getClassesConfig().set("Players." + p.getName() + ".Cooldown.Heal.Directheal", p.getWorld().getFullTime()); 	//Cd set back										//Message to target
				   	p.sendMessage(ChatColor.GREEN + target.getDisplayName() + ": + " + sum + " Health!!");						//Message to caster(Player)
					if (target.getName() != p.getName()){
						p.sendMessage(ChatColor.DARK_GREEN + target.getDisplayName() + "-healed");
					}
				   	if (target.getHealth() > (20 - sum)){																	
						target.setHealth(20);
					}																								//Function for Healing
					if (target.getHealth() < (20 - sum + 1)){
						target.setHealth(target.getHealth() + sum);
					}
				}else{ p.sendMessage(ChatColor.YELLOW + "Cooldown!");}												//CD goes wrong
			}else{ p.sendMessage(ChatColor.BLUE + "Not enough Mana!");}												//not enough Mana
		}else{																										//If not a Heal
			if (p.getFoodLevel() > 1){																				//If Food 2+
				int sum = 2;																						//1 Heart heal
				p.setFoodLevel(p.getFoodLevel() - 2);																//Food = Food -2
				plugin.getClassesConfig().set("Players." + p.getName() + ".Cooldown.Heal.Directheal", p.getWorld().getFullTime());	//Cd set back
				target.sendMessage(ChatColor.GREEN + "+" + sum + "Health");										//Message to target
				if (target.getName() != p.getName()){
					p.sendMessage(ChatColor.DARK_GREEN + target.getDisplayName() + "-healed");
				}																								//Message to caster(Player)
				if (target.getHealth() > (20 - sum)){
					target.setHealth(20);
				}																								//Function for Healing
				if (target.getHealth() < (20 - sum + 1)){
					target.setHealth(target.getHealth() + sum);
				}
			}else{ p.sendMessage(ChatColor.BLUE + "Not enough Mana!");}											//not enough Mana
		}
	}

}
