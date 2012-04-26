package me.Chryb.Tournamental.Utilities;

import me.Chryb.Tournamental.Tournamental;

public class MathUtil {
	
	public static Tournamental plugin;
	public MathUtil(Tournamental instance){
		 plugin = instance;
		}
	
	public static double customRound(double x) {
		if (x % 1 < 0.5){
			x = x - (x % 1);
		} else {
			x = x + (1 - (x % 1));
		}
		return x;
	}
	
	public static boolean between(double d, double y, double z) {
		boolean erfolg1 = false;
	if (d <= y) {
		if (d >= z) {
		    erfolg1 = true;
		}
		else {
			erfolg1 = false;
		}}
	else {
		if (d <= z) {
			erfolg1 = true;
		}
			else {
			erfolg1 = false;
			}}
	return erfolg1;
	}

}
