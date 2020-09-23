package eu.mcone.qsg;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.qsg.commands.QSQCMD;
import eu.mcone.qsg.listener.*;
import eu.mcone.qsg.state.EndState;
import eu.mcone.qsg.state.InGameState;
import eu.mcone.qsg.state.LobbyState;
import lombok.Getter;

public class QSQ extends GamePlugin {

    public QSQ() {
        super(Gamemode.UNDEFINED, "qsg.prefix",
                Option.BACKPACK_MANAGER_REGISTER_GADGET_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_OUTFIT_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_HAT_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_TRAIL_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_EXCLUSIVE_CATEGORY,
                Option.BACKPACK_MANAGER_USE_RANK_BOOTS
        );
    }

    @Getter
    private static QSQ instance;
    @Getter
    private CoreWorld gameWorld;

    @Override
    public void onGameEnable() {
        instance = this;
        gameWorld = CoreSystem.getInstance().getWorldManager().getWorld(getGameConfig().parseConfig().getGameWorld());

        sendConsoleMessage("§aInitializing new GameState Handler...");

        getGameStateManager()
                .addGameState(new LobbyState())
                .addGameState(new InGameState())
                .addGameState(new EndState())
                .startGame();

        getDamageLogger();

        getPlayerManager();
        getTeamManager();

        getBackpackManager();
        getBackpackManager().setItemSlot(0);
        getBackpackManager().setFallbackSlot(0);
        getBackpackManager().disableItem(DefaultItem.COINBOMB);

        sendConsoleMessage("§aRegistering Commands and Listeners...");
        registerEvents(
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new PlayerDeathListener(),
                new PlayerPlaceListener(),
                new GeneralPlayerListener(),
                new InventoryTriggerListener(),
                new GameEndListener()
        );

        registerCommands(
                new QSQCMD()
        );


        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");

    }

    @Override
    public void onGameDisable() {

        sendConsoleMessage("§cPlugin disabled!");

    }
}
