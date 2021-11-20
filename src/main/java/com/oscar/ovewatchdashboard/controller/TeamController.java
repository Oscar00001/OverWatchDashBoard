package com.oscar.ovewatchdashboard.controller;

import com.oscar.ovewatchdashboard.model.Team;

import java.time.LocalDate;
import java.util.List;

import com.oscar.ovewatchdashboard.model.Match;
import com.oscar.ovewatchdashboard.repository.MatchRepository;
import com.oscar.ovewatchdashboard.repository.TeamRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);
        Pageable pageAble= PageRequest.of (0,20);
        team.setMatches(matchRepository.getByAttackerOrDefenderOrderByRoundStartTimeDesc(teamName, teamName, pageAble));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);
        return this.matchRepository.getMatchesByTeamBetweenRoundStartTime(teamName, startDate, endDate);
        // return this.matchRepository.getByAttackerOrDefenderAndRoundStartTimeBetweenOrderByRoundStartTimeDesc(teamName, teamName, startDate, endDate);
    }
}
//jpa allows us to sorta create query with class name 
//create repo using the defined interface and the jpa will create the infrustructor 
