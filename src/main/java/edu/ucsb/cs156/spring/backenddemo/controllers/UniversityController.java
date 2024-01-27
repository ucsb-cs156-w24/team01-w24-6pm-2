package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "University information from http://universities.hipolabs.com/search?name={name}")
@Slf4j
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityQueryService universityQueryService;

    @Operation(summary = "Get information about universities", description = "Returns data about universities based on the provided name.")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversity(
        @Parameter(name = "name", description = "Name of the university", example = "Harvard") @RequestParam String name
    ) {
        log.info("getUniversity: name={}", name);
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }

}
