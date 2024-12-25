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
                p.sendActionBar(mm.deserialize("<#04a5d1>||||| "+mM+"<gray>/</gray>"+mM+" |||||</#04a5d1> <white>+</white><#04a5d1>"+cM+"</#04a5d1>"));
            }
            else{
            int sw = (int) ((aM/mM)*5);
            switch (sw) {
                case 0:
                p.sendActionBar(mm.deserialize("<white>||||| "+aM+"<gray>/</gray>"+mM+" |||||</white>"));
                break;
                case 1:
                p.sendActionBar(mm.deserialize("<gradient:#04a5d1:white:white:white:white>||||| "+aM+"<gray>/</gray>"+mM+" |||||</gradient>"));
                break;
                case 2:
                p.sendActionBar(mm.deserialize("<gradient:#04a5d1:#04a5d1:white:white:white>||||| "+aM+"<gray>/</gray>"+mM+" |||||</gradient>"));
                break;
                case 3:
                p.sendActionBar(mm.deserialize("<gradient:#04a5d1:#04a5d1:#04a5d1:white:white>||||| "+aM+"<gray>/</gray>"+mM+" |||||</gradient>"));
                break;
                case 4:
                p.sendActionBar(mm.deserialize("<gradient:#04a5d1:#04a5d1:#04a5d1:#04a5d1:white>||||| "+aM+"<gray>/</gray>"+mM+" |||||</gradient>"));
                break;
                case 5:
                p.sendActionBar(mm.deserialize("<#04a5d1>||||| "+aM+"<gray>/</gray>"+mM+" |||||</#04a5d1>"));
                break;
                }
            }
        },0,10);
    }
}