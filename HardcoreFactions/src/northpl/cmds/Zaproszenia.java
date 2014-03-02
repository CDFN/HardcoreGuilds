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

public class Zaproszenia implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Zaproszenia(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (GuildUtils.czyGraczMaGildie(sender.getName()) == true) {
			
			if (GuildUtils.czyGraczJestOwnerem(sender.getName(), GuildUtils.dajGildieGracza(sender.getName()))) {
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.GOLD+"= = = [ "+ChatColor.RED+" Zaproszenia" + ChatColor.GOLD+" ] = = =");
			String zaproszeni;
			if (Bufor.zaproszenia.get(GuildUtils.dajGildieGracza(sender.getName())) == null) {
				zaproszeni = "brak";
			} else {
				zaproszeni = Bufor.zaproszenia.get(GuildUtils.dajGildieGracza(sender.getName()));
			}
			sender.sendMessage(ChatColor.GOLD+"Zaproszeni gracze: "+ChatColor.RED+zaproszeni);
			sender.sendMessage(ChatColor.GOLD+"/zaproszenia zapros <nick> - zaprasza gracza");
			sender.sendMessage(ChatColor.GOLD+"/zaproszenia odwolaj <nick> - odwoluje zaproszenia");
		} else  {
			if (args[0].equalsIgnoreCase("zapros")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.RED+"Uzycie: /zaproszenia zapros <nick>");
				} else {
					if (args[1].contains("=") || args[1].contains(";") || args[1].contains("'") || args[1].contains("\"")) {
						sender.sendMessage(ChatColor.RED+"Niedozwolone znaki");
					} else {
						if (!GuildUtils.czyGraczMaGildie(args[1])) {
						if (GuildUtils.czyJestZaproszony(args[1], GuildUtils.dajGildieGracza(sender.getName())) == true) {
							sender.sendMessage(ChatColor.RED+"Ten gracz jest juz zaproszony!");
						} else {
							//Tutaj spelnia wszystkie wymagania i mozna go zapraszac :>
							if (Bukkit.getOnlinePlayers().equals(args[1])) {
								Bukkit.getPlayer(args[1]).sendMessage(ChatColor.DARK_GREEN+"Zostales zaproszony do gildii"+ChatColor.YELLOW+GuildUtils.dajGildieGracza(sender.getName()));
							}
							
							sender.sendMessage(ChatColor.DARK_GREEN+"Zaprosiles gracza "+ChatColor.YELLOW+args[1]);
							
					        try{
					            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
					            Statement st = conn.createStatement();
					            try{
					                st.execute("INSERT INTO invite VALUES ('0','"+args[1]+"','"+GuildUtils.dajGildieGracza(sender.getName())+"')");
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
					        Bufor.budujListeZaproszen();
						}
						} else {
							sender.sendMessage(ChatColor.RED+"Gracz ma juz gildie");
						}
					}
				}
			} else if (args[0].equalsIgnoreCase("odwolaj")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.RED+"Uzycie: /zaproszenia odwolaj <nick>");
				} else {
					if (args[1].contains("=") || args[1].contains(";") || args[1].contains("'") || args[1].contains("\"")) {
						sender.sendMessage(ChatColor.RED+"Niedozwolone znaki");
					} else {
						if (GuildUtils.czyJestZaproszony(args[1], GuildUtils.dajGildieGracza(sender.getName())) == true) {
							if (Bukkit.getOnlinePlayers().equals(args[1])) {
								Bukkit.getPlayer(args[1]).sendMessage(ChatColor.DARK_GREEN+"Twoje zaproszenie do gildii"+ChatColor.YELLOW+GuildUtils.dajGildieGracza(sender.getName())+ChatColor.DARK_GREEN+" zostalo anulowane");
							}
							
							sender.sendMessage(ChatColor.DARK_GREEN+"Anulowales zaproszenie gracza "+ChatColor.YELLOW+args[1]);
							
					        try{
					            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
					            Statement st = conn.createStatement();
					            try{
					                st.execute("DELETE FROM invite WHERE Nick='"+args[1]+"' AND Guild='"+GuildUtils.dajGildieGracza(sender.getName())+"'");
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
					        Bufor.budujListeZaproszen();
							
							
						} else {
							sender.sendMessage(ChatColor.RED+"Ten gracz nie jest zaproszony!");
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED+"Nie ma takiej sub-komendy");
			}
		}
		
			} else {
				sender.sendMessage(ChatColor.RED+"Nie jestes wlascicielem gildii! Nie mozesz zarzadzac zaproszeniami");
			}
		
		} else {
			sender.sendMessage(ChatColor.RED+"Nie masz zadnej gildii");
		}
		
		return false;
	}

}
