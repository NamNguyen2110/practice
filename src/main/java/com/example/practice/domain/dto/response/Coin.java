package com.example.practice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coin implements Serializable {
    private String id;
    private String image;
    private String symbol;
    private String name;
    private BigDecimal price_change_percentage_24h;
    private BigDecimal current_price;
    private String description;
    private String trade_url;

    public void setPrice_change_percentage_24h(BigDecimal price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCurrent_price(BigDecimal current_price) {
        this.current_price = current_price.setScale(2, RoundingMode.HALF_UP);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public BigDecimal getCurrent_price() {
        return current_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrade_url() {
        return trade_url;
    }

    public void setTrade_url(String trade_url) {
        this.trade_url = trade_url;
    }
}
