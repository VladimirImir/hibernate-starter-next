package com.dev.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<T extends Serializable> implements BaseEntity<T> {

    private Instant createdAt;
    private String createdBy;

    private Instant updateAt;
    private String updateBy;

    @PrePersist
    public void prePersist() {
        setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdateAt(Instant.now());
    }

}
