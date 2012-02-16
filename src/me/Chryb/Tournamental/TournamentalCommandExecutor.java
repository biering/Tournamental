package me.Chryb.Tournamental;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TournamentalCommandExecutor implements CommandExecutor {
	
	private Tournamental plugin;
	 
	public TournamentalCommandExecutor(Tournamental plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if (!(sender instanceof Player)) {
	           sender.sendMessage(ChatColor.RED + "You must be a player!");
	           return true;
	        }
    
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("defzone")){
			if (args.length < 1){
				p.sendMessage(ChatColor.RED + "Too few arguments!!!");
				return true;
			}
			if (args.length > 1){
				p.sendMessage(ChatColor.RED + "Too many arguments!!!");
				return true;
			}
			if (args.length == 1){
				String zone_name = args[0];
				if ((plugin.posx1 == 0.0) && (plugin.posx2 == 0.0) && (plugin.posz1 == 0.0) && (plugin.posz2 == 0.0)){
					p.sendMessage(ChatColor.RED + "You have to type the positions!");
					return true;
				} else {
					plugin.defineZone(zone_name);
					p.sendMessage(ChatColor.BLUE + "New Zone " + zone_name + " registered!");
					return true;
				}
			}
		}
		
		//POS1
				if (cmd.getName().equalsIgnoreCase("tpos1")){
					if(args.length == 0){
						plugin.posx1 = p.getLocation().getX();
						plugin.posx1 = plugin.customRound(plugin.posx1);
						plugin.posy1 = p.getLocation().getY();
						plugin.posy1 = plugin.customRound(plugin.posy1);
						plugin.posz1 = p.getLocation().getZ();
						plugin.posz1 = plugin.customRound(plugin.posz1);
						p.sendMessage(ChatColor.AQUA + "[CitySytem]" + ChatColor.RED + " Pos1" + ChatColor.WHITE + " saved.");
						p.sendMessage("x = " + plugin.posx1);
						p.sendMessage("y = " + plugin.posy1);
						p.sendMessage("z = " + plugin.posz1);
					
						return true;
					} else {
						return false;
					}
				}
				
				//POS2
				if (cmd.getName().equalsIgnoreCase("tpos2")){
					if(args.length == 0){
						plugin.posx2 = p.getLocation().getX();
						plugin.posx2 = plugin.customRound(plugin.posx2);
						plugin.posy2 = p.getLocation().getY();
						plugin.posy2 = plugin.customRound(plugin.posy2);
						plugin.posz2 = p.getLocation().getZ();
						plugin.posz2 = plugin.customRound(plugin.posz2);
						p.sendMessage(ChatColor.DARK_AQUA + "[CitySytem]" + ChatColor.RED + " Pos2" + ChatColor.WHITE + " saved.");
						p.sendMessage("x = " + plugin.posx2);
						p.sendMessage("y = " + plugin.posy2);
						p.sendMessage("z = " + plugin.posz2);
						return true;
					} else {
						return false;
					}
				}
				
				//VERT
				if (cmd.getName().equalsIgnoreCase("tvert")){
					if (p.hasPermission("CitySystem.vert")) {
					  if(args.length == 0){
						plugin.posy1 = 0.0;
						plugin.posy2 = 128.0;
						p.sendMessage("Blocks selected from top to bottom!!");
						return true;
					} else {
						return false;
					}
				} else {
					p.sendMessage(ChatColor.RED + "You don't have the permission to do that!!!"); 
					return true;
				}
			}
		
		
		
		return false;
	}

}
