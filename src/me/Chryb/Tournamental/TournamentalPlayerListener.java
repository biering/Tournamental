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
 �this.plugin = plugin;
 }

 public void onPlayerInteract(PlayerInteractEvent event){
 �ItemStack m = event.getItem();
 �Player p = event.getPlayer();
 �Action value = (event.getAction().LEFT_CLICK_AIR);
 �Action value1 = (event.getAction().LEFT_CLICK_BLOCK);
 �boolean LeftClickTester = value.equals(true);
 �Player target = Tournamental.getTargetPlayer(p);
 � //ItemStack blazerod = new ItemStack(Material.BLAZE_ROD, 1);
 �
 �if (value.equals(value1) == false){ //setzt den LeftClickTester auf true wenn eines von beiden(value1 oder value2) wahr ist.
 � LeftClickTester = true;
 �}
 �
 �if (m.getTypeId() == 280){
 � p.sendMessage("item id true");
 � if (value.equals(false)){
 � �plugin.Rapidfire(p);
 � �p.sendMessage("value true");
 � } else {
 � �plugin.Rapidfire(p);
 � �p.sendMessage("value false");
 � �return;
 � }
 � 
 �}
 �
 �if (m.getTypeId() == 279){
 � p.sendMessage("item id true");
 � if (value.equals(false)){
 � �plugin.Rapidfire(p);
 � �p.sendMessage("value true");
 � } else {
 � �plugin.Rapidfire(p);
 � �p.sendMessage("value false");
 � �return;
 � }
 � 
 �}
 � �
 �if (m.getTypeId() == 283){ //golden sword, funktion ohne cds, essenskosten, classestest und armortest, 15.Feb.2012 Paul M�nch
 � if (target == null){
 � �if (p.isSneaking()){ �// [Shift]
 � � if (LeftClickTester){ //[LMB]
 � � �plugin.HealoverTime(p);
 � � }else{ � � //[RMB]
 � � �plugin.load(p);
 � � }
 � �}else{ � � // no shift
 � � if (LeftClickTester){ //[LMB]
 � � �plugin.Directheal(p, p);
 � � }else{ � � //[RMB]
 � � �p.sendMessage("no target");
 � � }
 � �}
 � }else{
 � �if (p.isSneaking()){ �// [Shift]
 � � if (LeftClickTester){ //[LMB]
 � � �plugin.HealoverTime(target);
 � � }else{ � � //[RMB]
 � � �plugin.load(target);
 � � }
 � �}else{ � � // no shift
 � � if (LeftClickTester){ //[LMB]
 � � �plugin.Directheal(p, target);
 � � }else{ � � //[RMB]
 � � �plugin.dot(target);
 � � }
 � �}
 � }
 � 
 �}
 }
}