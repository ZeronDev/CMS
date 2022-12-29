package net.phcode.censormutespam.censormutespam.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static net.phcode.censormutespam.censormutespam.Data.DataC.*;

public class Chatcom implements CommandExecutor, TabExecutor {
    final private Plugin pl;
    public Chatcom(Plugin p) {
        this.pl = p;
    }

    public static List<String> Range(int start, int last) {
        List<String> meth = new ArrayList<>();
        for (int i = start; i<=last; i++) {
            meth.add(String.valueOf(i));
        }
        return meth;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 검열
        if (sender instanceof Player) {
            if (sender.isOp() || sender.hasPermission("cms.c")) {
                if (args.length == 0) {
                    sender.sendMessage(prefix + "메시지추가, 메시지제거, 메시지조회, 경고설정, 경고조회, 최대경고 중 하나를 선택해주세요!");
                } else if (args[0].equalsIgnoreCase("메시지추가")) {
                    if (args.length == 1) {
                        sender.sendMessage(prefix + "추가할 값을 입력해주세요!");
                    } else {
                        boolean incorrect = false;
                        for (String str : Arrays.copyOfRange(args, 1, args.length)) {
                            if (CensorshipM.contains(str)) {
                                incorrect = true;
                                sender.sendMessage(prefix + str + "은 이미 존재합니다!");
                            }
                        }
                        if (!incorrect) {
                            List<String> arg = new ArrayList<>(Arrays.asList(args));
                            arg.remove(0);
                            CensorshipM.addAll(arg);
                            sender.sendMessage(prefix + "성공적으로 추가 되었습니다!");
                            return true;
                        }
                    }
                } else if (args[0].equalsIgnoreCase("메시지제거")) {
                    if (args.length == 1) {
                        sender.sendMessage(prefix + "제거할 값을 입력해주세요!");
                    } else {
                        boolean incorrect = false;
                        for (String str : Arrays.copyOfRange(args, 1, args.length)) {
                            if (!CensorshipM.contains(str)) {
                                incorrect = true;
                                sender.sendMessage(prefix + str + "은 존재하지 않는 검열어입니다!");
                            }
                        }
                        if (!incorrect) {
                            List<String> arg = new ArrayList<>(Arrays.asList(args));
                            arg.remove(0);
                            CensorshipM.removeAll(arg);
                            sender.sendMessage(prefix + "성공적으로 제거 되었습니다!");
                            return true;
                        }
                    }
                } else if (args[0].equalsIgnoreCase("메시지조회")) {
                    sender.sendMessage("- [§c 검열어 §f] -\n- " + String.join("\n- ", CensorshipM));
                    return true;
                } else if (args[0].equalsIgnoreCase("경고설정")) {
                    if (args.length <= 2) {
                        sender.sendMessage(prefix + "설정할 플레이어, 설정할 값을 입력해주세요!");
                    } else {
                        if (PwarnV.containsKey(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())) {
                            if (!args[2].matches("-?\\d+")) {
                                sender.sendMessage(prefix + "0 ~ " + MaxWarn + "까지 수를 입력해주세요!");
                            } else {
                                if (Integer.parseInt(args[2]) > MaxWarn) {
                                    sender.sendMessage(prefix + "0 ~ " + MaxWarn + "까지 수를 입력해주세요!");
                                } else {
                                    PwarnV.put(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString(), Integer.parseInt(args[2]));
                                    sender.sendMessage(prefix + "성공적으로 " + args[1] + "의 경고가 설정 되었습니다!");
                                    return true;
                                }
                            }
                        } else {
                            sender.sendMessage(prefix + "알 수 없는 플레이어 입니다!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("경고조회")) {
                    if (args.length == 1) {
                        sender.sendMessage(prefix + "플레이어를 선택해주세요");
                    } else if (PwarnV.containsKey(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())) {
                        sender.sendMessage(prefix + args[1] + "의 경고는 " + PwarnV.get(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString()) + "입니다!");
                    } else {
                        sender.sendMessage(prefix + "알 수 없는 플레이어 입니다!");
                    }
                } else if (args[0].equalsIgnoreCase("최대경고")) {
                    if (args.length == 1) {
                        sender.sendMessage(prefix + "값을 입력해주세요!");
                    } else {
                        if (!args[1].matches("-?\\d+")) {
                            sender.sendMessage(prefix + "정수를 입력해주세요!");
                        } else {
                            if (Integer.parseInt(args[1]) < 0) {
                                sender.sendMessage(prefix + "0보다 큰 수를 입력해주세요!");
                            } else {
                                MaxWarn = Integer.parseInt(args[1]);
                                sender.sendMessage(prefix + "성공적으로 최대경고가 설정되었습니다!");
                                return true;
                            }
                        }
                    }
                } else {
                    sender.sendMessage(prefix + "메시지추가, 메시지제거, 메시지조회, 경고설정, 경고조회, 최대경고 중 하나를 선택해주세요!");
                }
            } else {
                sender.sendMessage(prefix + "OP나 펄미션이 있어야 사용 가능합니다!");
            }
        } else {
            if (args.length == 0) {
                pl.getLogger().info(prefix + "메시지추가, 메시지제거, 메시지조회, 경고설정, 경고조회, 최대경고 중 하나를 선택해주세요!");
            } else if (args[0].equalsIgnoreCase("메시지 추가")) {
                if (args.length == 1) {
                    pl.getLogger().info(prefix + "추가할 값을 입력해주세요!");
                } else {
                    boolean incorrect = false;
                    for (String str : Arrays.copyOfRange(args, 1, args.length)) {
                        if (CensorshipM.contains(str)) {
                            incorrect = true;
                            sender.sendMessage(prefix + str + "은 이미 존재합니다!");
                        }
                    }
                    if (!incorrect) {
                        List<String> arg = new ArrayList<>(Arrays.asList(args));
                        arg.remove(0);
                        CensorshipM.addAll(arg);
                        sender.sendMessage(prefix + "성공적으로 추가 되었습니다!");
                        return true;
                    }
                }
            } else if (args[0].equalsIgnoreCase("메시지제거")) {
                if (args.length == 1) {
                    pl.getLogger().info(prefix + "제거할 값을 입력해주세요!");
                } else {
                    boolean incorrect = false;
                    for (String str : Arrays.copyOfRange(args, 1, args.length)) {
                        if (!CensorshipM.contains(str)) {
                            incorrect = true;
                            pl.getLogger().info(prefix + str + "은 존재하지 않는 검열어입니다!");
                        }
                    }
                    if (!incorrect) {
                        List<String> arg = new ArrayList<>(Arrays.asList(args));
                        arg.remove(0);
                        CensorshipM.removeAll(arg);
                        pl.getLogger().info(prefix + "성공적으로 제거 되었습니다!");
                        return true;
                    }
                }
            } else if (args[0].equalsIgnoreCase("메시지조회")) {
                pl.getLogger().info("- [§c 검열어 §f] -\n- " + String.join("\n- ", CensorshipM));
                return true;
            } else if (args[0].equalsIgnoreCase("경고설정")) {
                if (args.length <= 2) {
                    pl.getLogger().info(prefix + "설정할 플레이어, 설정할 값을 입력해주세요!");
                } else {
                    if (PwarnV.containsKey(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())) {
                        if (!args[2].matches("-?\\d+")) {
                            pl.getLogger().info(prefix + "0보다 큰 수를 입력해주세요!");
                        } else {
                            if (Integer.parseInt(args[2]) > MaxWarn) {
                                pl.getLogger().info(prefix + "0 ~ " + MaxWarn + "까지 수를 입력해주세요!");
                            } else {
                                PwarnV.put(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString(), Integer.parseInt(args[2]));
                                pl.getLogger().info(prefix + "성공적으로 " + args[1] + "의 경고가 설정 되었습니다!");
                                return true;
                            }
                        }
                    } else {
                        pl.getLogger().info(prefix + "알 수 없는 플레이어 입니다!");
                    }
                }
            } else if (args[0].equalsIgnoreCase("경고조회")) {
                    if (args.length == 1) {
                        pl.getLogger().info(prefix + "플레이어를 선택해주세요");
                    } else if (PwarnV.containsKey(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())) {
                        pl.getLogger().info(prefix + args[1] + "의 경고는 " + PwarnV.get(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString()) + "입니다!");
                    } else {
                        pl.getLogger().info(prefix + "알 수 없는 플레이어 입니다!");
                    }
            }else if (args[0].equalsIgnoreCase("최대경고")) {
                if (args.length == 1) {
                    pl.getLogger().info(prefix + "값을 입력해주세요!");
                } else {
                    if (!args[1].matches("-?\\d+")) {
                        pl.getLogger().info(prefix + "정수를 입력해주세요!");
                    } else {
                        if (Integer.parseInt(args[1]) < 0) {
                            pl.getLogger().info(prefix + "0보다 큰 수를 입력해주세요!");
                        } else {
                            MaxWarn = Integer.parseInt(args[1]);
                            pl.getLogger().info(prefix + "성공적으로 최대경고가 설정되었습니다!");
                            return true;
                        }
                    }
                }
            } else {
                pl.getLogger().info(prefix + "메시지추가, 메시지제거, 메시지조회, 경고설정, 경고조회, 최대경고 중 하나를 선택해주세요!");
            }

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("메시지추가", "메시지제거", "메시지조회", "경고설정", "경고조회", "최대경고");//, "경고 증가", "경고 차감");
//        } else if (args[0].equalsIgnoreCase("경고 증가")
//                || args[0].equalsIgnoreCase("경고 차감")) {
//            List<String> list = new ArrayList<>();
//            PwarnV.keySet().forEach(p -> list.add(p));
//            return list;
        } else if (args[0].equalsIgnoreCase("메시지제거")) {
            return CensorshipM;
        } else if (args[0].equalsIgnoreCase("메시지추가") || args[0].equalsIgnoreCase("메시지조회") || args[0].equalsIgnoreCase("최대경고")) {
            return Arrays.asList(" ");
        } else if (args.length == 3 && args[0].equalsIgnoreCase("경고설정")) {
            return Range(0, MaxWarn);
        }
        return null;
    }
}
