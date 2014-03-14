package br.AtomGamers.Thor;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class StartGameSetup extends EventoManager {

    protected boolean getSubCommand(CommandSender sender, String[] args) {

        if (args.length >= 1) {
            if (ArenaLocation.getEntrada() == null) {
                sender.sendMessage(getMessage("Message11"));
            } else if (ArenaLocation.getSaida() == null) {
                sender.sendMessage(getMessage("Message12"));
            } else if (isOpen()) {
                sender.sendMessage(getMessage("Message13"));
            } else if (isOcorrendo()) {
                sender.sendMessage(getMessage("Message14"));
            } else {
                getParticipantes().clear();
                setAberto(true);
                Processo01(5);
            }
        }

        return true;
    }

    public static void Processo01(final int x) {
        List<String> msg = getMensagens().getStringList("WarningStartMessages");
        for (String i : msg) {
            i = i.replaceAll("&", "§");
            Bukkit.broadcastMessage(i);
        }
        Plugin plugin = Bukkit.getPluginManager().getPlugin("EventoThor");
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                if (x > 0) {
                    Processo01(x - 1);
                } else {
                    CheckPlayers();
                }
            }
        }, 15 * 20L);
    }

    protected static void CheckPlayers() {
        if (getParticipantes().size() > 1) {
            Processo02();
        } else {
            List<String> msgs = getMensagens().getStringList("DontHavePlayersToStart");
            for (Player i : getParticipantes()) {
                PlayerBackup.restoreOriginalInventory(i);
            }
            getParticipantes().clear();
            PlayerBackup.PCIF.clear();
            PlayerBackup.IO.clear();
            setOcorrendo(false);
            setAberto(false);
            for (String i : msgs) {
                i = i.replaceAll("&", "§");
                Bukkit.broadcastMessage(i);
            }
        }
    }

    public static void Processo02() {
        setOcorrendo(true);
        setAberto(false);
        List<String> msg = getMensagens().getStringList("Warning10SecondsToStart");
        for (String i : msg) {
            i = i.replaceAll("&", "§");
            Bukkit.broadcastMessage(i);
        }
        Plugin plugin = Bukkit.getPluginManager().getPlugin("EventoThor");
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                Processo03();
            }
        }, 10 * 20L);
    }

    public static void Processo03() {
        List<String> msg = getMensagens().getStringList("WarningThorStarted");
        for (String i : msg) {
            i = i.replaceAll("&", "§");
            Bukkit.broadcastMessage(i);
        }
        LightningsManager.Loop();
    }
}
