package northpl.cmds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bufor {

	public static List<String> listaGildii = new ArrayList<String>();
	public static List<String> listaNazwaGildii = new ArrayList<String>();
	
	public static Map<String, String> cuboidy = new HashMap<String, String>();
	public static Map<String, String> gracze = new HashMap<String, String>();
	public static Map<String, String> ownerzy = new HashMap<String, String>();
	public static Map<String, String> zaproszenia = new HashMap<String, String>();

	public static int iloscGildii;
	
	
	public static void budujIloscGildii() {
		iloscGildii = 0;
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery("SELECT Tag FROM guilds");
                while (rs.next()) {
                    iloscGildii++;
                }
                
                System.out.println("[DEBUG] Zaktualizowano liczbe gildii do: " + iloscGildii);
                
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
	public static void budujListeGildii() {
		listaGildii.clear();
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM guilds");
                while (rs.next()) {
                    listaGildii.add(rs.getString(2));
                }
                System.out.println("[DEBUG] Zaktualizowano liste gildii do: " + listaGildii);
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
	public static void budujListeNazwGildii() {
		listaNazwaGildii.clear();
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM guilds");
                while (rs.next()) {
                    listaNazwaGildii.add(rs.getString("Nazwa"));
                }
                System.out.println("[DEBUG] Zaktualizowano liste nazw gildii do: " + listaGildii);
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
	public static void budujListeOwnerow() {
		ownerzy.clear();
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM players");
                while (rs.next()) {
                    if (rs.getString(4).equals("O")) {
                    	ownerzy.put(rs.getString(3), rs.getString(2));
                    }
                }
                System.out.println("[DEBUG] Zaktualizowano liste ownerow gildii do: " + ownerzy);
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
	public static void budujListeGraczy() {
		gracze.clear();
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM players");
                while (rs.next()) {
                    	gracze.put(rs.getString(2), rs.getString(3));
                }
                System.out.println("[DEBUG] Zaktualizowano liste graczy do: " + gracze);
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
	public static void budujListeCubow() {
		cuboidy.clear();
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM cuboids");
                while (rs.next()) {
                	String Tag = rs.getString("Tag");
                	int minX = rs.getInt(3);
                	int minZ = rs.getInt(4);
                	int maxX = rs.getInt(5);
                	int maxZ = rs.getInt(6);
                	String split = " , ";
                	
                    cuboidy.put(Tag, minX+split+minZ+split+maxX+split+maxZ);
                }
                System.out.println("[DEBUG] Zaktualizowano liste cuboidów do: " + cuboidy);
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
	public static void budujListeZaproszen() {
		zaproszenia.clear();
		String tempZaproszenia = null;
		
        try{
            Connection conn = DriverManager.getConnection(GuildUtils.ip, GuildUtils.login, GuildUtils.haslo);
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM invite");
                
                for (int i = 0; i < iloscGildii; i++) {
                	String actualGuild = Bufor.listaGildii.get(i);
                	
                	
                    while (rs.next()) {
                    	if (rs.getString("Guild").equals(actualGuild)) {
                    	if (tempZaproszenia == null) {
                    		tempZaproszenia = rs.getString("Nick");
                    	} else {
                    		tempZaproszenia = tempZaproszenia + " , " +rs.getString("Nick");
                    	}
                    		
                    	}
                }
                	zaproszenia.put(actualGuild, tempZaproszenia);
                	tempZaproszenia = null;
                    
                }
                
                
                System.out.println("[DEBUG] Zaktualizowano liste zaproszen do: " + gracze);
                conn.close();
                }catch (SQLException e){e.printStackTrace(); System.out.println("Cos sie zepsulo ;/");}
        }catch (SQLException e){System.out.println("Uwaga!!!! Mamy problem z polaczeniem!!!!");}
	}
	
}
