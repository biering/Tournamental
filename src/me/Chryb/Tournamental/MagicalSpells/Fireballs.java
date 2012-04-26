package me.Chryb.Tournamental.MagicalSpells;

//import org.bukkit.entity.Fireball;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

public class Fireballs {
	
	public static Tournamental plugin;
	public Fireballs(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "You shoot a fireball!";
    }

    public static String getName()
    {
        return "fireball";
    }
	
	   public static void onCast(Player p){
	        if (HasPlayerAbility.hasAbility(p, "fireball")){
	 	      Fireball fb = p.getWorld().spawn(p.getLocation().add(0, 3, 0), Fireball.class);
	   	      fb.setShooter(p.getPlayer());
	          fb.setYield(3);
	        }
	     }
	


}
