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
public class ActivityLog {
    @Id
    @GeneratedValue
    @Column(name = "activity_id")
    private Long activityId;

    @Enumerated(EnumType.STRING)
    private Activity activity;

    @Column(name = "activity_owner")
    private String activityOwner;

    @Column(name = "activity_date_time")
    private String activityDateTime;
}
