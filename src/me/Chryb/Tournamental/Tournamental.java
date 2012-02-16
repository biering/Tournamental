package me.Chryb.Tournamental;

import java.util.HashMap;



import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.bukkit.entity.*;

public class Tournamental extends JavaPlugin {
	public final TournamentalPlayerListener playerListener = new TournamentalPlayerListener(this);
	//public final TournamentBlockListener bl = new TournamentBlockListener(this);
	//public final TournamentEntityListener el = new TournamentEntityListener(this);
	
	private TournamentalCommandExecutor cmdsExecutor;

	@Override
	public void onDisable() {
		System.out.println("[Tournamental] Plugin disabled!");
	}

	@Override
	public void onEnable() {
		loadConfiguration();
		registerHooks();
		//reloadConfiguration();
		cmdsExecutor = new TournamentalCommandExecutor(this);
		getCommand("defzone").setExecutor(cmdsExecutor);
		getCommand("tpos1").setExecutor(cmdsExecutor);
		getCommand("tpos2").setExecutor(cmdsExecutor);
		getCommand("tvert").setExecutor(cmdsExecutor);
		System.out.println("[Tournamental] Plugin enabled!");
	}
	
	public void registerHooks() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Highest, this);
}
	
	public double posx1 = 0;
	public double posx2 = 0;
	public double posy1 = 0;
	public double posy2 = 0;
	public double posz1 = 0;
	public double posz2 = 0;
	public double posx3 = 0;
	public double posy3 = 0;
	public double posz3 = 0;
	
	private void loadConfiguration(){
		this.getConfig().addDefault("Config", null);
		this.getConfig().addDefault("Zones" , null);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void defineZone(String z){
		String Zones = "Zones." + z + ".main";
		this.getConfig().addDefault(Zones + ".min", posx1);
		this.getConfig().addDefault(Zones + ".max", posx2);
		this.getConfig().addDefault(Zones + ".city", 0);
		this.getConfig().addDefault(Zones + ".lobby.min", 0);
		this.getConfig().addDefault(Zones + ".lobby.max", 0);
		this.getConfig().addDefault(Zones + ".fight.min", 0);
		this.getConfig().addDefault(Zones + ".fight.max", 0);
		this.getConfig().addDefault(Zones + ".team.min", 0);
		this.getConfig().addDefault(Zones + ".team.max", 0);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		return;
	}
	
	public void addLobby(String z){
		String Lobby = "Zones." + z + ".main.lobby";
		this.getConfig().set(Lobby + ".min", posx1);
		this.getConfig().set(Lobby + ".max", posx2);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void addFight(String z){
		String Fight = "Zones." + z + ".main.fight";
		this.getConfig().set(Fight + ".min", posx1);
		this.getConfig().set(Fight + ".max", posx2);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void addTeam(String z){
		String Team = "Zones." + z + ".main.team";
		this.getConfig().set(Team + ".min", posx1);
		this.getConfig().set(Team + ".max", posx2);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	public double customRound(double x) {
		if (x % 1 < 0.5){
			x = x - (x % 1);
		} else {
			x = x + (1 - (x % 1));
		}
		return x;
	}
	
	//funktion zum suchen eines Players als ziel(linear) 13.Feb.2012, Paul Münch
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
	public void heali(Player p){
		//Basisidee ist , dass der anvisierte Spieler sich auf einer Linie zwischen dem Spieler
		//und dem anvisierten Block befinden muss. Daher ist es sicherlich möglich eine Liste aller Spieler
		//auf dieser Linie zu bestimmen. Davon wiederum wird der erstbeste genommen und geheilt.
		
	}
	//Adminspells
	public void strike(Player p){
		p.getWorld().strikeLightning(p.getTargetBlock(null, Integer.MAX_VALUE).getLocation());
	}
    public void fireball(Player p){
	Fireball fb = p.getWorld().spawn(p.getLocation().add(0, 3, 0), Fireball.class);
  	fb.setShooter(p.getPlayer());
    fb.setYield(3);
	}
    public void smallfireball(Player p){
    SmallFireball sfb = p.getWorld().spawn(p.getLocation().add(0, 3, 0), SmallFireball.class);
  	sfb.setShooter(p.getPlayer());
    sfb.setYield(3);
    }
    public void createMinions(Player p, Player targetplayer){
    	p.sendMessage("not implemented");
    	
    }
    public void Totoaleultimativekomplettvernichtungszauber(Player p){
    	p.sendMessage("not implemented");
    }
    //classspells
    	//Tank
    	public void Roar(Player p){
    		//Grundidee ist, dass alle Gegner den castenden Player angreifen oder zumindest ihm ihr 
    		//Gesicht zuwenden müssen 
    		//[Shift]+[LMB]
    		p.sendMessage("not implemented");
    	}
    	public void Slow(Player p){
    		//Grundidee ist, dass alle Gegner in einem kleinen Radius verlangsamt werden
    		//[Shift]+[RMB]
    		p.sendMessage("not implemented");
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
    	public void CombatAoE(Player p){
    		//Der Spieler schadet allen Entitys in einem kleinen Radius 
    		//[Shift]+[LMB]
    		p.sendMessage("not implemented");
    	}
    	public void Shadowstep(Player p){
    		//Der Spieler portet sich hinter seinen Gegner und erhält kurze Zeit einen
    		//Schadensburst
    		//[Shift]+[RMB]
    		p.sendMessage("not implemented");
    	}
    		public void Superspell_HardStrike(Player p){
    		//Der Spieler verursacht normalen Schaden und wirft seinen Gegner von sich weg. Nachdem dieser auf dem Boden auftrifft
    		//trifft ihn ein Blitz. Nach dem blitzeinschlag wird der Spieler hinter den Gegner geportet.
    		//Kosten: 5 Aufladungen
    		//um diese Superspells, oder Ultimates nutzen zu können sollten die Player Improved Chat oder ähnliches
    		//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
    		p.sendMessage("not implemented");
    	}
    	//Archer
    	public void Rapidfire(Player p){
    		Vector pa = p.getLocation().getDirection();
    		Vector a = new Vector(10,0,0);
    		double z = Math.random()*5-2.5;
    		double y = Math.random()*2.5;
    		Entity e = p.getWorld().spawnArrow(p.getLocation().add(0,y,z), pa, (float)5, (float)2);
    		Entity f = p.getWorld().spawnArrow(p.getLocation().add(0,y,z), pa, (float)5, (float)2);
    		Entity g = p.getWorld().spawnArrow(p.getLocation().add(0,y,z), pa, (float)5, (float)2);
    		Entity h = p.getWorld().spawnArrow(p.getLocation().add(0,y,z), pa, (float)5, (float)2);
    		e.setVelocity(a);
    		f.setVelocity(a);
    		g.setVelocity(a);
    		h.setVelocity(a);
    		p.getWorld().getEntities().add(e);
    		p.getWorld().getEntities().add(f);
    		p.getWorld().getEntities().add(g);
    		p.getWorld().getEntities().add(h);
    		//Der Spieler schießt ein Strahl aus Pfeilen mit großem Spread (ca 10 Stück)
    		//[Shift]+[LMB]
		p.sendMessage("not implemented");
		}
		public void Jumpback(Player p){
			//Der Spieler springt zurück und erhält sehr kurze Zeit eine höhere Laufgeschwindigkeit
			//[Shift]+[RMB]
			p.sendMessage("not implemented");
		}
			public void Superspell_DarkArrow(Player p){
			//wenn der nächste Pfeil , der abgeschossen wird, trifft , wird das ziel in einen Steinkäfig eingesperrt und ein Dynamitblock fällt vomHimmel
			//um diese Superspells, oder Ultimates nutzen zu können sollten die Player Improved Chat oder ähnliches
			//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
			//Kosten: 5 Aufladungen
			p.sendMessage("not implemented");
		}
		//Heal
		public void Directheal(Player p){
			//Der Spieler erhält einen Instant-Heal
			//[LMB]
			if (p.getHealth() > 11){
				p.setHealth(20);
				p.sendMessage("+8Health");
			}	
			if (p.getHealth() < 12)
				p.setHealth(p.getHealth() + 8);
			    p.sendMessage("+8Health");
		}
		public void dot(Player p){
			//Der Spieler im target erhält einen Debuff (siehe Poison) 
			//[RMB]
			p.sendMessage("not implemented");
		}
		public void fireresi(Player p){
			//Der anvisierte Spieler erhält kurze Zeit über komplette Feuerresistenz
			//Kosten = 2
			//[LMB]
			p.sendMessage("not implemented");
		}
		public void HealoverTime(Player p){
			//Der Spieler im target erhält einen HoT, wer's nicht kennt googlet das!. Wenn kein Spieler im Target "hottet" sich der caster selbst.
			//[RMB]
			p.sendMessage("not implemented");
		}
			public void Superspell_HolyShit(Player p){
			//Das Leben aller Entities im Umkreis wird wieder um 8 erhöht,inclusive das des Zaubernden. NPCs werden vom Spieler weg geworfen(aber so richtig).
			//verwenden, da es keine Möglichkeit zur Bindung gibt.. Oder man entfernt das blocken, was den Tanks aber sicher nicht gefallen wird.
			//Kosten: 3 Aufladungen
			p.sendMessage("not implemented");
		}
		//Mage
		public void Smallfireball2(Player p){
			//Der Spieler wirft einen kleinen Feuerball
			//[LMB]
			p.sendMessage("not implemented");
		}
		public void Blink(Player p){
			//Der Spieler teleportiert sich ca 8 Blöcke in die Richtung seines Cursors
			//[RMB]
			p.sendMessage("not implemented");
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
