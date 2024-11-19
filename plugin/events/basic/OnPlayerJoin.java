package plugin.events.basic;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import main.Core;


public class OnPlayerJoin implements Listener{ 
	public static ArrayList<String> playerList = new ArrayList<String>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){ //Nota: il nome del metodo è arbitrario. Questo, per esempio non viene mai citato nella documentazione
		Player player = event.getPlayer();
		for(Object name:Core.config.getList("players")) {
			playerList.add((String) name);
		}
		
		String playerName = event.getPlayer().getName();
		
		if(Core.config.getList("players").contains(playerName)) {
			System.out.println(playerName + " è entrato nel server.");
			event.setJoinMessage(playerName + " è entrato nel server.");

		}else {
			System.out.println(playerName + " è entrato nel server per la prima volta.");
			event.setJoinMessage(playerName + " è entrato nel server per la prima volta.");

			Core.config.getList("players");
		}
		
		
		
		
	}
}
