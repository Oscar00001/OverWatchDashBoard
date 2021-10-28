package com.oscar.ovewatchdashboard.data;
import java.time.LocalDateTime;

// package com.example.batchprocessing;
import com.oscar.ovewatchdashboard.model.Match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
//item takes the inpput and output type 
//input is the data 
// output will return the process method
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {

    // private LocalDateTime startTime;
    // private long   id;
    // private String tournamentTitle;
    // private String mapType;
    // private String mapName;
    // private String playerName;
    // private String teamName;
    // private String statName;
    // private String heroName;
    // private double statAmount;
    /**
     * id = id 
     * city = name  map 
     * date = date 
     * team = team 
     * venue = type map 
     * hero = last name? 
     * player = name 
     */

    Match match = new Match();
    match.setStartTime(LocalDateTime.parse(matchInput.getStart_time()));
    match.setId(Long.parseLong(matchInput.getEsports_match_id()));
    match.setTournamentTitle(matchInput.getTournament_title());
    match.setMapType(matchInput.getMap_type());
    match.setMapName(matchInput.getMap_name());
    match.setPlayerName(matchInput.getPlayer_name());
    match.setTeamName(matchInput.getTeam_name());
    match.setStatName(matchInput.getStat_name());
    match.setHeroName(matchInput.getHero_name());
    match.setStatAmount(Double.parseDouble(matchInput.getStat_amount()));
  
  
    
    return match;
  }

}

