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

        //[temporary] This will be overriten by armor & skills/stats of the player in annother class, but gives out a base value, that is not noticible by the player
        if ((EStats.INSTANCE.statsManager.getAktMana(p) == null)){
            EStats.INSTANCE.statsManager.setAktMana(p,(double) 0);
        }
        if ((EStats.INSTANCE.statsManager.getMaxMana(p) == null)){
            EStats.INSTANCE.statsManager.setMaxMana(p,(double) 100);
        }
        if ((EStats.INSTANCE.statsManager.getAktLeben(p) == null)){
            EStats.INSTANCE.statsManager.setAktLeben(p,(double) 10);
        }
        if ((EStats.INSTANCE.statsManager.getMaxLeben(p) == null)){
            EStats.INSTANCE.statsManager.setMaxLeben(p,(double) 100);
        }

        Bukkit.getScheduler().runTaskTimer(EStats.INSTANCE, () ->{
            double aM = EStats.INSTANCE.statsManager.getAktMana(p);
            double mM = EStats.INSTANCE.statsManager.getMaxMana(p);
            double aL = EStats.INSTANCE.statsManager.getAktLeben(p);
            double mL = EStats.INSTANCE.statsManager.getMaxLeben(p);

            if(aM > mM){
                double cM = (aM-mM);
                p.sendActionBar(mm.deserialize("<#04a5d1>||||| "+mM+"<gray>/</gray>"+mM+" |||||</#04a5d1> <white>+</white><#04a5d1>"+cM+"</#04a5d1>"));
            }
            else{
            int sw = (int) ((aM/mM)*5);
            //If we wanna uses more than just a health number, make this all strings and do the same for health, to just add it using 10+2 cases total.
            switch (sw) {
                case 0:
                p.sendActionBar(mm.deserialize("<bold><red>"+aL+"</red></bold><gray>/<bold></gray><red>"+mL+"</red>"+
                "                       "+
                "<white>||||| "+aM+"</bold><gray>/<bold></gray>"+mM+" |||||</white></bold>"));
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