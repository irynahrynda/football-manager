package com.example.footballmanager.service;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);

    Player getPlayerById(Long id);

    List<Player> getAllPlayers();

    List<Player> getAllPlayersByTeam(Team team);

    String deletePlayerById(Long id);
}
