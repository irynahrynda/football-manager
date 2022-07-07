package com.example.footballmanager.controller;

import com.example.footballmanager.dto.TeamRequestDto;
import com.example.footballmanager.dto.TeamResponseDto;
import com.example.footballmanager.mapper.TeamMapper;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.TeamService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto createTeam(@Valid @RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamService.createTeam(teamMapper.mapToModel(teamRequestDto));
        return teamMapper.mapToDto(team);
    }

    @GetMapping("/{id}")
    public TeamResponseDto getById(@PathVariable Long id) {
        return teamMapper.mapToDto(teamService.getTeamById(id));
    }

    @GetMapping
    public List<TeamResponseDto> getAllTeams() {
        return teamService.getAllTeams()
                .stream()
                .map(teamMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TeamResponseDto updateTeam(@PathVariable Long id,
                                     @Valid @RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamMapper.mapToModel(teamRequestDto);
        team.setId(id);
        return teamMapper.mapToDto(teamService.createTeam(team));
    }

    @DeleteMapping("/{id}")
    public String deleteTeamById(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "Team by id " + id + " is deleted";
    }
}
