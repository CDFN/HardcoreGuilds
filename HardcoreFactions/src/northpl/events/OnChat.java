package northpl.events;

//import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import northpl.cmds.GuildUtils;
import northpl.main.Main;

public class OnChat implements Listener {

	public OnChat(Main plugin)
	  {
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	  }
	
	@SuppressWarnings("unused")
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onChatSend(AsyncPlayerChatEvent event) {
		String format = event.getFormat();
		String wiadomosc = event.getMessage();
		String nick = event.getPlayer().getName();
		
		String gildia = GuildUtils.dajGildieGracza(nick);

	}
	
}
