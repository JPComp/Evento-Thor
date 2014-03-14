package br.AtomGamers.Thor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayGameSetup extends EventoManager {

    protected boolean getSubCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            if (isOcorrendo()) {
                sender.sendMessage(getMessage("Message6"));
            } else {
                sender.sendMessage(getMessage("Message7"));
            }
        } else {
            if (isOpen()) {
                if (getParticipantes().contains((Player) sender)) {
                    sender.sendMessage(getMessage("Message9"));
                } else {
                    PlayerBackup.addOriginalInventory((Player) sender);
                    getParticipantes().add((Player) sender);
                    ((Player) sender).teleport(ArenaLocation.getEntrada());
                    if (getConfig().getBoolean("Kit.Use")) {
                        PlayerBackup.setPlayerFakeInventory((Player) sender);
                    }
                    sender.sendMessage(getMessage("Message10"));
                }
            } else {
                sender.sendMessage(getMessage("Message8"));
            }
        }

        return true;
    }
}
