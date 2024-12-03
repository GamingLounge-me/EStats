package me.gaminglounge.estats.listener;


import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.gaminglounge.estats.EStats;
import net.kyori.adventure.text.minimessage.MiniMessage;


//Class to catch damage, add/check for custom sources and calculate the real damage beeing dealtl, also want to add textdisplays around the entity position with the damage that was taken.
public class GetDamage implements Listener {

    MiniMessage miniMessage = MiniMessage.miniMessage();


    @EventHandler
    public void onDamagedBy(EntityDamageByEntityEvent event){

        EStats.INSTANCE.entityName.add_(event.getEntity());
        if(event.getDamager() instanceof Player
        || event.getDamager() instanceof Projectile
        & event.getEntity() instanceof LivingEntity){
            double randomOffset = ThreadLocalRandom.current().nextDouble(-0.4, 0.4);
            double randomLowPitch = ThreadLocalRandom.current().nextDouble(0.56, 0.57);
            double volume = 0.3;
            
            event.getEntity().setCustomNameVisible(true);
            Bukkit.getScheduler().runTaskLater(EStats.INSTANCE, ()->EStats.INSTANCE.entityName.remove_(event.getEntity()), 60);

            Location entityposition = event.getEntity().getLocation();
            TextDisplay counter = (TextDisplay) entityposition.getWorld().spawnEntity(entityposition.add(randomOffset,randomOffset+1,randomOffset), EntityType.TEXT_DISPLAY);
            counter.text(miniMessage.deserialize("<red>-"+String.format("%.1f", event.getDamage())+"</red>"));
            counter.setBillboard(Billboard.VERTICAL);
            counter.setPersistent(false);
            if(event.getDamager() instanceof Projectile proj)
                if(proj.getShooter() instanceof Player p)
                {
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP ,(float) volume , (float) randomLowPitch);
                }
                if(event.getDamager() instanceof Player p){
                    p.playSound(event.getDamager(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP ,(float) volume , (float) randomLowPitch);
                }

            Bukkit.getScheduler().runTaskLater(EStats.INSTANCE,()->{
            counter.remove();
            },40);

            LivingEntity living = (LivingEntity) event.getEntity();

            int HP = (int) (((living.getHealth() - event.getDamage())/living.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue())*10);

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


