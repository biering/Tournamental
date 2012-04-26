package me.Chryb.Tournamental.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.MagicalSpells.Slow;

public class SRMBListener implements Listener{
	
	public static Tournamental plugin;
	public static Slow slow;
	public SRMBListener(Tournamental plugin) {
		SRMBListener.plugin = plugin;
	}
	
	 @EventHandler(priority = EventPriority.HIGH)	//Shift + RMB
	 public void onRMBSHIFT(PlayerInteractEvent event){
	 Action action = event.getAction();
	 Player player = event.getPlayer();
	 if (player.getItemInHand().getTypeId() != 0){
    //ItemStack i = event.getItem();
	 if ((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) && player.isSneaking()){
		//fireresi - Class Heal
	 		
	 }
	}
}

}
