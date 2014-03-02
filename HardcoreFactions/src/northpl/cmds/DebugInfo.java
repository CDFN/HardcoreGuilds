package northpl.cmds;

import northpl.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugInfo implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public DebugInfo(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  
		  if (sender.isOp()) {
				Bukkit.broadcastMessage("[DEBUG] Liczba gildii: " + Bufor.iloscGildii);
				Bukkit.broadcastMessage("[DEBUG] Lista nazw gildii: " + Bufor.listaNazwaGildii);
				Bukkit.broadcastMessage("[DEBUG] Lista tagów: " + Bufor.listaGildii);
				Bukkit.broadcastMessage("[DEBUG] Lista cuboidów: " + Bufor.cuboidy);
				Bukkit.broadcastMessage("[DEBUG] Lista graczy: " + Bufor.gracze);
				Bukkit.broadcastMessage("[DEBUG] Lista ownerow: " + Bufor.ownerzy);
				Bukkit.broadcastMessage("[DEBUG] Lista zaproszen: " + Bufor.zaproszenia);
		  }
		  
		  return false;
	  }
	
}
