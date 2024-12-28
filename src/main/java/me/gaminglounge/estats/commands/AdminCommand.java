package me.gaminglounge.estats.commands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.gaminglounge.estats.EStats;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class AdminCommand {
 
    EStats eStats = EStats.INSTANCE;
    public static final NamespacedKey LEVEL = new NamespacedKey("estats", "level");   // PersistentDataContainer key for "level".

    public AdminCommand() {
        new CommandAPICommand("Estats")
                .withAliases("est")
                .withPermission(CommandPermission.OP)
                .withSubcommand(new CommandAPICommand("setAktMana")
                    .withArguments(new DoubleArgument("Mana"))
                    .withOptionalArguments(new EntitySelectorArgument.OneEntity("Spieler"))
                    .executesPlayer((player, args) -> {
                        Player target = (Player) args.get("Spieler");
                        //Wenn null -> target = executor
                        if (target == null) target = player;

                        @SuppressWarnings("null")
                        double mana = (double) args.get("Mana");
                        EStats.INSTANCE.statsManager.setAktMana(target,mana);
                    }))
                    
                .withSubcommand(new CommandAPICommand("setMaxMana")
                .withArguments(new DoubleArgument("MaxMana"))
                .withOptionalArguments(new EntitySelectorArgument.OneEntity("Spieler"))
                .executesPlayer((player, args) -> {
                    Player target = (Player) args.get("Spieler");
                    //Wenn null -> target = executor
                    if (target == null) target = player;

                    @SuppressWarnings("null")
                    double mana = (double) args.get("MaxMana");
                    EStats.INSTANCE.statsManager.setMaxMana(target,mana);
                }))

                .withSubcommand(new CommandAPICommand("setMaxLeben")
                .withArguments(new DoubleArgument("MaxLeben"))
                .withOptionalArguments(new EntitySelectorArgument.OneEntity("Spieler"))
                .executesPlayer((player, args) -> {
                    Player target = (Player) args.get("Spieler");
                    //Wenn null -> target = executor
                    if (target == null) target = player;

                    @SuppressWarnings("null")
                    double leben = (double) args.get("MaxLeben");
                    EStats.INSTANCE.statsManager.setMaxLeben(target,leben);
                }))
                
                .withSubcommand(new CommandAPICommand("setAktLeben")
                .withArguments(new DoubleArgument("Leben"))
                .withOptionalArguments(new EntitySelectorArgument.OneEntity("Spieler"))
                .executesPlayer((player, args) -> {
                    Player target = (Player) args.get("Spieler");
                    //Wenn null -> target = executor
                    if (target == null) target = player;

                    @SuppressWarnings("null")
                    double leben = (double) args.get("Leben");
                    EStats.INSTANCE.statsManager.setAktLeben(target,leben);
                }))

                .withSubcommand(new CommandAPICommand("setAktLvl")
                .withArguments(new IntegerArgument("Lvl"))
                .withOptionalArguments(new EntitySelectorArgument.OneEntity("Spieler"))
                .executesPlayer((player, args) -> {
                    Player target = (Player) args.get("Spieler");
                    //Wenn null -> target = executor
                    if (target == null) target = player;
                    if (!target.getPersistentDataContainer().has(LEVEL, PersistentDataType.INTEGER)) {
                        target.getPersistentDataContainer().set(LEVEL, PersistentDataType.INTEGER, 1);
                    }

                    @SuppressWarnings("null")
                    int Lvl = (int) args.get("Lvl");
                    target.getPersistentDataContainer().set(LEVEL, PersistentDataType.INTEGER, Lvl);
                })
                ).register();
    }
}
