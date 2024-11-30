package me.gaminglounge.estats.listener;


import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.gaminglounge.estats.EStats;
import net.kyori.adventure.text.minimessage.MiniMessage;


//Class to catch damage, add/check for custom sources and calculate the real damage beeing dealtl, also want to add textdisplays around the entity position with the damage that was taken.
public class GetDamage implements Listener {

    MiniMessage miniMessage = MiniMessage.miniMessage();


    @EventHandler
    public void onDamagedbBy(EntityDamageByEntityEvent event){

        //damageTest(event.getDamage(), event.getEntity().getType(), event.getDamager().getName(),event.getDamageSource().getDamageType());
        //damageDisplay(event.getDamage(),event.getDamager());
        EStats.INSTANCE.entityName.add_(event.getEntity());
        if(event.getDamager() instanceof Player & event.getEntity() instanceof LivingEntity){
            event.getEntity().setCustomNameVisible(true);
            Bukkit.getScheduler().runTaskLater(EStats.INSTANCE, ()->EStats.INSTANCE.entityName.remove_(event.getEntity()), 60);

            double randomOffset = ThreadLocalRandom.current().nextDouble(-0.4, 0.4);

            Location entityposition = event.getEntity().getLocation();
            TextDisplay counter = (TextDisplay) entityposition.getWorld().spawnEntity(entityposition.add(randomOffset,randomOffset+1,randomOffset), EntityType.TEXT_DISPLAY);
            counter.text(miniMessage.deserialize("<red>-"+String.format("%.1f", event.getDamage())+"</red>"));
            counter.setBillboard(Billboard.VERTICAL);
            counter.setPersistent(false);

            Bukkit.getScheduler().runTaskLater(EStats.INSTANCE,()->{
            counter.remove();
            },40);

            LivingEntity living = (LivingEntity) event.getEntity();

            int HP = (int) ((living.getHealth()/living.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue())*10);
            System.out.println (HP);

            switch (HP) {
                case 0:
                event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮         </red><gray>]</gray>"));
                break;
                case 1:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮         </red><gray>]</gray>"));
                break;
                case 2 :
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮        </red><gray>]</gray>"));
                break;
                case 3:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮       </red><gray>]</gray>"));
                break;
                case 4:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮      </red><gray>]</gray>"));
                break;
                case 5:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮▮     </red><gray>]</gray>"));
                break;
                case 6:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮▮▮    </red><gray>]</gray>"));
                break;
                case 7:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮▮▮▮   </red><gray>]</gray>"));
                break;
                case 8:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮▮▮▮▮  </red><gray>]</gray>"));
                break;
                case 9:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮▮▮▮▮▮ </red><gray>]</gray>"));
                break;
                case 10:
                    event.getEntity().customName(miniMessage.deserialize("<gray>[</gray><red>▮▮▮▮▮▮▮▮▮▮</red><gray>]</gray>"));
                break;
                    
                default:
                    break;
                                
            }

        }
    }
}
/*
Just a Test

    public void damageTest(Double damage, EntityType entity, String damager, DamageType damageType){

        System.out.println(entity+": Took "+damage+""+damageType+" damage from "+damager+".");

    }*/


