package com.patrick.entities;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "dettes")
public class Dette extends AbstractEntity {

    private LocalDate date;
    private Double montant ;
    private Double montantVerser;
    @Transient
    private Double montantRestant;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable=true)
    private Client client ;
    @OneToMany(mappedBy = "dette")
    private List<Detail> details;
    @OneToMany
    private List<Paiement> paiements;
    @OneToOne
    @JoinColumn(name = "demande_dette_id")
    private DemandesDette demandeDette;

    @Override
    public String toString() {
        return "Dette [date=" + date + ", montant=" + montant + ", montantVerser=" + montantVerser + ", montantRestant="
                + montantRestant + ", client=" + client + ", details=" + details + ", paiements=" + paiements + "]";
    }























    /*@ManyToMany
    private List<Article> articles;*/





}
