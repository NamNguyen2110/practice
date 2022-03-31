package com.example.practice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CoinResponse {
    private String image;
    private String symbol;
    private String name;
    private BigDecimal price_change_percentage_24h;
    private BigDecimal current_price;
    private String description;
    private String trade_url;
}
