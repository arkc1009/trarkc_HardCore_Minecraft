package com.arkc.hardcore;

import com.mysql.fabric.xmlrpc.base.Array;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class MainEvent implements Listener {

    public static HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
    static List<Player>players = new ArrayList<>();

    @EventHandler
    public void PlayerAdd(PlayerJoinEvent e) {
        hashMap.put(e.getPlayer().getName(), false);

    }

    @EventHandler
    public void Target(EntityTargetEvent e) {

        Bukkit.broadcastMessage("§c" + e.getTarget());
    }


    @EventHandler
    public void spawnMob(EntitySpawnEvent e) {



        if(e.getEntity().getType()==EntityType.ZOMBIE) {
            int temp = new Random().nextInt(10);
            if(temp==1) {
                SpawnEilteZombie((LivingEntity) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), e.getEntityType()));
                e.setCancelled(true);
            }
        }
        else if(e.getEntity().getType()==EntityType.SKELETON) {
            Skeleton sk = (Skeleton)e.getEntity();
        }
    }

    @EventHandler
    public void entityDeath(EntityDeathEvent e) {
        if(e.getEntity().getCustomName()=="EliteZombie") {
           e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.POTATO, 15));
           e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.IRON_INGOT, 10));
        }
    }

    public void SpawnEilteZombie(LivingEntity ent) {
        ent.setCustomName("§cEliteZombie");
        double setHP = 80.0;
        ent.getEquipment().setItemInMainHand(new ItemStack((Material.IRON_SWORD)));
        ent.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(setHP);
        ent.setHealth(setHP);
        ent.getEquipment().setItemInMainHandDropChance(15.0f);
        ent.getEquipment().setChestplateDropChance(0);
    }


    @EventHandler
    public void Hitscan(EntityDamageByEntityEvent e) {
        if(e.getEntity().getType()==EntityType.COW) {
            Bukkit.broadcastMessage("COW ATTACK");
            Animals animals = (Animals)e.getEntity();
            Bukkit.broadcastMessage("" + animals);
            LivingEntity lent = (LivingEntity)e.getDamager();
            Bukkit.broadcastMessage("DAMAGER : " + lent);

            animals.setTarget(lent);
        }
        if(e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;

        Bukkit.broadcastMessage(""+ e.getDamager() + "\n" + e.getDamager().getType() + "\n" + e.getDamager().getType().getEntityClass());
        if(e.getDamager().getType() == EntityType.PLAYER) Bukkit.broadcastMessage("Player");



        double ArrowY = e.getDamager().getLocation().getY();
        double Y = e.getEntity().getLocation().getY();

        boolean Head;
        if(ArrowY-Y>1.5) Head=true;
        else Head=false;

        double dmg = e.getDamage();

        if(Head) {
            Bukkit.broadcastMessage("HEADSHOT!");
            dmg*=1.5;
            e.setDamage(dmg);
        }
        Bukkit.broadcastMessage(" Damage : " + dmg);

    }

    @EventHandler
    public void Damaged(EntityDamageEvent e) {
        Player p = (Player)e.getEntity();

        Bukkit.broadcastMessage("§c"+ p.getHealth());
    }





    @EventHandler
    public void BlockBreak(BlockBreakEvent e) {
        Player p = (Player)e.getPlayer();

        Material playerMt = p.getInventory().getItemInMainHand().getType();
        String playerSt = playerMt.toString();

        Material blockMt = e.getBlock().getType();


        String Stat = SetStat(blockMt);

        p.sendMessage("Breeak : " + blockMt); // 블럭 타입 확인
        p.sendMessage("Stat : " + Stat); // 스탯 함수로 전달하여 스탯상태 확인
        p.sendMessage("Block : "+ blockMt.isSolid()); //완전히 블럭인지 판단 하는 듯,?
        switch (Stat) {

            case "LOG":
                if (!playerSt.contains("AXE") && playerMt!=Material.FLINT) {
                    p.sendMessage("§c해당 아이템으로는 이 아이템을 부실 수 없습니다!");
                    e.setCancelled(true);
                }
                break;
            case "STONE":
                if (!playerSt.contains("PICKAXE")) {
                    p.sendMessage("§c해당 아이템으로는 이 아이템을 부실 수 없습니다!");
                    e.setCancelled(true);
                }
                break;
            case "SOLID":
                if(!playerSt.contains("SHEARS") && !playerSt.contains("HOE")) {
                    p.sendMessage("§c해당 아이템으로는 이 아이템을 부실 수 없습니다!");
                    e.setCancelled(true);
                }
                break;
        }

    }

    public String SetStat(Material m) {

        String bT = m.toString();

        if(!m.isSolid()) return "SOLID";

        if(bT.contains("ORE") || bT.contains("STONE") || bT.contains("BRICK") || bT.contains("BLOCK") || bT.contains("STAINED_C") || bT.contains("HARD_CLAY")) {
            Bukkit.broadcastMessage("vadasdad");
            if(bT=="NOTE_BLOCK" || bT=="HAY_BLOCK" || bT=="SNOW_BLOCK" || bT=="MELON_BLOCK") return "NONE";


            return "STONE";
        }
        else if(bT.contains("LOG") || bT.contains("LOG_2") || bT.contains("OAK") || bT.contains("WOOD")) {
            Bukkit.broadcastMessage("asdasdawd");
            return "LOG";
        }
        return "NONE";
    }

    @EventHandler
    public void RowEat(PlayerItemConsumeEvent e) {

        Player p = e.getPlayer();

        Bukkit.broadcastMessage(""+ e.getItem().getType());

        if(e.getItem().getType().toString().contains("RABBIT") || e.getItem().getType().toString().contains("RAW")) {
            RawEffect(p);
        }

    }

    public void RawEffect(Player p) {
        List<PotionEffect> pelist = new ArrayList<>();
        pelist.add(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 1));
        pelist.add(new PotionEffect(PotionEffectType.POISON, 600, 0));
        pelist.add(new PotionEffect(PotionEffectType.HUNGER, 2000, 0));

        p.sendMessage("§c날것의 음식을 먹어서 식중독 상태에 걸렸습니다!\n§4/Toe 를 이용하여 위 문제를 해결하세요!");

        hashMap.put(p.getName(), true);

        for(PotionEffect pe : pelist) {
            p.addPotionEffect(pe);
        }
    }




}
