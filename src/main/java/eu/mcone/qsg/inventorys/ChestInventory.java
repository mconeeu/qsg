package eu.mcone.qsg.inventorys;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gameapi.api.backpack.Level;
import eu.mcone.qsg.enums.ChestLoot;
import org.bukkit.entity.Player;

import java.util.*;

public class ChestInventory extends CoreInventory {

    private static final int FIGHT_ITEM_COUNT = 5;
    private static final List<ChestLoot> FIGHT_ITEMS = new ArrayList<>();

    static {
        for (ChestLoot item : ChestLoot.values()) {

            for (int i = 0; i < (Level.values().length - item.getLevel().ordinal()); i++) {
                FIGHT_ITEMS.add(item);
            }
        }
    }

    private final Map<Integer, ChestLoot> setItems = new HashMap<>();
    private final Random slotRandom = new Random();
    private final Random fightRandom = new Random();

    public ChestInventory(Player player) {
        super("§fLoot-Kiste", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        for (int i = 0; i < FIGHT_ITEM_COUNT; i++) {
            int slot = getSlotRandom();
            ChestLoot item = getRandomFightItem();

            setItem(slot, item.getItemStack());
            setItems.put(slot, item);
        }

        openInventory();
    }

    private int getSlotRandom() {
        int slot = slotRandom.nextInt(InventorySlot.ROW_6);
        return setItems.containsKey(slot) ? getSlotRandom() : slot;
    }

    private ChestLoot getRandomFightItem() {
        ChestLoot item = FIGHT_ITEMS.get(fightRandom.nextInt(FIGHT_ITEMS.size()));
        return setItems.containsValue(item) ? getRandomFightItem() : item;
    }
}
