package eu.mcone.qsg.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.gameapi.api.gamestate.common.EndGameState;
import eu.mcone.qsg.QSQ;
import eu.mcone.qsg.state.EndState;
import eu.mcone.qsg.state.LobbyState;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void on(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndGameState) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                    CoreSystem.getInstance().getWorldManager().getWorld(QSQ.getInstance().getGameConfig().parseConfig().getLobby()).teleport(player, "spawn");
                }

                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                    || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndGameState) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if ((QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState)
                && !player.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAchievementAward(PlayerAchievementAwardedEvent e) {
        Player player = e.getPlayer();
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if ((QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState)
                && !player.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onTrigger(InventoryClickEvent e) {
        e.setCancelled((QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState)
                && !e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.toWeatherState())
            e.setCancelled(true);
    }

    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent e) {
        e.setCancelled(QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState);
    }

    @EventHandler
    public void on(PlayerItemDamageEvent e) {
        e.setCancelled(QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState ||
                QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState);
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndState);
    }
}
