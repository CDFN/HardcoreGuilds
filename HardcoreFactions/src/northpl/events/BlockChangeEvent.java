package northpl.events;

import java.util.Arrays;
import java.util.List;

import northpl.cmds.Bufor;
import northpl.cmds.GuildUtils;
import northpl.main.Cuboid;
import northpl.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockChangeEvent implements Listener {

	public BlockChangeEvent(Main plugin)
	  {
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	  }
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onBlockRemove(BlockBreakEvent event)
	  {
		  int iloscGildii = Bufor.iloscGildii;
		  
		  if (iloscGildii != 0) {
				 for (int i = 0; i < iloscGildii;i++)
				 {
					 if (!(event.isCancelled())) {
					 String aktualnaGildia = Bufor.listaGildii.get(i);
					 String lol = Bufor.cuboidy.get(aktualnaGildia);
					 int x1 = 0;
					 int z1 = 0;
					 int x2 = 0;
					 int z2 = 0;

					 		
					 		List<String> items = Arrays.asList(lol.split("\\s*,\\s*"));

					 		
					 			x1 = Integer.parseInt(items.get(0));
					 			z1 = Integer.parseInt(items.get(1));
					 			x2 = Integer.parseInt(items.get(2));
					 			z2 = Integer.parseInt(items.get(3));
					 
		                Location loc1 = new Location(Bukkit.getWorld("world"), x1, 0, z1);
		                Location loc2 = new Location(Bukkit.getWorld("world"), x2, 257, z2);
		                Cuboid cuboid = new Cuboid(loc1, loc2);
					 
					 if (cuboid.contains(event.getBlock().getLocation())) {
						 if (GuildUtils.czyGraczMaGildie(event.getPlayer().getName())) {
			  String gildiaGracza = GuildUtils.dajGildieGracza(event.getPlayer().getName());
			  if (!(aktualnaGildia.equals(gildiaGracza))) {
				  event.setCancelled(true);
			  event.getPlayer().sendMessage(ChatColor.RED+"To teren innej gildii! Nie mozesz tutaj budowac");
		  }
					 } else {
						 event.setCancelled(true);
						  event.getPlayer().sendMessage(ChatColor.RED+"To teren innej gildii! Nie mozesz tutaj budowac");
					 }
		  
				 }
		  }
		  }
	  }
	  }
	
	
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onBlockPlace(BlockPlaceEvent event)
	  {
		  int iloscGildii = Bufor.iloscGildii;
		  
		  if (iloscGildii != 0) {
				 for (int i = 0; i < iloscGildii;i++)
				 {
					 if (!(event.isCancelled())) {
					 String aktualnaGildia = Bufor.listaGildii.get(i);
					 String lol = Bufor.cuboidy.get(aktualnaGildia);
					 int x1 = 0;
					 int z1 = 0;
					 int x2 = 0;
					 int z2 = 0;

					 		
					 		List<String> items = Arrays.asList(lol.split("\\s*,\\s*"));
					 			x1 = Integer.parseInt(items.get(0));
					 			z1 = Integer.parseInt(items.get(1));
					 			x2 = Integer.parseInt(items.get(2));
					 			z2 = Integer.parseInt(items.get(3));
					 
		                Location loc1 = new Location(Bukkit.getWorld("world"), x1, 0, z1);
		                Location loc2 = new Location(Bukkit.getWorld("world"), x2, 257, z2);
		                Cuboid cuboid = new Cuboid(loc1, loc2);
					 
					 if (cuboid.contains(event.getBlock().getLocation())) {
						 if (GuildUtils.czyGraczMaGildie(event.getPlayer().getName())) {
			  String gildiaGracza = GuildUtils.dajGildieGracza(event.getPlayer().getName());
			  if (!(aktualnaGildia.equals(gildiaGracza))) {
				  event.setCancelled(true);
			  event.getPlayer().sendMessage(ChatColor.RED+"To teren innej gildii! Nie mozesz tutaj budowac");
		  }
					 } else {
						 event.setCancelled(true);
						  event.getPlayer().sendMessage(ChatColor.RED+"To teren innej gildii! Nie mozesz tutaj budowac");
					 }
		  
				 }
		  }
		  }
	  }
	  }
	  
}
