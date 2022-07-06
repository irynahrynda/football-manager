package com.example.footballmanager.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TeamResponseDto {
    private Long id;
    private String teamName;
    private Double commissionTransferPercent;
    private BigDecimal balance;
}
