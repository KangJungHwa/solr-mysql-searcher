package com.datadynamics.io.solr.controller;

import com.datadynamics.io.solr.model.Job;
import com.datadynamics.io.solr.service.SolrService;
import com.datadynamics.io.solr.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public ResponseEntity findByJobName(@RequestParam  String jobName) {
        log.info("{}", String.format("jobName을 '%s'로 검색합니다.", jobName));
        long startMillis = System.currentTimeMillis();
        Optional<Job> optionalJob = solrService.findByJobName(jobName);
        System.out.println(optionalJob.get());

        String endMillis = String.valueOf(System.currentTimeMillis() - startMillis);
        log.info("총 처리 시간은 {}ms 입니다.", endMillis);
       //ResponseEntity responseEntity = new ResponseEntity();

        return ResponseEntity.ok(MapUtils.map("elapsed (ms)", endMillis, "name", optionalJob.get().getJobName()));
    }
    @GetMapping("/search/product")
    public ResponseEntity findProduct(@RequestParam  String searchStr) {
        log.info("{}", String.format("Product을 '%s'로 검색합니다.", searchStr));
        long startMillis = System.currentTimeMillis();
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/techproducts").build();
        SolrQuery solrQuery = new SolrQuery();
        String query=String.format("name:'%s'", searchStr);
        solrQuery.setQuery(query);
        solrQuery.set("fl","*");
        solrQuery.setRows(10);
        QueryResponse solrResponse=null;
        try {
            solrResponse = httpSolrClient.query(solrQuery);
            System.out.println(solrResponse);
            System.out.println("Total Documents : "+solrResponse.getResults().getNumFound());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Optional<Job> optionalJob = solrService.findByJobName(jobName);
//        System.out.println(optionalJob.get());

        String endMillis = String.valueOf(System.currentTimeMillis() - startMillis);
        log.info("총 처리 시간은 {}ms 입니다.", endMillis);
        //ResponseEntity responseEntity = new ResponseEntity();

        return ResponseEntity.ok(MapUtils.map("elapsed (ms)", endMillis, "name", solrResponse.getResults().getNumFound()));
    }
}
