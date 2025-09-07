package com.draconincdomain.mc.Commands;

import com.draconincdomain.mc.Annotations.Commands;
import com.draconincdomain.mc.Core.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

@Commands
public class TeamCommand extends CommandCore {

    public TeamCommand() {
        super("ohh team", "Mange Teams", "ohh.admin", 0);
    }

    @Override
    protected void execute(Player player, String[] args) {

        if (args.length == 0) {
            player.sendMessage("Usage: /ohh team <create|delete|leave|list|info|assignhelper|removehelper>");
            return;
        }

        String subCmd = args[0];

        switch (subCmd) {
            case "create":
                handleCreateTeam(player, args);
                break;
            case "delete":
                handleDeleteTeam(player, args);
                break;
            case "leave":
                break;
            case "list":
                break;
            case "info":
                break;
            case "assignhelper":
                break;
            case "removehelper":
                break;

        }
    }

    private void handleCreateTeam(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /ohh team create <teamName> [color]");
            return;
        }

        String teamName = args[1];
        String color = args.length > 2 ? args[2].toUpperCase() : "WHITE";

        String teamPrefix = "[" + teamName + "]";
        String teamSuffix = "";

        int teamId = GameManager.getInstance().createTeam(teamName, teamPrefix, teamSuffix, color);

        if (teamId == -1) {
            player.sendMessage(ChatColor.RED + "A team with that name already exists!");
        } else {
            player.sendMessage(ChatColor.GREEN + "Team '" + teamName + "' created successfully with ID: " + teamId);
        }
    }

    private void handleDeleteTeam(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /ohh team delete <teamId>");
            return;
        }

        GameManager.getInstance().deleteTeam(Integer.parseInt(args[1]));
    }


    @Override
    protected List<String> commandCompletion(Player player, Command command, String[] strings) {
        return List.of();
    }
}
