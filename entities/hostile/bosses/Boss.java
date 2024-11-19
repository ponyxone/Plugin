package entities.hostile.bosses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import main.Core;
import plugin.events.basic.OnDamage;
import resources.EntityUtils;

public class Boss {
	String initialName;
	LivingEntity boss;
	LivingEntity bossMount;
	EntityType bossType;
	int bossHp;
	Location bossLocation;
	

	public void setBoss(LivingEntity boss) {
		this.boss = boss;
	}

	
	public Boss(EntityType type, String name, int hp, Location location) {
		this.bossHp = hp;
		this.bossLocation = location;
		this.bossType = type;
		this.initialName = name;
		Entity entity = location.getWorld().spawnEntity(location, type);
		entity.setCustomName(name);
		AbstractHorse mount = EntityUtils.randomBossMount(entity);
		LivingEntity lMount = (LivingEntity) mount;
		lMount.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
		lMount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 1));
		LivingEntity lEntity = (LivingEntity) entity;
		lEntity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
		mount.addPassenger(entity);
		PersistentDataContainer bossPdc = entity.getPersistentDataContainer();
		bossPdc.set(new NamespacedKey(Core.getPluginInstance(), "bossKey"), PersistentDataType.STRING, "isBoss");
		ItemStack[] entityEquipment = EntityUtils.randomEquipment(type);
		if(entity.getType() == EntityType.SKELETON) {
			Skeleton entityClass = (Skeleton) entity;
			entityClass.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
			entityClass.setHealth(hp);
			entityClass.getEquipment().setItemInMainHand(entityEquipment[0]);
			entityClass.getEquipment().setHelmet(entityEquipment[1]);
			entityClass.getEquipment().setChestplate(entityEquipment[2]);
			entityClass.getEquipment().setLeggings(entityEquipment[3]);
			entityClass.getEquipment().setBoots(entityEquipment[4]);
			EntityUtils.setBossSkeleton(entity, entityClass);
		}
		else if(entity.getType() == EntityType.STRAY) {
			Stray entityClass = (Stray) entity;
			entityClass.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
			entityClass.setHealth(hp);
			entityClass.getEquipment().setItemInMainHand(entityEquipment[0]);
			entityClass.getEquipment().setHelmet(entityEquipment[1]);
			entityClass.getEquipment().setChestplate(entityEquipment[2]);
			entityClass.getEquipment().setLeggings(entityEquipment[3]);
			entityClass.getEquipment().setBoots(entityEquipment[4]);
			EntityUtils.setBossStray(entity, entityClass);
		}
		else if(entity.getType() == EntityType.ZOMBIE) {
			Zombie entityClass = (Zombie) entity;
			entityClass.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp+60);
			entityClass.setHealth(hp+60);
			entityClass.getEquipment().setItemInMainHand(entityEquipment[0]);
			entityClass.getEquipment().setHelmet(entityEquipment[1]);
			entityClass.getEquipment().setChestplate(entityEquipment[2]);
			entityClass.getEquipment().setLeggings(entityEquipment[3]);
			entityClass.getEquipment().setBoots(entityEquipment[4]);
			EntityUtils.setBossZombie(entity, entityClass);
		}
		else if(entity.getType() == EntityType.HUSK) {
			Husk entityClass = (Husk) entity;
			entityClass.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp+60);
			entityClass.setHealth(hp+60);
			entityClass.getEquipment().setItemInMainHand(entityEquipment[0]);
			entityClass.getEquipment().setHelmet(entityEquipment[1]);
			entityClass.getEquipment().setChestplate(entityEquipment[2]);
			entityClass.getEquipment().setLeggings(entityEquipment[3]);
			entityClass.getEquipment().setBoots(entityEquipment[4]);
			EntityUtils.setBossHusk(entity, entityClass);
		}	
		this.boss = (LivingEntity)entity;
		this.bossMount = mount;
	}
	
	public void removeBoss() {
		boss.remove();
		bossMount.remove();
	}
	
	
	public EntityType getBossType() {
		return bossType;
	}
	
	
	public String getBossInitialName() {
		return initialName;
	}
	
	
	public int getBossHealth() {
		return bossHp;
	}
	
	public Location getBossLocation() {
		return bossLocation;
	}
	
	public LivingEntity getBossMount() {
		return bossMount;
	}
	public LivingEntity getBoss() {
		return boss;
	}
}
