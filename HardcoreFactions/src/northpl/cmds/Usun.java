package northpl.cmds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import northpl.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Usun implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Usun(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  
		  if (args.length == 0) {
			  if (GuildUtils.czyGraczMaGildie(sender.getName()) == true) {
				  if (GuildUtils.czyGraczJestOwnerem(sender.getName(), GuildUtils.dajGildieGracza(sender.getName()))) {
					  sender.sendMessage(ChatColor.RED+"Itemy na frakcje nie zostana zwrocone!");
					  sender.sendMessage("Wpisz /usun potwierdz aby usunac gildie");
				  } else {
					  sender.sendMessage("Nie jestes wlascicielem gildii");
				  }
			  } else {
				  sender.sendMessage("Nie posiadasz zadnej gildii");
			  }
		  } else if (args.length == 1){
			  if (args[0].equalsIgnoreCase("potwierdz")) {
			  if (GuildUtils.czyGraczMaGildie(sender.getName()) == true) {
				  if (GuildUtils.czyGraczJestOwnerem(sender.getName(), GuildUtils.dajGildieGracza(sender.getName()))) {
					  Bukkit.broadcastMessage(ChatColor.GREEN+"Gildia "+ChatColor.RED+GuildUtils.dajGildieGracza(sender.getName())+ChatColor.GREEN+" zostala usunieta");
					  String gildiaGracza = GuildUtils.dajGildieGracza(sender.getName());
				        try{
				            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
				            Statement st = conn.createStatement();
				            try{
				                st.execute("DELETE FROM guilds WHERE Tag='"+gildiaGracza+"'");
				                st.execute("DELETE FROM players WHERE Guild='"+gildiaGracza+"'");
				                st.execute("DELETE FROM cuboids WHERE Tag='"+gildiaGracza+"'");
				                st.execute("DELETE FROM invite WHERE Guild='"+gildiaGracza+"'");
				                conn.close();
					            Bufor.budujListeCubow();
					            Bufor.budujListeGildii();
					            Bufor.budujIloscGildii();
					            Bufor.budujListeGraczy();
					            Bufor.budujListeOwnerow();
				                }catch (SQLException e){
				                    e.printStackTrace();
				                    System.out.println("Cos sie zepsulo ;/");
						            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas usuwania gidii, zglos to zdarzenie administratorowi");
				                }				            
				        }catch (SQLException e){
				            System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");
				            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas usuwania gildii, zglos to zdarzenie administratorowi");
				        }
				  } else {
					  sender.sendMessage("Nie jestes wlascicielem gildii");
				  }
			  } else {
				  sender.sendMessage("Nie posiadasz zadnej gildii");
			  }
			  } else {
				  sender.sendMessage(ChatColor.RED+"Wpisz /usun potwierdz aby usunac gildie");
			  }
		  } else {
			  sender.sendMessage(ChatColor.RED+"Zbyt duza ilosc argumentow");
		  }
		  
		  return false;
	  }
}
