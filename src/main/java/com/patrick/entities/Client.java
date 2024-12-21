package com.patrick.entities;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OneToOne;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false,of ={"surname"})
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    private String surname;
    private String telephone;
    private String adresse;
    @OneToOne(fetch =FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user; 
    @OneToMany(mappedBy = "client")
    private List<Dette> dettes;


@Override
public String toString() {
    return "Client{" +
           "id=" + getId() +   
           ", surname='" + surname + '\'' +
           ", telephone='" + telephone + '\'' +
           ", adresse='" + adresse + '\'' +
           ", user=" + (user != null ? user.getLogin() : "null") + 
           ", dettesCount=" + (dettes != null ? dettes.size() : 0) +
           '}';
}

public void setDettes(Dette dette) {
    dettes.add(dette);
 }




}


