package eu.mcone.qsg.listener;

import eu.mcone.gameapi.api.gamestate.common.EndGameState;
import eu.mcone.qsg.QSQ;
import eu.mcone.qsg.state.LobbyState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if ((QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndGameState)
                && !e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        } else {
            if (block.getType().equals(Material.TNT)) {
                block.setType(Material.AIR);
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                TNTPrimed tnt = player.getWorld().spawn(block.getLocation(), TNTPrimed.class);
                tnt.setFuseTicks(42);
            }
        }
    }
}
