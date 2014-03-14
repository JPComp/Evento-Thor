package br.AtomGamers.Thor;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LightningsManager extends EventoManager {

    protected static void Loop() {
        if (isOcorrendo()) {
            int i = new Random().nextInt(getParticipantes().size());
            int lightnings = getConfig().getInt("RaiosPorVez");
            int Tempo = getConfig().getInt("TempoEntreNovosRaios");
            Player p = getParticipantes().get(i);
            setLightning(p, lightnings);
            Plugin plugin = Bukkit.getPluginManager().getPlugin("EventoThor");
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    Loop();
                }
            }, Tempo * 20L);
        }
    }

    protected static void setLightning(final Player sender, final int x) {
        sender.getWorld().strikeLightning(sender.getLocation());
        if (x > 0) {
            setLightning(sender, x - 1);
        }
    }
}
