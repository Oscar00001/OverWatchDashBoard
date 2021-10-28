package com.oscar.ovewatchdashboard.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Match {
    //  private String start_time;
    // private String esports_match_id;
    // private String tournament_title;
    // private String map_type;
    // private String map_name;
    // private String player_name;
    // private String team_name;
    // private String stat_name;
    // private String hero_name;
    // private String stat_amount;
    private LocalDateTime startTime;
    @Id
    private long   id;
    private String tournamentTitle;
    private String mapType;
    private String mapName;
    private String playerName;
    private String teamName;
    private String statName;
    private String heroName;
    private double statAmount;
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTournamentTitle() {
        return tournamentTitle;
    }
    public void setTournamentTitle(String tournamentTitle) {
        this.tournamentTitle = tournamentTitle;
    }
    public String getMapType() {
        return mapType;
    }
    public void setMapType(String mapType) {
        this.mapType = mapType;
    }
    public String getMapName() {
        return mapName;
    }
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getStatName() {
        return statName;
    }
    public void setStatName(String statName) {
        this.statName = statName;
    }
    public String getHeroName() {
        return heroName;
    }
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
    public double getStatAmount() {
        return statAmount;
    }
    public void setStatAmount(double statAmount) {
        this.statAmount = statAmount;
    }
  
    
}
