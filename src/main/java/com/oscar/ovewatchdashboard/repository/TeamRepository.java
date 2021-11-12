package com.oscar.ovewatchdashboard.repository;

import com.oscar.ovewatchdashboard.model.Team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long>{
    Team findByTeamName(String teamName);
}
