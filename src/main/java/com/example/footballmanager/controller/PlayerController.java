package com.example.footballmanager.controller;

import com.example.footballmanager.dto.PlayerRequestDto;
import com.example.footballmanager.dto.PlayerResponseDto;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.service.PlayerService;
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
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService,
                            PlayerMapper playerMapper,
                            TeamService teamService) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
        this.teamService = teamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponseDto createPlayer(@Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerService.createPlayer(playerMapper.mapToModel(playerRequestDto));
        return playerMapper.mapToDto(player);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponseDto getById(@PathVariable Long id) {
        return playerMapper.mapToDto(playerService.getPlayerById(id));
    }

    @GetMapping
    public List<PlayerResponseDto> getAllPlayers() {
        return playerService.getAllPlayers().stream()
                .map(playerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponseDto> getAllPlayersByTeam(@PathVariable Long id) {
        return playerService.getAllPlayersByTeam(teamService.getTeamById(id)).stream()
                .map(playerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponseDto updatePlayer(@PathVariable Long id,
                                          @Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerMapper.mapToModel(playerRequestDto);
        player.setId(id);
        return playerMapper.mapToDto(playerService.createPlayer(player));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return "Player by id " + id + " was deleted";
    }
}
