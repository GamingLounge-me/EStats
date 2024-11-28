package me.gaminglounge.estats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.gaminglounge.estats.listener.GetDamage;

public final class EStats extends JavaPlugin {

    public static EStats INSTANCE;//Creates an object named INSTANCE, that is the class EStat it self (I hate my life).
    public EntityName entityName;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;//Declared/assigns The Main Class as INSTANCE. (Why the f do we need this???)
        entityName = new EntityName();//Makes the clase Entityname a runing instance, that can be used again latter on if needed.
        entityName.scanner();//Activates scaner Method in the precreated entityName.

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new GetDamage(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
