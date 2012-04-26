package me.Chryb.Tournamental;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private Tournamental plugin;
	 
	public Commands(Tournamental plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if (!(sender instanceof Player)) {
	           sender.sendMessage(ChatColor.RED + "You must be a player!");
	           return true;
	        }
		
		
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("addlobby")){
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
				//	plugin.addLobby(zone_name);
					p.sendMessage(ChatColor.BLUE + "New Zone " + zone_name + " registered!");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("addfight")){
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
					//plugin.addFight(zone_name);
					p.sendMessage(ChatColor.BLUE + "New Zone " + zone_name + " registered!");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("addteam")){
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
					//plugin.addTeam(zone_name);
					p.sendMessage(ChatColor.BLUE + "New Zone " + zone_name + " registered!");
					return true;
				}
			}
		}
		//DEFZONE
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
		
		//ADDCLASS
		if (cmd.getName().equalsIgnoreCase("addclass")){
			if (args.length < 2){
				p.sendMessage(ChatColor.RED + "Too few arguments!!!");
				return true;
			}
			if (args.length > 2){
				p.sendMessage(ChatColor.RED + "Too many arguments!!!");
				return true;
			}
			if (args.length == 2){
				String target_p = args[0];
				Player target = plugin.getServer().getPlayer(target_p);
				String target_c = args[1];
				plugin.setClass(target, target_c);
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("getlevel")){
			if (p.getName().equalsIgnoreCase("Chr4st0ph")){
				p.sendMessage(ChatColor.DARK_PURPLE + "A real god never needs a level!");
			}else{
				if (p.getName().equalsIgnoreCase("Painter21")){
				if(args.length == 0){
					  p.sendMessage(ChatColor.YELLOW + "Not enough Arguments!");
					  return false;
				  }
				  if (args.length > 1){
					  p.sendMessage(ChatColor.YELLOW + "Too Many Arguments!");
					  return false;
				  }
				  if (args.length == 1){
						  p.sendMessage("passt");
						  p.setLevel(Integer.parseInt(args[0]));
						  return true;
				  }
				  p.sendMessage("Bug!");
				}else{ p.sendMessage("you dont have the permission to do that!");}
			}
			  
		  }
		return false;
	}

}
