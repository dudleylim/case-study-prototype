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
    @Column(name = "comm_id")
    private Long commId;

    @Column(name = "comm_name", nullable = false)
    private String commName;

    @Column(name = "comm_description")
    private String commDescription;

    @Column(name = "comm_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status commStatus;

    @Column(name = "comm_status_reason")
    private String commStatusReason;

    // Foreign Keys
    @ManyToOne
    private CommodityClass commClass;

    // System Generated Values
    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "date_last_modified")
    private String dateLastModified;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
