package br.AtomGamers.Thor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class EventoManager {

    private static final Economy econ = null;
    private static final List<Player> plrs = new ArrayList<Player>();
    private static boolean iop = false;
    private static boolean aberto = false;

    protected static FileConfiguration getConfig() {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "config.yml");
        FileConfiguration config;
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    protected static FileConfiguration getMensagens() {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "messages.yml");
        FileConfiguration config;
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    protected static String getMessage(String msg) {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "messages.yml");
        FileConfiguration config;
        config = YamlConfiguration.loadConfiguration(file);
        String msg2 = config.getString(msg);
        msg2 = msg2.replaceAll("&", "§");
        return msg2;
    }

    protected static String getMessage(Player sender, String msg) {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "messages.yml");
        FileConfiguration config;
        config = YamlConfiguration.loadConfiguration(file);
        String msg2 = config.getString(msg);
        msg2 = msg2.replaceAll("%player%", sender.getName());
        msg2 = msg2.replaceAll("&", "§");
        return msg2;
    }

    protected static String getMessage(Player sender, String result, String msg) {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "messages.yml");
        FileConfiguration config;
        config = YamlConfiguration.loadConfiguration(file);
        String msg2 = config.getString(msg);
        msg2 = msg2.replaceAll("%player%", sender.getName());
        msg2 = msg2.replaceAll("%result%", result);
        msg2 = msg2.replaceAll("&", "§");
        return msg2;
    }

    protected static List<Player> getParticipantes() {
        return plrs;
    }

    protected static void setOcorrendo(boolean i) {
        iop = i;
    }

    protected static boolean isOcorrendo() {
        return iop;
    }

    protected static void setAberto(boolean i) {
        aberto = i;
    }

    protected static boolean isOpen() {
        return aberto;
    }

    protected static Economy getEconomy() {
        return econ;
    }
}
