package eu.mcone.qsg.enums;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.backpack.Level;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ChestLoot {

    DIAMOND_SWORD(Level.LEGENDARY, new ItemBuilder(Material.DIAMOND_SWORD, 1).displayName("§3Diamantschwert").create()),
    STONE_SWORD_1(Level.UNUSUAL, new ItemBuilder(Material.STONE_SWORD, 1).displayName("§3Steinschwert").create()),
    STONE_SWORD_2(Level.EPIC, new ItemBuilder(Material.STONE_SWORD, 1).displayName("§3Steinschwert").create()),
    WOOD_SWORD_2(Level.USUAL, new ItemBuilder(Material.WOOD_SWORD, 1).displayName("§3Holzschwert").create()),

    CHAINMAIL_HELMET(Level.USUAL, new ItemBuilder(Material.CHAINMAIL_HELMET, 1).displayName("§3Kettenhelm").create()),
    CHAINMAIL_HELMET_1(Level.UNUSUAL, new ItemBuilder(Material.CHAINMAIL_HELMET, 1).displayName("§3Kettenhelm").create()),
    CHAINMAIL_HELMET_2(Level.EPIC, new ItemBuilder(Material.CHAINMAIL_HELMET, 1).displayName("§3Kettenhelm").create()),
    LEATHER_HELMET(Level.USUAL, new ItemBuilder(Material.LEATHER_HELMET, 1).displayName("§3Lederhelm").create()),
    LEATHER_HELMET_1(Level.USUAL, new ItemBuilder(Material.LEATHER_HELMET, 1).displayName("§3Lederhelm").create()),
    LEATHER_HELMET_2(Level.UNUSUAL, new ItemBuilder(Material.LEATHER_HELMET, 1).displayName("§3Lederhelm").create()),

    DIAMOND_CHESTPLATE(Level.EPIC, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).displayName("§3Diamantbrutsplatte").create()),

    CHAINMAIL_CHESTPLATE(Level.UNUSUAL, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1).displayName("§3Kettenbrutsplatte").create()),
    LEATHER_CHESTPLATE(Level.USUAL, new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).displayName("§3Lederbrutsplatte").create()),
    GOLD_CHESTPLATE(Level.EPIC, new ItemBuilder(Material.GOLD_CHESTPLATE, 1).displayName("§3Goldbrutsplatte").create()),
    IRON_CHESTPLATE(Level.LEGENDARY, new ItemBuilder(Material.IRON_CHESTPLATE, 1).displayName("§3Goldbrutsplatte").create()),

    CHAINMAIL_LEGGINGS(Level.UNUSUAL, new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).displayName("§3Kettenhose").create()),
      GOLD_LEGGINGS(Level.EPIC, new ItemBuilder(Material.GOLD_LEGGINGS, 1).displayName("§3Goldhose").create()),
    LEATHER_LEGGINGS(Level.USUAL, new ItemBuilder(Material.LEATHER_LEGGINGS, 1).displayName("§3Lederhose").create()),

    CHAINMAIL_BOOTS(Level.USUAL, new ItemBuilder(Material.CHAINMAIL_BOOTS, 1).displayName("§3Kettenschuhe").create()),
    IRON_BOOTS(Level.UNUSUAL, new ItemBuilder(Material.IRON_BOOTS, 1).displayName("§3Eisenschuhe").create()),
    LEATHER_BOOTS(Level.USUAL, new ItemBuilder(Material.LEATHER_BOOTS, 1).displayName("§3Lederschuhe").create()),

    COOKED_BEEF(Level.USUAL, new ItemBuilder(Material.COOKED_BEEF, 16).displayName("§7Fleisch").create()),
    GOLDEN_APPLE(Level.UNUSUAL, new ItemBuilder(Material.GOLDEN_APPLE, 1).displayName("§6Zauberapfel").create()),
    CARROT(Level.USUAL, new ItemBuilder(Material.CARROT, 16).displayName("§6Karotte").create()),
    COOKIE(Level.UNUSUAL, new ItemBuilder(Material.COOKIE, 16).displayName("§6Kekse").create()),
    CAKE(Level.EPIC, new ItemBuilder(Material.CAKE, 3).displayName("§3Kuchen").create()),

    TNT(Level.UNUSUAL, new ItemBuilder(Material.TNT, 1).displayName("§cT§fN§cT").create()),
    TNT_1(Level.EPIC, new ItemBuilder(Material.TNT, 3).displayName("§cT§fN§cT").create()),
    FISHING_ROD(Level.USUAL, new ItemBuilder(Material.FISHING_ROD, 1).displayName("§3Angel").create()),
    ENDER_PEARL(Level.LEGENDARY, new ItemBuilder(Material.ENDER_PEARL, 1).displayName("§cEnderperle").create()),

    SPEED(Level.UNUSUAL, new ItemBuilder(Material.POTION, 1, 1).displayName("§3Schnelligkeit").create()),
    SPEED_1(Level.EPIC, new ItemBuilder(Material.POTION, 2, 1).displayName("§3Schnelligkeit").create()),
    SPEED_2(Level.LEGENDARY, new ItemBuilder(Material.POTION, 3, 1).displayName("§3Schnelligkeit").create()),
    STRENGTH(Level.EPIC, new ItemBuilder(Material.POTION, 1, 5).displayName("§3Stärke").create()),

    STONE_PICKAXE(Level.USUAL, new ItemBuilder(Material.STONE_PICKAXE, 1).displayName("§7Steinspitzhacke").create()),
    GOLD_PICKAXE(Level.EPIC, new ItemBuilder(Material.GOLD_PICKAXE, 1).displayName("§6Goldspitzhacke").create()),
    IRON_PICKAXE(Level.USUAL, new ItemBuilder(Material.IRON_PICKAXE, 1).displayName("§fEisenspitzhacke").create());


    private final ItemStack itemStack;
    private final Level level;

    ChestLoot(Level level, ItemStack itemStack) {
        this.level = level;
        this.itemStack = itemStack;
    }

    public static List<ChestLoot> getItems(Level level) {
        List<ChestLoot> items = new ArrayList<>();
        for (ChestLoot item : values()) {
            if (item.getLevel().equals(level)) {
                items.add(item);
            }
        }

        return items;
    }
    }
