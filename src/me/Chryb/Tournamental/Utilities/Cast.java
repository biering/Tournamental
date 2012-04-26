package me.Chryb.Tournamental.Utilities;

import org.bukkit.entity.Player;

import me.Chryb.Tournamental.Tournamental;
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

public class Cast {
	
	public static Tournamental plugin;
	public Cast(Tournamental instance){
		 plugin = instance;
		}
	
	public static void action(String spell, Player caster, Player target){
		
		if (spell.equalsIgnoreCase(Dot.getName())){
			Dot.onCast(caster, target);
		}
		
		if (spell.equalsIgnoreCase(Slow.getName())){
			Slow.onCast(caster);
		}
		
		if (spell.equalsIgnoreCase(Thunder.getName())){
			Thunder.onCast(caster);
		}
		
		if (spell.equalsIgnoreCase(Rapidfire.getName())){
			Rapidfire.onCast(caster);
		}
		
		if (spell.equalsIgnoreCase(Fireballs.getName())){
			Fireballs.onCast(caster);
		}
		
		if (spell.equalsIgnoreCase(SmallFireballs.getName())){
			SmallFireballs.onCast(caster, 3);
		}
		
		if (spell.equalsIgnoreCase(SmallFireballs.getName())){
			Dot.onCast(caster, target);
		}
		
		if (spell.equalsIgnoreCase(Directheal.getName())){
			Directheal.onCast(caster, target);
		}
		
		if (spell.equalsIgnoreCase(HealOverTime.getName())){
			HealOverTime.onCast(caster, target);
		}
		
		if (spell.equalsIgnoreCase(CombatAoE.getName())){
			CombatAoE.onCast(caster);
		}
		
		if (spell.equalsIgnoreCase(FireResistance.getName())){
			FireResistance.onCast(caster, target);
		}
		
		if (spell.equalsIgnoreCase(Jumpback.getName())){
			Jumpback.onCast(caster);
		}
		
		
	}

}
