package net.phcode.censormutespam.censormutespam.Functions;
import org.bukkit.scheduler.BukkitRunnable;
import static net.phcode.censormutespam.censormutespam.Data.DataC.*;

public class MuteFunC extends BukkitRunnable {
    String PlayerUUID;
    public MuteFunC(String pn) {
        PlayerUUID = pn;
    }
    @Override
    public void run() {
        if (MutedPlayer.contains(PlayerUUID)) {
            MutedPlayer.remove(PlayerUUID);
        }
    }
}
