package me.Chryb.Tournamental.Listener;

import me.Chryb.Tournamental.Tournamental;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class ClassSignListener implements Listener{
	
	public static Tournamental plugin;
	public ClassSignListener(Tournamental plugin) {
		ClassSignListener.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onSignChange(SignChangeEvent event) {
        Player p = event.getPlayer();
        // Action a = (event.)
        if(event.getLine(0).contains("[Mage]")){
             event.setLine(0, "¤6[Mage]");
             event.setLine(1, "¤1Class");
             p.sendMessage(ChatColor.BLUE + "Mage-Sign successfully created!");
        }
        if(event.getLine(0).contains("[Tank]")){
            event.setLine(0, "¤6[Tank]");
            event.setLine(1, "¤1Class");
            p.sendMessage(ChatColor.BLUE + "Tank-Sign successfully created!");
       }
        if(event.getLine(0).contains("[Combat]")){
            event.setLine(0, "¤6[Combat]");
            event.setLine(1, "¤1Class");
            p.sendMessage(ChatColor.BLUE + "Combat-Sign successfully created!");
       }
        if(event.getLine(0).contains("[Archer]")){
            event.setLine(0, "¤6[Archer]");
            event.setLine(1, "¤1Class");
            p.sendMessage(ChatColor.BLUE + "Archer-Sign successfully created!");
       }
        if(event.getLine(0).contains("[Heal]")){
            event.setLine(0, "¤6[Heal]");
            event.setLine(1, "¤1Class");
            p.sendMessage(ChatColor.BLUE + "Heal-Sign successfully created!");
       }
    }
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onClassSignClick(PlayerInteractEvent event){
		 Action action = event.getAction();
		 Player player = event.getPlayer();
		 if (action.equals(Action.RIGHT_CLICK_BLOCK)){
			 if ((event.getClickedBlock().getTypeId() == 63) || (event.getClickedBlock().getTypeId() == 68)) {
	              Sign sign = (Sign) event.getClickedBlock().getState();
	              if(sign.getLine(0).contains("Mage")) {
	            	  //Mage-Sign
	            	  plugin.setClass(player, "mage");
	            	  player.sendMessage(ChatColor.YELLOW + "Now, you are in the Class Mage!!");
		          }
	              if(sign.getLine(0).contains("Combat")){
	            	//Combat-Sign
	            	  plugin.setClass(player, "combat");
	            	  player.sendMessage(ChatColor.YELLOW + "Now, you are in the Class Combat!!");
	              }
	              if(sign.getLine(0).contains("Archer")){
		            	//Archer-Sign
		            	  plugin.setClass(player, "archer");
		            	  player.sendMessage(ChatColor.YELLOW + "Now, you are in the Class Archer!!");
		          }
	              if(sign.getLine(0).contains("Tank")){
		            	//Tank-Sign
		            	  plugin.setClass(player, "tank");
		            	  player.sendMessage(ChatColor.YELLOW + "Now, you are in the Class Tank!!");
		          }
	              if(sign.getLine(0).contains("Heal")){
		            	//Heal-Sign
		            	  plugin.setClass(player, "heal");
		            	  player.sendMessage(ChatColor.YELLOW + "Now, you are in the Class Heal!!");
		          }
	 }
		 }
	}

}
