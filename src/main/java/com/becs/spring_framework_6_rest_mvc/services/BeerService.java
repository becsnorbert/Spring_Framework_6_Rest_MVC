package com.becs.spring_framework_6_rest_mvc.services;

import com.becs.spring_framework_6_rest_mvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

}
