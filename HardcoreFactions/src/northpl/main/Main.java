package northpl.main;

import org.bukkit.plugin.java.JavaPlugin;

import northpl.cmds.*;
import northpl.events.*;

public class Main extends JavaPlugin {

	  public static Main plugin;

	  public static Main getPlugin() {
	      return plugin;
	  }

	
	
	public void onEnable() {
		
		new BlockChangeEvent(this);
		new OnChat(this);
		getCommand("dolacz").setExecutor(new Dolacz(this));
		getCommand("odejdz").setExecutor(new Opusc(this));
		getCommand("zaproszenia").setExecutor(new Zaproszenia(this));
		getCommand("stworz").setExecutor(new Zaloz(this));
		getCommand("usun").setExecutor(new Usun(this));
		getCommand("gildia").setExecutor(new Gildia(this));
		getCommand("przebuduj").setExecutor(new Przebuduj(this));
		getCommand("debuginfo").setExecutor(new DebugInfo(this));
		
	    getServer().dispatchCommand(getServer().getConsoleSender(), "przebuduj");
	}
	
	public void onDisable() {

	}
	
}
