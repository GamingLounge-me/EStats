package me.gaminglounge.estats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.gaminglounge.estats.commands.AdminCommand;
import me.gaminglounge.estats.listener.GetDamage;
import me.gaminglounge.estats.listener.PlayerInformation;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;

public final class EStats extends JavaPlugin {

    public static EStats INSTANCE;//Creates an object named INSTANCE, that is the class EStat it self (I hate my life).
    public EntityName entityName;
    public StatsManager statsManager;
    public AdminCommand adminCommand;


    public void onLoad(){
        INSTANCE = this;
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;//Declared/assigns The Main Class as INSTANCE. (Why the f do we need this???)
        entityName = new EntityName();//Makes the clase Entityname a runing instance, that can be used again latter on if needed.
        entityName.scanner();//Activates scaner Method in the precreated entityName.
        statsManager = new StatsManager();
        adminCommand = new AdminCommand();
        
        CommandAPI.onEnable();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerInformation(), this);
        pm.registerEvents(new GetDamage(), this);
    }
    
    @Override
    public void onDisable() {
        CommandAPI.onDisable();
        // Plugin shutdown logic
    }
}
