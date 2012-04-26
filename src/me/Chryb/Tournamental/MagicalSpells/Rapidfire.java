package me.Chryb.Tournamental.MagicalSpells;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Rapidfire {
	
	public static Tournamental plugin;
	public Rapidfire(Tournamental instance){
		 plugin = instance;
		}
	
	public static String getDescription()
    {
        return "...";
    }

    public static String getName()
    {
        return "rapidfire";
    }
	
    public static void onCast(Player p){
    	if (HasPlayerAbility.hasAbility(p, "rapidfire"))	{
    	    Vector pa = p.getLocation().getDirection();
    		Vector sd = plugin.playerdirection(p);
    		double x = 0;
    		double z = 0;
    		if (Math.random() < 0.5){
    			x = 1/(Math.pow(Math.pow(sd.getX(), 2)/(Math.pow(sd.getZ(), 2)) + 1, 0.5));
    			z = 1/(Math.pow(Math.pow(sd.getZ(), 2)/(Math.pow(sd.getX(), 2)) + 1, 0.5));
    		}
    		Entity e = p.getWorld().spawnArrow(p.getLocation().add(x,0,z), pa, (float)5, (float)2);
    		e.setVelocity(sd.multiply(5));
    		p.getWorld().getEntities().add(e);
    		//Der Spieler schießt ein Strahl aus Pfeilen mit grossem Spread (ca 10 Stueck)
    		//[Shift]+[LMB]
		p.sendMessage("not implemented");
    	}
	}

}
