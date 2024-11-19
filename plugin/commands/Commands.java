package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import plugin.events.basic.l;


public class Commands implements CommandExecutor, Listener{
	public boolean suicideController;

	@Override
	public boolean onCommand(CommandSender commandExecutor, Command commandName, String arg2, String[] arg3) {
		if(commandName.getName().equalsIgnoreCase("suicide")) {

			suicideController = true;

			if(commandExecutor instanceof Player) {
				l.suicideController = true;
				Player playerCommandExecutor = (Player) commandExecutor;
				playerCommandExecutor.setHealth(0);
				System.out.println(suicideController + "salam");

				Bukkit.broadcast(playerCommandExecutor.getName() + " ha scelto la strada più semplice","default");
				l.suicideController = false;

			}else {
				System.out.println("Questo comando non può essere eseguito qui.");
			}
		}
		return true;
	}

}
