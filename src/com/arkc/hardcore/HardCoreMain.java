package com.arkc.hardcore;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class HardCoreMain extends JavaPlugin{

    public static BossBar bar;
    public static List<Player> playerList = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info("HardCore On!");
        getServer().getPluginManager().registerEvents(new MainEvent(), this);
        getCommand("test").setExecutor(new MainCommand());
        getCommand("PHP").setExecutor(new PlayerHPCommand());
        getCommand("Toe").setExecutor(new ToeCommand());
        bar.setTitle("");
        bar.setColor(BarColor.WHITE);
        bar.setStyle(BarStyle.SOLID);
    }

    @Override
    public void onDisable() {
        getLogger().info("HardCore off.");
    }








}
