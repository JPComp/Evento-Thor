package br.AtomGamers.Thor;

import org.bukkit.command.CommandSender;

public class CommandCancelledSetup extends EventoManager {

    protected boolean getSubCommand(CommandSender sender, String[] args) {

        sender.sendMessage(getMessage("Message15"));
        return true;
    }
}
