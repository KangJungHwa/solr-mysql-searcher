package com.datadynamics.io.solr.repository;

import com.datadynamics.io.solr.model.Job;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface SolrRepository extends SolrCrudRepository<Job,String> {
   // List<Job> findImagePathsByFilenameContains(String filename);
}
