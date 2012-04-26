package me.Chryb.Tournamental;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.Chryb.Tournamental.Listener.ClassSignListener;
import me.Chryb.Tournamental.Listener.LMBListener;
import me.Chryb.Tournamental.Listener.RMBListener;
import me.Chryb.Tournamental.Listener.SLMBListener;
import me.Chryb.Tournamental.Listener.SRMBListener;
import me.Chryb.Tournamental.Listener.SelecterListener;
import me.Chryb.Tournamental.Listener.PvPListener;
import me.Chryb.Tournamental.Listener.ZonesListener;
import me.Chryb.Tournamental.MagicalSpells.CombatAoE;
import me.Chryb.Tournamental.MagicalSpells.Directheal;
import me.Chryb.Tournamental.MagicalSpells.Dot;
import me.Chryb.Tournamental.MagicalSpells.FireResistance;
import me.Chryb.Tournamental.MagicalSpells.Fireballs;
import me.Chryb.Tournamental.MagicalSpells.HealOverTime;
import me.Chryb.Tournamental.MagicalSpells.Jumpback;
import me.Chryb.Tournamental.MagicalSpells.Rapidfire;
import me.Chryb.Tournamental.MagicalSpells.Slow;
import me.Chryb.Tournamental.MagicalSpells.SmallFireballs;
import me.Chryb.Tournamental.MagicalSpells.Thunder;
import me.Chryb.Tournamental.Utilities.Cast;
import me.Chryb.Tournamental.Utilities.GetPlayersClass;
import me.Chryb.Tournamental.Utilities.HasPlayerAbility;
import me.Chryb.Tournamental.Utilities.MathUtil;
import me.Chryb.Tournamental.Zones.FightingZone.DefineFightingZone;
import me.Chryb.Tournamental.Zones.FightingZone.InFightingZone;
import me.Chryb.Tournamental.Zones.Lobby.DefineLobbyZone;
import me.Chryb.Tournamental.Zones.Lobby.InLobbyZone;
import me.Chryb.Tournamental.Zones.TeamZone.DefineTeamZone;
import me.Chryb.Tournamental.Zones.TeamZone.InTeamZone;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.bukkit.inventory.ItemStack;

public class Tournamental extends JavaPlugin {
	public final LMBListener lmbListener = new LMBListener(this);
	public final RMBListener rmbListener = new RMBListener(this);
	public final SLMBListener slmbListener = new SLMBListener(this);
	public final SRMBListener srmbListener = new SRMBListener(this);
	public final ZonesListener zonesListener = new ZonesListener(this);
	public final SelecterListener selecterListener = new SelecterListener(this);
	public final ClassSignListener blockListener = new ClassSignListener(this);
	public final PvPListener entityListener = new PvPListener(this);
	public final HasPlayerAbility hasPlayerAbility = new HasPlayerAbility(this);
	public final GetPlayersClass getPlayersClass = new GetPlayersClass(this);
	public final Slow slow = new Slow(this);
	public final Fireballs fireball = new Fireballs(this);
	public final SmallFireballs smallFireball = new SmallFireballs(this);
	public final Thunder thunder = new Thunder(this);
	public final FireResistance fireResistance = new FireResistance(this);
	public final Directheal directheal = new Directheal(this);
	public final MathUtil mathUtil = new MathUtil(this);
	public final HealOverTime healOverTime = new HealOverTime(this);
	public final Dot dot = new Dot(this);
	public final Jumpback jumpback = new Jumpback(this);
	public final CombatAoE combatAoE = new CombatAoE(this);
	public final Rapidfire rapidfire = new Rapidfire(this);
	public final Cast cast = new Cast(this);
	public final DefineLobbyZone defineLobbyZone = new DefineLobbyZone(this);
	public final DefineTeamZone defineTeamZone = new DefineTeamZone(this);
	public final DefineFightingZone defineFightingZone = new DefineFightingZone(this);
	public final InFightingZone inFightingZone = new InFightingZone(this);
	public final InTeamZone inTeamZone = new InTeamZone(this);
	public final InLobbyZone inLobbyZone = new InLobbyZone(this);
	
	
	
	
	private FileConfiguration classesConfig = null;
	private File classesConfigFile = null;
	private FileConfiguration playersConfig = null;
	private File playersConfigFile = null;
	private FileConfiguration zonesConfig = null;
	private File zonesConfigFile = null;
	
	private Commands cmdsExecutor;

