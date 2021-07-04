package com.datadynamics.io.solr.service;

import com.datadynamics.io.solr.model.Job;
import com.datadynamics.io.solr.repository.SolrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SolrService {

    @Autowired
    SolrRepository solrRepository;

    public Iterable<Job> findAll() {
        return solrRepository.findAll();
    }
    public Optional<Job> findByJobName(String jobName) {
        return solrRepository.findByJobName(jobName);
    }
}
