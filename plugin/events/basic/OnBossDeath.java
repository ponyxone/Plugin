package plugin.events.basic;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import main.Core;
import resources.EntityUtils;

public class OnBossDeath implements Listener{
	@EventHandler
	public void onBossDeath(EntityDeathEvent event) {
		if(EntityUtils.isBoss(event.getEntity())) {
			event.getDrops().clear();
				World wd = event.getEntity().getWorld();
				for(int i = 0; i!= 10; i++) {
				wd.strikeLightning(event.getEntity().getLocation());
			} 
				try {
					OnDamage.bossChildrenList.forEach(x->x.remove());
					OnDamage.bossChildrenList.clear();
					Core.getBoss().getBossMount().remove();
					
							} catch (Exception e) {}
				EntityUtils.dropRandomReward(event.getEntity());
				
				}
		
				
				
			} {
				
			}}
		
	
