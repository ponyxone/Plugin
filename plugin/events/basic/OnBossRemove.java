package plugin.events.basic;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import resources.EntityUtils;

public class OnBossRemove implements Listener{

	@EventHandler
	public void onBossRemoved(EntityDeathEvent event) {
		if(EntityUtils.isBoss(event.getEntity())) {
			try {
				OnDamage.bossChildrenList.forEach(x->x.remove());
				OnDamage.bossChildrenList.clear();
			} catch (Exception e) {}
		}	
	}
}
