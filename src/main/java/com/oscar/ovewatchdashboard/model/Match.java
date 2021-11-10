package com.oscar.ovewatchdashboard.model;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {
private LocalDate roundStartTime;
@Id
private int matchId;
private long gameNumber;
private String matchWinner;
private String mapWinner;
private String mapLoser;
private String mapName;
private long mapRound;
private long winningTeamFinalMapScore;
private long losingTeamFinalMapScore;
private String controlRoundName;
private String attacker;
private String defender;
private double attackerPayloadDistance; 

public LocalDate getRoundStartTime() {
    return roundStartTime;
}
public void setRoundStartTime(LocalDate roundStartTime) {
    this.roundStartTime = roundStartTime;
}

public int getMatchId() {
    return matchId;
}
public void setMatchId(int matchId) {
    this.matchId = matchId;
}

public long getGameNumber() {
    return gameNumber;
}
public void setGameNumber(long gameNumber) {
    this.gameNumber = gameNumber;
}
public String getMatchWinner() {
    return matchWinner;
}
public void setMatchWinner(String matchWinner) {
    this.matchWinner = matchWinner;
}
public String getMapWinner() {
    return mapWinner;
}
public void setMapWinner(String mapWinner) {
    this.mapWinner = mapWinner;
}
public String getMapLoser() {
    return mapLoser;
}
public void setMapLoser(String mapLoser) {
    this.mapLoser = mapLoser;
}
public String getMapName() {
    return mapName;
}
public void setMapName(String mapName) {
    this.mapName = mapName;
}
public long getMapRound() {
    return mapRound;
}
public void setMapRound(long mapRound) {
    this.mapRound = mapRound;
}
public long getWinningTeamFinalMapScore() {
    return winningTeamFinalMapScore;
}
public void setWinningTeamFinalMapScore(long winningTeamFinalMapScore) {
    this.winningTeamFinalMapScore = winningTeamFinalMapScore;
}
public long getLosingTeamFinalMapScore() {
    return losingTeamFinalMapScore;
}
public void setLosingTeamFinalMapScore(long losingTeamFinalMapScore) {
    this.losingTeamFinalMapScore = losingTeamFinalMapScore;
}
public String getControlRoundName() {
    return controlRoundName;
}
public void setControlRoundName(String controlRoundName) {
    this.controlRoundName = controlRoundName;
}
public String getAttacker() {
    return attacker;
}
public void setAttacker(String attacker) {
    this.attacker = attacker;
}
public String getDefender() {
    return defender;
}
public void setDefender(String defender) {
    this.defender = defender;
}
public double getAttackerPayloadDistance() {
    return attackerPayloadDistance;
}
public void setAttackerPayloadDistance(double attackerPayloadDistance) {
    this.attackerPayloadDistance = attackerPayloadDistance;
}



}
