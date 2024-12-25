package me.gaminglounge.estats.listener;

import java.text.DecimalFormat;

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
//[temporary] This will be overriten by armor & skills/stats of the player, in annother class, but should give out a base value
        if ((EStats.INSTANCE.statsManager.getMaxMana(p) == null)){
            EStats.INSTANCE.statsManager.setMaxMana(p,(double) 100);
        }

        Bukkit.getScheduler().runTaskTimer(EStats.INSTANCE, () ->{
            double aM = EStats.INSTANCE.statsManager.getAktMana(p);
            double mM = EStats.INSTANCE.statsManager.getMaxMana(p);
            if(aM > mM){
                double cM = (aM-mM);
                DecimalFormat df = new DecimalFormat("#.00");
                df.format(cM);
                p.sendActionBar(mm.deserialize("<#04a5d1>||||| "+mM+"<gray>/</gray>"+mM+" |||||</#04a5d1> <white>+</white><#04a5d1>"+cM+"</#04a5d1>"));
            }
            else{
                //This doesn't work at all, it should go from white at 0% to blue(#04a5d1) & white at 50% and blue(#04a5d1) at 100%
            double g = (((aM/mM)/2));
            p.sendActionBar(mm.deserialize("<gradient:#04a5d1:gray:white:"+g+">||||| "+aM+"<gray>/</gray>"+mM+" |||||</gradient>"));
            }
        },0,10);
    }
}