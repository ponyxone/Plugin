package resources;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import entities.hostile.bosses.Boss;
import main.Core;
import plugin.events.basic.OnDamage;

public class EntityUtils{
	Random random = new Random();
	Entity entity;
	LivingEntity lChild;
	public EntityUtils() {
		
	}
	
	public static boolean isBoss(Entity entity) {
		if(entity.getPersistentDataContainer().has(new NamespacedKey(Core.getPluginInstance(), "bossKey"))) {
			if(entity.getPersistentDataContainer().get(new NamespacedKey(Core.getPluginInstance(), "bossKey"), PersistentDataType.STRING)=="isBoss") {
			return true;
			}else {return false;}
	}else {return false;}
		}
	public static boolean isBossMount(Entity entity) {
		if(entity.getPersistentDataContainer().has(new NamespacedKey(Core.getPluginInstance(), "horseKey"))) {
			if(entity.getPersistentDataContainer().get(new NamespacedKey(Core.getPluginInstance(), "horseKey"), PersistentDataType.STRING)== "isBossHorse") {
			return true;
			}else {			
return false;}
	}else {			
return false;}
	}
	
	
	
	public EntityType randomEntityType() {
		EntityType[] entityRace = {EntityType.SKELETON, EntityType.STRAY, EntityType.ZOMBIE, EntityType.HUSK,};
		int randomEntity = random.nextInt(entityRace.length);
		return entityRace[randomEntity];
	}
	
	
	public String randomName() {	
		String[] bossNames = {
			    "Vexxar", "Malgathor", "Zephyrus", "Ravenna", "Drakmor",
			    "Xanathor", "Thalador", "Morgul", "Azura", "Grendor",
			    "Seraphina", "Grimtide", "Valkyra", "Ignatius", "Mordecai",
			    "Astaroth", "Selene", "Vorago", "Fenrir", "Sylvestra",
			    "Valakar", "Nocturna", "Baelor", "Xalvador", "Nephilim",
			    "Hecate", "Dargor", "Thalia", "Argos", "Morgana",
			    "Varathor", "Lilith", "Kael'thas", "Astoria", "Arkanis",
			    "Azazel", "Lyra", "Belial", "Zarael", "Morwyn",
			    "Samael", "Isolde", "Ragnar", "Xanthe", "Tharador",
			    "Celestia", "Valrak", "Azrael", "Styx", "Morgath",
			    "Sylvanas", "Draven", "Nyx", "Grimgor", "Vespera",
			    "Lucian", "Zarathos", "Morgara", "Andromeda", "Xalvira",
			    "Vorathor", "Liliana", "Korvus", "Zephyra", "Vortis",
			    "Malachi", "Zaladar", "Nyxeria", "Hades", "Morgorath",
			    "Seraphim", "Vexia", "Zarathustra", "Mordeus", "Xandor",
			    "Valkyria", "Thalos", "Azuris", "Xerxes", "Velenor",
			    "Morvanna", "Zaraeth", "Nyxen", "Thanatos", "Vorgrimm",
			    "Sylvaris", "Valeria", "Zalvador", "Mordred", "Xylia",
			    "Drakara", "Vexon", "Zephyris", "Malgor", "Ravencroft",
			    "Thaladoria", "Azurian", "Xanthor", "Morgulon", "Zaraen"
			};

		int nome = random.nextInt(bossNames.length);
		return bossNames[nome];
	}
	
	
	public int randomHp() {
		int hp = random.nextInt(100, 150);
		return hp;
		}
	
	
	public static Location findRandomLocation(World world) {
		//Si ringrazia Choco per l'aiuto

	    ThreadLocalRandom random = ThreadLocalRandom.current();
	    Location location = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
	    int x = 0, y = 0, z = 0;

	    int attempts = 0;
	
	    while (attempts++ < 10) {
	        x = random.nextInt(-1000, 1000);
	        z = random.nextInt(-1000, 1000);
	        y = world.getHighestBlockYAt(x, z);
	        if (world.getBiome(x, y, z) == Biome.OCEAN) {
	            continue; // Would want to check for more than just OCEAN, obviously
	        }
	        Chunk chunk = world.getChunkAt(x >> 4, z >> 4);

	        for (int i = 0; i < 16; i++) {
	            int minX = chunk.getX() << 4, minZ = chunk.getZ() << 4;
	            int maxX = minX + 16, maxZ = minZ + 16;
	            x = random.nextInt(minX, maxX); z = random.nextInt(minZ, maxZ);
	            y = world.getHighestBlockYAt(x, z);

	            Block block = world.getBlockAt(x, y, z); // Could also use World#getType(x, y, z) to avoid a Block allocation
	            if(block.getType().isAir()) {
	                y--;
	                }
	            else if(block.isLiquid()) {
	                printCoordinates(location);
	                System.out.println("Is Water");
	            }
	            else {
	            	location.setX(x);
	            	location.setY(y+1);
	            	location.setZ(z);
	            	printCoordinates(location);
	            	System.out.println("is fine");
	            return location;
	            }
	        }
	    }

	    return null; // Just a safety in case it couldn't find a position in 10 (* 16) attempts
	}
	public Location randomLocation() {
        Location location = null;
        boolean generateNew = true;
        World world = Bukkit.getServer().getWorlds().get(0);
        while(generateNew) {
            boolean k = true;

            int x = random.nextInt(-5000, 5000);
            int z = random.nextInt(-5000, 5000);


        int y = 320;
        while(k==true) {
            location = new Location(world, x, y, z);
            System.out.println(location.getChunk().isLoaded());
            if(location.getBlock().getType().isAir()) {
                y--;
                }
            else if(location.getBlock().isLiquid()) {
                k=false;
                printCoordinates(location);
                System.out.println("Is Water");
            }
            else {location.setY(y+1);
            	generateNew=false;
                location.getChunk().setForceLoaded(true);
                return location;
            
            
            }

            }
        }
        return null;
    }
	
	
	public static ItemStack[] randomEquipment(EntityType entity) {
		Random random = new Random();
		ItemStack mainWeapon = null, helmet, chestplate, leggings, boots;
		if(entity == EntityType.SKELETON || entity == EntityType.STRAY) {
			mainWeapon = new ItemStack(Material.BOW);
			mainWeapon.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, random.nextInt(8, 15));
		}else if (entity == EntityType.ZOMBIE || entity == EntityType.HUSK) {
			mainWeapon = new ItemStack(Material.DIAMOND_SWORD);
			mainWeapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, random.nextInt(8, 15));
		}
		int randomArmorChoice = random.nextInt(0, 1);
		Material[] helmetList = {Material.CHAINMAIL_HELMET, Material.LEATHER_HELMET};
		Material[] chestplateList = {Material.CHAINMAIL_CHESTPLATE, Material.LEATHER_CHESTPLATE};
		Material[] leggingsList = {Material.CHAINMAIL_LEGGINGS, Material.LEATHER_LEGGINGS};
		Material[] bootsList = {Material.CHAINMAIL_BOOTS, Material.LEATHER_BOOTS};
		helmet = new ItemStack(helmetList[randomArmorChoice]);
		chestplate = new ItemStack(chestplateList[randomArmorChoice]);
		leggings = new ItemStack(leggingsList[randomArmorChoice]);
		boots = new ItemStack(bootsList[randomArmorChoice]);
		
		ItemStack[] equipment = {mainWeapon, helmet, chestplate, leggings, boots};
		return equipment;
	}
	
	
	public static ArrayList<PotionEffectType> randomEffects(EntityType entity) {
		return null;
	}
	
	
	
	
	public static void spawnBossChildren(Boss boss, Location spawnPoint) {
		World wd = Bukkit.getServer().getWorlds().get(0);
		int childCounter = 0;
		Entity child = null;
		Entity bat = null;
		
		if(boss.getBossType() == EntityType.SKELETON) {
			
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
				OnDamage.bossChildrenList.add((LivingEntity)bat);
			}
			
			while(childCounter!=3) {
				child = wd.spawnEntity(spawnPoint, EntityType.SKELETON);
				LivingEntity lChild = (LivingEntity) child;
				EntityEquipment lChildEquipment = lChild.getEquipment();
				lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
				lChild.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
				OnDamage.bossChildrenList.add(lChild);

				childCounter++;}
			
		}if(boss.getBossType() == EntityType.STRAY) {
			
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
				OnDamage.bossChildrenList.add((LivingEntity)bat);
			}
			
			while(childCounter!=3) {
				child = wd.spawnEntity(spawnPoint, EntityType.STRAY);
				LivingEntity lChild = (LivingEntity) child;
				EntityEquipment lChildEquipment = lChild.getEquipment();
				lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
				OnDamage.bossChildrenList.add(lChild);

				childCounter++;}
			
		}if(boss.getBossType() == EntityType.ZOMBIE) {
			
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
				OnDamage.bossChildrenList.add((LivingEntity)bat);

			}
			
			while(childCounter!=3) {
				child = wd.spawnEntity(spawnPoint, EntityType.ZOMBIE);
				LivingEntity lChild = (LivingEntity) child;
				EntityEquipment lChildEquipment = lChild.getEquipment();
				lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
				OnDamage.bossChildrenList.add(lChild);

				childCounter++;}
			
		}if(boss.getBossType() == EntityType.HUSK) {
			
			for(int i = 0;i!=10; i++) {
				bat = wd.spawnEntity(spawnPoint, EntityType.BAT);
				OnDamage.bossChildrenList.add((LivingEntity)bat);

			}
			
			while(childCounter!=3) {
				child = wd.spawnEntity(spawnPoint, EntityType.HUSK);
				LivingEntity lChild = (LivingEntity) child;
				EntityEquipment lChildEquipment = lChild.getEquipment();
				lChildEquipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
				OnDamage.bossChildrenList.add(lChild);

				
				childCounter++;}
			
		}
		
		wd.spawnParticle(Particle.EXPLOSION_HUGE, spawnPoint, 1);
		wd.spawnParticle(Particle.SMOKE_LARGE, spawnPoint, 5);

	}
	
	
	
	
	public static AbstractHorse randomBossMount(Entity boss) {
		Random random = new Random();
		String name = boss.getName();
		Location spawnPoint = boss.getLocation();
		int randomMountChoice = random.nextInt(2);
		int HORSE_MAX_HEALTH = 30;
		EntityType[] mounts = {EntityType.ZOMBIE_HORSE, EntityType.SKELETON_HORSE};
		Entity mount = spawnPoint.getWorld().spawnEntity(spawnPoint, mounts[randomMountChoice]);
		mount.setGlowing(true);
		mount.setCustomName(name + "'s Horse");
		AbstractHorse entityClass = (AbstractHorse) mount;
		entityClass.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(HORSE_MAX_HEALTH);
		entityClass.setHealth(HORSE_MAX_HEALTH);

		entityClass.getPersistentDataContainer().set(new NamespacedKey(Core.getPluginInstance(), "horseKey"), PersistentDataType.STRING, "isBossHorse" );
		return entityClass;
	}
	
	public static void setBossSkeleton(Entity entity, Skeleton skeleton) {
		entity.setGlowing(true);
		skeleton.setRemoveWhenFarAway(false);
	}	
	public static void setBossZombie(Entity entity, Zombie zombie) {
		entity.setGlowing(true);
		zombie.setRemoveWhenFarAway(false);
		}
	public static void setBossHusk(Entity entity, Husk husk) {
		entity.setGlowing(true);
		husk.setRemoveWhenFarAway(false);
		}
	public static void setBossStray(Entity entity, Stray stray) {
		entity.setGlowing(true);
		stray.setRemoveWhenFarAway(false);
		}
	
		public static void printCoordinates(Location location) {
			System.out.println(location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
		}
		public static void printCoordinatesInChat(Location location) {
			Bukkit.broadcastMessage(location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " ");
		}
		public static String getCoordinates(Location location) {
			return(location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " ");
		}
		public static String getXZCoordinates(Location location) {
			return(location.getBlockX() + " ∼ " + location.getBlockZ());
		}
		
		public static int randomRewardEnchantmentLevel(int min, int max) {
			Random random = new Random();
			return random.nextInt(min, max);
		}
		
		public static void dropRandomReward(Entity entity) {
			Random random = new Random();

		    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		    sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, randomRewardEnchantmentLevel(4, 8));
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        sword.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    ItemStack bow = new ItemStack(Material.BOW);
		    bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, randomRewardEnchantmentLevel(4, 8));
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        bow.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    ItemStack iHelmet = new ItemStack(Material.IRON_HELMET);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        iHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelIHelmet = randomRewardEnchantmentLevel(4, 6);
		    iHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelIHelmet);

		    ItemStack dHelmet = new ItemStack(Material.DIAMOND_HELMET);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        dHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelDHelmet = randomRewardEnchantmentLevel(4, 6);
		    dHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelDHelmet);


		    ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        ironChestplate.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelIronChestplate = randomRewardEnchantmentLevel(4, 6);
		    ironChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelIronChestplate);

		    ItemStack dChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        dChestplate.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelDChestplate = randomRewardEnchantmentLevel(4, 6);
		    dChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelDChestplate);

		    ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        ironLeggings.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelIronLeggings = randomRewardEnchantmentLevel(4, 6);
		    ironLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelIronLeggings);

		    ItemStack dLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        dLeggings.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelDLeggings = randomRewardEnchantmentLevel(4, 6);
		    dLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelDLeggings);

		    ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        ironBoots.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelIronBoots = randomRewardEnchantmentLevel(4, 6);
		    ironBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelIronBoots);

		    ItemStack dBoots = new ItemStack(Material.DIAMOND_BOOTS);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        dBoots.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    int protectionLevelDBoots = randomRewardEnchantmentLevel(4, 6);
		    dBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protectionLevelDBoots);

		    ItemStack shield = new ItemStack(Material.SHIELD);
		    if (random.nextBoolean()) {
		        int unbreakingLevel = random.nextInt(3) + 1;
		        shield.addUnsafeEnchantment(Enchantment.DURABILITY, unbreakingLevel);
		    }
		    
		    
		    ItemStack[] drops = {sword, bow, iHelmet, dHelmet, ironChestplate, dChestplate, ironLeggings, dLeggings, ironBoots, dBoots, shield};
		    ItemStack drop = drops[random.nextInt(drops.length)];
	        
	        Core.getPluginInstance().getServer().getWorlds().get(0).dropItem(entity.getLocation(), drop).setInvulnerable(true);
	        
		}
		
	}

		
	
	

