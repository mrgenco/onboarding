package com.mrg.onboarding.document.model;

import com.mrg.onboarding.user.AppUser;
import com.mrg.onboarding.user.web.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private AppUser createdBy;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_updated_by", referencedColumnName = "id")
    private AppUser lastUpdatedBy;

    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    @Column(name = "status")
    private Integer status;


    @PrePersist
    private void prePersist() {
        this.uuid = UUID.randomUUID(); // Assuming you want to generate UUID here as well
        this.createdTime = LocalDateTime.now();
        this.lastUpdatedTime = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate() {
        this.lastUpdatedTime = LocalDateTime.now();
    }

}
