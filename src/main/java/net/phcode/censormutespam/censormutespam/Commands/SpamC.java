package net.phcode.censormutespam.censormutespam.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import static net.phcode.censormutespam.censormutespam.Data.DataC.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpamC implements CommandExecutor, TabExecutor {
    //도배
    final private Plugin pl;
    public SpamC(Plugin p) {
        this.pl = p;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp() || sender.hasPermission("cms.s")) {
                if (args.length == 0) {
                    sender.sendMessage(prefix + "옵션을 선택해 주세요!");
                } else if (args.length == 1) {
                    sender.sendMessage(prefix + "값을 입력해주세요!");
                } else if (args.length == 3) {
                    if (PwarnV.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                        if (!args[1].matches("-?\\d+")) {
                            sender.sendMessage(prefix + "숫자를 입력해주세요!");
                        } else if (args[0].equalsIgnoreCase("메시지최대길이")) {
                            if (SpamPlayer.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                                List spam = SpamPlayer.get(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                                spam.set(0, Integer.parseInt(args[1]));
                                SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), spam);
                            } else {
                                SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), Arrays.asList(Integer.parseInt(args[1]), Mdelay));
                            }
                            sender.sendMessage(prefix + "값이 성공적으로 설정되었습니다!");
                        } else if (args[0].equalsIgnoreCase("메시지입력딜레이")) {
                            if (SpamPlayer.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                                List spam = SpamPlayer.get(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                                spam.set(1, Double.parseDouble(args[1]));
                                SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), spam);
                            } else {
                                SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), Arrays.asList(MaxMlength, Double.parseDouble(args[1])));
                            }
                            sender.sendMessage(prefix + "값이 성공적으로 설정되었습니다!");
                        }
                    } else {
                        sender.sendMessage(prefix + "알 수 없는 플레이어 입니다!");
                    }
                } else if (!args[1].matches("-?\\d+")) {
                    sender.sendMessage(prefix + "숫자를 입력해주세요!");
                } else if (args[0].equalsIgnoreCase("메시지최대길이")) {
                    MaxMlength = Integer.parseInt(args[1]);
                    sender.sendMessage(prefix + "값이 성공적으로 설정되었습니다!");
                } else if (args[0].equalsIgnoreCase("메시지입력딜레이")) {
                    Mdelay = Double.parseDouble(args[1]);
                    sender.sendMessage(prefix + "값이 성공적으로 설정되었습니다!");
                }
            } else {
                sender.sendMessage(prefix + "OP나 펄미션이 있어야 사용 가능합니다!");
            }
        } else {
            if (args.length == 0) {
                pl.getLogger().info(prefix + "옵션을 선택해 주세요!");
            } else if (args.length == 1) {
                pl.getLogger().info(prefix + "값을 입력해주세요!");
            } else if (args.length == 3) {
                if (PwarnV.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                    if (!args[1].matches("-?\\d+")) {
                        pl.getLogger().info(prefix + "숫자를 입력해주세요!");
                    } else if (args[0].equalsIgnoreCase("메시지최대길이")) {
                        if (SpamPlayer.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                            List spam = SpamPlayer.get(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                            spam.set(0, Integer.parseInt(args[1]));
                            SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), spam);
                        } else {
                            SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), Arrays.asList(Integer.parseInt(args[1]), Mdelay));
                        }
                        sender.sendMessage(prefix + "값이 성공적으로 설정되었습니다!");
                    } else if (args[0].equalsIgnoreCase("메시지입력딜레이")) {
                        if (SpamPlayer.containsKey(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString())) {
                            List spam = SpamPlayer.get(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                            spam.set(1, Double.parseDouble(args[1]));
                            SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), spam);
                        } else {
                            SpamPlayer.put(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString(), Arrays.asList(MaxMlength, Double.parseDouble(args[1])));
                        }
                        pl.getLogger().info(prefix + "값이 성공적으로 설정되었습니다!");
                    }
                } else {
                    pl.getLogger().info(prefix + "알 수 없는 플레이어 입니다!");
                }
            } else if (!args[1].matches("-?\\d+")) {
                pl.getLogger().info(prefix + "숫자를 입력해주세요!");
            } else if (args[0].equalsIgnoreCase("메시지최대길이")) {
                MaxMlength = Integer.parseInt(args[1]);
                pl.getLogger().info(prefix + "값이 성공적으로 설정되었습니다!");
            } else if (args[0].equalsIgnoreCase("메시지입력딜레이")) {
                Mdelay = Double.parseDouble(args[1]);
                pl.getLogger().info(prefix + "값이 성공적으로 설정되었습니다!");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("메시지최대길이", "메시지입력딜레이");
        } else if (args.length == 2) {
            return Arrays.asList(" ");
        }
        return null;
    }
}
