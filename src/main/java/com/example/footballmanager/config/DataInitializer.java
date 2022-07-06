package com.example.footballmanager.config;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.TeamService;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final PlayerService playerService;
    private final TeamService teamService;

    public DataInitializer(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @PostConstruct
    public void inject() {
        Team thunder = new Team();
        thunder.setTeamName("Thunder");
        thunder.setCommissionTransferPercent(8.0);
        thunder.setBalance(BigDecimal.valueOf(2500000));
        teamService.createTeam(thunder);

        Team arizona = new Team();
        arizona.setTeamName("Arizona");
        arizona.setCommissionTransferPercent(5.0);
        arizona.setBalance(BigDecimal.valueOf(1700000));
        teamService.createTeam(arizona);

        Team carolina = new Team();
        carolina.setTeamName("Carolina");
        carolina.setCommissionTransferPercent(7.0);
        carolina.setBalance(BigDecimal.valueOf(1800000));
        teamService.createTeam(carolina);

        Team arsenal = new Team();
        arsenal.setTeamName("Arsenal");
        arsenal.setCommissionTransferPercent(9.0);
        arsenal.setBalance(BigDecimal.valueOf(2800000));
        teamService.createTeam(arsenal);

        Player diegoDorlan = new Player();
        diegoDorlan.setFirstName("Diego");
        diegoDorlan.setLastName("Dorlan");
        diegoDorlan.setAge(32);
        diegoDorlan.setMonthsOfExperience(141);
        diegoDorlan.setTeam(carolina);
        playerService.createPlayer(diegoDorlan);

        Player benArni = new Player();
        benArni.setFirstName("Ben");
        benArni.setLastName("Arni");
        benArni.setAge(26);
        benArni.setMonthsOfExperience(93);
        benArni.setTeam(arizona);
        playerService.createPlayer(benArni);

        Player oliverMaki = new Player();
        oliverMaki.setFirstName("Oliver");
        oliverMaki.setLastName("Maki");
        oliverMaki.setAge(24);
        oliverMaki.setMonthsOfExperience(29);
        oliverMaki.setTeam(arsenal);
        playerService.createPlayer(oliverMaki);
    }
}
