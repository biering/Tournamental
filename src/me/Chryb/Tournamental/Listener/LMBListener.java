package me.Chryb.Tournamental.Listener;

import me.Chryb.Tournamental.Tournamental;
import me.Chryb.Tournamental.MagicalSpells.Slow;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class LMBListener implements Listener{

	public static Tournamental plugin;
	public static Slow slow;
	public LMBListener(Tournamental plugin) {
		LMBListener.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		String p_s = p.getName();
		if (plugin.getPlayersConfig().getBoolean("Players." + p.getName() + ".registered") != true){
			plugin.registerPlayer(p_s);
			p.sendMessage(ChatColor.BLUE + "[Tournamental]" + ChatColor.WHITE +  " Now, you are registered!");
		} else {
			p.sendMessage(ChatColor.BLUE + "[Tournamental]" + ChatColor.WHITE + " You are already registered!");
		}
		
	}
	
	 @EventHandler(priority = EventPriority.HIGH) //LMB Click
	 public void onLMB(PlayerInteractEvent event){
	 Action action = event.getAction();
	 Player player = event.getPlayer();
	 if (player.getItemInHand().getTypeId() != 0){
	    if ((action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) && !player.isSneaking()){
	    	
	    	
	    }
	 }
	        
}
	 

	 

	 

	 
}