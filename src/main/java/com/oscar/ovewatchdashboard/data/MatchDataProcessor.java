package com.oscar.ovewatchdashboard.data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.oscar.ovewatchdashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        System.out.println(matchInput.getRound_start_time());
        match.setRoundStartTime((LocalDate.parse(matchInput.getRound_start_time())));
        match.setMatchId(Integer.parseInt(matchInput.getMatch_id()));
        match.setGameNumber(Long.parseLong(matchInput.getGame_number()));
        match.setMatchWinner(matchInput.getMatch_winner());
        match.setMapWinner(matchInput.getMap_winner());
        match.setMapLoser(matchInput.getMap_lose());
        match.setMapRound(Long.parseLong(matchInput.getMap_round()));
        match.setWinningTeamFinalMapScore(Long.parseLong(matchInput.getWinning_team_final_map_score()));
        match.setLosingTeamFinalMapScore(Long.parseLong(matchInput.getLosing_team_final_map_score()));
        match.setControlRoundName((matchInput.getControl_round_name()));
        match.setAttacker(matchInput.getAttacker());
        match.setDefender(matchInput.getDefender());
        match.setAttackerPayloadDistance(Double.parseDouble(matchInput.getAttacker_payload_distance()));

        return match;
        }
}
