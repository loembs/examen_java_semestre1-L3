package com.patrick.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.patrick.entities.Dette;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "paiement")
public class Paiement extends AbstractEntity{
    private Double  montantverse ;
    @ManyToOne
    Dette dette;
    
}
