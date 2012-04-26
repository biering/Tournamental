package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Dot {
	
	public static Tournamental plugin;
	public Dot(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "Das Target erhaelt einen Debuff (siehe Poison)!";
    }

    public static String getName()
    {
        return "dot";
    }
	
	public static void onCast(Player p, Player target){
		
		if (HasPlayerAbility.hasAbility(p, "dot")) {
			if (p.getFoodLevel() > 5){																					//If Food = 6+
				if ((plugin.getClassesConfig().getDouble("Classes.Cooldown.Heal.Dot") + 150 < p.getWorld().getFullTime())){ //If cooldown ok
					int eqippeditems = 0;		
				
					if (p.getInventory().getHelmet() == (new ItemStack (314, 1))){
						eqippeditems++;	
					}
					if (p.getInventory().getChestplate() == (new ItemStack (315, 1))){
						eqippeditems++;	
					}
					if (p.getInventory().getLeggings() == (new ItemStack (316, 1))){
						eqippeditems++;	
					}
					if (p.getInventory().getBoots() == (new ItemStack (317, 1))){
						eqippeditems++;	
					}
					if (p.getInventory().contains(2264)){
						eqippeditems++;	
					}
					int sum = 2;
					if (eqippeditems > 1){
						sum = sum + 2;
						if (eqippeditems > 3){
							sum = sum + 3;
						}
					}
					p.setFoodLevel(p.getFoodLevel() - 6);															//Food = Food -6
					plugin.getClassesConfig().set("Classes.Cooldown.Heal.Dot", p.getWorld().getFullTime());			//Cd set back
					target.sendMessage(ChatColor.DARK_RED + "dotted");												//Message to target
				   	p.sendMessage(ChatColor.DARK_AQUA + target.getDisplayName() + "dotted");							//Message to caster(Player)
					target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, sum, 1));
				}else{p.sendMessage(ChatColor.YELLOW + "Cooldown!");}												//CD goes wrong
			}else{ p.sendMessage(ChatColor.YELLOW + "Not enough Mana!");}											//not enough Mana
		}else{																										//If not a Heal
			if (p.getFoodLevel() > 5){																				//If Food 6+
				int sum = 2;																						//2er Dot
				p.setFoodLevel(p.getFoodLevel() - 6);																//Food = Food -6
				plugin.getClassesConfig().set("Classes.Cooldown.Heal.Dot", p.getWorld().getFullTime());				//Cd set back
				target.sendMessage(ChatColor.DARK_RED + "dotted");													//Message to target
			   	p.sendMessage(ChatColor.DARK_AQUA + target.getDisplayName() + "dotted");							//Message to caster(Player)
				target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, sum, 1));
			}else{p.sendMessage(ChatColor.YELLOW + "Not enough Mana!");}												//not enough Mana
		}
	}

}
