package com.patrick.entities;



import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.patrick.entities.Enums.EtatDemande;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "demande_dettes")
public class DemandesDette extends AbstractEntity {
    private LocalDate dateDemande;
    private Double montantDemande;
    @OneToMany(mappedBy = "demandesDette")
    private List<Detail> details;

    @Enumerated(EnumType.STRING) 
    private EtatDemande etat;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private LocalDate dateRelance;
    @OneToOne(mappedBy = "demandeDette")
    private Dette dette;
    @Override
    public String toString() {
        return "DemandesDette [id=" + id + ", dateDemande=" + dateDemande + ", montantDemande=" + montantDemande
                + ", etat=" + etat + ", client=" + client + ", dateRelance=" + dateRelance + ", dette=" + dette + "]";
    }
    
}

