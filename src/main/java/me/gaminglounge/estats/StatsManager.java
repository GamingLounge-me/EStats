package me.gaminglounge.estats;

import java.util.HashMap;

import org.bukkit.entity.Entity;

public class StatsManager {
    
    private HashMap<Entity, Double> aktLeben;
    private HashMap<Entity, Double> aktMaxMana;
    private HashMap<Entity, Double> aktMana;

    public StatsManager() {

        aktLeben = new HashMap<>();
        aktMaxMana = new HashMap<>();
        aktMana = new HashMap<>();

    }

    public void setAktLeben(Entity e, Double d) {
        aktLeben.put(e, d);
    }
    
    public Double getAktLeben(Entity e) {
        return aktLeben.get(e);
    }
    
    public void removeAktLeben(Entity e) {
        aktLeben.remove(e);
    }

    public void setMaxMana(Entity e, Double d) {
        aktMaxMana.put(e, d);
    }
    
    public Double getMaxMana(Entity e) {
        return aktMaxMana.get(e);
    }
    
    public void removeMaxMana(Entity e) {
        aktMaxMana.remove(e);
    }

    public void setAktMana(Entity e, Double d) {
        aktMana.put(e, d);
    }
    
    public Double getAktMana(Entity e) {
        return aktMana.get(e);
    }
    
    public void removeAktMana(Entity e) {
        aktMana.remove(e);
    }
}