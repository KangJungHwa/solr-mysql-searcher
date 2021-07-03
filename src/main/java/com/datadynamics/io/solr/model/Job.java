package com.datadynamics.io.solr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Lob;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Job {
    @Id
    @Indexed("job_id")
    Long jobId;

    @Indexed("job_name")
    String jobName;

    @Indexed("script_location")
    String scriptLocation;

}
