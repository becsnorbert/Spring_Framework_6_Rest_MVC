package com.becs.spring_framework_6_rest_mvc.services;

import com.becs.spring_framework_6_rest_mvc.model.Beer;
import com.becs.spring_framework_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id - in service. Id: {}", id.toString());

        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Soproni")
                .beerStyle(BeerStyle.LAGER)
                .upc("123456")
                .price(new BigDecimal("540.15"))
                .quantityOnHands(122)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }

}
