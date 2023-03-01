package com.becs.spring_framework_6_rest_mvc.controller;

import com.becs.spring_framework_6_rest_mvc.model.Beer;
import com.becs.spring_framework_6_rest_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    //@RequestMapping(value = "/api/v1/beer/{beerId}", method = RequestMethod.GET)
    @GetMapping("/{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer by Id - in controller. Id: {}", beerId.toString());

        return beerService.getBeerById(beerId);
    }

}

