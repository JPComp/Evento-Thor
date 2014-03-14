package br.AtomGamers.Thor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArenaListener extends EventoManager implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onDropItem(PlayerDropItemEvent e) {
        Player sender = (Player) e.getPlayer();
        if (getParticipantes().contains(sender)) {
            getMessage(sender, "Message1");
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBreak(BlockBreakEvent e) {
        Player sender = (Player) e.getPlayer();
        if (getParticipantes().contains(sender)) {
            getMessage(sender, "Message2");
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlace(BlockPlaceEvent e) {
        Player sender = (Player) e.getPlayer();
        if (getParticipantes().contains(sender)) {
            getMessage(sender, "Message3");
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player sender = (Player) e.getPlayer();
        if (getParticipantes().contains(sender)) {
            String cmd = e.getMessage().toLowerCase();
            if (cmd.startsWith("/warp")) {
                sender.sendMessage(getMessage(sender, "/warp", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/sethome")) {
                sender.sendMessage(getMessage(sender, "/sethome", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/home")) {
                sender.sendMessage(getMessage(sender, "/home", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/etp")) {
                sender.sendMessage(getMessage(sender, "/etp", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/tpa")) {
                sender.sendMessage(getMessage(sender, "/tpa", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/bau")) {
                sender.sendMessage(getMessage(sender, "/bau", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/kit")) {
                sender.sendMessage(getMessage(sender, "/kit", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/bring")) {
                sender.sendMessage(getMessage(sender, "/bring", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/call")) {
                sender.sendMessage(getMessage(sender, "/call", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/clear")) {
                sender.sendMessage(getMessage(sender, "/clear", "Message4"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/eventothor")) {
                sender.sendMessage(getMessage(sender, "/eventothor", "Message4"));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onAdministratorCommand(PlayerCommandPreprocessEvent e) {
        Player sender = (Player) e.getPlayer();
        if (isOpen()) {
            String cmd = e.getMessage().toLowerCase();
            if (cmd.startsWith("/stop")) {
                sender.sendMessage(getMessage(sender, "/stop", "Message5"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/reload")) {
                sender.sendMessage(getMessage(sender, "/reload", "Message5"));
                e.setCancelled(true);
            }
            if (cmd.startsWith("/kickall")) {
                sender.sendMessage(getMessage(sender, "/kickall", "Message5"));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerDeath(PlayerDeathEvent e) {
        Player sender = (Player) e.getEntity().getPlayer();
        if (getParticipantes().contains(sender)) {
            e.getDrops().clear();
            if (getConfig().getBoolean("RestoreOriginalInventoryOnDeath")) {
                PlayerBackup.restoreOriginalInventory(sender);
            }
            sender.teleport(ArenaLocation.getSaida());
            getParticipantes().remove(sender);
            sender.updateInventory();
            FinalizeGameSetup.setCheckedDeathWinnerAction(sender);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerQuit(PlayerQuitEvent e) {
        Player sender = (Player) e.getPlayer();
        if (getParticipantes().contains(sender)) {
            if (getConfig().getBoolean("ClearInventoryOnQuit")) {
                sender.getInventory().clear();
                sender.getInventory().setHelmet(null);
                sender.getInventory().setChestplate(null);
                sender.getInventory().setLeggings(null);
                sender.getInventory().setBoots(null);
            }
            if (getConfig().getBoolean("RestoreOriginalInventoryOnQuit")) {
                PlayerBackup.restoreOriginalInventory(sender);
            }
            sender.updateInventory();
            sender.teleport(ArenaLocation.getSaida());
            FinalizeGameSetup.setCheckedQuitWinnerAction(sender);
        }
    }
}
