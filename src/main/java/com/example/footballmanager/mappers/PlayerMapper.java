package com.example.footballmanager.mappers;

import com.example.footballmanager.dto.PlayerRequestDto;
import com.example.footballmanager.dto.PlayerResponseDto;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    private final TeamService teamService;

    public PlayerMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    public PlayerResponseDto mapToDto(Player player) {
        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(player.getId());
        playerResponseDto.setFirstName(player.getFirstName());
        playerResponseDto.setLastName(player.getLastName());
        playerResponseDto.setAge(player.getAge());
        playerResponseDto.setMonthsOfExperience(player.getMonthsOfExperience());
        playerResponseDto.setTeamId(player.getTeam().getId());
        return playerResponseDto;
    }

    public Player mapToModel(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        player.setFirstName(playerRequestDto.getFirstName());
        player.setLastName(playerRequestDto.getLastName());
        player.setAge(playerRequestDto.getAge());
        player.setMonthsOfExperience(playerRequestDto.getMonthsOfExperience());
        player.setTeam(teamService.getTeamById(playerRequestDto.getTeamId()));
        return player;
    }
}
