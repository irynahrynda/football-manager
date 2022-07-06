package com.example.footballmanager.repository;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeam(Team team);
}
