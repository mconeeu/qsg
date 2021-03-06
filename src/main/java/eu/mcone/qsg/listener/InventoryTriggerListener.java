package eu.mcone.qsg.listener;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.backpack.defaults.DefaultCategory;
import eu.mcone.gameapi.api.gamestate.common.EndGameState;
import eu.mcone.qsg.QSQ;
import eu.mcone.qsg.inventorys.ChestInventory;
import eu.mcone.qsg.state.LobbyState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryTriggerListener implements Listener {

    public static final ItemStack QUIT_ITEM = new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§4§lVerlassen §8» §7§overlasse die Spielrunde.").create();

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null) {
                if (QSQ.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                        || QSQ.getInstance().getGameStateManager().getRunning() instanceof EndGameState) {
                    if (itemStack.getType().equals(Material.IRON_DOOR)) {
                        player.performCommand("hub");
                    } else if (itemStack.hasItemMeta()) {
                        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")) {
                            e.setCancelled(true);
                            QSQ.getInstance().getBackpackManager().openBackpackInventory(DefaultCategory.GADGET.name(), player);
                        }
                        e.setCancelled(true);
                    }
                }
            } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                if (clicked == Material.CHEST) {
                    e.setCancelled(true);
                    new ChestInventory(player);
                }
            }
        }
    }
}
