package com.becs.spring_framework_6_rest_mvc.controller;

import com.becs.spring_framework_6_rest_mvc.model.Beer;
import com.becs.spring_framework_6_rest_mvc.services.BeerService;
import com.becs.spring_framework_6_rest_mvc.services.BeerServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class) // No full auto configuration, only spring mvc, and the test limited only to BeerController.class
@AutoConfigureMockMvc(printOnlyOnFailure = false)   // Nem csak fail tesztnel irja ki a request-et es a response-t.
class BeerControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();


    @Test
    void testListBeer() throws Exception {
        // The mocked class should return with what kind of values
        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3))); //Test with jsonPath and hamcrest is()
    }

    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0);
        System.out.println(testBeer.getBeerName());

        // The mocked class should return with what kind of values
        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);

        mockMvc.perform(get("/api/v1/beer/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))   //Test with jsonPath and hamcrest is()
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName()))); //Test with jsonPath and hamcrest is()

        // If we want the result back:
        MvcResult result = mockMvc.perform(get("/api/v1/beer/" + testBeer.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.id", is(testBeer.getId().toString())))     //Test with jsonPath and hamcrest is()
                //.andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())))    //Test with jsonPath and hamcrest is()
                .andReturn(); // Return the result to MvcResult

        // We can do anything the result
        String response = result.getResponse().getContentAsString();
        System.out.println(response);

    }


    void testRestReqestResponse() throws Exception {
        String jsonRequest = new JSONObject()
                //.put("applicantName", applicant.getApplicantName())
                //.put("applicantCity", applicant.getApplicantCity())
                .put("applicantAddress", "osztaly.attributum")
                .put("applicantPostcode", "osztaly.attributum")
                .toString();

        MvcResult response = mockMvc.perform(post("/url/applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // UTF-8-á alakítjuk, mert ISO_8859_1-ben jön vissza, hogy össze tudjuk hasonlítani
        String responseString = response.getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONObject responseJsonApplicant = new JSONObject(response.getResponse().getContentAsString(StandardCharsets.UTF_8));

        JSONArray jsonArray = new JSONArray(response.getResponse().getContentAsString(StandardCharsets.UTF_8));
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        JSONObject jsonObject2 = jsonArray.getJSONObject(1);
    }

}