package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.TransferRequestDto;
import com.example.footballmanager.dto.TransferResponseDto;
import com.example.footballmanager.mapper.TransferMapper;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.model.Transfer;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.repository.TransferRepository;
import com.example.footballmanager.service.TransferService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TransferServiceImpl(TransferRepository transferRepository,
                               TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.transferRepository = transferRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    @Override
    public TransferResponseDto transferPlayer(TransferRequestDto transferRequestDto) {

        Team sellerTeam = teamRepository.findById(transferRequestDto.getTeamSellerId())
                .orElseThrow(() -> new NoSuchElementException("Can't get sellerTeam by id"));

        Team buyerTeam = teamRepository.findById(transferRequestDto.getTeamBuyerId())
                .orElseThrow(() -> new NoSuchElementException("Can't get buyerTeam by id"));

        Player player = playerRepository.findById(transferRequestDto.getPlayerId())
                .orElseThrow(() -> new NoSuchElementException("Can't get player by id"));

        BigDecimal transferPrice = calculateTransferPrice(player, sellerTeam);

        if (buyerTeam.getBalance().compareTo(transferPrice) < 0) {
            throw new RuntimeException("Insufficient balance in buyer's account");
        }

        validatePlayerTeam(player, sellerTeam);

        updateTeamsBalances(sellerTeam, buyerTeam, transferPrice);
        movePlayerToBuyerTeam(player, buyerTeam);

        Transfer transfer = createTransfer(transferPrice, player, buyerTeam, sellerTeam);

        return new TransferMapper().mapToDto(transfer);
    }

    private Transfer createTransfer(BigDecimal transferPrice, Player player,
                                    Team buyerTeam, Team sellerTeam) {
        Transfer transfer = new Transfer();
        transfer.setTransferPrice(transferPrice);
        transfer.setPlayer(player);
        transfer.setBuyerTeam(buyerTeam);
        transfer.setSellerTeam(sellerTeam);
        return transferRepository.save(transfer);
    }

    private void validatePlayerTeam(Player player, Team sellerTeam) {
        if (!player.getTeam().getId().equals(sellerTeam.getId())) {
            throw new RuntimeException("Input data is incorrect");
        }
    }

    private void updateTeamsBalances(Team sellerTeam, Team buyerTeam, BigDecimal transferPrice) {
        buyerTeam.setBalance(buyerTeam.getBalance().subtract(transferPrice));
        sellerTeam.setBalance(sellerTeam.getBalance().add(transferPrice));

        teamRepository.save(buyerTeam);
        teamRepository.save(sellerTeam);
    }

    private void movePlayerToBuyerTeam(Player player, Team buyerTeam) {
        player.setTeam(buyerTeam);
        playerRepository.save(player);
    }

    private BigDecimal calculateTransferPrice(Player player, Team sellerTeam) {
        BigDecimal transferPriceForPlayer = BigDecimal.valueOf(player.getMonthsOfExperience())
                .multiply(BigDecimal.valueOf(100000))
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.CEILING);
        BigDecimal commissionOfTransfer = transferPriceForPlayer
                .divide(BigDecimal.valueOf(100), RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(sellerTeam.getCommissionTransferPercent()));
        return transferPriceForPlayer.add(commissionOfTransfer);
    }
}
