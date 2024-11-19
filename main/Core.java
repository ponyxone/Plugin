package main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import entities.hostile.bosses.Boss;
import plugin.commands.AllPlayers;
import plugin.commands.Commands;
import plugin.events.basic.OnBossDeath;
import plugin.events.basic.OnBossRemove;
import plugin.events.basic.OnBossSpawn;
import plugin.events.basic.OnDamage;
import plugin.events.basic.OnPlayerJoin;
import plugin.events.basic.l;
import resources.EntityUtils;

public class Core extends JavaPlugin implements Listener {
    public static Core pluginInstance;
    public static FileConfiguration config;
    public static Boss boss;

    @Override
    public void onEnable() {
        pluginInstance = this;
        config = getConfig();
        System.out.println("---Plugin Online---");
        System.out.println(this.getConfig().getList("players"));
        BukkitScheduler scheduler = getServer().getScheduler();

        // Event Registration
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), pluginInstance);
        getServer().getPluginManager().registerEvents(new OnDamage(), pluginInstance);
        getServer().getPluginManager().registerEvents(new l(), pluginInstance);
        getServer().getPluginManager().registerEvents(new OnBossDeath(), pluginInstance);
        getServer().getPluginManager().registerEvents(new OnBossRemove(), pluginInstance);
        getServer().getPluginManager().registerEvents(new OnBossSpawn(), pluginInstance);

        // Command Registration
        getCommand("suicide").setExecutor(new Commands());
        getCommand("everyone").setExecutor(new AllPlayers());

        // Configuration File
        getConfig().options().copyDefaults();
        EntityUtils entityRandomiser = new EntityUtils();

        


        try {
            boss.removeBoss();
        } catch (Exception e) {}

        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("Boss Can Now Spawn");
                scheduler.scheduleSyncRepeatingTask(getPluginInstance(), new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boss.removeBoss();
                            OnDamage.bossChildrenList.forEach(x -> x.remove());
                            OnDamage.bossChildrenList.clear();
                        } catch (Exception e) {
                            
                        }
                        EntityUtils entityRandomiser = new EntityUtils();
                        boss = new Boss(entityRandomiser.randomEntityType(), entityRandomiser.randomName(), entityRandomiser.randomHp(), entityRandomiser.randomLocation());
                        boss.getBossMount().setInvulnerable(false);
                        boss.getBoss().setInvulnerable(true);
                        Bukkit.broadcastMessage(boss.getBossInitialName() + " è spawnato alle coordinate " + EntityUtils.getCoordinates(boss.getBossLocation()));
                    }
                }, 0L, 10000L);
            }
        }.runTaskLater(getPluginInstance(), 10);  
    }

    @Override
    public void onDisable() {
        try {
            boss.removeBoss();
        } catch (Exception e) {}

        System.out.println("---Plugin Offline---");
    }

    public static Core getPluginInstance() {
        return pluginInstance;
    }

    public static Boss getBoss() {
        return boss;
    }
}
