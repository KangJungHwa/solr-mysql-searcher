package com.datadynamics.io.solr.controller;

import com.datadynamics.io.solr.model.Job;
import com.datadynamics.io.solr.service.SolrService;
import com.datadynamics.io.solr.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
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
    @GetMapping("/search/cat_std_word")
    public ResponseEntity findProduct(@RequestParam  String searchStr) {
        //http://localhost:9988/service/solr/search/product?searchStr=The
        log.info("{}", String.format("테이블을 '%s'로 검색합니다.", searchStr));
        long startMillis = System.currentTimeMillis();
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/cat_std_word").build();
        SolrQuery solrQuery = new SolrQuery();
        //String query=String.format("name:'%s'", searchStr);
        String query=String.format("*:*");
        solrQuery.setQuery(query);
        QueryResponse queryResponse=null;
        try {
          queryResponse = httpSolrClient.query(solrQuery);
          SolrDocumentList solrDocs = queryResponse.getResults();
          Iterator<SolrDocument> iterator = solrDocs.iterator();
        while (iterator.hasNext()) {
            SolrDocument solrDocument = iterator.next();
//          String docId = (String) solrDocument.getFieldValue("id");
            System.out.println("solrDocument " + solrDocument);
            String synonym = (String) solrDocument.getFieldValue("synonym");
            String[] arrSyn=synonym.split(",");
            for ( String word:arrSyn) {
                System.out.println("word " + word.toString());
            }
            System.out.println("Document " + synonym);
        }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String endMillis = String.valueOf(System.currentTimeMillis() - startMillis);
        log.info("총 처리 시간은 {}ms 입니다.", endMillis);

        return ResponseEntity.ok(MapUtils.map("elapsed (ms)", endMillis, "name", queryResponse.getResults().getNumFound()));
    }
}
