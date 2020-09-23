package eu.mcone.qsg.commands;

import eu.mcone.coresystem.api.bukkit.command.CoreCommand;
import eu.mcone.qsg.QSQ;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QSQCMD extends CoreCommand {

    public QSQCMD() {
        super("qsg");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        Player p = (Player) commandSender;

        QSQ.getInstance().getMessenger().send(p, "§8§m---------- §r§b§lMCONE-Minewar §8§m----------");
        QSQ.getInstance().getMessenger().send(p, "§7Entwickelt von §fMarvio");
        QSQ.getInstance().getMessenger().send(p, "§r");
        QSQ.getInstance().getMessenger().send(p, "§7§oWir bemühen uns darum alle Systeme und Spielmodi so effizient wie möglich zu gestalten.");
        QSQ.getInstance().getMessenger().send(p, "§7§oDeshalb sind auch alle von uns verwendeten Plugins ausschließlich selbst entwickelt!");
        QSQ.getInstance().getMessenger().send(p, "§8§m---------- §r§b§lMCONE-Minewar §8§m----------");
        p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1);

        return false;
    }
}
