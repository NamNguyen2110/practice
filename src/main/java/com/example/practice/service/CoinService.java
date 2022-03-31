package com.example.practice.service;

import com.example.practice.domain.dto.request.SearchDTO;
import com.example.practice.domain.dto.response.ResponseData;

public interface CoinService {
    ResponseData<?> getAllByCurrency(SearchDTO request);


}
