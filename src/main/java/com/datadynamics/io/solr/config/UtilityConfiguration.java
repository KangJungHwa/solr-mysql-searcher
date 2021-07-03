package com.datadynamics.io.solr.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Utililty Class의 Spring Boot Configuration.
 */
@Configuration
@Slf4j
public class UtilityConfiguration {

    @Value("${spring.data.solr.host}")
    String solrURL;

    /**
     * 이미지 병렬 로딩을 위한 Thread Pool Executor를 생성한다.
     *
     * @return Thread Pool Executor
     */
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        final int maxCore = Runtime.getRuntime().availableProcessors();
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(maxCore);
    }

    /**
     * Solr Client를 생성한다.
     * Solr Client의 파라미터를 조정하고 싶은 경우 이 부분을 수정한다.
     *
     * @return Solr Client
     */
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder().withBaseSolrUrl(solrURL).build();
    }

    /**
     * Solr 호출을 위한 Spring Solr Template을 생성한다.
     *
     * @return Spring Solr Template
     */
    @Bean
    public SolrOperations solrTemplate() {
        return new SolrTemplate(solrClient());
    }

}