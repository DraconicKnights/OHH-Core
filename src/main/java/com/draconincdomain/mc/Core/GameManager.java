package com.draconincdomain.mc.Core;

import com.draconincdomain.mc.Data.PlayerData;
import com.draconincdomain.mc.Data.PlayerTeam;

import java.util.Map;

public class GameManager {
    private static GameManager instance;

    public GameManager() {
        instance = this;
    }

    public void startGame() {

    }

    public void endGame() {

    }

    public void resetGame() {
        endGame();
        startGame();
    }

    public int createTeam(String teamName, String teamPrefix, String teamSuffix, String teamColor) {
        if (PlayerData.teamExists(teamName)) {
            return -1;
        }

        int teamId = generateNextTeamId();

        PlayerTeam newTeam = new PlayerTeam(teamId, teamName, teamPrefix, teamSuffix, teamColor);

        PlayerData.getPlayerTeams().put(teamId, newTeam);

        return teamId;
    }


    public void deleteTeam(int teamId) {
        PlayerData.getPlayerTeams().remove(teamId);
    }

    public Map<Integer, PlayerTeam> getPlayerTeams() {
        return PlayerData.getPlayerTeams();
    }

    public PlayerTeam getTeam(int teamId) {
        return PlayerData.getPlayerTeam(teamId);
    }

    private int generateNextTeamId() {
        if (PlayerData.getPlayerTeams().isEmpty()) {
            return 1;
        }

        int maxId = PlayerData.getPlayerTeams().keySet().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        return maxId + 1;
    }

    public static GameManager getInstance() {
        return instance;
    }
}
