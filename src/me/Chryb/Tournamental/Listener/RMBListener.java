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
import me.Chryb.Tournamental.MagicalSpells.Thunder;

public class RMBListener implements Listener{
	
	public static Tournamental plugin;
	public static Slow slow;
	public RMBListener(Tournamental plugin) {
		RMBListener.plugin = plugin;
	}
	
	 @EventHandler(priority = EventPriority.HIGH)	//RMB Click
	 public void onRMB(PlayerInteractEvent event){
	 Action action = event.getAction();
	 Player player = event.getPlayer();
	 if (player.getItemInHand().getTypeId() != 0){
	 ItemStack i = event.getItem();
	 if ((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) && !player.isSneaking()){
	 	//smallfireball - Class Mage
		 if (i.getTypeId()==280){
			 Thunder.onCast(player);
		 }
		 if (i.getTypeId()==281){
			 plugin.fun(player);
		 }
		 if (i.getTypeId()==282){
			// plugin.smallfireball(player, 20);
		 }
	 }
	}
}

}
