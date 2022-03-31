package com.example.practice.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDTO implements Serializable {
   private String vsCurrency;
   private Integer page;
   private Integer perPage;
}
