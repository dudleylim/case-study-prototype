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
    private Long comm_class_id;

    @Column(nullable = false)
    private String comm_class_name;

    private String description;

    @Column(nullable = false)
    private Status comm_class_status;

    private String comm_class_status_reason;

    // Foreign Keys
    @Column(nullable = false)
    private String service_type;

    // System Generated Values
    private String date_created;
    private String date_last_modified;
    private String created_by;
    private String last_modified_by;
}
