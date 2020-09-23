package eu.mcone.qsg.state;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.event.gamestate.GameStateStartEvent;
import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.player.GamePlayerState;
import eu.mcone.qsg.QSQ;
import eu.mcone.qsg.scoreboard.InGameObjective;
import org.bukkit.entity.Player;

public class InGameState extends eu.mcone.gameapi.api.gamestate.common.InGameState {

    public InGameState() {
        super(60 * 45);
    }

    @Override
    public void onStart(GameStateStartEvent event) {

        for (Player player : QSQ.getInstance().getPlayerManager().getPlayers(GamePlayerState.PLAYING)) {
            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player);
            corePlayer.getScoreboard().setNewObjective(new InGameObjective());
        }

    }

    @Override
    public void onStop(GameStateStopEvent event) {
    }

}