	@Override
	public void onDisable() {
		System.out.println("[Tournamental] Plugin disabled!");
	}

	@Override
	public void onEnable() {
		loadConfiguration();
		registerHooks();
		//reloadConfiguration();
		cmdsExecutor = new Commands(this);
		getCommand("defzone").setExecutor(cmdsExecutor);
		getCommand("addteam").setExecutor(cmdsExecutor);
		getCommand("addfight").setExecutor(cmdsExecutor);
		getCommand("addlobby").setExecutor(cmdsExecutor);
		getCommand("addclass").setExecutor(cmdsExecutor);
		getCommand("getlevel").setExecutor(cmdsExecutor);
		System.out.println("[Tournamental] Plugin enabled!");
		loadClasses(); //Classes-Config File
		loadPlayers(); //Players-Config File
		loadZones(); //Zones-Config File
	}
	
	public void registerHooks() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(lmbListener, this);
		pm.registerEvents(rmbListener, this);
		pm.registerEvents(slmbListener, this);
		pm.registerEvents(srmbListener, this);
		pm.registerEvents(blockListener, this);
		pm.registerEvents(entityListener, this);
		pm.registerEvents(zonesListener, this);
		pm.getPlugin("Rested");
    }
	
	public double posx1 = 0.0;
	public double posy1 = 0.0;
	public double posz1 = 0.0;
	public double posx2 = 0.0;
	public double posy2 = 0.0;
	public double posz2 = 0.0;

	private void loadConfiguration(){
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	//Players-Configuration
	public void reloadPlayersConfig() {
	    if (playersConfigFile == null) {
	    playersConfigFile = new File(getDataFolder(), "players.yml");
	    }
	    playersConfig = YamlConfiguration.loadConfiguration(playersConfigFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("players.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        playersConfig.setDefaults(defConfig);
	    }
	}
	public FileConfiguration getPlayersConfig() {
	    if (playersConfig == null) {
	        reloadPlayersConfig();
	    }
	    return playersConfig;
	}
	public void savePlayersConfig() {
	    if (playersConfig == null || playersConfigFile == null) {
	    return;
	    }
	    try {
	    	playersConfig.save(playersConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + playersConfigFile, ex);
	    }
	}
	private void loadPlayers(){
		this.getPlayersConfig().options().copyDefaults(true);
		this.savePlayersConfig();
	}
	
	//Classes-Configuration
	public void reloadClassesConfig() {
	    if (classesConfigFile == null) {
	    classesConfigFile = new File(getDataFolder(), "classes.yml");
	    }
	    classesConfig = YamlConfiguration.loadConfiguration(classesConfigFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("classes.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        classesConfig.setDefaults(defConfig);
	    }
	}
	public FileConfiguration getClassesConfig() {
	    if (classesConfig == null) {
	        reloadClassesConfig();
	    }
	    return classesConfig;
	}
	public void saveClassesConfig() {
	    if (classesConfig == null || classesConfigFile == null) {
	    return;
	    }
	    try {
	    	classesConfig.save(classesConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + classesConfigFile, ex);
	    }
	}
	private void loadClasses(){
	//if (this.getClassesConfig().getBoolean("Config.Reload?") != false){
		String[] abilities = {};
		String[] abilities1 = {};
		String[] abilities2 = {};
		String[] abilities3 = {};
		String[] abilities4 = {};
		String[] abilities5 = {};
		String[] abilities6 = {};
		String[] abilities7 = {};
		String[] abilities8 = {};
		String[] abilities9 = {};
		String[] abilities10 = {};
		String[] abilities11 = {};
		String godclass = "Classes.Godclass";
		String mage = "Classes.Mage";
		String combat = "Classes.Combat";
		String tank = "Classes.Tank";
		String heal = "Classes.Heal";
		String archer = "Classes.Archer";
		this.getClassesConfig().addDefault("Config.Reload?", false);
		//this.getClassesConfig().addDefault("Spells.Heal.Directheal.Cooldown", 150);
		
		this.getClassesConfig().addDefault(godclass + ".Abilities", abilities);
		this.getClassesConfig().addDefault(godclass + ".Ultimates", abilities1);
		
		this.getClassesConfig().addDefault(mage + ".Abilities", abilities2);
		this.getClassesConfig().addDefault(mage + ".Ultimates", abilities3);
		
		this.getClassesConfig().addDefault(combat + ".Abilities", abilities4);
		this.getClassesConfig().addDefault(combat + ".Ultimates", abilities5);
		
		this.getClassesConfig().addDefault(tank + ".Abilities", abilities6);
		this.getClassesConfig().addDefault(tank + ".Ultimates", abilities7);
		
		this.getClassesConfig().addDefault(heal + ".Abilities", abilities8);
		this.getClassesConfig().addDefault(heal + ".Ultimates", abilities9);
		
		this.getClassesConfig().addDefault(archer + ".Abilities", abilities10);
		this.getClassesConfig().addDefault(archer + ".Ultimates", abilities11);
		this.getClassesConfig().options().copyDefaults(true);
		this.saveClassesConfig();
		/*} else {
			this.getClassesConfig().options().copyDefaults(true);
			this.saveClassesConfig();
		}*/
	}
	
	
	//Zones-Config
	public void reloadZonesConfig() {
	    if (zonesConfigFile == null) {
	    zonesConfigFile = new File(getDataFolder(), "zones.yml");
	    }
	    zonesConfig = YamlConfiguration.loadConfiguration(zonesConfigFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("zones.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        zonesConfig.setDefaults(defConfig);
	    }
	}
	public FileConfiguration getZonesConfig() {
	    if (zonesConfig == null) {
	        reloadZonesConfig();
	    }
	    return zonesConfig;
	}
	public void saveZonesConfig() {
	    if (zonesConfig == null || zonesConfigFile == null) {
	    return;
	    }
	    try {
	    	zonesConfig.save(zonesConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + zonesConfigFile, ex);
	    }
	}
	private void loadZones(){
		String[] zones_list = {};
		this.getZonesConfig().addDefault("Zones.Global", zones_list);
		this.getZonesConfig().options().copyDefaults(true);
		this.saveZonesConfig();
	}
	
	//Classes-Methods
	public void setClass(Player p, String c){
		if (c.equalsIgnoreCase("godclass")){
			p.getInventory().clear();
			p.getInventory().setChestplate(new ItemStack (307, 1));
			p.getInventory().addItem(new ItemStack (276, 1));
			p.getInventory().addItem(new ItemStack (364, 64));
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "godclass" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "godclass");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
		}
		if (c.equalsIgnoreCase("mage")){
			p.getInventory().clear();
			p.getInventory().setHelmet(new ItemStack (302, 1));
			p.getInventory().setChestplate(new ItemStack (303, 1));
			p.getInventory().setLeggings(new ItemStack (304, 1));
			p.getInventory().setBoots(new ItemStack (305, 1));
			p.getInventory().addItem(new ItemStack (369, 1));
			p.getInventory().addItem(new ItemStack (364, 64));
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "mage" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "mage");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
		}
		if (c.equalsIgnoreCase("heal")){
			p.getInventory().clear();
			p.getInventory().setHelmet(new ItemStack (314, 1));
			p.getInventory().setChestplate(new ItemStack (315, 1));
			p.getInventory().setLeggings(new ItemStack (316, 1));
			p.getInventory().setBoots(new ItemStack (317, 1));
			p.getInventory().addItem(new ItemStack (283, 1));
			p.getInventory().addItem(new ItemStack (261, 1));
			p.getInventory().addItem(new ItemStack (262, 64));
			p.getInventory().addItem(new ItemStack (364, 64));
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "heal" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "heal");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
		}
		if (c.equalsIgnoreCase("tank")){
			p.getInventory().clear();
			p.getInventory().setHelmet(new ItemStack (310, 1));
			p.getInventory().setChestplate(new ItemStack (311, 1));
			p.getInventory().setLeggings(new ItemStack (312, 1));
			p.getInventory().setBoots(new ItemStack (313, 1));
			p.getInventory().addItem(new ItemStack (267, 1));
			p.getInventory().addItem(new ItemStack (364, 64));
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "tank" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "tank");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
		}
		if (c.equalsIgnoreCase("combat")){
			p.getInventory().clear();
			p.getInventory().setHelmet(new ItemStack (306, 1));
			p.getInventory().setChestplate(new ItemStack (307, 1));
			p.getInventory().setLeggings(new ItemStack (308, 1));
			p.getInventory().setBoots(new ItemStack (309, 1));
			p.getInventory().addItem(new ItemStack (276, 1));
			p.getInventory().addItem(new ItemStack (364, 64));
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "combat" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "combat");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
		}
		if (c.equalsIgnoreCase("archer")){
			p.getInventory().clear();
			p.getInventory().setHelmet(new ItemStack (298, 1));
			p.getInventory().setChestplate(new ItemStack (299, 1));
			p.getInventory().setLeggings(new ItemStack (300, 1));
			p.getInventory().setBoots(new ItemStack (301, 1));
			p.getInventory().addItem(new ItemStack (261, 1));
			p.getInventory().addItem(new ItemStack (262, 256));
			p.getInventory().addItem(new ItemStack (272, 1));
			p.getInventory().addItem(new ItemStack (364, 64));
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "archer" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "archer");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
			return;
		}
		if (c.equalsIgnoreCase("none")){
			p.getInventory().clear();
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "none" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "none");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
			return;
		}
		return;
	}
	public void setRawClass(Player p, String c){
		if (c.equalsIgnoreCase("godclass")){
			p.sendMessage("The player has now the class: " + ChatColor.GREEN + "godclass" + ChatColor.WHITE + "!!");
			this.getPlayersConfig().set("Players." + p.getName() + ".class", "godclass");
			this.getPlayersConfig().options().copyDefaults(true);
			this.savePlayersConfig();
		}
			if (c.equalsIgnoreCase("mage")){
				p.sendMessage("The player has now the class: " + ChatColor.GREEN + "mage" + ChatColor.WHITE + "!!");
				this.getPlayersConfig().set("Players." + p.getName() + ".class", "mage");
				this.getPlayersConfig().options().copyDefaults(true);
				this.savePlayersConfig();
			}
			if (c.equalsIgnoreCase("heal")){
				p.sendMessage("The player has now the class: " + ChatColor.GREEN + "heal" + ChatColor.WHITE + "!!");
				this.getPlayersConfig().set("Players." + p.getName() + ".class", "heal");
				this.getPlayersConfig().options().copyDefaults(true);
				this.savePlayersConfig();
			}
			if (c.equalsIgnoreCase("tank")){
				p.sendMessage("The player has now the class: " + ChatColor.GREEN + "tank" + ChatColor.WHITE + "!!");
				this.getPlayersConfig().set("Players." + p.getName() + ".class", "tank");
				this.getPlayersConfig().options().copyDefaults(true);
				this.savePlayersConfig();
			}
			if (c.equalsIgnoreCase("combat")){
				p.sendMessage("The player has now the class: " + ChatColor.GREEN + "combat" + ChatColor.WHITE + "!!");
				this.getPlayersConfig().set("Players." + p.getName() + ".class", "combat");
				this.getPlayersConfig().options().copyDefaults(true);
				this.savePlayersConfig();
			}
			if (c.equalsIgnoreCase("archer")){
				p.sendMessage("The player has now the class: " + ChatColor.GREEN + "archer" + ChatColor.WHITE + "!!");
				this.getPlayersConfig().set("Players." + p.getName() + ".class", "archer");
				this.getPlayersConfig().options().copyDefaults(true);
				this.savePlayersConfig();
				return;
			}
			if (c.equalsIgnoreCase("none")){
				p.sendMessage("The player has now the class: " + ChatColor.GREEN + "none" + ChatColor.WHITE + "!!");
				this.getPlayersConfig().set("Players." + p.getName() + ".class", "none");
				this.getPlayersConfig().options().copyDefaults(true);
				this.savePlayersConfig();
				return;
			}
			return;
		}
	
	//Get-Methods

	
	//Has-Methods

	
	//register-Player
	public void registerPlayer(String p){
		String root = "Players." + p;
		this.getPlayersConfig().addDefault(root + ".level", 0);
		this.getPlayersConfig().addDefault(root + ".class", "none");
		this.getPlayersConfig().addDefault(root + ".load", 0);
		this.getPlayersConfig().addDefault(root + ".Cooldown.Heal.Directheal", 0.0);
		this.getPlayersConfig().addDefault(root + ".registered", true);
		this.getPlayersConfig().addDefault(root + ".Cooldown.Heal.World", 0);
		this.getPlayersConfig().options().copyDefaults(true);
		this.savePlayersConfig();
	}
	
	public void defineZone(String z){
		Vector pos1 = new Vector();
	    pos1.setX(posx1);
		pos1.setY(posy1);
		pos1.setZ(posz1);
		
		Vector pos2 = new Vector();
		pos2.setX(posx2);
		pos2.setY(posy2);
		pos2.setZ(posz2);
		String Zones = "Zones." + z + ".main";
		this.getZonesConfig().addDefault(Zones + ".pos1", pos1);
		this.getZonesConfig().addDefault(Zones + ".pos2", pos2);
		this.getZonesConfig().addDefault(Zones + ".city", 0);
		this.getZonesConfig().addDefault(Zones + ".lobby.pos1", 0);
		this.getZonesConfig().addDefault(Zones + ".lobby.pos2", 0);
		this.getZonesConfig().addDefault(Zones + ".fight.pos1", 0);
		this.getZonesConfig().addDefault(Zones + ".fight.pos2", 0);
		this.getZonesConfig().addDefault(Zones + ".team.pos1", 0);
		this.getZonesConfig().addDefault(Zones + ".team.pos2", 0);
		//this.getZonesConfig().getList("Zones.Global").add(z);
		this.getZonesConfig().options().copyDefaults(true);
		this.saveZonesConfig();
		return;
	}
	

	

	

	
	
	public double playerdistance(Player p, Player t){									//gibt die Distanz zwischen zwei Spielern zurück
		double distance = 	Math.pow
								(Math.pow((p.getLocation().getBlockX() - t.getLocation().getBlockX()), 2) + 
									Math.pow((p.getLocation().getBlockY() - t.getLocation().getBlockY()), 2) + 
										Math.pow((p.getLocation().getBlockZ() - t.getLocation().getBlockZ()), 2)
											, 0.5);
		return distance;
	}
	public Vector playerdirection(Player p){											//gibt die Sichtrichtung des Spielers zurück
		return new Vector (p.getLocation().getBlockX() - p.getTargetBlock(null, 1).getX(), p.getLocation().getBlockY() - p.getTargetBlock(null, 1).getY(),p.getLocation().getBlockZ() - p.getTargetBlock(null, 1).getZ());
	}
	
	//funktion zum suchen eines Players als ziel(linear) 13.Feb.2012, Paul Muench
	public static Player getTargetPlayer(final Player player) {
        return getTarget(player, player.getWorld().getPlayers());
    }
 
    public static org.bukkit.entity.Entity getTargetEntity(final org.bukkit.entity.Entity entity) {
        return getTarget(entity, entity.getWorld().getEntities());
    }
 
    public static <T extends org.bukkit.entity.Entity> T getTarget(final org.bukkit.entity.Entity entity, final Iterable<T> entities) {
        if (entity == null)
            return null;
        T target = null;
        final double threshold = 1;
        for (final T other : entities) {
            final Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
            if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < threshold && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0) {
                if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation()))
                    target = other;
            }
        }
        return target;
    }
	
	//Combat-Methods
    public int sum = 0;
	//Adminspells
    

    
    ///////  READY   ////////
   
    

    
    public void createMinions(Player p, Player targetplayer){
    	p.sendMessage("not implemented");
    }
    
    public void Totoaleultimativekomplettvernichtungszauber(Player p){
    	p.sendMessage("not implemented");
    }
    //classspells
    	//Tank
    public void roar(Player p){
    	if (HasPlayerAbility.hasAbility(p, "roar")){
    		p.sendMessage("not implemented");
    	}
    }

    	public void Superspell_LastFight(Player p){
    		//der Spieler nimmt für wenige Sekunden kaum noch Schaden und kann nicht sterben, wenn also der Schaden trotz
    		//der verminderung so stark ist, dass der Tank auf ein halbes Herz kommt, bleibts dabei, der Effekt sollte ca 10 sekunden anhalten und die Aufladungen
    		//die dafür benötigt werden sollten bei 5 liegen
    		//um diese Superspells, oder Ultimates nutzen zu können sollten die Player Improved Chat oder ähnliches
    		//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
    		p.sendMessage("not implemented");
    	}
    	//Combat

    	
    	
    public void shadowstep(Player p, Player target){
    		//Der Spieler portet sich hinter seinen Gegner und erhält kurze Zeit einen
    		//Schadensburst
    		//[Shift]+[RMB]
    	if (HasPlayerAbility.hasAbility(p, "shadowstep")){
			if (p.getFoodLevel() > 3){																					
				if ((this.getClassesConfig().getDouble("Players."+ p.getName() +".Cooldown.Combat.Shadowstep") + 30 < p.getWorld().getFullTime())){ //If cooldown ok
					if (playerdistance(p, target) < 10){
					//Entity et = p.getWorld().spawnArrow(new Location(p.getWorld(), target.getLocation().getBlockX() - target.getTargetBlock(null, 1).getX(), target.getLocation().getBlockY() - target.getTargetBlock(null, 1).getY(),target.getLocation().getBlockZ() - target.getTargetBlock(null, 1).getZ()),new Vector (0, 0, 0), (float) 0, (float) 0);
					p.teleport(target);
					if (p.canSee(target) == false){
						p.teleport(target);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 2));
					p.setFoodLevel(p.getFoodLevel() - 4);
					this.getClassesConfig().set("Players." + p.getName() + ".Cooldown.Combat.Shadowstep", p.getWorld().getFullTime()); 	//Cd set back
					}
				}else{ p.sendMessage(ChatColor.YELLOW + "Cooldown!");}												//CD goes wrong
			}else{ p.sendMessage(ChatColor.BLUE + "Not enough Energy!");}												//not enough Mana
		}
	}
    	
    		//slow
    	public void Superspell_HardStrike(Player p){
    		//Der Spieler verursacht normalen Schaden und wirft seinen Gegner von sich weg. Nachdem dieser auf dem Boden auftrifft
    		//trifft ihn ein Blitz. Nach dem blitzeinschlag wird der Spieler hinter den Gegner geportet.
    		//Kosten: 5 Aufladungen
    		//um diese Superspells, oder Ultimates nutzen zu können sollten die Player Improved Chat oder ähnliches
    		//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
    		p.sendMessage("not implemented");
    	}
    	//Archer


	
	public void fun(Player p){
		//Der Spieler springt zurueck und erhaelt sehr kurze Zeit eine hoehere Laufgeschwindigkeit
		//[Shift]+[RMB]
	if (HasPlayerAbility.hasAbility(p, "Jumpback")){
		if (p.getFoodLevel() > 2){																					
			if ((this.getPlayersConfig().getDouble("Players."+ p.getName() +".Cooldown.Combat.Jumpback") + 30 < p.getWorld().getFullTime())){ //If cooldown ok
				this.getPlayersConfig().set("Players." + p.getName() + ".Cooldown.Combat.Jumpback", p.getWorld().getFullTime()); 	//Cd set back
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 50));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 50));
				
				} else { p.sendMessage(ChatColor.YELLOW + "Cooldown!");}
			} else { p.sendMessage(ChatColor.BLUE + "Not enough Energy!");}
		} else { p.sendMessage(ChatColor.RED + "No perm!!!"); }									
	}
	
	
		public void Superspell_DarkArrow(Player p){
			//wenn der nächste Pfeil , der abgeschossen wird, trifft , wird das ziel in einen Steinkäfig eingesperrt und ein Dynamitblock fällt vomHimmel
			//um diese Superspells, oder Ultimates nutzen zu können sollten die Player Improved Chat oder ähnliches
			//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
			//Kosten: 5 Aufladungen
			p.sendMessage("not implemented");
		}
		//Heal

	

	
		public void Superspell_HolyShit(Player p){
			//Das Leben aller Entities im Umkreis wird wieder um 8 erhöht,inclusive das des Zaubernden. NPCs werden vom Spieler weg geworfen(aber so richtig).
			//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
			//Kosten: 3 Aufladungen
			p.sendMessage("not implemented");
		}
		//Mage
	public void Blink(Player p){
			//Der Spieler teleportiert sich ca 8 Blöcke in die Richtung seines Cursors
			//[RMB]
			p.sendMessage("not implemented");
		}
	public void Block(Player p){
			//stößt alle Spieler ein stück zurück und schließt den Spieler 10s in einen Glasblock ein
		}
		public void Superspell_Gastfireball(Player p){
			//Der Spieler wirft einen Ghastfireball)
			//Kosten = 2
			//[LMB]
			p.sendMessage("not implemented");
		}
		public void Superspell_Burst(Player p){
			//Der Spieler explodiert in einem kleinen Radius, wobei kein Schaden an der Umwelt angerichtet wird. Jedoch wird
			//die Umwelt in Flammen gesetzt und der Spieler kann nun, stehend auf einer erhöten Position (Schweben, stehend, mal sehen)
			//um lässt einen Feuerstrahl an flames los.
			//Aufladungen = 15
			p.sendMessage("not implemented");
		}
		//Heal+Mage
	public void load(Player p){
			//Der Spieler im Target erhält einer Aufladung. Diese verschwindet nach 10Minuten, wenn keine neue hinzukommt.#
			//Diese Aufladungen werden für superspells oder ultimates verwendet.
			//[Shift + RMB]
			p.sendMessage("not implemented");
		}
}
