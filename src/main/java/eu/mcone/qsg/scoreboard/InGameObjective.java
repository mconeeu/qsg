package eu.mcone.qsg.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.player.GamePlayerState;
import eu.mcone.qsg.QSQ;

public class InGameObjective extends eu.mcone.gameapi.api.scoreboard.InGameObjective {

    public InGameObjective() {
        super();
    }

    @Override
    protected void onInGameRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onRegister(corePlayer, entry);
        GamePlayer gamePlayer = QSQ.getInstance().getGamePlayer(corePlayer.getUuid());

        entry.setTitle("§7§l⚔ §b§l§nQSG");
        entry.setScore(6, "");
        entry.setScore(5, "§8» §7Kills:");
        entry.setScore(4, "   §f§l" + gamePlayer.getRoundKills());
        entry.setScore(3, "");
        entry.setScore(2, "§8» §7Lebene Spieler:");
        onReload(corePlayer, entry);
    }

    @Override
    protected void onInGameReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onReload(corePlayer, entry);
        GamePlayer gamePlayer = GameAPI.getInstance().getGamePlayer(corePlayer);
        entry.setScore(4, "   §f§l" + gamePlayer.getRoundKills());
        entry.setScore(1, "    §f§l" + QSQ.getInstance().getPlayerManager().getPlayers(GamePlayerState.PLAYING).size());
    }
}