package GamingLounge.me.eStats;


import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import net.kyori.adventure.text.Component;

public class EntityName {
    

        public static final NamespacedKey ignore = new NamespacedKey("Estats", "ignore"); //creates the substructure for the persistantdatacontainer and "ignore" it self.


        //Method to give entitys a name/nametag -> latter on giving it the stats.
public void scaner(){

Bukkit.getScheduler().runTaskTimer(null, ()->{

for (World world : Bukkit.getWorlds()){ //creates the component world which is everry world
    for (Entity activeEntitys : world.getEntities()){//gets the entity's in that world and puts it in the component activeEntitys
        if (activeEntitys.getPersistentDataContainer().has(ignore)) return;//checks if it is not supposed to be renamed, by checking for the ignore persistant data container.
        activeEntitys.customName(Component.text("Test")/*Everrything within this can be a component insted of an componant that is the text within this. */);
        activeEntitys.setCustomNameVisible(true);
    }
}}, 0, 20);

}

}
