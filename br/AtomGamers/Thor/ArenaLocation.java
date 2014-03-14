package br.AtomGamers.Thor;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ArenaLocation {

    protected static FileConfiguration getConfig() {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "config.yml");
        FileConfiguration config;
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    protected static void setEntrada(Location i) {
        int x = i.getBlockX();
        int y = i.getBlockY();
        int z = i.getBlockZ();
        float yaw = i.getYaw();
        float pitch = i.getPitch();
        String w = i.getWorld().getName();
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "config.yml");
        getConfig().set("Entrada", w + "/" + x + "/" + y + "/" + z + "/" + yaw + "/" + pitch);
        try {
            getConfig().save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static void setSaida(Location i) {
        int x = i.getBlockX();
        int y = i.getBlockY();
        int z = i.getBlockZ();
        float yaw = i.getYaw();
        float pitch = i.getPitch();
        String w = i.getWorld().getName();
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "config.yml");
        getConfig().set("Saida", w + "/" + x + "/" + y + "/" + z + "/" + yaw + "/" + pitch);
        try {
            getConfig().save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static Location getEntrada() {
        String[] location = getConfig().getString("Entrada").split("/");
        World w = Bukkit.getWorld(location[0]);
        int x = Integer.parseInt(location[1]);
        int y = Integer.parseInt(location[2]);
        int z = Integer.parseInt(location[3]);
        float yaw = Float.parseFloat(location[4]);
        float pitch = Float.parseFloat(location[5]);
        return new Location(w, x, y, z, yaw, pitch);
    }

    protected static Location getSaida() {
        String[] location = getConfig().getString("Saida").split("/");
        World w = Bukkit.getWorld(location[0]);
        int x = Integer.parseInt(location[1]);
        int y = Integer.parseInt(location[2]);
        int z = Integer.parseInt(location[3]);
        float yaw = Float.parseFloat(location[4]);
        float pitch = Float.parseFloat(location[5]);
        return new Location(w, x, y, z, yaw, pitch);
    }
}
