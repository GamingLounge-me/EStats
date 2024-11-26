package GamingLounge.me.eStats;


import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import net.kyori.adventure.text.Component;

public class EntityName {
    

        public static final NamespacedKey ignore = new NamespacedKey("estats", "ignore"); //creates the substructure for the persistantdatacontainer and "ignore" it self.


        //Method to give entitys a name -> latter on giving it the stats.
public void scaner(){

Bukkit.getScheduler().runTaskTimer(EStats.INSTANCE/*Why the f does this have to be called EStats.INSTANCE....... */, ()->{

for (World world : Bukkit.getWorlds()){ //creates the component world which is everry world
    for (Entity activeEntitys : world.getEntities()){//gets the entity's in that world and puts it in the component activeEntitys
        if (activeEntitys.getPersistentDataContainer().has(ignore)) continue;//checks if it is not supposed to be renamed, by checking for the ignore persistant data container.
        if (activeEntitys instanceof Item)continue/*skipes this if and goes back to the beginning?*/;
        for (Entity player : Bukkit.getOnlinePlayers()){
            if (player.getLocation().distanceSquared(activeEntitys.getLocation()) <= 10 * 10)/*Checks for entitys surounding the Entity player*/{
                activeEntitys.setCustomNameVisible(true);//Should activate the name
                activeEntitys.customName(Component.text("Test")/*Everrything within this can be a component instead of an componant that is the text within this. */);
/*                if (!(player.getLocation().distanceSquared(activeEntitys.getLocation()) <= 10 * 10)){//Checks if entity is not within this 10*10 range
                    activeEntitys.setCustomNameVisible(false);//Should deactivate the name
                } */
                if(player.getLocation().distanceSquared(activeEntitys.getLocation()) > 10 * 10){/*Checks if the entity is further out then this. */
                    activeEntitys.setCustomNameVisible(false);//Should deactivate the name.
                    activeEntitys.remove();//debuging
                }
            }
        }
    }
}}, 0, 20);

}

}
