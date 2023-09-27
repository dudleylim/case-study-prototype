package com.apcargo.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @Id
    @GeneratedValue
    private Long comm_id;

    @Column(nullable = false)
    private String comm_name;

    private String description;

    @Column(nullable = false)
    private Status comm_status;

    private String comm_status_reason;

    @Column(nullable = false)
    private String service_type;

    // System Generated Values
    private String date_created;
    private String date_last_modified;
    private String created_by;
    private String last_modified_by;
}
