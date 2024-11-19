package plugin.events.basic;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import main.Core;
import resources.EntityUtils;

public class OnDamage implements Listener{
	public static ArrayList<LivingEntity> bossChildrenList = new ArrayList<LivingEntity>();
	@EventHandler
	public void onDamageBoss(EntityDamageByEntityEvent event){
		if (!(event.getDamager() instanceof Player) && ((EntityUtils.isBoss(event.getEntity())) || EntityUtils.isBossMount(event.getEntity())) ) {
			event.setCancelled(true);
		}
		if(event.getDamager() instanceof Player) {
			Entity entity = event.getEntity();
			if(event.getEntity().getPersistentDataContainer().has(new NamespacedKey(Core.getPluginInstance(), "bossKey"))) {
				if(entity.getPersistentDataContainer().get(new NamespacedKey(Core.getPluginInstance(), "bossKey"), PersistentDataType.STRING)=="isBoss") {
					String name = Core.getBoss().getBossInitialName();
					LivingEntity lEntity = (LivingEntity) entity;
					entity.setCustomName(name + " " + (int)lEntity.getHealth());
				}
				
			}else if(entity.getPersistentDataContainer().has(new NamespacedKey(Core.getPluginInstance(), "horseKey"))) {
				if(entity.getPersistentDataContainer().get(new NamespacedKey(Core.getPluginInstance(), "horseKey"), PersistentDataType.STRING)== "isBossHorse") {
					EntityUtils.spawnBossChildren(Core.getBoss(), entity.getLocation());
				}
				
			}
			
	}}}
 