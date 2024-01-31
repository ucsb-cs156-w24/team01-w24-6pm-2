package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = TidesController.class)
public class TidesControllerTests {
  private ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  TidesQueryService mockTidesQueryService;


  @Test
  public void test_getTides() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    String begin_date = "20210101";
    String end_date = "20210102";
    String station = "9411340";
    when(mockTidesQueryService.getJSON(eq(begin_date),eq(end_date),eq(station))).thenReturn(fakeJsonResult);

    String url = String.format("/api/tides/get?begin_date=%s&end_date=%s&station=%s",begin_date,end_date,station);

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}