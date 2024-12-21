package com.patrick.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected int id;
    @Column(name="create_at")
    private LocalDateTime createAt;
    @Column(name="update_at")
    private LocalDateTime updateAt;
    @PrePersist
    public void onPrepersist(){
        this.setCreateAt(LocalDateTime.now());
        this.setUpdateAt(LocalDateTime.now());
    }
    @PreUpdate
    public void onPreUpdate(){
        this.setUpdateAt(LocalDateTime.now());
    }

    

}
