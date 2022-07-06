package com.example.footballmanager.service;

import com.example.footballmanager.dto.TransferRequestDto;
import com.example.footballmanager.dto.TransferResponseDto;

public interface TransferService {
    TransferResponseDto transferPlayer(TransferRequestDto transferRequestDto);
}
