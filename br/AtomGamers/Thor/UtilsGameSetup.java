package br.AtomGamers.Thor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UtilsGameSetup extends EventoManager {

    protected boolean getSubCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {
            sender.sendMessage(getMessage("Message16"));
        } else if (args.length > 1) {
            if (args[1].equalsIgnoreCase("entrada")) {
                if (!(sender instanceof Player)) {
                    getMessage("Message17");
                } else {
                    ArenaLocation.setEntrada(((Player) sender).getLocation());
                }
            } else if (args[1].equalsIgnoreCase("saida")) {
                if (!(sender instanceof Player)) {
                    getMessage("Message17");
                } else {
                    ArenaLocation.setSaida(((Player) sender).getLocation());
                }
            } else if (args[1].equalsIgnoreCase("kit")) {
                if (!(sender instanceof Player)) {
                    getMessage("Message17");
                } else {
                    PlayerBackup.setKit(((Player) sender).getInventory());
                }
            } else {
                return new CommandCancelledSetup().getSubCommand(sender, args);
            }
        }

        return true;
    }
}
