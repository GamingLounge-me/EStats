package GamingLounge.me.eStats;

import org.bukkit.plugin.java.JavaPlugin;

public final class EStats extends JavaPlugin {

    public static EStats INSTANCE;//Creates an object named INSTANCE, that is the class EStat it self (I hate my life).

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;//Declared/assigns The Main Class as INSTANCE. (Why the f do we need this???)
        EntityName entityName = new EntityName();//Makes the clase Entityname a runing instance, that can be used again latter on if needed.
        entityName.scaner();//Activates scaner Method in the precreated entityName.
        new EntityName();//Starts an/the instance of the Entityname clase because entityname itself does not do that.
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
