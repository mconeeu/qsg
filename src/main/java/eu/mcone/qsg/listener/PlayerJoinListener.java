package eu.mcone.qsg.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.event.player.GamePlayerLoadedEvent;
import eu.mcone.gameapi.api.player.PlayerManager;
import eu.mcone.gameapi.api.team.TeamManager;
import eu.mcone.qsg.QSQ;
import eu.mcone.qsg.scoreboard.LobbyObjective;
import eu.mcone.qsg.state.EndState;
import eu.mcone.qsg.state.InGameState;
import eu.mcone.qsg.state.LobbyState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void on(GamePlayerLoadedEvent e) {
        Player player = e.getBukkitPlayer();

        player.setGameMode(GameMode.SURVIVAL);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);

        player.getInventory().setItem(8, InventoryTriggerListener.QUIT_ITEM);

        if (QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState) {
            e.getCorePlayer().getScoreboard().setNewObjective(new LobbyObjective());

            for (CorePlayer all : CoreSystem.getInstance().getOnlineCorePlayers()) {
                if (all.getScoreboard() != null) {
                    all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                }
            }

            player.getInventory().setItem(6, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an").create());
            player.getInventory().setItem(7, TeamManager.TEAM);

            CoreSystem.getInstance().getWorldManager().getWorld(QSQ.getInstance().getGameConfig().parseConfig().getLobby()).teleport(player, "spawn");
        } else if (QSQ.getInstance().getGameStateManager().getRunning() instanceof InGameState) {
            player.getInventory().setArmorContents(null);
            player.getInventory().clear();

            player.getInventory().setItem(7, PlayerManager.SPECTATOR);
            player.getInventory().setItem(8, InventoryTriggerListener.QUIT_ITEM);
            CoreSystem.getInstance().getWorldManager().getWorld(QSQ.getInstance().getGameConfig().parseConfig().getLobby()).teleport(player, "game.spectator");
        } else if (QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState) {
            CoreSystem.getInstance().getWorldManager().getWorld(QSQ.getInstance().getGameConfig().parseConfig().getLobby()).teleport(player, "spawn");
            e.getCorePlayer().getScoreboard().setNewObjective(new LobbyObjective());

            player.getInventory().setItem(8, InventoryTriggerListener.QUIT_ITEM);
            player.getInventory().setItem(7, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an").create());

            for (CorePlayer all : CoreSystem.getInstance().getOnlineCorePlayers()) {
                all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        }
    }
}
