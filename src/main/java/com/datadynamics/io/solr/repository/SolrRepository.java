package com.datadynamics.io.solr.repository;

import com.datadynamics.io.solr.model.Job;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;


public interface SolrRepository extends SolrCrudRepository<Job,String> {
   Optional<Job> findByJobName(String jobName);
}
