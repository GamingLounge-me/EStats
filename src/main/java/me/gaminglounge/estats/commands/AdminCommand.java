package me.gaminglounge.estats.commands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import me.gaminglounge.estats.EStats;

import org.bukkit.entity.Player;

public class AdminCommand {
 
    EStats eStats = EStats.INSTANCE;

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
                    })
                    ).register();
    }

}
