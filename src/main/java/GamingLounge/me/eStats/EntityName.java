package GamingLounge.me.eStats;


import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;


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
            activeEntitys instanceof ArmorStand||
            !(activeEntitys instanceof Damageable)//Should do the trick
            ) continue;

        if(activeEntitys.getWorld().getNearbyPlayers(activeEntitys.getLocation(), 10).size()>0){//Size checks if the total ammount of players within 10 block's in higher then 0.
            activeEntitys.customName(Component.text("Test"));//Everrything within this can be a component instead of an componant that is the text within this.
            activeEntitys.setCustomNameVisible(true);//Should activate the name
        }
            else{//If the Entity is further out then it will do the following
                activeEntitys.setCustomNameVisible(false);//Should deactivate the name.
            }
    }
}}, 0, 20);

}

}
