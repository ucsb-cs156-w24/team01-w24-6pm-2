package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Tides info from NOAA")
@Slf4j
@RestController
@RequestMapping("/api/tides/get")
public class TidesController {
        
        ObjectMapper mapper = new ObjectMapper();
    
        @Autowired
        TidesQueryService tidesQueryService;
    
        @Operation(summary = "Get tides for a certain station", description = "JSON return format documented here: https://api.tidesandcurrents.noaa.gov/api/prod/")
        @GetMapping("/get")
        public ResponseEntity<String> getTides(
            @Parameter(name="station", description="station id", example="9411340") @RequestParam String station,
            @Parameter(name="begin_date", description="begin date", example="20210101") @RequestParam String begin_date,
            @Parameter(name="end_date", description="end date", example="20210102") @RequestParam String end_date
        ) throws JsonProcessingException {
            log.info("getTides: station={} begin_date={} end_date={}", station, begin_date, end_date);
            String result = tidesQueryService.getJSON(station, begin_date, end_date);
            return ResponseEntity.ok().body(result);
        }

}
