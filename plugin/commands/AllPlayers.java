package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import main.Core;

public class AllPlayers implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	

        if (command.getName().equalsIgnoreCase("everyone")) {
        	String allPlayers = null;
            StringBuilder builder = new StringBuilder();
            for(Object name:JavaPlugin.getPlugin(Core.class).getConfig().getList("players")) {
                builder.append(name + " ");
                }
                allPlayers = builder.toString().trim();
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(allPlayers);

            }else {
                   System.out.println(allPlayers);  
            }
        }
        return true;
    }
}
