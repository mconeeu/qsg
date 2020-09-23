package eu.mcone.qsg.state;

import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.EndGameState;
import eu.mcone.qsg.QSQ;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndState extends EndGameState {


    @Override
    public void onStop(GameStateStopEvent event) {
        super.onStop(event);
        for (Player player : Bukkit.getOnlinePlayers()) {
            QSQ.getInstance().getMessenger().send(player, "§7Der Server startet nun neu...");
        }

        Bukkit.getServer().shutdown();
    }
}
