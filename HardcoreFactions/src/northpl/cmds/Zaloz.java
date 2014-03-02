package northpl.cmds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import northpl.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Zaloz implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	  
	public Zaloz(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	String uzycie = ChatColor.RED+"Uzycie: /stworz <4 literowa nazwa> <pelna nazwa>";
	String CzteryLitery = ChatColor.RED+"Krotka nazwa gildii musi miec 4 znaki";
	String wiecejNizCztery = ChatColor.RED+"Pelna nazwa gildii musi miec wiecej niz cztery znaki i nie wiecej niz 15";
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  
		  if (args.length == 0) {
				 if (GuildUtils.czyGraczMaGildie(sender.getName()) == false) {
			  sender.sendMessage(uzycie);
				 } else {
					 sender.sendMessage(ChatColor.RED+"Masz juz gildie!");
				 }
		  } else if (args.length == 1) {
				 if (GuildUtils.czyGraczMaGildie(sender.getName()) == false) {
			  sender.sendMessage(uzycie);
				 } else {
					 sender.sendMessage(ChatColor.RED+"Masz juz gildie!");
				 }
		  } else if (args.length == 2) {
			 String tag = args[0].toUpperCase();
			 String nazwa = args[1];
			 String owner = sender.getName();
			 
			 if (GuildUtils.czyGraczMaGildie(owner) == false) { //Nie patrz na to false :P False tu oznacza, ze ma nie miec gildii
			 if (tag.length() == 4) {
				 if (GuildUtils.czyTakiTagIstnieje(tag) == false) {
				 if (nazwa.length() > 4 && nazwa.length() <= 15) {
					 if (GuildUtils.czyTakaNazwaIstnieje(nazwa) == false) {
						 if (GuildUtils.czyGraczMaItemy(owner)) {
					 boolean czyMozeZakladac = true;
						 
						//Cuboid
						 Player gracz = Bukkit.getPlayer(sender.getName()); int x = gracz.getLocation().getBlockX();
						 int z = gracz.getLocation().getBlockZ(); String world = gracz.getLocation().getWorld().getName();
						 
						 //Pozycje1
						 int x_pos1 = x - 51;
						 int z_pos1 = z - 51;
						 
						 //Pozycje2
						 int x_pos2 = x + 51;
						 int z_pos2 = z + 51;
						 
						 System.out.println("[CzokoGuild] Tworzenie cuboida dla gildii " + tag + " pozycje1: ("+x_pos1+" | "+z_pos1+") pozycje2: ("+x_pos2+" | "+z_pos2+") swiat: "+world);
						 
						 int iloscGildii = Bufor.iloscGildii;
						 
						 if (iloscGildii != 0) {
							 
						 for (int i = 0; i < iloscGildii;i++)
						 {
							 String aktualnaGildia = Bufor.listaGildii.get(i);
							 
							 System.out.println("Lista Gildii: "+Bufor.listaGildii);
							 System.out.println("Gildia sprawdzana przy tej petli: "+aktualnaGildia);
							 
							 int x_pos1_pp = 0;
							 int z_pos1_pp = 0;
							 
							 int x_pos2_pp = 0;
							 int z_pos2_pp = 0;
							 
							 String gildiaPrzyTejPetli = Bufor.cuboidy.get(aktualnaGildia);
								 
								 List<String> list = Lists.newArrayList(Splitter.on(" , ").split(gildiaPrzyTejPetli));
								 System.out.println("Lista po splicie: "+list);
									 x_pos1_pp = Integer.parseInt(list.get(0));
									 z_pos1_pp = Integer.parseInt(list.get(1));
									 x_pos2_pp = Integer.parseInt(list.get(2));
									 z_pos2_pp = Integer.parseInt(list.get(3));
							 
							 
							 System.out.println(x_pos1_pp);
							 System.out.println(z_pos1_pp);
							 System.out.println(x_pos2_pp);
							 System.out.println(z_pos2_pp);
							 
							 System.out.println("= = = Koniec petli = = ="); System.out.println("");
							 
						   if(GuildUtils.doesCollide(x_pos1, z_pos1, x_pos2, z_pos2, x_pos1_pp, z_pos1_pp, x_pos2_pp, z_pos2_pp))
						   {
							   czyMozeZakladac = false;
							   System.out.println("["+owner+"] Nie zezwolono na tworzenie gildii");
						   } else {
							   System.out.println("["+owner+"] Zezwolono na tworzenie gildii");
						   }
						   
						 }//Tutaj koniec petli
						 
						 } else {
							 czyMozeZakladac = true;
						 }
						 
						 //Koniec cuboid
						 if (czyMozeZakladac != false) {
				        try{
				        	GuildUtils.zabierzItemyGraczowi(owner);
				            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
				            Statement st = conn.createStatement();
				            try{

				                
				                st.execute("INSERT INTO guilds VALUES ('0','"+tag+"','"+nazwa+"','"+owner+"')");
				                st.execute("INSERT INTO players VALUES ('0','"+owner+"','"+tag+"','O')");
				                st.execute("INSERT INTO cuboids VALUES ('0','"+tag+"','"+x_pos1+"','"+z_pos1+"','"+x_pos2+"','"+z_pos2+"')");
				                
				                conn.close();
				                
				                }catch (SQLException e){
				                    e.printStackTrace();
				                    System.out.println("Cos sie zepsulo ;/");
						            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas tworzenia gidii, zglos to zdarzenie administratorowi");
				                }
				            
				            Bukkit.broadcastMessage(ChatColor.GREEN+"Gracz "+ChatColor.RED+sender.getName()+ChatColor.GREEN+" zalozyl gildie o nazwie "+ChatColor.RED+nazwa +ChatColor.GREEN+ " ["+ChatColor.RED+tag+ChatColor.GREEN+"]");
				            Bufor.budujListeCubow();
				            Bufor.budujListeGildii();
				            Bufor.budujListeNazwGildii();
				            Bufor.budujIloscGildii();
				            Bufor.budujListeGraczy();
				            Bufor.budujListeOwnerow();
				            

				            
				        }catch (SQLException e){
				            System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");
				            sender.sendMessage(ChatColor.RED+"Wystapil problem podczas tworzenia gildii, zglos to zdarzenie administratorowi");
				        }
					 } else {
						 sender.sendMessage(ChatColor.RED+"Jestes zbyt blisko innej gildii. Oddal sie i sprobuj ponownie");
					 }
					 } else {
						sender.sendMessage(ChatColor.RED+"Nie masz itemów na gildie!"); 
					 }
					 } else {
						 sender.sendMessage("Gildia o takiej nazwie istnieje!");
					 }
				 } else {
					 sender.sendMessage(wiecejNizCztery);
				 }

				 } else {
				 sender.sendMessage("Gildia o takim tagu istnieje!");
				 }
			 } else {
				 sender.sendMessage(CzteryLitery);
			 }
		  } else {
			  sender.sendMessage(ChatColor.RED+"Masz juz gildie!");
			 }
		  }
		  
		  return false;
	  }
	
}
