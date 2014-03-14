package br.AtomGamers.Thor;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FinalizeGameSetup extends EventoManager {

    protected static void setCheckedDeathWinnerAction(Player sender) {
        if (getParticipantes().size() > 1) {
            for (Player i : getParticipantes()) {
                i.sendMessage(getMessage(sender, "Message18"));
            }
        } else if (getParticipantes().size() == 1) {
            Processo01();
        }
    }

    protected static void setCheckedQuitWinnerAction(Player sender) {
        if (getParticipantes().size() > 1) {
            for (Player i : getParticipantes()) {
                i.sendMessage(getMessage(sender, "Message20"));
            }
        } else if (getParticipantes().size() == 1) {
            Processo01();
        }
    }

    protected static void getWinnerMessages(String vencedor) {
        List<String> msg = getMensagens().getStringList("WinnerMessages");
        for (String i : msg) {
            i = i.replaceAll("%player%", vencedor);
            i = i.replaceAll("&", "§");
            Bukkit.broadcastMessage(i);
        }
    }

    protected static void Processo01() {
        for (Player i : getParticipantes()) {
            i.sendMessage(getMessage("Message19"));
            getWinnerMessages(i.getName());
            getEconomy().depositPlayer(i.getName(), getConfig().getInt("Premio"));
        }
        PlayerBackup.IO.clear();
        PlayerBackup.PCIF.clear();
        getParticipantes().clear();
        setOcorrendo(false);
        setAberto(false);
    }
}
