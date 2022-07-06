package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.TransferResponseDto;
import com.example.footballmanager.model.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {
    public TransferResponseDto mapToDto(Transfer transfer) {
        TransferResponseDto transferResponseDto = new TransferResponseDto();
        transferResponseDto.setId(transfer.getId());
        transferResponseDto.setTransferPrice(transfer.getTransferPrice());
        return transferResponseDto;
    }
}
