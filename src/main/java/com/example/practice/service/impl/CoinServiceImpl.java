package com.example.practice.service.impl;

import com.example.practice.domain.dto.request.SearchDTO;
import com.example.practice.domain.dto.response.Coin;
import com.example.practice.domain.dto.response.CoinDetails;
import com.example.practice.domain.dto.response.CoinResponse;
import com.example.practice.domain.dto.response.ResponseData;
import com.example.practice.service.CoinService;
import com.example.practice.service.feign.CoinGeckoClient;
import com.example.practice.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CoinServiceImpl implements CoinService {
    @Autowired
    private CoinGeckoClient coinGeckoClient;
    @Autowired
    private JsonUtils jsonUtils;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseData<?> getAllByCurrency(SearchDTO request) {
        List<Coin> coins = new ArrayList<>();
        List<CoinResponse> coinsResponses = new ArrayList<>();
        if (request.getPage() == null) {
            request.setPage(0);
        }
        if (request.getPerPage() == null) {
            request.setPerPage(100);
        }
        ResponseEntity<List<Coin>> data = coinGeckoClient.getCoinList(request.getVsCurrency(), request.getPage(), request.getPerPage());
        if (data.getStatusCode().value() == HttpStatus.OK.value()) {
            log.info("Coin Gecko: {}", jsonUtils.convertObjToString(data.getBody()));
            coins = data.getBody();
            if (coins != null && !coins.isEmpty()) {
                coins.forEach(coin -> {
                    ResponseEntity<CoinDetails> coinDetails = coinGeckoClient.getCoinDetail(coin.getId());
                    if (coinDetails.getStatusCode() == HttpStatus.OK) {
                        log.info("Coin details: {}", jsonUtils.convertObjToString(coinDetails.getBody()));
                        CoinDetails details = coinDetails.getBody();
                        if (details != null) {
                            coin.setDescription(details.getDescription().getEn());
                            if (details.getTickers() != null && !details.getTickers().isEmpty()) {
                                coin.setTrade_url(details.getTickers().get(0).getTrade_url());
                            }
                        }
                        coinsResponses.add(mapper.map(coin,CoinResponse.class));
                    } else {
                        log.error("Coin Gecko server error, URL: /api/v3/coins/markets?vs_currency with statusCode: {}", coinDetails.getStatusCode().value());
                    }
                });
            }
        } else {
            log.error("Coin Gecko server error, URL: /api/v3/coins/ with statusCode: /api/v3/coins/markets?vs_currency {}", data.getStatusCode().value());
        }
        return new ResponseData<>()._ok(coinsResponses);
    }
}
