package entities.hostile.bosses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class BossChildren {
	
	World wd = Bukkit.getServer().getWorlds().get(0);
	int childCounter = 0;
	Entity child = null;
	Entity bat = null;
	LivingEntity lChild;
	EntityEquipment lChildEquipment;
	public BossChildren(Boss boss, Location spawnPoint) {
		
		if(boss.getBossType() == EntityType.SKELETON) {		
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);	
			while(childCounter!=3) {
				child = wd.spawnEntity(spawnPoint, EntityType.SKELETON);
				lChild = (LivingEntity) child;
				lChildEquipment = lChild.getEquipment();
				lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
				for(int i = 0;i!=10; i++) {
					bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
				}
				childCounter++;}
			
		}else if(boss.getBossType() == EntityType.SKELETON) {		
			bat = wd.spawnEntity(spawnPoint, EntityType.BAT);	
		while(childCounter!=3) {
			child = wd.spawnEntity(spawnPoint, EntityType.SKELETON);
			lChild = (LivingEntity) child;
			lChildEquipment = lChild.getEquipment();
			lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
			}}
			childCounter++;}
		else if(boss.getBossType() == EntityType.SKELETON) {		
			bat = wd.spawnEntity(spawnPoint, EntityType.BAT);	
		while(childCounter!=3) {
			child = wd.spawnEntity(spawnPoint, EntityType.SKELETON);
			lChild = (LivingEntity) child;
			lChildEquipment = lChild.getEquipment();
			lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
			}
			childCounter++;}}
		else if(boss.getBossType() == EntityType.SKELETON) {		
			bat = wd.spawnEntity(spawnPoint, EntityType.BAT);	
		while(childCounter!=3) {
			child = wd.spawnEntity(spawnPoint, EntityType.SKELETON);
			lChild = (LivingEntity) child;
			lChildEquipment = lChild.getEquipment();
			lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
			}
			childCounter++;}
		}
		
		wd.spawnParticle(Particle.EXPLOSION_HUGE, spawnPoint, 1);
		wd.spawnParticle(Particle.SMOKE_LARGE, spawnPoint, 5);

	
	}
	
}
