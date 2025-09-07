package com.draconincdomain.mc.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Player Team Core
 */
public class PlayerTeam {

    ///  ==== Player Team Data Values ==== ///
    public int Id;
    public String TeamName;
    public String TeamPrefix;
    public String TeamSuffix;
    public String TeamColor;
    public int TeamScore;
    private List<UUID> TeamMembers;
    private UUID TeamHelper;

    public static final int MAX_TEAM_SIZE = 4;

    /**
     * Player Team Constructor
     * @param id
     * @param teamName
     * @param teamPrefix
     * @param teamSuffix
     * @param teamColor
     */
    public PlayerTeam(int id, String teamName, String teamPrefix, String teamSuffix, String teamColor) {
        Id = id;
        TeamName = teamName;
        TeamPrefix = teamPrefix;
        TeamSuffix = teamSuffix;
        TeamColor = teamColor;

        TeamScore = 0;
        TeamMembers = new ArrayList<>();
        TeamHelper = null;
    }

    ///  ==== Player Team Core Logic ==== ///

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTeamPrefix() {
        return TeamPrefix;
    }

    public void setTeamPrefix(String teamPrefix) {
        TeamPrefix = teamPrefix;
    }

    public String getTeamSuffix() {
        return TeamSuffix;
    }

    public void setTeamSuffix(String teamSuffix) {
        TeamSuffix = teamSuffix;
    }

    public String getTeamColor() {
        return TeamColor;
    }

    public void setTeamColor(String teamColor) {
        TeamColor = teamColor;
    }

    public int getTeamScore() {
        return TeamScore;
    }

    public void setTeamScore(int teamScore) {
        TeamScore = teamScore;
    }

    ///  ==== Player List Logic ==== ///

    public List<UUID> getTeamMembers() {
        return TeamMembers;
    }

    public void setTeamMembers(List<UUID> teamMembers) {
        TeamMembers = teamMembers;
    }

    public void addMember(UUID uuid) {
        TeamMembers.add(uuid);
    }

    public void removeMember(UUID uuid) {
        TeamMembers.remove(uuid);
    }

    public boolean hasMember(UUID uuid) {
        return TeamMembers.contains(uuid);
    }

    public void clearMembers() {
        TeamMembers.clear();
    }

    public boolean isTeamFull() {
        return TeamMembers.size() >= MAX_TEAM_SIZE;
    }

    ///  === Team Helper Logic ==== ///

    public UUID getHelper() {
        return TeamHelper;
    }

    public void setHelper(UUID teamHelper) {
        TeamHelper = teamHelper;
    }

    public boolean hasHelper() {
        return TeamHelper != null;
    }

    public void removeHelper() {
        TeamHelper = null;
    }

}
