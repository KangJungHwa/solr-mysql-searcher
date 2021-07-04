package com.datadynamics.io.solr.controller;

import com.datadynamics.io.solr.model.Job;
import com.datadynamics.io.solr.service.SolrService;
import com.datadynamics.io.solr.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/solr")
@Slf4j
public class SorlController {

    @Autowired
    SolrService solrService;


    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok(solrService.findAll());
    }

    @GetMapping("/search/jobname")
    public ResponseEntity directoryNameEquals(@RequestParam  String jobName) {
        log.info("{}", String.format("jobName을 '%s'로 검색합니다.", jobName));
        long startMillis = System.currentTimeMillis();
        Optional<Job> optionalJob = solrService.findByJobName(jobName);
        System.out.println(optionalJob.get());

        String endMillis = String.valueOf(System.currentTimeMillis() - startMillis);
        log.info("총 처리 시간은 {}ms 입니다.", endMillis);
       //ResponseEntity responseEntity = new ResponseEntity();

        return ResponseEntity.ok(MapUtils.map("elapsed (ms)", endMillis, "name", optionalJob.get().getJobName()));
    }
}
