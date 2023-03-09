package com.becs.spring_framework_6_rest_mvc.controller;

import com.becs.spring_framework_6_rest_mvc.model.Beer;
import com.becs.spring_framework_6_rest_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;


    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        log.info("The {} Beer has been deleted!", beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{beerId}")
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId,
                                     @RequestBody Beer beer) {

        beerService.updateBeer(beerId, beer);
        log.info("The {} Beer has been updated!", beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer)
    {
        Beer savedBeer = beerService.saveNewBeer(beer);
        log.info("New Beer has been saved. The new Beer ID: {}", savedBeer.getId().toString());

        // Good practice to inform the client int the HTTP response header.
        // This case, we send back the new Beer location.
        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    //@RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    @GetMapping("/{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer by Id - in controller. Id: {}", beerId.toString());

        return beerService.getBeerById(beerId);
    }

}

