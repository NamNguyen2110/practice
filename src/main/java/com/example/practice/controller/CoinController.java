package com.example.practice.controller;

import com.example.practice.domain.dto.request.SearchDTO;
import com.example.practice.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CoinController {
    @Autowired
    private CoinService coinService;

    @GetMapping("/coins")
    public ResponseEntity<?> get_coins(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "per_page", required = false, defaultValue = "5") int size, @RequestParam(value = "vsCurrency", defaultValue = "USD") String vsCurrency) {
        return ResponseEntity.ok().body(coinService.getAllByCurrency(
                SearchDTO.builder()
                        .vsCurrency(vsCurrency)
                        .perPage(size)
                        .page(page)
                        .build()));
    }

}
