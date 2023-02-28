package com.becs.spring_framework_6_rest_mvc.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Beer {

    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHands;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
