package plugin.events.basic;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import main.Core;
import resources.EntityUtils;

public class OnBossSpawn implements Listener{
	@EventHandler
	public void onBossSpawn(EntitySpawnEvent event) {
		if(EntityUtils.isBossMount(event.getEntity())) {
			
	    }
		}
	}
	

