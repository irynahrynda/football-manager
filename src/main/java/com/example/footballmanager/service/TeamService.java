package com.example.footballmanager.service;

import com.example.footballmanager.model.Team;
import java.util.List;

public interface TeamService {
    Team createTeam(Team teamR);

    Team getTeamById(Long id);

    List<Team> getAllTeams();

    void deleteTeamById(Long id);
}
