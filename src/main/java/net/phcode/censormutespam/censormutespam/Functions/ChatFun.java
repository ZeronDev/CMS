package net.phcode.censormutespam.censormutespam.Functions;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static net.phcode.censormutespam.censormutespam.Data.DataC.*;


public class ChatFun extends BukkitRunnable {
    // 채팅 딜레이
    final private String playerName;
    private double count;

    public ChatFun(String pn) {
        this.playerName = pn;
        if (SpamPlayer.containsKey(Bukkit.getOfflinePlayer(playerName).getUniqueId().toString())) {
            this.count = (double) SpamPlayer.get(Bukkit.getOfflinePlayer(playerName).getUniqueId().toString()).get(1);
        } else {
            this.count = Mdelay;
        }
    }

    @Override
    public void run() {
        count -= 0.1;
        ChattedPlayers.put(playerName, Double.parseDouble(String.format("%.2f", (float) count)));
        if (count<=0) {
            ChattedPlayers.remove(playerName);
            cancel();
        }
    }
}
