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
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<#04a5d1>\u258A\u258A\u258A\u258A\u258A "+String.format("%.1f",aM+(mM-aM))+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</#04a5d1> <white>+</white><#04a5d1>"+String.format("%.1f",cM)+"</#04a5d1>"));
            }
            else{
            int sw = (int) ((aM/mM)*5);
            //If we wanna uses more than just a health number, make this all strings and do the same for health, to just add it using 10+2 cases total.
            switch (sw) {
                case 0:
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<#89def5>\u258A</#89def5><gradient:white:gray:gray>\u258A\u258A\u258A\u258A "+String.format("%.1f",aM)+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</gradient>"));
                break;
                case 1:
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<gradient:#04a5d1:white:white:white:gray>\u258A\u258A\u258A\u258A\u258A "+String.format("%.1f",aM)+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</gradient>"));
                break;
                case 2:
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<gradient:#04a5d1:#04a5d1:white:white:gray>\u258A\u258A\u258A\u258A\u258A "+String.format("%.1f",aM)+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</gradient>"));
                break;
                case 3:
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<gradient:#04a5d1:#04a5d1:#04a5d1:white:gray>\u258A\u258A\u258A\u258A\u258A "+String.format("%.1f",aM)+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</gradient>"));
                break;
                case 4:
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<gradient:#04a5d1:#04a5d1:#04a5d1:#04a5d1:white>\u258A\u258A\u258A\u258A\u258A "+String.format("%.1f",aM)+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</gradient>"));
                break;
                case 5:
                p.sendActionBar(mm.deserialize("<red>"+String.format("%.1f",aL)+"</red><gray> | </gray><red>"+String.format("%.1f",mL)+" \u2665</red>"+
                "    "+
                "<#04a5d1>\u258A\u258A\u258A\u258A\u258A "+String.format("%.1f",aM)+"<gray> | </gray>"+String.format("%.1f",mM)+" \u258A\u258A\u258A\u258A\u258A</#04a5d1>"));
                break;
                }
            }
        },0,10);
    }
}



/*
# Name/ID: "Saurons Stab"
# Itemtype: "Carrot on a stick"
# Resourcepack ID: "7"
# Stats:
#   Mana: +10
#   Health_in_percentage: -5
# Functions:
#   Keybild: shift+leftkey+leftkey+rightkey
#       fireball:
#           coldown: 4
#           cost:
#               mana: 20
#       projectile:
#               ./projectiles/generic_fireball_2.Yaml
#           trail:
#               ./trails/generic_breath_2.Yaml
#           damage:
#               fire: 20
# Header: "Saurons Stab"
# Description:
# Sauron kann gar keine Feuerbälle schießen, deswegen tun wir es.
 */