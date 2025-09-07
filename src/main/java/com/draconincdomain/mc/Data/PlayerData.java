package com.draconincdomain.mc.Data;

import java.util.Map;

public class PlayerData {
    private static Map<Integer, PlayerTeam> playerTeams;

    public static Map<Integer, PlayerTeam> getPlayerTeams() {
        return playerTeams;
    };

    public static void clearPlayerTeams() {
        playerTeams.clear();
    }

    public static PlayerTeam getPlayerTeam(int id) {
        return playerTeams.get(id);
    }

    public static void setPlayerTeams(Map<Integer, PlayerTeam> playerTeams) {
        PlayerData.playerTeams = playerTeams;
    }

    public static boolean teamExists(String teamName) {
        return playerTeams.values().stream()
                .anyMatch(team -> team.getTeamName().equalsIgnoreCase(teamName));
    }
}
