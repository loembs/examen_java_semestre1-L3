package com.patrick.entities;

import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false,of ={ "ref","libelle"})
@ToString
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity{
    private String ref;
    private String libelle;
    private Double prix;
    private int qtestock;
    
    @OneToMany(mappedBy = "article")
    private List<Detail> details;
    @PrePersist
    private void prePersist() {
        if (this.ref == null) {
            this.ref = "A" + "O".repeat(String.valueOf(this.id).length() > 4 ? 0 : 4 - String.valueOf(this.id).length()) + this.id;
        }
    }
    
    /*@ManyToMany(mappedBy = "articles")
    private List<Dette> dettes;*/
    
}
