package net.phcode.censormutespam.censormutespam.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class HelpC implements CommandExecutor {
    //명령어모음
    final private Plugin pl;
    public HelpC(Plugin p) {
        this.pl = p;
    }
    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp() || sender.hasPermission("cms.h")) {
                sender.sendMessage("""
                            - [ §c검열§f ] -
                        /검열 메시지추가 (메시지) : 검열될 메시지를 추가합니다
                        /검열 메시지제거 (메시지) : 검열될 메시지를 제거합니다
                        /검열 메시지조회 : 검열될 메시지를 조회합니다
                        /검열 경고설정 (플레이어) (경고) : 플레이어의 경고를 설정합니다
                        /검열 경고조회 (플레이어) : 플레이어의 경고를 조회합니다
                        /검열 최대경고 (최대경고) : 최대경고를 설정합니다
                            - [ §c음소거§f ] -
                        /음소거 (플레이어) : 플레이어를 음소거 합니다
                        /기간음소거 플레이어 (분/시간/날) (값) : 플레이어를 정해진 시간동안 음소거합니다
                            - [ §c도배방지§f ] -
                        /도배 (메시지 최대 길이/메시지 입력 딜레이) [플레이어] : 도배방지 관련 설정을 합니다
                        """);
            } else {
                sender.sendMessage("[§c명령어모음§f]OP나 펄미션이 있어야 사용 가능합니다!");
            }
        } else {
            pl.getLogger().info("""
                            - [ §c검열§f ] -
                        /검열 메시지추가 (메시지) : 검열될 메시지를 추가합니다
                        /검열 메시지제거 (메시지) : 검열될 메시지를 제거합니다
                        /검열 메시지조회 : 검열될 메시지를 조회합니다
                        /검열 경고설정 (플레이어) (경고) : 플레이어의 경고를 설정합니다
                        /검열 경고조회 (플레이어) : 플레이어의 경고를 조회합니다
                        /검열 최대경고 (최대경고) : 최대경고를 설정합니다
                            - [ §c음소거§f ] -
                        /음소거 (플레이어) : 플레이어를 음소거 합니다
                        /기간음소거 플레이어 (분/시간/날) (값) : 플레이어를 정해진 시간동안 음소거합니다
                            - [ §c도배방지§f ] -
                        /도배 (메시지 최대 길이/메시지 입력 딜레이) [플레이어] : 도배방지 관련 설정을 합니다
                        """);
        }
        return false;
    }
}
