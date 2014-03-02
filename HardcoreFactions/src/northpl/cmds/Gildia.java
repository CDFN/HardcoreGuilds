package northpl.cmds;

import northpl.main.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Gildia implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Gildia(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  if (args.length == 0) {
			  sender.sendMessage(ChatColor.RED+"Uzycie: /gildia <tag>");
		  } else if (args.length == 1) {
			  if (args[0].length() == 4) {
			  String gildia = args[0];
			  if (!GuildUtils.czyTakiTagIstnieje(gildia)) {
			  
			  
			  //Juz wyswietlamy info
			  sender.sendMessage(ChatColor.GOLD+"= = = [ "+ChatColor.RED+gildia+ChatColor.GOLD+" ] = = =");
			  sender.sendMessage(ChatColor.GOLD+"Nazwa gildii: "+GuildUtils.zamienNaNazwe(gildia));
			  sender.sendMessage(ChatColor.GOLD+"Wlasciciel gildii: "+GuildUtils.dajOwneraGildii(gildia));
			  sender.sendMessage(ChatColor.GOLD+"Gracze: ");
			  sender.sendMessage(ChatColor.GOLD+"Sojusze: ");
			  } else {
				  sender.sendMessage(ChatColor.RED+"Nie ma gildii o takim tagu!");
			  }
			  } else {
				  sender.sendMessage(ChatColor.RED+"Tag sklada sie z 4 znaków!");
			  }
			  } else {
			  sender.sendMessage(ChatColor.RED+"Za duzo argumentów");
		  }
		  
		  return false;
	  }
	
}
