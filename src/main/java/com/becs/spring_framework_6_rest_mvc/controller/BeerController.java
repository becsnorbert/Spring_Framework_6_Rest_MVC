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
public class BeerController {

    // If this property is static, then we can use it in our tests.
    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;


    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId,
                                              @RequestBody Beer beer) {
        beerService.patchBeerById(beerId, beer);
        log.info("The {} Beer has been Patched!", beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        log.info("The {} Beer has been deleted!", beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId,
                                     @RequestBody Beer beer) {

        beerService.updateBeerById(beerId, beer);
        log.info("The {} Beer has been updated!", beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
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
    @GetMapping(BEER_PATH)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    //@RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    @GetMapping(BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer by Id - in controller. Id: {}", beerId.toString());

        return beerService.getBeerById(beerId);
    }

}

