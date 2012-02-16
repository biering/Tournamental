package me.Chryb.Tournamental;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class TournamentalPlayerListener extends PlayerListener{

 public static Tournamental plugin;
 public TournamentalPlayerListener(Tournamental plugin) {
 Êthis.plugin = plugin;
 }

 public void onPlayerInteract(PlayerInteractEvent event){
 ÊItemStack m = event.getItem();
 ÊPlayer p = event.getPlayer();
 ÊAction value = (event.getAction().LEFT_CLICK_AIR);
 ÊAction value1 = (event.getAction().LEFT_CLICK_BLOCK);
 Êboolean LeftClickTester = value.equals(true);
 ÊPlayer target = Tournamental.getTargetPlayer(p);
 Ê //ItemStack blazerod = new ItemStack(Material.BLAZE_ROD, 1);
 Ê
 Êif (value.equals(value1) == false){ //setzt den LeftClickTester auf true wenn eines von beiden(value1 oder value2) wahr ist.
 Ê LeftClickTester = true;
 Ê}
 Ê
 Êif (m.getTypeId() == 280){
 Ê p.sendMessage("item id true");
 Ê if (value.equals(false)){
 Ê Êplugin.Rapidfire(p);
 Ê Êp.sendMessage("value true");
 Ê } else {
 Ê Êplugin.Rapidfire(p);
 Ê Êp.sendMessage("value false");
 Ê Êreturn;
 Ê }
 Ê 
 Ê}
 Ê
 Êif (m.getTypeId() == 279){
 Ê p.sendMessage("item id true");
 Ê if (value.equals(false)){
 Ê Êplugin.Rapidfire(p);
 Ê Êp.sendMessage("value true");
 Ê } else {
 Ê Êplugin.Rapidfire(p);
 Ê Êp.sendMessage("value false");
 Ê Êreturn;
 Ê }
 Ê 
 Ê}
 Ê Ê
 Êif (m.getTypeId() == 283){ //golden sword, funktion ohne cds, essenskosten, classestest und armortest, 15.Feb.2012 Paul MŸnch
 Ê if (target == null){
 Ê Êif (p.isSneaking()){ Ê// [Shift]
 Ê Ê if (LeftClickTester){ //[LMB]
 Ê Ê Êplugin.HealoverTime(p);
 Ê Ê }else{ Ê Ê //[RMB]
 Ê Ê Êplugin.load(p);
 Ê Ê }
 Ê Ê}else{ Ê Ê // no shift
 Ê Ê if (LeftClickTester){ //[LMB]
 Ê Ê Êplugin.Directheal(p, p);
 Ê Ê }else{ Ê Ê //[RMB]
 Ê Ê Êp.sendMessage("no target");
 Ê Ê }
 Ê Ê}
 Ê }else{
 Ê Êif (p.isSneaking()){ Ê// [Shift]
 Ê Ê if (LeftClickTester){ //[LMB]
 Ê Ê Êplugin.HealoverTime(target);
 Ê Ê }else{ Ê Ê //[RMB]
 Ê Ê Êplugin.load(target);
 Ê Ê }
 Ê Ê}else{ Ê Ê // no shift
 Ê Ê if (LeftClickTester){ //[LMB]
 Ê Ê Êplugin.Directheal(p, target);
 Ê Ê }else{ Ê Ê //[RMB]
 Ê Ê Êplugin.dot(target);
 Ê Ê }
 Ê Ê}
 Ê }
 Ê 
 Ê}
 }
}