package GamingLounge.me.eStats;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class EntityName {

    public static final NamespacedKey IGNORE = new NamespacedKey("estats", "ignore"); // PersistentDataContainer key for "ignore".
    public static final NamespacedKey LEVEL = new NamespacedKey("estats", "level");   // PersistentDataContainer key for "level".

    MiniMessage miniMessage = MiniMessage.miniMessage();

    public void scanner() {
        // Schedule a repeating task
        Bukkit.getScheduler().runTaskTimer(EStats.INSTANCE, () -> {
            for (World world : Bukkit.getWorlds()) { // Iterate through all worlds
                for (Entity activeEntity : world.getEntities()) { // Iterate through all entities

                    // Skip entities that are ignored or invalid
                    if (activeEntity.getPersistentDataContainer().has(IGNORE, PersistentDataType.BYTE)
                            || activeEntity instanceof ArmorStand
                            || !(activeEntity instanceof Damageable)
                            || !(activeEntity instanceof LivingEntity)) {
                        continue;
                    }

                    // Check if players are nearby
                    if (activeEntity.getWorld().getNearbyPlayers(activeEntity.getLocation(), 15).size() > 0) {
                        if (!activeEntity.getPersistentDataContainer().has(LEVEL, PersistentDataType.INTEGER)) {
                            // If the entity does not have a Level, set it to 1
                            activeEntity.getPersistentDataContainer().set(LEVEL, PersistentDataType.INTEGER, 1);
                            continue;
                        }

                        Integer entityLevel = activeEntity.getPersistentDataContainer().get(LEVEL, PersistentDataType.INTEGER);

                        // Null safety check
                        if (entityLevel == null|| entityLevel == 0) {
                            entityLevel = 1; // Default level
                        }

                        activeEntity.setCustomNameVisible(true); // Enable custom name visibility

                        // Calculate tier by dividing level by 10, declearing it as an int and then rounding up by using the Math.ceil() Method 
                        int tier = (int) Math.ceil((entityLevel / 10.0));
                        //Debugging
//                        System.out.println("Tier"+tier);
//                        System.out.println("Lvl"+entityLevel);


                        // Determine name tag based on level range
                        Component entityNameTag;
                        switch (tier) {
                            case 1 -> { // Level 1-10
                                entityNameTag = miniMessage.deserialize(
                                        "<yellow>" + activeEntity.getType() + "</yellow> " +
                                                "<gray>[</gray><white>" + entityLevel + "</white><gray>]</gray>");
                            }
                            case 2 -> { // Level 11-20
                                entityNameTag = miniMessage.deserialize(
                                        "<yellow>" + activeEntity.getType() + "</yellow> " +
                                                "<gray>[</gray><lime>" + entityLevel + "</lime><gray>]</gray>");
                            }
                            default -> { // Levels outside defined ranges
                                entityNameTag = miniMessage.deserialize(
                                        "<yellow>" + activeEntity.getType() + "</yellow> " +
                                                "<gray>[</gray><red>" + entityLevel + "</red><gray>]</gray>");
                            }
                        }

                        // Set the entity's custom name
                        activeEntity.customName(entityNameTag);

                    } else { // No players nearby
                        activeEntity.setCustomNameVisible(false); // Disable custom name visibility
                    }
                }
            }
        }, 0, 20); // Repeat task every 20 ticks (1 second)
    }
}
