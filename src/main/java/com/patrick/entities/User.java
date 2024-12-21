package com.patrick.entities;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.patrick.entities.Enums.Etat;
import com.patrick.entities.Enums.Role;


import javax.persistence.OneToOne;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")

public class User extends AbstractEntity {
  
    private String login;
    private String nom;
    private String prenom;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role ;
    private Etat etat;
    @OneToOne
    @JoinColumn(name ="client_id",nullable=true)
    private Client client;
    
    
}
