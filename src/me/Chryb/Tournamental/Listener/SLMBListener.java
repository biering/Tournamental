package me.Chryb.Tournamental.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.MagicalSpells.Slow;

public class SLMBListener implements Listener{
	
	public static Tournamental plugin;
	public static Slow slow;
	public SLMBListener(Tournamental plugin) {
		SLMBListener.plugin = plugin;
	}
	
	 @EventHandler(priority = EventPriority.HIGH)	//Shift + LMB
	 public void onLMBSHIFT(PlayerInteractEvent event){
	 Action action = event.getAction();
	 Player player = event.getPlayer();
	 if (player.getItemInHand().getTypeId() != 0){
     ItemStack i = event.getItem();
	 if ((action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) && player.isSneaking()){
		
		 if (i.getTypeId()==280){
			// plugin.fireball(player);
		 }
	 }
  }
} 

}
