package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Get holiday info from https://date.nager.at/api/v2/publicholidays")
@Slf4j
@RestController
@RequestMapping("/api/publicholidays")
public class PublicHolidaysController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary="Get a country's ISO codes and more", description ="Country data uploaded to OpenDataSoft by the International Labour Organization")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="year", example="2023") @RequestParam String year,
        @Parameter(name="countryCode", example="us") @RequestParam String countryCode
        ) throws JsonProcessingException {
            log.info("getPublicHolidays: year={}", year);
            log.info("getPublicHolidays: countryCode={}", countryCode);
            String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }

}