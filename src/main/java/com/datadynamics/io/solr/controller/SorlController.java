package com.datadynamics.io.solr.controller;

import com.datadynamics.io.solr.service.SolrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/service/solr1")
@Slf4j
public class SorlController {

    @Autowired
    SolrService solrService;


    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok(solrService.findAll());
    }
}
