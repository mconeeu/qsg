package eu.mcone.qsg.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.scoreboard.LobbyObjective;
import eu.mcone.qsg.QSQ;

public class EndObjective extends LobbyObjective {

    public EndObjective() {
        super();
    }

    @Override
    protected void onLobbyRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onRegister(corePlayer, entry);

        GamePlayer gamePlayer = QSQ.getInstance().getGamePlayer(corePlayer.getUuid());
        entry.setTitle("§7§l⚔ §b§l§nQSG");
        entry.setScore(3, "");
        entry.setScore(2, "§8» §7Kills:");
        onReload(corePlayer, entry);
    }

    @Override
    protected void onLobbyReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        GamePlayer gamePlayer = QSQ.getInstance().getGamePlayer(corePlayer.getUuid());
        entry.setScore(1, "    §f§l" + gamePlayer.getRoundKills());
    }
}
