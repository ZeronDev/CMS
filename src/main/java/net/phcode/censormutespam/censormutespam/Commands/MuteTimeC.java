package net.phcode.censormutespam.censormutespam.Commands;

import net.phcode.censormutespam.censormutespam.Functions.MuteFunC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;



import java.util.Arrays;
import java.util.List;

import static net.phcode.censormutespam.censormutespam.Data.DataC.*;

public class MuteTimeC implements CommandExecutor, TabExecutor {
    //음소거기간
    private final Plugin pl;
    public MuteTimeC(Plugin p) {
        this.pl = p;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp() || sender.hasPermission("cms.m")) {
                if (args.length == 0) {
                    sender.sendMessage(prefix + "음소거 시킬 플레이어를 선택해주세요!");
                } else if (args.length <= 2) {
                    sender.sendMessage(prefix + "설정할 단위, 값, 플레이어를 설정해주세요!");
                } else if (!PwarnV.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                    sender.sendMessage(prefix + "알 수 없는 플레이어 입니다!");
                } else if (MutedPlayer.contains(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                    sender.sendMessage(prefix + "이미 음소거 되어 있습니다!");
                } else if (!args[1].matches("-?\\d+")) {
                    sender.sendMessage(prefix + "정수를 입력해주세요!");
                } else {
                    if (args[0].equalsIgnoreCase("분")) {
                        MutedPlayer.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                        sender.sendMessage(prefix + "성공적으로 " + args[2] + "가 음소거 되었습니다!");
                        MuteFunC mfc = new MuteFunC(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                        mfc.runTaskLater(pl, (long) 1200*Integer.parseInt(args[1]));
                    } else if (args[0].equalsIgnoreCase("시간")) {
                        MutedPlayer.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                        sender.sendMessage(prefix + "성공적으로 " + args[2] + "가 음소거 되었습니다!");
                        MuteFunC mfc = new MuteFunC(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                        mfc.runTaskLater(pl, (long) 72000*Integer.parseInt(args[1]));
                    } else if (args[0].equalsIgnoreCase("날")) {
                        MutedPlayer.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                        sender.sendMessage(prefix + "성공적으로 " + args[2] + "가 음소거 되었습니다!");
                        MuteFunC mfc = new MuteFunC(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                        mfc.runTaskLater(pl, (long) 172800*Integer.parseInt(args[1]));
                    }

                }
            } else {
                sender.sendMessage(prefix + "OP나 펄미션이 있어야 사용 가능합니다!");
            }
        } else {
            if (args.length == 0) {
                pl.getLogger().info(prefix + "음소거 시킬 플레이어를 선택해주세요!");
            } else if (args.length <= 2) {
                pl.getLogger().info(prefix + "설정할 단위, 값, 플레이어를 설정해주세요!");
            } else if (!PwarnV.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                pl.getLogger().info(prefix + "알 수 없는 플레이어 입니다!");
            } else if (MutedPlayer.contains(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                pl.getLogger().info(prefix + "이미 음소거 되어 있습니다!");
            }  else if (!args[2].matches("-?\\d+")) {
                pl.getLogger().info(prefix + "정수를 입력해주세요!");
            } else {
                if (args[0].equalsIgnoreCase("분")) {
                    MutedPlayer.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                    pl.getLogger().info(prefix + "성공적으로 " + args[2] + "가 음소거 되었습니다!");
                    MuteFunC mfc = new MuteFunC(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                    mfc.runTaskLater(pl, (long) 1200*Integer.parseInt(args[1]));
                } else if (args[0].equalsIgnoreCase("시간")) {
                    MutedPlayer.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                    pl.getLogger().info(prefix + "성공적으로 " + args[2] + "가 음소거 되었습니다!");
                    MuteFunC mfc = new MuteFunC(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                    mfc.runTaskLater(pl, (long) 72000*Integer.parseInt(args[1]));
                } else if (args[0].equalsIgnoreCase("날")) {
                    MutedPlayer.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                    pl.getLogger().info(prefix + "성공적으로 " + args[2] + "가 음소거 되었습니다!");
                    MuteFunC mfc = new MuteFunC(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                    mfc.runTaskLater(pl, (long) 172800*Integer.parseInt(args[1]));
                }

            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("분", "시간", "날");
        } else if (args.length == 2) {
            return Arrays.asList(" ");
        }
        return null;
    }
}
