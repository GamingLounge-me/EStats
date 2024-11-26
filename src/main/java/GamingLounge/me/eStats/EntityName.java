package GamingLounge.me.eStats;


import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;

import net.kyori.adventure.text.Component;

public class EntityName {
    

        public static final NamespacedKey ignore = new NamespacedKey("estats", "ignore"); //creates the substructure for the persistantdatacontainer and "ignore" it self.


        //Method to give entitys a name -> latter on giving it the stats.
public void scaner(){

Bukkit.getScheduler().runTaskTimer(EStats.INSTANCE/*Why the f does this have to be called EStats.INSTANCE....... */, ()->{

for (World world : Bukkit.getWorlds()){ //creates the component world which is everry world
    for (Entity activeEntitys : world.getEntities()){//gets the entity's in that world and puts it in the component activeEntitys

        if (
            activeEntitys.getPersistentDataContainer().has(ignore)||//checks if it is not supposed to be renamed, by checking for the ignore persistant data container.
            activeEntitys instanceof Item||//Ignores Item's
            activeEntitys instanceof FallingBlock//Ignores Falligstuff Like Sand and so on
            ) continue;

        for (Entity player : Bukkit.getOnlinePlayers()){

            if (player.getWorld()!= activeEntitys.getWorld())continue; //Fixes the plugin checking for the distance between the player and and entity that is not in the same world, by comparing if they are in the same world.
            if (player.getLocation().distanceSquared(activeEntitys.getLocation()) <= 100){//Checks for entitys surounding the Entity player pls use radius^2. ;)
                activeEntitys.customName(Component.text("Test"));//Everrything within this can be a component instead of an componant that is the text within this.
                activeEntitys.setCustomNameVisible(true);//Should activate the name
            }
                
            else{//If the Entity is further out then it will do the following
                activeEntitys.setCustomNameVisible(false);//Should deactivate the name.
            }
        }
    }
}}, 0, 20);

}

}
