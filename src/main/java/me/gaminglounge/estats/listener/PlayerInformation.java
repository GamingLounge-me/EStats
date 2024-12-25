package me.gaminglounge.estats.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.gaminglounge.estats.EStats;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class PlayerInformation implements Listener {


    MiniMessage mm = MiniMessage.miniMessage();

    @EventHandler
    public void Hotbar(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if ((EStats.INSTANCE.statsManager.getAktMana(p) == null)){
            EStats.INSTANCE.statsManager.setAktMana(p,(double) 0);
        }

        Bukkit.getScheduler().runTaskTimer(EStats.INSTANCE, () ->{
            double aM = EStats.INSTANCE.statsManager.getAktMana(p);
            p.sendActionBar(mm.deserialize("<aqua>"+aM+"</aqua>"));
        },0,10);
    }
}
