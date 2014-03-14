package br.AtomGamers.Thor;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EventoThor extends JavaPlugin {

    @Override
    public void onEnable() {
        getFiles();
        getListeners();
        getCommand("eventothor").setExecutor(new AGCommandSender());
        ConsoleCommandSender ssd = Bukkit.getConsoleSender();
        ssd.sendMessage(" ");
        ssd.sendMessage("§b[EventoThor] §fPlugin inicializado. (Autor=AtomGamers)");
        ssd.sendMessage("§b[EventoThor] §fMensagens/configuração prontas para uso!");
        ssd.sendMessage("§b[EventoThor] §fComandos e Listeners prontos para o evento!");
        ssd.sendMessage(" ");
    }

    @Override
    public void onDisable() {
        for (Player i : EventoManager.getParticipantes()) {
            i.teleport(ArenaLocation.getSaida());
            PlayerBackup.restoreOriginalInventory(i);
        }
        PlayerBackup.IO.clear();
        PlayerBackup.PCIF.clear();
        EventoManager.getParticipantes().clear();
        EventoManager.setOcorrendo(false);
        EventoManager.setAberto(false);
        saveConfig();
        ConsoleCommandSender ssd = Bukkit.getConsoleSender();
        ssd.sendMessage(" ");
        ssd.sendMessage("§b[EventoThor] §fPlugin finalizado. (Autor=AtomGamers)");
        ssd.sendMessage("§b[EventoThor] §fRestauracao de inventarios interminados completado.");
        ssd.sendMessage("§b[EventoThor] §fEfetuando Limpeza no sistema de Backup do EventoThor.");
        ssd.sendMessage(" ");
    }

    protected void getFiles() {
        File i = new File(getDataFolder(), "EventoThor.AGInfo");
        File message = new File(getDataFolder(), "messages.yml");
        File file = new File(getDataFolder(), "config.yml");
        if (!i.exists()) {
            try {
                i.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!message.exists()) {
            try {
                saveResource("messages_template.yml", false);
                new File(getDataFolder(), "messages_template.yml").renameTo(new File(getDataFolder(), "messages.yml"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (!file.exists()) {
            try {
                saveResource("config_template.yml", false);
                new File(getDataFolder(), "config_template.yml").renameTo(new File(getDataFolder(), "config.yml"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        reloadConfig();
    }

    protected void getListeners() {
        Bukkit.getPluginManager().registerEvents(new ArenaListener(), this);
    }

}
