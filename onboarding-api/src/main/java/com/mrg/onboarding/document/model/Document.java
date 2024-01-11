package com.mrg.onboarding.document.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    @Column(name = "status")
    private Integer status;

    @PrePersist
    public void onPrePersist() {
        this.setUuid(UUID.randomUUID());
        this.setCreatedBy(1L); // TODO get from security context
        this.setLastUpdatedBy(1L); // TODO get from security context
        this.setCreatedTime(LocalDateTime.now());
        this.setLastUpdatedTime(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setCreatedBy(1L); // TODO get from security context
        this.setLastUpdatedBy(1L);
        this.setLastUpdatedTime(LocalDateTime.now());
    }
}
