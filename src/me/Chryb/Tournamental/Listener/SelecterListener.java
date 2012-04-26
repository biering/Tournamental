package me.Chryb.Tournamental.Listener;

import me.Chryb.Tournamental.Tournamental;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SelecterListener implements Listener{
	
	public static Tournamental plugin;
	public SelecterListener(Tournamental plugin) {
		SelecterListener.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	 public void onSelecterLMB(PlayerInteractEvent event){
	 Action action = event.getAction();
	 Player player = event.getPlayer();
	 if (player.getItemInHand().getTypeId() != 0){
	 ItemStack i = event.getItem();
	 if (action.equals(Action.LEFT_CLICK_BLOCK)){
		if (i.getTypeId() == 275){
			Location l = event.getClickedBlock().getLocation();
			plugin.posx1 = l.getX();
			//plugin.posx1 = plugin.customRound(plugin.posx1);
			plugin.posy1 = l.getY();
			//plugin.posy1 = plugin.customRound(plugin.posy1);
			plugin.posz1 = l.getZ();
			//plugin.posz1 = plugin.customRound(plugin.posz1);
		}
	 }
	 }
	}
	 
	 @EventHandler(priority = EventPriority.HIGH)
	 public void onSelecterRMB(PlayerInteractEvent event){
	 Action action = event.getAction();
	 Player player = event.getPlayer();
	 if (player.getItemInHand().getTypeId() != 0){
	 ItemStack i = event.getItem();
	 if ((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) && !player.isSneaking()){
		 if (i.getTypeId() == 275){
				Location l = event.getClickedBlock().getLocation();
				plugin.posx2 = l.getX();
				//plugin.posx2 = plugin.customRound(plugin.posx2);
				plugin.posy2 = l.getY();
				//plugin.posy2 = plugin.customRound(plugin.posy2);
				plugin.posz2 = l.getZ();
				//plugin.posz2 = plugin.customRound(plugin.posz2);
			}
	 }
	}
}

}
