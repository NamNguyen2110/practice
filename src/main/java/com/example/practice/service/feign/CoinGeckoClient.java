package com.example.practice.service.feign;

import com.example.practice.domain.dto.response.Coin;
import com.example.practice.domain.dto.response.CoinDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CoinGeckoClient", url = "https://api.coingecko.com/api/v3/coins")
public interface CoinGeckoClient {
    @GetMapping("/markets")
    ResponseEntity<List<Coin>> getCoinList(@RequestParam("vs_currency") String vsCurrency, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "per_page", required = false) Integer perPage);

    @GetMapping("/{id}")
    ResponseEntity<CoinDetails> getCoinDetail(@PathVariable("id") String coin);
}
