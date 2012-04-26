package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;

public class SmallFireballs {
	
	public static Tournamental plugin;
	public SmallFireballs(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "The Player shoots SmallFireballs";
    }

    public static String getName()
    {
        return "smallfireballs";
    }
	
    public static void onCast(Player p, int amount){
    	if (HasPlayerAbility.hasAbility(p, "smallfireball")){
    		for (int i = 0; i < amount; i++){
    SmallFireball sfb = p.getWorld().spawn(p.getLocation().add(0, 3, 0), SmallFireball.class);
  	sfb.setShooter(p.getPlayer());
    sfb.setYield(3);
    		}
    	}
    }

}
