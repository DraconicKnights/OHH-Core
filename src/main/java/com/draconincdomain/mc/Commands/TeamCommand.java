package com.draconincdomain.mc.Commands;

import com.draconincdomain.mc.Annotations.Commands;
import com.draconincdomain.mc.Core.GameManager;
import com.draconincdomain.mc.Data.PlayerTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Commands
public class TeamCommand extends CommandCore {

    public TeamCommand() {
        super("ohh team", "Mange Teams", "ohh.admin", 0);
    }

    @Override
    protected void execute(Player player, String[] args) {

        if (args.length == 0) {
            player.sendMessage("Usage: /ohh team <create|delete|list|info|assignhelper|removehelper>");
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
            case "list":
                handleListTeam(player, args);
                break;
            case "info":
                handleInfoTeam(player, args);
                break;
            case "assignhelper":
                handleAssignHelperTeam(player, args);
                break;
            case "removehelper":
                handleRemoveHelperTeam(player, args);
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

    private void handleListTeam(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /ohh team list");
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GOLD).append("============= Teams: ============= \n");

        GameManager.getInstance().getPlayerTeams().forEach((teamId, team) -> {
           sb.append(teamId).append(": ").append(team.getTeamName()).append("\n");
        });

        sb.append(ChatColor.GOLD).append("=====================================\n");

        player.sendMessage(sb.toString());
    }

    private void handleInfoTeam(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /ohh team info <teamId>");
            return;
        }

        int teamId = Integer.parseInt(args[1]);

        PlayerTeam team = GameManager.getInstance().getTeam(teamId);

        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GOLD).append("============= Team Info: ============= \n");
        sb.append(ChatColor.AQUA).append("Team ID: ").append(teamId).append("\n");
        sb.append(ChatColor.AQUA).append("Team Name: ").append(team.getTeamName()).append("\n");
        sb.append(ChatColor.AQUA).append("Team Prefix: ").append(team.getTeamPrefix()).append("\n");
        sb.append(ChatColor.AQUA).append("Team Suffix: ").append(team.getTeamSuffix()).append("\n");
        sb.append(ChatColor.AQUA).append("Team Color: ").append(team.getTeamColor()).append("\n");

        sb.append(ChatColor.GOLD).append("=====================================\n");
        sb.append(ChatColor.GOLD).append("Team Members: \n");
        sb.append(ChatColor.GOLD).append("=====================================\n");

        team.getTeamMembers().forEach(uuid -> {
            sb.append(ChatColor.AQUA).append("Player: ").append(Bukkit.getPlayer(uuid).getName()).append("\n");
        });
    }

    private void handleAssignHelperTeam(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /ohh team assignhelper <teamId> <playerName>");
            return;
        }

        int teamId = Integer.parseInt(args[1]);
        String playerName = args[2];

        UUID playerUUID = Bukkit.getPlayer(playerName).getUniqueId();

        PlayerTeam team = GameManager.getInstance().getTeam(teamId);

        if (team.hasHelper()) {
            player.sendMessage(ChatColor.RED + "This team already has a helper!");
        } else {
            team.setHelper(playerUUID);
            player.sendMessage(ChatColor.GOLD + "Team helper assigned successfully!");
        }

    }

    private void handleRemoveHelperTeam(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /ohh team removehelper <teamId> <playerName>");
        }

        int teamId = Integer.parseInt(args[1]);

        PlayerTeam team = GameManager.getInstance().getTeam(teamId);

        if (!team.hasHelper()) {
            player.sendMessage(ChatColor.RED + "This team doesn't have a helper!");
        } else {
            team.setHelper(null);
            player.sendMessage(ChatColor.GOLD + "Team helper removed successfully!");
        }

    }


    @Override
    protected List<String> commandCompletion(Player player, Command command, String[] strings) {
        return List.of();
    }
}
