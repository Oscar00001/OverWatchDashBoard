package com.oscar.ovewatchdashboard.repository;
import java.time.LocalDate;
import java.util.List;

import com.oscar.ovewatchdashboard.model.Match;
import com.oscar.ovewatchdashboard.model.Team;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository <Match,Long>{
    List<Match> getByAttackerOrDefenderOrderByRoundStartTimeDesc(String attacker,String defender, Pageable pageAble);
    @Query ("select m from Match m where (m.attacker = :teamName or m.defender =:teamName) and m.roundStartTime between :dateStart and :dateEnd order by roundStartTime desc")
    List <Match> getMatchesByTeamBetweenRoundStartTime(
        @Param ("teamName") String teamName,
        @Param ("dateStart")LocalDate dateStart, 
        @Param ("dateEnd") LocalDate endTime);
    // List<Match> getByAttackerOrDefenderAndRoundStartTimeBetweenOrderByRoundStartTimeDesc(String attacker,String defender, LocalDate date1, LocalDate date2);

    default List<Match> findLastestMatchesbyTeam (String teamName, int count){
        return getByAttackerOrDefenderOrderByRoundStartTimeDesc(teamName,teamName, PageRequest.of(0, count));
    }
}
