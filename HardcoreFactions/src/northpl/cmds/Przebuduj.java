package northpl.cmds;

import northpl.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Przebuduj implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Przebuduj(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  long startTime = System.currentTimeMillis();
		  if (sender.isOp()) {
			  	Bukkit.broadcastMessage("[DEBUG] Rozpoczeto przebudowe lokalnego cache");
				Bufor.budujIloscGildii();
				Bukkit.broadcastMessage("[DEBUG] Zaktualizowano liczbe gildii: " + Bufor.iloscGildii);
				Bufor.budujListeGildii();
				Bukkit.broadcastMessage("[DEBUG] Zaktualizowano liste gildii: " + Bufor.listaGildii);
				Bufor.budujListeCubow();
				Bukkit.broadcastMessage("[DEBUG] Zaktualizowano liste cuboidów: " + Bufor.cuboidy);
				Bufor.budujListeGraczy();
				Bukkit.broadcastMessage("[DEBUG] Zaktualizowano liste graczy: " + Bufor.gracze);
				Bufor.budujListeOwnerow();
				Bukkit.broadcastMessage("[DEBUG] Zaktualizowano liste ownerow: " + Bufor.ownerzy);
				Bufor.budujListeZaproszen();
				Bukkit.broadcastMessage("[DEBUG] Zaktualizowano liste zaproszen: " + Bufor.zaproszenia);
	            Bufor.budujListeNazwGildii();
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				String s = String.valueOf(totalTime);
				Bukkit.broadcastMessage(s);
		  }
		  
		  return false;
	  }

}
