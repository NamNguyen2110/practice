package com.example.practice.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Roi implements Serializable {
    private BigDecimal times;
    private String currency;
    private BigDecimal percentage;
}
