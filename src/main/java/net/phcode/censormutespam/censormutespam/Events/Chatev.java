package net.phcode.censormutespam.censormutespam.Events;;

import net.phcode.censormutespam.censormutespam.Functions.ChatFun;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.phcode.censormutespam.censormutespam.Data.DataC.*;
import static net.phcode.censormutespam.censormutespam.Functions.DetectFun.detect;

public class Chatev implements Listener {
    //이벤트
    final private Plugin pl;
    String[] chs = {
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ",
            "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
            "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ",
            "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    };
    public Chatev(Plugin p) { this.pl = p; }

    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
    @EventHandler
    @SuppressWarnings("deprecation")
    public void Chatevent(AsyncPlayerChatEvent e) {
        if (!(Integer.parseInt(PwarnV.get(e.getPlayer().getUniqueId().toString()).toString()) == MaxWarn)) {
            if (!MutedPlayer.contains(e.getPlayer().getUniqueId().toString())) {
                if (!ChattedPlayers.containsKey(e.getPlayer().getName())) {
                    int max = 0;
                    if (SpamPlayer.containsKey(e.getPlayer().getUniqueId().toString())) {
                        max = (int) SpamPlayer.get(e.getPlayer().getUniqueId().toString()).get(0);
                    } else {
                        max = MaxMlength;
                    }
                    if (!(e.getMessage().length() > max)) {
                        ChatFun sch = new ChatFun(e.getPlayer().getName());
                        sch.runTaskTimerAsynchronously(pl, 0L, 2L);
                        boolean isCen = false;
                        List<Boolean>  cened = new ArrayList<>();
                        for (String s : CensorshipM) {
                            boolean[] isHan = new boolean[s.length()];
                            char[] messages = new char[s.length()];
                            for (int i = 0; i<s.length(); i++) {
                                messages[i]=s.charAt(i);
                                isHan[i] = (s.charAt(i) >= 0xAC00) ? true : false;
                            }
                            boolean test = false;
                            for (boolean han : isHan) {
                                if (han) {
                                    test = true;
                                    break;
                                }
                            }

                            if (containsIgnoreCase(e.getMessage(), s)) {
                                cened.add(true);
                                if (!isCen) {
                                    PwarnV.put(e.getPlayer().getUniqueId().toString(), Integer.parseInt(PwarnV.get(e.getPlayer().getUniqueId().toString()).toString())+1);
                                    isCen = true;

                                }

                                if (test) {
                                    String nm = "";

                                    for (int i = 0; i<s.length(); i++) {
                                        if (isHan[i]) {
                                            if (containsIgnoreCase(s, String.valueOf(messages[i]))) {
                                                int next = 0;
                                                while (true) {
                                                    if (next == s.length()) {
                                                        break;
                                                    }
                                                    if (!detect(Arrays.copyOfRange(messages, i, i+s.length()), s, 0)) {
                                                        break;
                                                    } else {
                                                        next += 1;
                                                    }
                                                }
                                                if (next == s.length()) {
                                                    nm += s;
                                                }
                                            } else {
                                                char cho = (char) ((messages[i] - 0xAC00) / 28 / 21);
                                                nm += chs[cho];
                                            }
                                        } else {
                                            nm += messages[i];
                                        }
                                    }


                                    if (containsIgnoreCase(e.getMessage(), nm)) {

                                        StringBuilder star = new StringBuilder("");
                                        for (int i = 1; i<=s.length(); i++) {
                                            star.append("*");
                                        }
                                        e.setMessage(e.getMessage().replace(nm, star));
                                        e.getPlayer().sendMessage(prefix + "경고가 추가 되었습니다 (현재 : " + PwarnV.get(e.getPlayer().getUniqueId().toString()).toString()+ " )");
                                        pl.getLogger().info(prefix + e.getPlayer().getName() + "이 경고를 받았습니다");
                                    }
                                } else {
                                    StringBuilder star = new StringBuilder("");
                                    for (int i = 1; i<=s.length(); i++) {
                                        star.append("*");
                                    }
                                    e.setMessage(e.getMessage().replace(s, star));
                                    e.getPlayer().sendMessage(prefix + "경고가 추가 되었습니다 (현재 : " + PwarnV.get(e.getPlayer().getUniqueId().toString()).toString()+ " )");
                                    pl.getLogger().info(prefix + e.getPlayer().getName() + "이 경고를 받았습니다");
                                }
                            } else if (test) {
                                String nm = "";

                                for (int i = 0; i<s.length(); i++) {
                                    if (isHan[i]) {
                                        char cho = (char)((messages[i]-0xAC00)/28/21);
                                        nm += chs[cho];
                                    } else {
                                        nm += messages[i];
                                    }
                                }

                                if (containsIgnoreCase(e.getMessage(), nm)) {
                                    if (!isCen) {
                                        PwarnV.put(e.getPlayer().getUniqueId().toString(), Integer.parseInt(PwarnV.get(e.getPlayer().getUniqueId().toString()).toString())+1);
                                        isCen = true;
                                    }

                                    StringBuilder star = new StringBuilder("");
                                    for (int i = 1; i<=s.length(); i++) {
                                        star.append("*");
                                    }
                                    e.setMessage(e.getMessage().replace(nm, star));
                                    e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2f, 10f);
                                    e.getPlayer().sendMessage(prefix + "경고가 추가 되었습니다 (현재 : " + PwarnV.get(e.getPlayer().getUniqueId().toString()).toString()+ " )");
                                    pl.getLogger().info(prefix + e.getPlayer().getName() + "이 경고를 받았습니다");
                                }
                            }
                        }
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(prefix + "메시지 길이는 " + MaxMlength + "을 넘을 수 없습니다!");
                    }
                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(prefix + "추가 메시지를 보내려면 " + ChattedPlayers.get(e.getPlayer().getName()) + "초를 기다려야 합니다!");
                }
            } else {
                e.setCancelled(true);
                e.getPlayer().sendMessage(prefix + "관리자가 음소거를 해 채팅을 칠 수 없습니다!");
            }
        } else {
            e.setCancelled(true);
            e.getPlayer().sendMessage(prefix + "경고가 최대치에 달해 채팅을 칠 수 없습니다!");
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPlayedBefore()) PwarnV.put(e.getPlayer().getUniqueId().toString(), 0);
    }
}
