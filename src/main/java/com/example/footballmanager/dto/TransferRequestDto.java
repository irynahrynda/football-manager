package com.example.footballmanager.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private Long playerId;
    private Long teamSellerId;
    private Long teamBuyerId;
}
