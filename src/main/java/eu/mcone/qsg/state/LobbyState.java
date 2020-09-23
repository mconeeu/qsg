package eu.mcone.qsg.state;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.LobbyGameState;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.qsg.QSQ;
import eu.mcone.qsg.scoreboard.LobbyObjective;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class LobbyState extends LobbyGameState {

    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();

    static {
        setObjective(LobbyObjective.class);
        for (Map.Entry<String, CoreLocation> location : QSQ.getInstance().getGameWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("QSG-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
        QSQ.getInstance().sendConsoleMessage("§2Loaded " + SPAWN_LOCATIONS.size() + " Locations for QSG");
    }

    public Location getRandomSpawn() {
        List<Location> locations = new ArrayList<>();

        for (Map.Entry<Location, Long> location : SPAWN_LOCATIONS.entrySet()) {
            if (((System.currentTimeMillis() / 1000) - location.getValue()) > 3) {
                locations.add(location.getKey());
            }
        }

        Location location = locations.get(new Random().nextInt(locations.size() - 1));
        SPAWN_LOCATIONS.put(location, System.currentTimeMillis() / 1000);

        return location;
    }


    @Override
    public void onStop(GameStateStopEvent event) {
        for (GamePlayer gp : QSQ.getInstance().getOnlineGamePlayers()) {
            Player p = gp.bukkit();
            p.getInventory().clear();

            CoreSystem.getInstance().createTitle().title("§7§oSuche deine Items!").subTitle(gp.getTeam().getLabel()).stay(4).fadeIn(1).fadeOut(2).send(p);
            p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);

            p.teleport(getRandomSpawn());
        }
    }
}
