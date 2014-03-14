package br.AtomGamers.Thor;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopGameSetup extends EventoManager {

    protected boolean getSubCommand(CommandSender sender, String[] args) {

        if (args.length >= 1) {
            setOcorrendo(false);
            setAberto(false);
            for (Player p : getParticipantes()) {
                p.teleport(ArenaLocation.getSaida());
                PlayerBackup.restoreOriginalInventory(p);
            }
            Mensagens();
        }

        return true;
    }

    public static void Mensagens() {
        List<String> msg = getMensagens().getStringList("WarningStopMessages");
        for (String i : msg) {
            i = i.replaceAll("&", "§");
            Bukkit.broadcastMessage(i);
        }
    }
}
