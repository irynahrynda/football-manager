package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.TeamRequestDto;
import com.example.footballmanager.dto.TeamResponseDto;
import com.example.footballmanager.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getId());
        teamResponseDto.setTeamName(team.getTeamName());
        teamResponseDto.setCommissionTransferPercent(team.getCommissionTransferPercent());
        teamResponseDto.setBalance(team.getBalance());
        return teamResponseDto;
    }

    public Team mapToModel(TeamRequestDto teamRequestDto) {
        Team team = new Team();
        team.setTeamName(teamRequestDto.getTeamName());
        team.setCommissionTransferPercent(teamRequestDto.getCommissionTransferPercent());
        team.setBalance(teamRequestDto.getBalance());
        return team;
    }
}
