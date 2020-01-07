package com.arkc.hardcore;

import net.minecraft.server.v1_12_R1.Items;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        ItemStack dirt = new ItemStack(Material.DIRT);
        ItemStack flint = new ItemStack(Material.FLINT);

        Player p = (Player)commandSender;

        dirt.setAmount(10);
        flint.setAmount(1);
        Material hand = p.getInventory().getItemInMainHand().getType();



        int count = p.getInventory().getItemInMainHand().getAmount();
        String scan = dirtScan(hand, count);
        p.sendMessage(hand.toString() + "count : " + count);
        switch (scan) {
            case "!DIRT":
                p.sendMessage("§c손에 있는 아이템이 흙이 아닙니다!");
                return false;
            case "COUNT":
                p.sendMessage("§c흙이 " + (10-count) + "개 부족합니다!");
                return false;
            default:
                p.getInventory().removeItem(dirt);
                p.getInventory().addItem(flint);
        }

        return false;
    }

    public String dirtScan(Material Phand, int count) { //1을 반환하면 흙을 안들고 있는거 / 2를 반환하면 개수가 부족한거

        if(Phand!=Material.DIRT) return "!DIRT";

        if(10-count>0) return "COUNT";
        else return "GIVE";
    }
}

