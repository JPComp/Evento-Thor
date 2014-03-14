package br.AtomGamers.Thor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AGCommandSender extends EventoManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

        if (cmd.getName().equalsIgnoreCase("eventothor")) {
            if (args.length == 0) {
                return new PlayGameSetup().getSubCommand(sender, args);
            } else {
                if (args[0].equalsIgnoreCase("iniciar")) {
                    return new StartGameSetup().getSubCommand(sender, args);
                } else if (args[0].equalsIgnoreCase("cancelar")) {
                    return new StopGameSetup().getSubCommand(sender, args);
                } else if (args[0].equalsIgnoreCase("marcar")) {
                    return new UtilsGameSetup().getSubCommand(sender, args);
                } else {
                    return new CommandCancelledSetup().getSubCommand(sender, args);
                }
            }
        }

        return false;
    }

}
