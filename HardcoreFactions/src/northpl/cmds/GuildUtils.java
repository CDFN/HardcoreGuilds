package northpl.cmds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class GuildUtils {
	
	//Ustawienia na otavi.pl
	static String ip = "4434";
	static String login = "3334434334";
	static String haslo = "NiePodam";
	
	//Ustawienia testowe
	//static String ip = "jdbc:mysql://localhost/czokoguilds";
	//static String login = "root";
	//static String haslo = "";
	
	public static boolean czyTakiTagIstnieje(String name) {
        return Bufor.listaGildii.contains(name);
	}
	
	public static boolean czyTakaNazwaIstnieje(String nazwa) {
        return Bufor.listaNazwaGildii.contains(nazwa);
	}
	
	public static boolean czyGraczMaGildie(String gracz) {
		boolean istnieje = false;
		if (Bufor.gracze.get(gracz) == null) {
			istnieje = false;
		} else {
			istnieje = true;
		}
		
        return istnieje;
	}
	
	public static boolean czyGraczJestOwnerem(String gracz, String guild) {
		boolean jestOwnerem = false;

		if (Bufor.ownerzy.get(guild) != null) {
		if (gracz.equals(Bufor.ownerzy.get(guild))) {
			jestOwnerem = true;
		}
		} else { jestOwnerem = false; }
		
        return jestOwnerem;
	}
	
	public static String dajGildieGracza(String gracz) {
		String lol = null;


		if (Bufor.gracze.get(gracz) != null) {
			lol = Bufor.gracze.get(gracz);
		}
		
        return lol;
	}
	
	public static String dajOwneraGildii(String guild) {
		String lol = null;

		if (Bufor.ownerzy.get(guild) != null) {
			lol = Bufor.ownerzy.get(guild);
		}
		
        return lol;
	}
	
	public static String zamienNaNazwe(String guild) {
		String lol = null;
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement st = conn.createStatement();
                ResultSet rs=st.executeQuery("select * from guilds where Tag like '"+'%'+guild+'%'+"'");

                boolean val = rs.next();
                if(val==false){
                    lol = null;
                 } else {
                	 lol = rs.getString(3);
                 }
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
        return lol;
	}
	
	public static boolean doesCollide(int teraz_minX, int teraz_minZ, int teraz_maxX, int teraz_maxZ, int petla_minX, int petla_minZ, int petla_maxX, int petla_maxZ) {
		return !(teraz_minX > petla_maxX || teraz_maxX < petla_minX || teraz_minZ > petla_maxZ || teraz_maxZ < petla_minZ);
		}
	
	public static boolean czyJestZaproszony(String player, String guildTag) {
		boolean zaproszony = false;
		if (Bufor.zaproszenia.get(guildTag) == null) {
			
			zaproszony=false;
			
		} else {
		 List<String> list = Lists.newArrayList(Splitter.on(" , ").split(Bufor.zaproszenia.get(guildTag)));
		 if (list.contains(player) == true) {
			 zaproszony = true;
		 }
		}
		return zaproszony;
	}
	
	public static int LiczbaGraczy(String guild) {
		int iloscGraczy = 0;
		
		if (Bufor.gracze.get(guild) == null) {
			
			iloscGraczy = 0;
			
		} else {
		 List<String> list = Lists.newArrayList(Splitter.on(" , ").split(Bufor.gracze.get(guild)));
		 String[] array = (String[]) list.toArray();
		 iloscGraczy = array.length;
		}
		
		return iloscGraczy;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean czyGraczMaItemy(String p) {
		boolean czyMa = true;
		Player pp = Bukkit.getPlayer(p);
		
		if (!pp.getInventory().contains(new ItemStack(Material.DIAMOND, 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.GOLD_INGOT, 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.getMaterial(289), 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.SUGAR_CANE, 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.BRICK, 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.EMERALD, 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.SAPLING, 64))) {
			czyMa = false;
		}
		if (!pp.getInventory().contains(new ItemStack(Material.getMaterial(39), 64))) {
			czyMa = false;
		}
		
		return czyMa;
	}
	
	@SuppressWarnings("deprecation")
	public static void zabierzItemyGraczowi(String gracz) {
		Player p = Bukkit.getPlayer(gracz); Inventory inv = p.getInventory();
		inv.removeItem(new ItemStack(Material.DIAMOND, 64));
		inv.removeItem(new ItemStack(Material.GOLD_INGOT, 64));
		inv.removeItem(new ItemStack(Material.getMaterial(289), 64));
		inv.removeItem(new ItemStack(Material.SUGAR_CANE, 64));
		inv.removeItem(new ItemStack(Material.getMaterial(39), 64));
		inv.removeItem(new ItemStack(Material.BRICK, 64));
		inv.removeItem(new ItemStack(Material.EMERALD, 64));
		inv.removeItem(new ItemStack(Material.SAPLING, 64));
	}

}
