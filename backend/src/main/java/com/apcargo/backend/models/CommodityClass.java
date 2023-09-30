package com.apcargo.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommodityClass {
    @Id
    @GeneratedValue
    @Column(name = "comm_class_id")
    private Long commClassId;

    @Column(name = "comm_class_name", nullable = false)
    private String commClassName;

    @Column(name = "comm_class_description")
    private String commClassDescription;

    @Column(name = "comm_class_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status commClassStatus;

    @Column(name = "comm_class_status_reason")
    private String commClassStatusReason;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

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
