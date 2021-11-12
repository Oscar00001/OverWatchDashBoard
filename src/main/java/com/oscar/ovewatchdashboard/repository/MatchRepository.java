package com.oscar.ovewatchdashboard.repository;
import java.util.List;

import com.oscar.ovewatchdashboard.model.Match;
import com.oscar.ovewatchdashboard.model.Team;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository <Match,Long>{
    List<Match> getByAttackerOrDefenderOrderByRoundStartTimeDesc(String attacker,String defender, Pageable pageAble);

    default List<Match> findLastestMatchesbyTeam (String teamName, int count){
        return getByAttackerOrDefenderOrderByRoundStartTimeDesc(teamName,teamName, PageRequest.of(0, count));
    }
}
