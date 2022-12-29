package net.phcode.censormutespam.censormutespam.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import static net.phcode.censormutespam.censormutespam.Data.DataC.*;

public class MuteC implements CommandExecutor {
    //음소거
    private final Plugin pl;
    public MuteC(Plugin p) {
        this.pl = p;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp() || sender.hasPermission("cms.m")) {
                if (args.length == 0) {
                    sender.sendMessage(prefix + "음소거 시킬 플레이어를 선택해주세요!");
                } else if (!PwarnV.containsKey(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString())) {
                    sender.sendMessage(prefix + "알 수 없는 플레이어 입니다!");
                } else if (MutedPlayer.contains(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString())) {
                    sender.sendMessage(prefix + "이미 음소거 되어 있습니다!");
                } else {
                    MutedPlayer.add(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString());
                    sender.sendMessage(prefix + "성공적으로 " + args[0] + "가 음소거 되었습니다!");
                    return true;
                }
            } else {
                sender.sendMessage(prefix + "OP나 펄미션이 있어야 사용 가능합니다!");
            }
        } else {
            if (args.length == 0) {
                pl.getLogger().info(prefix + "음소거 시킬 플레이어를 선택해주세요!");
            } else if (!PwarnV.containsKey(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString())) {
                pl.getLogger().info(prefix + "알 수 없는 플레이어 입니다!");
            } else if (MutedPlayer.contains(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString())) {
                pl.getLogger().info(prefix + "이미 음소거 되어 있습니다!");
            } else {
                MutedPlayer.add(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString());
                pl.getLogger().info(prefix + "성공적으로 " + args[0] + "가 음소거 되었습니다!");
                return true;
            }
        }
        return false;
    }
}
