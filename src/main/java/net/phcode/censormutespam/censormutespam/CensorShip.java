package net.phcode.censormutespam.censormutespam;

import net.phcode.censormutespam.censormutespam.Commands.*;
import net.phcode.censormutespam.censormutespam.Events.Chatev;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static net.phcode.censormutespam.censormutespam.Data.DataC.*;

public final class CensorShip extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //메인
        Bukkit.getServer().getPluginManager().registerEvents(new Chatev(this), this);
        getCommand("검열").setExecutor(new Chatcom(this));
        getCommand("검열").setTabCompleter(new Chatcom(this));
        getCommand("음소거").setExecutor(new MuteC(this));
        getCommand("음소거해제").setExecutor(new UnmuteC(this));
        getCommand("도배").setExecutor(new SpamC(this));
        getCommand("도배").setTabCompleter(new SpamC(this));
        getCommand("명령어모음").setExecutor(new HelpC(this));
        getCommand("기간음소거").setExecutor(new MuteTimeC(this));
        getCommand("기간음소거").setTabCompleter(new MuteTimeC(this));
        Bukkit.getLogger().info("§a검열 플러그인 활성화중...");
        Bukkit.getLogger().info("§a개발 : Phoenixcoder#5309");
        if (!new File(getDataFolder(), "config.yml").exists()) {
            this.getConfig().createSection("경고");
            this.getConfig().createSection("플레이어 도배 방지");
            getLogger().info("§3Config 설정 완료!");
        }
        this.getConfig().addDefault("검열메시지", CensorshipM);
        this.getConfig().addDefault("최대경고", MaxWarn);
        this.getConfig().addDefault("음소거된 플레이어", MutedPlayer);
        this.getConfig().addDefault("메시지 최대 길이", MaxMlength);
        this.getConfig().addDefault("메시지 딜레이", Mdelay);
        this.getConfig().addDefault("접두사", prefix);
        this.getConfig().options().copyDefaults(true);
        saveConfig();
        PwarnV.putAll(this.getConfig().getConfigurationSection("경고").getValues(true));
        this.getConfig().getConfigurationSection("플레이어 도배 방지").getKeys(true).forEach(key -> SpamPlayer.put(key, this.getConfig().getConfigurationSection("플레이어 도배 방지").getList(key)));
        CensorshipM.addAll(this.getConfig().getStringList("검열메시지"));
        MutedPlayer.addAll(this.getConfig().getStringList("음소거된 플레이어"));
        MaxWarn = this.getConfig().getInt("최대경고");
        MaxMlength = this.getConfig().getInt("메시지 최대 길이");
        Mdelay = this.getConfig().getDouble("메시지 딜레이");
        prefix = this.getConfig().getString("접두사");
        getLogger().info("§3Config 로딩 완료!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("§c검열 플러그인 비활성화중...");
        PwarnV.keySet().forEach(uuid -> this.getConfig().set("경고."+uuid, PwarnV.get(uuid)));
        SpamPlayer.keySet().forEach(uuid -> this.getConfig().set("플레이어 도배 방지."+uuid, SpamPlayer.get(uuid)));
        this.getConfig().set("검열메시지", CensorshipM);
        this.getConfig().set("최대경고", MaxWarn);
        this.getConfig().set("음소거된 플레이어", MutedPlayer);
        this.getConfig().set("메시지 최대 길이", MaxMlength);
        this.getConfig().set("메시지 딜레이", Mdelay);
        this.getConfig().set("접두사", prefix);
        saveConfig();
        getLogger().info("§3Config 저장 완료!");
    }
}
