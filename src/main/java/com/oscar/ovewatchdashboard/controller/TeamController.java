package com.oscar.ovewatchdashboard.controller;

import com.oscar.ovewatchdashboard.model.Team;
import com.oscar.ovewatchdashboard.repository.MatchRepository;
import com.oscar.ovewatchdashboard.repository.TeamRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);
        Pageable pageAble= PageRequest.of (0,4);
        team.setMatches(matchRepository.getByAttackerOrDefenderOrderByRoundStartTimeDesc(teamName, teamName, pageAble));
        return team;
    }
}
//jpa allows us to sorta create query with class name 
//create repo using the defined interface and the jpa will create the inferstrutire using the 
// class 