package br.AtomGamers.Thor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerBackup {

    protected static HashMap<Player, PlayerInventory> IO = new HashMap<Player, PlayerInventory>();
    protected static List<Player> PCIF = new ArrayList<Player>();

    protected static FileConfiguration getConfig() {
        FileConfiguration config;
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        File file = new File(folder, "config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    protected static void setKit(PlayerInventory i) {
        File folder = Bukkit.getPluginManager().getPlugin("EventoThor").getDataFolder();
        List<ItemStack> items = new ArrayList<ItemStack>();
        File config = new File(folder, "config.yml");
        ItemStack h = i.getHelmet();
        ItemStack p = i.getChestplate();
        ItemStack l = i.getLeggings();
        ItemStack b = i.getBoots();
        if (h != null) {
            getConfig().set("Kit.Helmo", h);
        } else {
            getConfig().set("Kit.Helmo", "Nenhum");
        }
        if (p != null) {
            getConfig().set("Kit.Peito", p);
        } else {
            getConfig().set("Kit.Peito", "Peito");
        }
        if (l != null) {
            getConfig().set("Kit.Calca", l);
        } else {
            getConfig().set("Kit.Calca", "Calca");
        }
        if (b != null) {
            getConfig().set("Kit.Botas", b);
        } else {
            getConfig().set("Kit.Botas", "Botas");
        }
        for (ItemStack item : i.getContents()) {
            items.add(item);
        }
        getConfig().set("Kit.Items", items);
        try {
            getConfig().save(config);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static void addOriginalInventory(Player sender) {
        IO.put(sender, sender.getInventory());
    }

    protected static void restoreOriginalInventory(Player sender) {
        PlayerInventory i = sender.getInventory();
        PlayerInventory o = IO.get(sender);
        i.clear();
        i.setHelmet(null);
        i.setChestplate(null);
        i.setLeggings(null);
        i.setBoots(null);
        i.setContents(o.getContents());
        i.setArmorContents(o.getArmorContents());
        IO.remove(sender);
    }

    protected static void setPlayerFakeInventory(Player sender) {
        PlayerInventory i = sender.getInventory();
        ItemStack h = getConfig().getItemStack("Kit.Helmo");
        ItemStack p = getConfig().getItemStack("Kit.Peito");
        ItemStack l = getConfig().getItemStack("Kit.Calca");
        ItemStack b = getConfig().getItemStack("Kit.Botas");
        if (h != null) {
            i.setHelmet(h);
        } else {
            i.setHelmet(null);
        }
        if (p != null) {
            i.setChestplate(p);
        } else {
            i.setChestplate(null);
        }
        if (l != null) {
            i.setLeggings(l);
        } else {
            i.setLeggings(null);
        }
        if (b != null) {
            i.setBoots(b);
        } else {
            i.setBoots(null);
        }
        List<ItemStack> items = (List<ItemStack>) getConfig().get("Kit.Items");
        for (int U = 0; U < items.size(); U++) {
            if (items.get(U) != null) {
                sender.getInventory().setItem(U, items.get(U));
            }
        }
        if (!PCIF.contains(sender)) {
            PCIF.add(sender);
        }
        sender.updateInventory();
    }
}
