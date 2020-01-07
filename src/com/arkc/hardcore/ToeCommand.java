package com.arkc.hardcore;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        List<PotionEffectType> pelist = new ArrayList<>();
        pelist.add(PotionEffectType.CONFUSION);
        pelist.add(PotionEffectType.HUNGER);
        pelist.add(PotionEffectType.POISON);

        HashMap<String, Boolean> map = new MainEvent().hashMap;

        Player p = (Player)commandSender;

        if(commandSender instanceof Player) {

            if(!map.get(p.getName())) {
                p.sendMessage("§4식중독 상태가 아닙니다!");
                return false;
            }

            ItemStack buc = new ItemStack(Material.BUCKET);


            int amount = p.getInventory().getItemInMainHand().getAmount();

            if(p.getInventory().getItemInMainHand().getType()==Material.BUCKET) {
                p.getInventory().getItemInMainHand().setAmount(amount-1);

                for(PotionEffectType pe : pelist) {
                    p.removePotionEffect(pe);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
                map.put(p.getName(), false);

            } else {
                p.sendMessage("§4토를 받아낼 무언가가 필요합니다!");
            }
        }



        return false;
    }
}
