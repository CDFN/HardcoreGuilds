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

public class Opusc implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Opusc(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (GuildUtils.czyGraczMaGildie(sender.getName()) == true) {
			if (GuildUtils.czyGraczJestOwnerem(sender.getName(), GuildUtils.dajGildieGracza(sender.getName())) == false) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED+"Aby potwierdzic opuszczenie gildii wpisz /opusc potwierdz");
				} else if (args.length == 1) {
					 if (args[0].equalsIgnoreCase("potwierdz")) {
						 Bukkit.broadcastMessage(ChatColor.YELLOW+sender.getName()+ChatColor.DARK_GREEN+" opuscil gildie "+ChatColor.YELLOW+GuildUtils.dajGildieGracza(sender.getName())+ChatColor.DARK_GREEN+"!");
					        try{
					            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
					            Statement st = conn.createStatement();
					            try{
					                st.execute("DELETE FROM players WHERE Nick='"+sender.getName()+"'");
					                conn.close();
					                }catch (SQLException e){
					                    e.printStackTrace();
					                    System.out.println("Cos sie zepsulo ;/");
							            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas tworzenia gidii, zglos to zdarzenie administratorowi");
					                }  
					        }catch (SQLException e){
					            System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");
					            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas tworzenia gildii, zglos to zdarzenie administratorowi");
					        }
			                Bufor.budujListeGraczy();
					 } else {
							sender.sendMessage(ChatColor.RED+"Aby potwierdzic opuszczenie gildii wpisz /opusc potwierdz");
					 }
				} else {
					sender.sendMessage(ChatColor.RED+"Niepoprawne uzycie");
				}
			} else {
				sender.sendMessage(ChatColor.RED+"Jestes wlascicielem gildii!");
			}
		} else {
			sender.sendMessage(ChatColor.RED+"Nie masz gildii!");
		}
			return false;
		}
	
}