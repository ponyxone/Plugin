package plugin.events.basic;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class l implements Listener{
	
	public static boolean suicideController;
	@EventHandler
	public void onPlayerSuicide(PlayerDeathEvent event) {
		//if(suicideController==true) {
			event.setDeathMessage(null);
		//}
	}
	//useless class lol
	
}