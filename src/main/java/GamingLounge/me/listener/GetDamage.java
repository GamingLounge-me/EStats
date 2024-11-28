package GamingLounge.me.listener;


import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import GamingLounge.me.eStats.EStats;


//Class to catch damage, add/check for custom sources and calculate the real damage beeing dealtl, also want to add textdisplays around the entity position with the damage that was taken.
public class GetDamage implements Listener {

    @EventHandler
    public void onDamagedbBy(EntityDamageByEntityEvent event){

        //damageTest(event.getDamage(), event.getEntity().getType(), event.getDamager().getName(),event.getDamageSource().getDamageType());
        //damageDisplay(event.getDamage(),event.getDamager());
        EStats.INSTANCE.entityName.add_(event.getEntity());
        event.getEntity().customName(null);
        event.getEntity().setCustomNameVisible(false);
        Bukkit.getScheduler().runTaskLater(EStats.INSTANCE, ()->EStats.INSTANCE.entityName.remove_(event.getEntity()), 60);
    }     
    
    public void damageDisplay(double damage, Entity damager) {

    }

/* 
Just a Test

    public void damageTest(Double damage, EntityType entity, String damager, DamageType damageType){

        System.out.println(entity+": Took "+damage+""+damageType+" damage from "+damager+".");
    
    }*/

}
