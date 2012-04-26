package me.Chryb.Tournamental.Listener;

import me.Chryb.Tournamental.Tournamental;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PvPListener implements Listener{
	public static Tournamental plugin;
	public PvPListener(Tournamental plugin) {
		PvPListener.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamage (EntityDamageEvent event1) {
  if (plugin.getConfig().getBoolean("Config.CityPvP.Enable") == false){
	
	  if (event1.getCause() == DamageCause.ENTITY_ATTACK) {
	EntityDamageByEntityEvent event = (EntityDamageByEntityEvent)event1;
	Entity wounded = event1.getEntity();
	Entity Damager = event.getDamager();
	
	if (Damager instanceof Player) {
	//Player Attacker = (Player)Damager;
	if (wounded instanceof Player) {
	//Player PWounded = (Player)wounded;
	//Location l = PWounded.getLocation();
	
	//if (plugin.inFightingZone(l)){
	/*	PWounded.sendMessage("You are in the fighting zone!!");
		event.setCancelled(false);
	}*/
	} else {
	}
	}
	}
	}
    }
}
