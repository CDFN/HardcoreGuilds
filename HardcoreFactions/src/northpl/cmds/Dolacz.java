package northpl.cmds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import northpl.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class Dolacz implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Dolacz(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (GuildUtils.czyGraczMaGildie(sender.getName()) == false) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED+"Uzycie: /dolacz <tag>");
		} else if (args.length == 1) {
			String gildia = args[0].toUpperCase();
			
			if (GuildUtils.czyTakiTagIstnieje(gildia)) {
				if (GuildUtils.czyJestZaproszony(sender.getName(), gildia)) {
					int iloscDiaxow = (GuildUtils.LiczbaGraczy(gildia) * 9);
					int iloscLyb = (GuildUtils.LiczbaGraczy(gildia) * 9);
					sender.sendMessage(ChatColor.RED+"Aby dolaczyc do gildii musisz miec "+iloscDiaxow+" emeraldów i "+iloscDiaxow+" surowych ryb");
										
					if ((Bukkit.getPlayer(sender.getName()).getInventory( ).contains( new ItemStack(Material.EMERALD), iloscDiaxow )) && (Bukkit.getPlayer(sender.getName()).getInventory( ).contains( new ItemStack(Material.RAW_FISH), iloscLyb ))) {
						Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"Gracz "+ChatColor.YELLOW+sender.getName()+ChatColor.DARK_GREEN+" dolaczyl do gildii "+ChatColor.YELLOW+gildia);

						Bukkit.getPlayer(sender.getName()).getInventory( ).removeItem(new ItemStack(Material.EMERALD, iloscDiaxow));
			        try{
			            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
			            Statement st = conn.createStatement();
			            try{
			                st.execute("INSERT INTO players VALUES ('0','"+sender.getName()+"','"+gildia+"','P')");
			                st.execute("DELETE FROM invite WHERE Nick='"+sender.getName()+"'");
			                conn.close();
			                }catch (SQLException e){
			                    e.printStackTrace();
			                    System.out.println("Cos sie zepsulo ;/");
					            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas tworzenia gidii, zglos to zdarzenie administratorowi");
			                }  
		                Bufor.budujListeGraczy();Bufor.budujListeZaproszen();
			        }catch (SQLException e){
			            System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");
			            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas tworzenia gildii, zglos to zdarzenie administratorowi");
			        }
					} else {
						sender.sendMessage(ChatColor.RED+"Nie masz wystarczajacej liczby emeraldów i ryb!");
					}
				} else {
					sender.sendMessage(ChatColor.RED+"Nie jestes zaproszony!");
				}
			} else {
				sender.sendMessage(ChatColor.RED+"Nie ma gildii o takim tagu!");
			}
		} else {
			sender.sendMessage(ChatColor.RED+"Uzycie: /dolacz <tag>");
		}
		
		} else {
			sender.sendMessage(ChatColor.RED+"Masz juz gildie!");
		}
		
		return false;
	}
	
}
